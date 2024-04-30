package br.com.vs1.imobiliaria.web.controllers;

import java.io.IOException;
import java.util.Date;

import br.com.vs1.imobiliaria.core.models.Agendamento;
import br.com.vs1.imobiliaria.core.models.Imovel;
import br.com.vs1.imobiliaria.core.repositories.AgendamentoRepository;
import br.com.vs1.imobiliaria.core.repositories.ImovelRepository;
import br.com.vs1.imobiliaria.web.dtos.AgendamentoDTO;
import br.com.vs1.imobiliaria.web.services.WebUsuarioService;
import br.com.vs1.imobiliaria.web.utils.UsuarioAutenticacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.vs1.imobiliaria.web.dtos.ImovelDTO;
import br.com.vs1.imobiliaria.web.services.WebArquivoUploadService;
import br.com.vs1.imobiliaria.web.services.WebImovelService;
import jakarta.validation.Valid;

@Controller
public class ImovelController {

    private final WebImovelService webImovelService;
    private final WebArquivoUploadService webArquivoUploadService;
    private final AgendamentoRepository agendamentoRepository;
    private final WebUsuarioService webUsuarioService;
    private final ModelMapper mapper;
    private final ImovelRepository imovelRepository;

    public ImovelController(WebImovelService webImovelService, WebArquivoUploadService webArquivoUploadService, AgendamentoRepository agendamentoRepository, WebUsuarioService webUsuarioService, ModelMapper mapper, ImovelRepository imovelRepository) {
        this.webImovelService = webImovelService;

        this.webArquivoUploadService = webArquivoUploadService;
        this.agendamentoRepository = agendamentoRepository;
        this.webUsuarioService = webUsuarioService;
        this.mapper = mapper;
        this.imovelRepository = imovelRepository;
    }

    @GetMapping("/anuncios")
    public ModelAndView imoveis(@RequestParam(required = false) String busca, UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("index");

        if (busca != null && !busca.isEmpty()) {
            return mv.addObject("imoveis", webImovelService.buscarPorParametros(busca));
        }
        mv.addObject("imoveis", webImovelService.buscarTodos());

        return mv;
    }

    @GetMapping("/imoveis/detalhes/{id}")
    public ModelAndView detalhesImovel(@PathVariable("id") Long id, UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("/paginas/detalhe-imovel");

        var imovel = imovelRepository.findById(id);
        if (imovel.get().getSoftDelete() && !usuarioAutenticacao.getNomeUsuario().equals( imovel.get().getUsuario().getEmail()))
            return new ModelAndView("redirect:/anuncios");

        mv.addObject("imovel", webImovelService.buscarPorId(id));


        return mv;
    }

    @GetMapping("/imoveis/marcar/horario/{id}")
    public ModelAndView marcarImovel(@PathVariable("id") Long id, AgendamentoDTO agendamentoDTO, UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("/paginas/agendamento");

        mv.addObject("imovel", webImovelService.buscarPorId(id));

        return mv;
    }

    @PostMapping("/imoveis/marcar/horario/{id}")
    public ModelAndView marcarAgendamento(@PathVariable("id") Long id, AgendamentoDTO agendamentoDTO, UsuarioAutenticacao usuarioAutenticacao) {


        ImovelDTO imovelDTO = webImovelService.buscarPorId(id);
        Imovel imovel = mapper.map(imovelDTO, Imovel.class);
        if (agendamentoDTO.getDataAgendamento().before(new Date()))
            return new ModelAndView("redirect:/imoveis/marcar/horario/" + id, "erro", true);


        Agendamento agendamento = new Agendamento();

        agendamento.setTurno(agendamentoDTO.getTurno());
        agendamento.setImovel(imovel);
        agendamento.setDataAgendamento(agendamentoDTO.getDataAgendamento());
        agendamento.setMensagem(agendamentoDTO.getMensagem());
        agendamento.setUsuario(webUsuarioService.busca(usuarioAutenticacao.getNomeUsuario()));
        agendamentoRepository.save(agendamento);


        return new ModelAndView("redirect:/agendamentos");
    }

    @GetMapping("/imoveis/editar/{id}")
    public ModelAndView editarImovel(@PathVariable("id") Long id, ImovelDTO imovelDTO, UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("/paginas/editar-imovel");

        mv.addObject("imovel", webImovelService.buscarPorId(id));

        return mv;
    }

    @GetMapping("/imoveis/novo")
    public ModelAndView novoImovel(ImovelDTO imovelDTO) {

        return new ModelAndView("/paginas/cadastro-imovel");
    }

    @PostMapping("/imoveis/novo")
    public ModelAndView salvarImovel(@Valid ImovelDTO imovelDTO, BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return new ModelAndView("/paginas/cadastro-imovel");
        }

        imovelDTO.setDataCadastro(new Date());
        if (imovelDTO.getFile().isEmpty()) {
            imovelDTO.setFoto(null);
            webImovelService.salvarImovel(imovelDTO);
            return new ModelAndView("redirect:/");
        }
        String fileName = webArquivoUploadService.imageUpload(imovelDTO.getFile(), "", true, 25);

        imovelDTO.setFoto(fileName);
        imovelDTO.setDataCadastro(new Date());
        webImovelService.salvarImovel(imovelDTO);

        return new ModelAndView("redirect:/");
    }

    @PostMapping("/imoveis/editar/{id}")
    public ModelAndView editarImovel(@PathVariable("id") Long id, @Valid ImovelDTO imovelDTO, BindingResult result) {

        if (result.hasErrors()) {
            System.out.println("erros" + result.getAllErrors());
            ModelAndView mv = new ModelAndView("/paginas/editar-imovel");
            mv.addObject("imovel", webImovelService.buscarPorId(id));
            return mv;
        }
        webImovelService.atualizarImovel(id, imovelDTO);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/imoveis/excluir/{id}")
    public ModelAndView excluirImovel(@PathVariable("id") Long id) {

        var imovel = imovelRepository.findById(id).get();

        imovel.setSoftDelete(!imovel.getSoftDelete());

        imovelRepository.save(imovel);

        return new ModelAndView("redirect:/anuncios");
    }

    @GetMapping("/imoveis/favoritar/{id}")
    public ModelAndView favoritarImovel(@PathVariable("id") Long id, UsuarioAutenticacao usuarioAutenticacao) {

        webImovelService.favoritarImovel(id, usuarioAutenticacao);

        return new ModelAndView("redirect:/imoveis/detalhes/" + id);
    }
}
