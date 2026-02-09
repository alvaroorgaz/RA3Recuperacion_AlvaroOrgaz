package com.example.Ra3FinalApplication.WebController;

import com.example.Ra3FinalApplication.DTO.UsuarioDTO;
import com.example.Ra3FinalApplication.Entity.Rol;
import com.example.Ra3FinalApplication.Entity.Usuario;
import com.example.Ra3FinalApplication.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/dashboard")
    public String dashboard() {

        return "admin/admin";
    }

    @ResponseBody
    @GetMapping("/verusuarios")
    public ResponseEntity<?> mostrarUsuarios() {
        try {
            List<Usuario> lista = usuarioService.mostrarusuarios();
            List<UsuarioDTO> lista2 = new ArrayList<>();

            for (Usuario usuario : lista) {
                String rol = usuario.getRol().name();
                UsuarioDTO usuariodto = new UsuarioDTO(
                        usuario.getId(),
                        usuario.getUsername(),
                        usuario.getEmail(),
                        usuario.getNombre(),
                        rol,
                        usuario.isActivo()
                );
                lista2.add(usuariodto);
            }
            return ResponseEntity.ok(lista2);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No hay usuarios que mostrar");
        }
    }

    @ResponseBody
    @DeleteMapping("/eliminarusuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar usuario");
        }
    }

    @ResponseBody
    @PutMapping("/editarusuario")
    public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDTO datosUsuario) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerUsuarioPorId(datosUsuario.getId());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no existe");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setNombre(datosUsuario.getNombre());
        usuario.setEmail(datosUsuario.getEmail());

        if (datosUsuario.getUsername() != null && !datosUsuario.getUsername().isEmpty()) {
            usuario.setUsername(datosUsuario.getUsername());
        }

        usuario.setActivo(datosUsuario.isActivo());

        // Actualizar rol
        if (datosUsuario.getRol() != null && !datosUsuario.getRol().isEmpty()) {
            try {
                Rol nuevoRol = Rol.valueOf(datosUsuario.getRol().toUpperCase());
                usuario.setRol(nuevoRol);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("Rol inválido");
            }
        }

        Usuario usuarioGuardado = usuarioService.actualizarUsuario(usuario);
        return ResponseEntity.ok(usuarioGuardado);
    }
}