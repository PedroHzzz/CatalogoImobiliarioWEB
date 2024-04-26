package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.web.services.WebImovelService;
import br.com.vs1.imobiliaria.web.utils.UsuarioAutenticacao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private final WebImovelService webImovelService;

    public HomeController(WebImovelService webImovelService) {
        this.webImovelService = webImovelService;
    }

    @GetMapping
    public ModelAndView index(UsuarioAutenticacao usuarioAutenticacao) {

        ModelAndView mv = new ModelAndView("index");

        mv.addObject("imoveis", webImovelService.buscarTodosComIntervalo());

        return mv;
    }
    @PostMapping
    public ModelAndView home(UsuarioAutenticacao usuarioAutenticacao) {

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/tos")
    public ModelAndView tos(UsuarioAutenticacao usuarioAutenticacao) {

        return new ModelAndView("/paginas/tos");
    }

}