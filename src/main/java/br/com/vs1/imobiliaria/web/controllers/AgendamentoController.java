package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.core.models.Agendamento;
import br.com.vs1.imobiliaria.core.repositories.AgendamentoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/agendamentos")

@Controller
public class AgendamentoController {

    private  final AgendamentoRepository agendamentoRepository;

    public AgendamentoController(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("paginas/agendamentos");
        mv.addObject("agendamentos", agendamentoRepository.findAll());
        return mv;
    }
}
