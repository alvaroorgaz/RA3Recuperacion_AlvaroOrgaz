package com.example.Ra3FinalApplication.Service;

import com.example.Ra3FinalApplication.Entity.Asignacion;
import com.example.Ra3FinalApplication.Repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository asignacionRepository;

    public List<Asignacion> mostrarTodasAsignaciones() {
        return asignacionRepository.findAll();
    }

    public List<Asignacion> obtenerAsignacionesPorCamion(Long camionId) {
        return asignacionRepository.findByCamionId(camionId);
    }

    public List<Asignacion> obtenerAsignacionesPorRuta(Long rutaId) {
        return asignacionRepository.findByRutaId(rutaId);
    }

    public Optional<Asignacion> obtenerAsignacionPorId(Long id) {
        return asignacionRepository.findById(id);
    }

    @Transactional
    public Asignacion guardarAsignacion(Asignacion asignacion) {
        return asignacionRepository.save(asignacion);
    }

    @Transactional
    public void eliminarAsignacion(Long id) {
        asignacionRepository.deleteById(id);
    }
}