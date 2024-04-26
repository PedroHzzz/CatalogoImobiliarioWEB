package br.com.vs1.imobiliaria.core.models;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_imoveis")
public class Imovel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal precoCompra;

    private BigDecimal precoAluguel;

    private Integer tamanho;

    private Integer quantidadeQuartos;

    private Integer quantidadeBanheiros;

    private Integer garagem;

    private boolean disponivel;

    private String descricao;

    private String foto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id",updatable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "usuario_id",updatable = false)
    private Usuario usuario;
    private Date dataCadastro;

    public Imovel() {
    }


    public Imovel(Long id, String nome, BigDecimal precoCompra, BigDecimal precoAluguel, Integer tamanho,
                  Integer quantidadeQuartos, Integer quantidadeBanheiros, Integer garagem, boolean disponivel,
                  String descricao, String foto, Endereco endereco, Date dataCadastro,Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.precoCompra = precoCompra;
        this.precoAluguel = precoAluguel;
        this.tamanho = tamanho;
        this.quantidadeQuartos = quantidadeQuartos;
        this.quantidadeBanheiros = quantidadeBanheiros;
        this.garagem = garagem;
        this.disponivel = disponivel;
        this.descricao = descricao;
        this.foto = foto;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(BigDecimal precoCompra) {
        this.precoCompra = precoCompra;
    }

    public BigDecimal getPrecoAluguel() {
        return precoAluguel;
    }

    public void setPrecoAluguel(BigDecimal precoAluguel) {
        this.precoAluguel = precoAluguel;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(Integer quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public Integer getQuantidadeBanheiros() {
        return quantidadeBanheiros;
    }

    public void setQuantidadeBanheiros(Integer quantidadeBanheiros) {
        this.quantidadeBanheiros = quantidadeBanheiros;
    }

    public Integer getGaragem() {
        return garagem;
    }

    public void setGaragem(Integer garagem) {
        this.garagem = garagem;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Imovel [id=" + id + ", nome=" + nome + ", precoCompra=" + precoCompra + ", precoAluguel=" + precoAluguel
                + ", tamanho=" + tamanho + ", quantidadeQuartos=" + quantidadeQuartos + ", quantidadeBanheiros="
                + quantidadeBanheiros + ", garagem=" + garagem + ", disponivel=" + disponivel + ", descricao="
                + descricao + ", foto=" + foto + ", endereco=" + endereco + ", dataCadastro=" + dataCadastro + "]";
    }


}
