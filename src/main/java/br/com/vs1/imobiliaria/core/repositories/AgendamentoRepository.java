package br.com.vs1.imobiliaria.core.repositories;

import br.com.vs1.imobiliaria.core.models.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AgendamentoRepository extends JpaRepository<Agendamento,Long> {
    boolean existsByDataAgendamentoAndImovelId(Date dataAgendamento, Long idImovel);
}
