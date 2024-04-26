package br.com.vs1.imobiliaria.core.repositories;

import br.com.vs1.imobiliaria.core.models.Permissoes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissoesRepository extends JpaRepository<Permissoes, Long> {
    Permissoes findByDescription(String descricao);
}
