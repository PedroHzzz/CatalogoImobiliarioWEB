package br.com.vs1.imobiliaria.web.dtos;

public class EnderecoDTO {


    private Long id;

    private String cep;

    private String logradouro;

    private String complemento;

    private String localidade;

    private String uf;

    private String ddd;

    private String bairro;


    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id,String cep, String logradouro, String complemento, String localidade,String bairro,String uf, String ddd, String siafi) {
       this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.localidade = localidade;
        this.uf = uf;
        this.bairro = bairro;
        this.ddd = ddd;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }



    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }


    @Override
    public String toString() {
        return "EnderecoDTO{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", complemento='" + complemento + '\'' +
                ", localidade='" + localidade + '\'' +
                ", bairro='" + bairro + '\'' +
                ", uf='" + uf + '\'' +
                ", ddd='" + ddd + '\'' +
                '}';
    }
}
