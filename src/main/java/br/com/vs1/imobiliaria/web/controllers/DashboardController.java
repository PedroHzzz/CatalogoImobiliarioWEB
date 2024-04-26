package br.com.vs1.imobiliaria.web.controllers;

import br.com.vs1.imobiliaria.core.models.Favorito;
import br.com.vs1.imobiliaria.core.models.Imovel;
import br.com.vs1.imobiliaria.core.repositories.FavoritosRepository;
import br.com.vs1.imobiliaria.core.repositories.ImovelRepository;
import br.com.vs1.imobiliaria.web.utils.UsuarioAutenticacao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final ImovelRepository imovelRepository;
    private final FavoritosRepository favoritosRepository;

    public DashboardController(ImovelRepository imovelRepository, FavoritosRepository favoritosRepository) {
        this.imovelRepository = imovelRepository;
        this.favoritosRepository = favoritosRepository;
    }

    @GetMapping
    public ModelAndView home(UsuarioAutenticacao usuarioAutenticacao) {
        List<Imovel> imoveis = imovelRepository.findAllByUsuarioId(usuarioAutenticacao.getUsuarioAutenticado().getId());
        return new ModelAndView("paginas/dashboard", "imoveis", imoveis);
    }

    @GetMapping("/favoritos")
    public ModelAndView favoritos(UsuarioAutenticacao usuarioAutenticacao) {
        List<Favorito> favoritos = favoritosRepository.findAllByUsuarioId(usuarioAutenticacao.getUsuarioAutenticado().getId());
        List<Imovel> imoveis = new ArrayList<>();
        favoritos.forEach(favorito -> imoveis.add(favorito.getImovel()));
        return new ModelAndView("paginas/favoritos", "imoveis", imoveis);
    }
}