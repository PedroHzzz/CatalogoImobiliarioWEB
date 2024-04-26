package br.com.vs1.imobiliaria.web.dtos;


public class FotoDTO {

    private String nome;

    private Long tamanho;

    private String tipo;

    private String url;


    public FotoDTO() {
    }

    public FotoDTO(String nome, Long tamanho, String tipo, String url) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.tipo = tipo;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FotoDTO{" +
                "nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", tipo='" + tipo + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
