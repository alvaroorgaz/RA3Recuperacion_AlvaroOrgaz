package com.example.Ra3FinalApplication.Repository;

import com.example.Ra3FinalApplication.Entity.Rol;
import com.example.Ra3FinalApplication.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    List<Usuario> findByRol(Rol rol);
}