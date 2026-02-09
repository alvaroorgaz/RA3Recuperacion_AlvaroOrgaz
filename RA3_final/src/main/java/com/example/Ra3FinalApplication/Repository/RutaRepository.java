package com.example.Ra3FinalApplication.Repository;

import com.example.Ra3FinalApplication.Entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    List<Ruta> findByActivaTrue();

    Optional<Ruta> findByZona(String zona);
}