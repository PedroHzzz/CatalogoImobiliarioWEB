package br.com.vs1.imobiliaria.core.repositories;

import br.com.vs1.imobiliaria.core.models.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritosRepository extends JpaRepository<Favorito,Long> {

    List<Favorito> findAllByUsuarioId(Long id);
}
