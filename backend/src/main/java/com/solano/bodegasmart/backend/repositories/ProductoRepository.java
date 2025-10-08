package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.Categoria;
import com.solano.bodegasmart.backend.entities.Producto;
import com.solano.bodegasmart.backend.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    // Buscar productos por categoría
    List<Producto> findByCategoriaProducto(Categoria categoria);

    // Buscar productos ACTIVOS por categoría
    List<Producto> findByCategoriaProductoAndEstadoProducto(Categoria categoria, Estado estado);

    // Buscar producto por nombre exacto
    Optional<Producto> findByNombreProducto(String nombreProducto);

    // Buscar producto ACTIVO por nombre
    Optional<Producto> findByNombreProductoAndEstadoProducto(String nombreProducto, Estado estado);

    // Buscar productos por nombre (búsqueda parcial, sin importar mayúsculas)
    List<Producto> findByNombreProductoContainingIgnoreCase(String nombre);

    // Buscar productos ACTIVOS por nombre (búsqueda parcial)
    List<Producto> findByNombreProductoContainingIgnoreCaseAndEstadoProducto(String nombre, Estado estado);

    // Buscar productos con stock bajo (útil para alertas de inventario)
    List<Producto> findByCantidadProductoLessThan(Integer cantidad);

    // Buscar productos ACTIVOS con stock bajo
    List<Producto> findByCantidadProductoLessThanAndEstadoProducto(Integer cantidad, Estado estado);

    // Buscar productos con stock disponible
    List<Producto> findByCantidadProductoGreaterThan(Integer cantidad);

    // Buscar productos ACTIVOS con stock disponible
    List<Producto> findByCantidadProductoGreaterThanAndEstadoProducto(Integer cantidad, Estado estado);

    // Verificar si existe un producto por nombre
    boolean existsByNombreProducto(String nombreProducto);

    // Verificar si existe un producto ACTIVO por nombre
    boolean existsByNombreProductoAndEstadoProducto(String nombreProducto, Estado estado);

    // Buscar todos los productos por estado
    List<Producto> findByEstadoProducto(Estado estado);

    // Contar productos por categoría
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.categoriaProducto = :categoria")
    Long countByCategoria(@Param("categoria") Categoria categoria);

    // Contar productos ACTIVOS por categoría
    @Query("SELECT COUNT(p) FROM Producto p WHERE p.categoriaProducto = :categoria AND p.estadoProducto = :estado")
    Long countByCategoriaAndEstado(@Param("categoria") Categoria categoria, @Param("estado") Estado estado);
}
