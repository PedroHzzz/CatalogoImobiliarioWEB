package br.com.vs1.imobiliaria.web.client;

import br.com.vs1.imobiliaria.web.dtos.EnderecoDTO;

public interface CepRequest {
    
    EnderecoDTO buscarEnderecoPorCep(String cep);

}
