package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.web.dtos.UsuarioDTO;
import br.com.vs1.imobiliaria.web.services.WebUsuarioService;
import br.com.vs1.imobiliaria.web.utils.UsuarioAutenticacao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard/user")
public class UserController {

    private WebUsuarioService webUsuarioService;


    public UserController(WebUsuarioService webUsuarioService) {
        this.webUsuarioService = webUsuarioService;
    }


    @GetMapping("/detalhes")
    public ModelAndView detalhesUsuario(UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("/paginas/perfil-usuario");

        mv.addObject("usuario", webUsuarioService.busca(usuarioAutenticacao.getNomeUsuario()));


        return mv;
    }

    @GetMapping("/perfil/editar")
    public ModelAndView editarPerfilPag(UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("/paginas/editar-perfil-usuario");

        mv.addObject("usuario", webUsuarioService.busca(usuarioAutenticacao.getNomeUsuario()));

        return mv;
    }

    @PostMapping("/perfil/editar")
    public ModelAndView editarPerfil(UsuarioAutenticacao usuarioAutenticacao, UsuarioDTO usuarioDTO) {

        ModelAndView mv = new ModelAndView("/paginas/perfil-usuario");

        webUsuarioService.atualizar(usuarioDTO);
        mv.addObject("usuario", webUsuarioService.busca(usuarioAutenticacao.getNomeUsuario()));


        return mv;
    }

}
