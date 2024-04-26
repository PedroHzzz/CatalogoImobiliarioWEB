package br.com.vs1.imobiliaria.web.dtos;

import br.com.vs1.imobiliaria.core.models.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AgendamentoDTO {

    private Usuario usuario;

    @Future(message = "Data de agendamento inválida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataAgendamento;

    private Long idImovel;

    @NotEmpty
    @NotBlank
    private String mensagem;

    private String turno;

    public AgendamentoDTO() {
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Long getIdImovel() {
        return idImovel;
    }

    public void setIdImovel(Long idImovel) {
        this.idImovel = idImovel;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
