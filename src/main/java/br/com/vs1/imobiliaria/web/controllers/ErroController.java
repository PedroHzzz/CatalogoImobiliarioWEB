package br.com.vs1.imobiliaria.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/erro")
public class ErroController {


    @GetMapping
    public ModelAndView paginaErro() {
        return new ModelAndView("/paginas/erro");
    }
}
