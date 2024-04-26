package br.com.vs1.imobiliaria.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vs1.imobiliaria.core.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {



    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 OR u.cpf = ?2")
    Optional<Usuario> findByEmailOrCpf(String email, String cpf);

    Optional<Usuario> findByEmail(String email);
}
