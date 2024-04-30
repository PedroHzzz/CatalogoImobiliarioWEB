package br.com.vs1.imobiliaria.web.services;

import br.com.vs1.imobiliaria.core.models.Endereco;
import br.com.vs1.imobiliaria.core.models.Favorito;
import br.com.vs1.imobiliaria.core.models.Imovel;
import br.com.vs1.imobiliaria.core.repositories.FavoritosRepository;
import br.com.vs1.imobiliaria.core.repositories.ImovelRepository;
import br.com.vs1.imobiliaria.web.dtos.EnderecoDTO;
import br.com.vs1.imobiliaria.web.dtos.ImovelDTO;
import br.com.vs1.imobiliaria.web.utils.UsuarioAutenticacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WebImovelService {

    private final ImovelRepository imovelRepository;
    private final ModelMapper mapper;
    private final WebEnderecoService webEnderecoService;

    private final FavoritosRepository favoritosRepository;
    private final WebUsuarioService webUsuarioService;
    public WebImovelService(ImovelRepository imovelRepository, ModelMapper mapper,
                            WebEnderecoService webEnderecoService, FavoritosRepository favoritosRepository, WebUsuarioService webUsuarioService) {
        this.imovelRepository = imovelRepository;
        this.mapper = mapper;
        this.webEnderecoService = webEnderecoService;
        this.favoritosRepository = favoritosRepository;
        this.webUsuarioService = webUsuarioService;
    }

    public List<Imovel> buscarTodosComIntervalo() {
        return imovelRepository.buscaLimitada();
    }

    public List<Imovel> buscarTodos() {
        return imovelRepository.findAll();
    }

    public List<Imovel> buscarPorParametros(String parametros) {
        return imovelRepository.buscaComParametros(parametros);
    }

    public ImovelDTO buscarPorId(Long id) {
        Optional<Imovel> imovel = imovelRepository.findById(id);

        if (imovel.isPresent()) {
            return mapper.map(imovel.get(), ImovelDTO.class);
        }

        // TODO: Tratar exceção
        throw new RuntimeException("Imóvel não encontrado");

    }


    public void salvarImovel(ImovelDTO imovelDTO) {
        UsuarioAutenticacao autenticado = new UsuarioAutenticacao();
        Endereco endereco = webEnderecoService.salvarEndereco(mapper.map(imovelDTO.getEndereco(), EnderecoDTO.class));

        imovelDTO.setEndereco(endereco);
        imovelDTO.setDataCadastro(new Date());
        imovelDTO.setUsuario(webUsuarioService.busca(autenticado.getNomeUsuario()));
        var imovel = mapper.map(imovelDTO, Imovel.class);
        imovelRepository.save(imovel);
    }

    public void atualizarImovel(Long id, ImovelDTO imovelDTO) {

        ImovelDTO entidade = this.buscarPorId(id);

        imovelDTO.setDataCadastro(entidade.getDataCadastro());

        Imovel imovel = mapper.map(imovelDTO, Imovel.class);

        imovelRepository.saveAndFlush(imovel);

    }

    public void deletarImovel(Long id) {
        ImovelDTO imovel = this.buscarPorId(id);
        imovelRepository.deleteById(imovel.getId());
    }
    public void favoritarImovel(Long id, UsuarioAutenticacao usuarioAutenticacao) {
        Imovel imovel = mapper.map(this.buscarPorId(id), Imovel.class);

        Favorito favorito = new Favorito();
        favorito.setImovel(imovel);
        favorito.setUsuario(usuarioAutenticacao.getUsuarioAutenticado());
        favoritosRepository.save(favorito);
    }
}
