package com.example.Ra3FinalApplication.Service;

import com.example.Ra3FinalApplication.Entity.Rol;
import com.example.Ra3FinalApplication.Entity.Usuario;
import com.example.Ra3FinalApplication.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> mostrarusuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerUsuariosPorRol(Rol rol) {
        return usuarioRepository.findByRol(rol);
    }

    @Transactional
    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null) {
            throw new IllegalArgumentException("Usuario inválido");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
}