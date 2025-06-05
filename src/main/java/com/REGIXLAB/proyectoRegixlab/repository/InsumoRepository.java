package com.REGIXLAB.proyectoRegixlab.repository;

import com.REGIXLAB.proyectoRegixlab.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    Optional<Insumo> findById(Long id);
    Optional<Insumo> findByNombre(String nombre);

    List<Insumo> findByEstadoSemaforizacion(String estado);
    List<Insumo> findByFechaRegistroBetween(LocalDate inicio, LocalDate fin);

}