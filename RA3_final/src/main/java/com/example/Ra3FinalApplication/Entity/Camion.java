package com.example.Ra3FinalApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "camiones")
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula", nullable = false, unique = true, length = 10)
    private String matricula;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "capacidad_kg", nullable = false)
    private BigDecimal capacidadKg;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCamion estado = EstadoCamion.DISPONIBLE;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    @OneToMany(mappedBy = "camion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asignacion> asignaciones = new ArrayList<>();
}