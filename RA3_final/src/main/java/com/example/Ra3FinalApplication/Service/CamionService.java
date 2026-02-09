package com.example.Ra3FinalApplication.Service;

import com.example.Ra3FinalApplication.Entity.Camion;
import com.example.Ra3FinalApplication.Entity.EstadoCamion;
import com.example.Ra3FinalApplication.Repository.CamionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CamionService {

    @Autowired
    private CamionRepository camionRepository;

    public List<Camion> mostrarTodosCamiones() {
        return camionRepository.findAll();
    }

    public List<Camion> mostrarCamionesActivos() {
        return camionRepository.findByActivoTrue();
    }

    public List<Camion> mostrarCamionesPorEstado(EstadoCamion estado) {
        return camionRepository.findByEstado(estado);
    }

    public Optional<Camion> obtenerCamionPorId(Long id) {
        return camionRepository.findById(id);
    }

    @Transactional
    public Camion guardarCamion(Camion camion) {
        return camionRepository.save(camion);
    }

    @Transactional
    public void eliminarCamion(Long id) {
        camionRepository.deleteById(id);
    }
}