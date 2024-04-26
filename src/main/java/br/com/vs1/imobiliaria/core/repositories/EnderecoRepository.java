package br.com.vs1.imobiliaria.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vs1.imobiliaria.core.models.Endereco;

import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.cep = ?1")
    Optional<Endereco> buscarPorCep(String cep);
 
}
