package com.example.Ra3FinalApplication.Service;

import com.example.Ra3FinalApplication.Entity.Ruta;
import com.example.Ra3FinalApplication.Repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;

    public List<Ruta> mostrarTodasRutas() {
        return rutaRepository.findAll();
    }

    public List<Ruta> mostrarRutasActivas() {
        return rutaRepository.findByActivaTrue();
    }

    public Optional<Ruta> obtenerRutaPorId(Long id) {
        return rutaRepository.findById(id);
    }

    @Transactional
    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    @Transactional
    public void eliminarRuta(Long id) {
        rutaRepository.deleteById(id);
    }
}