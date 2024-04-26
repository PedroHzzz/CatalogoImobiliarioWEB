package br.com.vs1.imobiliaria.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.vs1.imobiliaria.core.models.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {


    List<Imovel> findAllByUsuarioId(Long id);

    boolean existsByUsuarioId(Long id);
    @Query(value = "SELECT * FROM tb_imoveis WHERE data_cadastro >= DATE_TRUNC('year', CURRENT_DATE) AND now() > data_cadastro - interval '7' day LIMIT 9", nativeQuery = true)
    List<Imovel> buscaLimitada();

   
    @Query(value = "SELECT * FROM tb_imoveis WHERE LOWER(nome) LIKE LOWER(CONCAT('%', ?1, '%')) OR LOWER(descricao) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Imovel> buscaComParametros(String nomeOuDescricao);
}
