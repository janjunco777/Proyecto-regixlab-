package com.REGIXLAB.proyectoRegixlab.repository;
import com.REGIXLAB.proyectoRegixlab.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LoteRepository extends JpaRepository <Lote, Long> {
}
