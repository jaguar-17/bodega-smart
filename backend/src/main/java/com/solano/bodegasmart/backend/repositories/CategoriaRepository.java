package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.Categoria;
import com.solano.bodegasmart.backend.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    // Buscar una categoría por su nombre
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);

    // Verificar si una categoría existe por su nombre
    boolean existsByNombreCategoria(String nombreCategoria);

    // Buscar categorías por estado
    List<Categoria> findByEstadoCategoria(Estado estado);

    // Buscar solo categorías activas (más usado)
    List<Categoria> findByEstadoCategoriaOrderByNombreCategoriaAsc(Estado estado);

    // Buscar categoría activa por nombre
    Optional<Categoria> findByNombreCategoriaAndEstadoCategoria(String nombreCategoria, Estado estado);

    // Verificar si existe una categoría activa por nombre
    boolean existsByNombreCategoriaAndEstadoCategoria(String nombreCategoria, Estado estado);
}
