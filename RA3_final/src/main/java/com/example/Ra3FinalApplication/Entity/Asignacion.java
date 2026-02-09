package com.example.Ra3FinalApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asignaciones")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camion_id", nullable = false)
    private Camion camion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion;

    @PrePersist
    protected void onCreate() {
        if (fechaAsignacion == null) {
            fechaAsignacion = LocalDate.now();
        }
    }
}