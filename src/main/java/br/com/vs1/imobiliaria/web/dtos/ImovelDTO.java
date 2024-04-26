package br.com.vs1.imobiliaria.web.dtos;

import br.com.vs1.imobiliaria.core.models.Endereco;
import br.com.vs1.imobiliaria.core.models.Usuario;
import jakarta.servlet.http.Part;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;

public class ImovelDTO {

    private Long id;

    @NotEmpty(message = "O titulo não pode ser vazio")
    private String nome;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private BigDecimal precoCompra;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private BigDecimal precoAluguel;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private Integer tamanho;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private Integer quantidadeQuartos;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private Integer quantidadeBanheiros;

    @Min(value = 0, message = "O valor deve ser maior que 0")
    private Integer garagem;

    private boolean disponivel;
    @NotEmpty(message = "A descrição não pode ser vazia")
    private String descricao;

    private MultipartFile file;

    private String foto;
    private Endereco endereco;
    private Date dataCadastro;

    private Usuario usuario;

    public ImovelDTO() {
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


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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


    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "ImovelDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoAluguel=" + precoAluguel +
                ", tamanho=" + tamanho +
                ", quantidadeQuartos=" + quantidadeQuartos +
                ", quantidadeBanheiros=" + quantidadeBanheiros +
                ", garagem=" + garagem +
                ", disponivel=" + disponivel +
                ", descricao='" + descricao + '\'' +
                ", endereco=" + endereco +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
