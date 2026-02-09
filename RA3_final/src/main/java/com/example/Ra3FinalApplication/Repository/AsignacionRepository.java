package com.example.Ra3FinalApplication.Repository;

import com.example.Ra3FinalApplication.Entity.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    List<Asignacion> findByCamionId(Long camionId);

    List<Asignacion> findByRutaId(Long rutaId);
}