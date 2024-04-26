package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.web.dtos.UsuarioDTO;
import br.com.vs1.imobiliaria.web.services.WebUsuarioService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    private final WebUsuarioService usuarioService;
    private final WebUsuarioService webUsuarioService;

    public CadastroController(WebUsuarioService usuarioService, WebUsuarioService webUsuarioService) {
        this.usuarioService = usuarioService;
        this.webUsuarioService = webUsuarioService;
    }

    @GetMapping
    public ModelAndView paginaCadastroUsuario(UsuarioDTO usuarioDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new ModelAndView("/paginas/cadastro-usuario");
        }

        return new ModelAndView("redirect:/");
    }

    @PostMapping
    public ModelAndView cadastroUsuario(@Valid UsuarioDTO usuarioDTO, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("/paginas/cadastro-usuario");
        }

        if (usuarioService.usuarioExistente(usuarioDTO)) {

            return new ModelAndView("/paginas/cadastro-usuario", "erro", true);
        }


        webUsuarioService.cadastra(usuarioDTO);

        return new ModelAndView("redirect:/entrar", "sucesso", true);
    }
}
