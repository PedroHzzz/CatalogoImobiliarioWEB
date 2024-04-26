package br.com.vs1.imobiliaria.web.client;

import org.springframework.web.client.RestTemplate;

import br.com.vs1.imobiliaria.web.dtos.EnderecoDTO;

public class CepClient implements CepRequest {

    private String baseUrl = "https://viacep.com.br/ws/";

    private final RestTemplate restTemplate;

    public CepClient() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public EnderecoDTO buscarEnderecoPorCep(String cep) {
        EnderecoDTO endereco = restTemplate.getForObject(baseUrl + cep + "/json",
                EnderecoDTO.class);

        return endereco;
    }

}
