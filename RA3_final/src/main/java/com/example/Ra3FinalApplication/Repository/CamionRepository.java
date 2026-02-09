package com.example.Ra3FinalApplication.Repository;

import com.example.Ra3FinalApplication.Entity.Camion;
import com.example.Ra3FinalApplication.Entity.EstadoCamion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

    List<Camion> findByEstado(EstadoCamion estado);

    List<Camion> findByActivoTrue();
}