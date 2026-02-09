package com.example.Ra3FinalApplication.WebController;

import com.example.Ra3FinalApplication.Entity.Asignacion;
import com.example.Ra3FinalApplication.Entity.Camion;
import com.example.Ra3FinalApplication.Entity.Ruta;
import com.example.Ra3FinalApplication.Service.AsignacionService;
import com.example.Ra3FinalApplication.Service.CamionService;
import com.example.Ra3FinalApplication.Service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coordinador")
@PreAuthorize("hasRole('COORDINADOR')")
public class CoordinadorController {

    @Autowired
    private CamionService camionService;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private AsignacionService asignacionService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Camion> camiones = camionService.mostrarTodosCamiones();
        List<Ruta> rutas = rutaService.mostrarTodasRutas();
        List<Asignacion> asignaciones = asignacionService.mostrarTodasAsignaciones();

        model.addAttribute("camiones", camiones);
        model.addAttribute("rutas", rutas);
        model.addAttribute("asignaciones", asignaciones);

        return "coordinador/coordinador";
    }

    @PostMapping("/asignacion/crear")
    public String crearAsignacion(
            @RequestParam Long camionId,
            @RequestParam Long rutaId
    ) {
        asignacionService.guardarAsignacion(new Asignacion());
        return "redirect:/coordinador/dashboard";
    }

    @PostMapping("/asignacion/eliminar/{id}")
    public String eliminarAsignacion(@PathVariable Long id) {
        asignacionService.eliminarAsignacion(id);
        return "redirect:/coordinador/dashboard";
    }
}
