package br.com.vs1.imobiliaria.web.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.vs1.imobiliaria.core.models.Endereco;
import br.com.vs1.imobiliaria.core.repositories.EnderecoRepository;
import br.com.vs1.imobiliaria.web.dtos.EnderecoDTO;

@Service
public class WebEnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ModelMapper mapper;

    public WebEnderecoService(EnderecoRepository enderecoRepository, ModelMapper mapper) {
        this.enderecoRepository = enderecoRepository;
        this.mapper = mapper;
    }

    public EnderecoDTO buscarPorId(Long id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if (endereco.isPresent()) {
            return mapper.map(endereco.get(), EnderecoDTO.class);
        }

        throw new RuntimeException("Endereço não encontrado");

    }

    public EnderecoDTO buscarEnderecoPorCep(String cep) {

        Optional<Endereco> endereco = enderecoRepository.buscarPorCep(cep);

        if (endereco.isPresent()) {
            return mapper.map(endereco.get(), EnderecoDTO.class);
        }

        throw new RuntimeException("Endereço não encontrado");
    }

    public Endereco salvarEndereco(EnderecoDTO enderecoDTO) {
        return enderecoRepository.save(mapper.map(enderecoDTO, Endereco.class));
    }

    public void atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        EnderecoDTO endereco = this.buscarPorId(id);


        enderecoRepository.save(mapper.map(endereco, Endereco.class));

    }

    public void deletarEndereco(Long id) {
        EnderecoDTO endereco = this.buscarPorId(id);
        enderecoRepository.deleteById(endereco.getId());
    }
}
