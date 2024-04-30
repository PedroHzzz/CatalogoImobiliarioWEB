package br.com.vs1.imobiliaria.web.services;

import br.com.vs1.imobiliaria.core.models.Permissoes;
import br.com.vs1.imobiliaria.core.models.Usuario;
import br.com.vs1.imobiliaria.core.repositories.PermissoesRepository;
import br.com.vs1.imobiliaria.core.repositories.UsuarioRepository;
import br.com.vs1.imobiliaria.web.dtos.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WebUsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;
    private final PermissoesRepository permissionRepository;

    public WebUsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, ModelMapper mapper, PermissoesRepository permissionRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.permissionRepository = permissionRepository;
    }

    public boolean usuarioExistente(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmailOrCpf(usuarioDTO.getEmail(),
                usuarioDTO.getCpf());

        return usuarioOptional.isPresent();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return usuario.get();
    }

    public Usuario cadastra(UsuarioDTO usuarioDTO) {
        try {
            usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

            Usuario usuario = new Usuario();

            mapper.map(usuarioDTO, usuario);
            Permissoes userPermissao = permissionRepository.findByDescription("ROLE_USER");
            List<Permissoes> permissoes = new ArrayList<>();
            permissoes.add(userPermissao);
            usuario.setPermissoes(permissoes);

            return usuarioRepository.save(usuario);

        } catch (Exception e) {
            throw new UsernameNotFoundException("Email already exists");
        }
    }

    public Usuario atualizar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioDTO.getId()).get();
        usuario.setNome(usuarioDTO.getNome() == null ? usuario.getNome() : usuarioDTO.getNome());
        usuario.setCpf(usuarioDTO.getCpf() == null ? usuario.getCpf() : usuarioDTO.getCpf());
        usuario.setSenha(usuarioDTO.getSenha() != null ? passwordEncoder.encode(usuarioDTO.getSenha()) : usuario.getPassword());

        usuarioRepository.save(usuario);

        return usuario;
    }

    public Usuario busca(String nomeUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(nomeUsuario);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return usuario.get();
    }
}
