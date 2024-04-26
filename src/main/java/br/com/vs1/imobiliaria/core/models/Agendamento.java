package br.com.vs1.imobiliaria.core.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @Column(name = "data_agendamento")
    private Date dataAgendamento;

    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "turno")
    private String turno;

    public Agendamento() {
    }

    public Agendamento(Usuario usuario, Imovel imovel, Date dataAgendamento, String mensagem,String turno) {
        this.usuario = usuario;
        this.imovel = imovel;
        this.dataAgendamento = dataAgendamento;
        this.mensagem = mensagem;
        this.turno = turno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
