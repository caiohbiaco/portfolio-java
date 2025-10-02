package com.caio.cadastro_usuario.business;

import com.caio.cadastro_usuario.infrastructure.entitys.Usuario;
import com.caio.cadastro_usuario.infrastructure.entitys.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service


public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario) {
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado.")
        );

    }

    public void deletarUsuarioPorEmail(String email) {
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario) {
        Usuario usuarioEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .id(usuarioEntity.getId())
                .build();

                repository.saveAndFlush(usuarioAtualizado);
    }
}
