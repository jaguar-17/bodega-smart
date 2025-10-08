package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.DetalleVenta;
import com.solano.bodegasmart.backend.entities.Producto;
import com.solano.bodegasmart.backend.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {

    // Buscar detalles por venta
    List<DetalleVenta> findByVenta(Venta venta);

    // Buscar detalles por producto (para análisis)
    List<DetalleVenta> findByProducto(Producto producto);

    // Productos más vendidos (retorna lista de arrays: [Producto, cantidad total])
    @Query("SELECT d.producto, SUM(d.cantidad) as total FROM DetalleVenta d GROUP BY d.producto ORDER BY total DESC")
    List<Object[]> findProductosMasVendidos();

    // Productos más vendidos con límite
    @Query("SELECT d.producto, SUM(d.cantidad) as total FROM DetalleVenta d GROUP BY d.producto ORDER BY total DESC LIMIT :limite")
    List<Object[]> findTopProductosMasVendidos(@Param("limite") int limite);

    // Cantidad total vendida de un producto
    @Query("SELECT COALESCE(SUM(d.cantidad), 0) FROM DetalleVenta d WHERE d.producto = :producto")
    Long sumCantidadByProducto(@Param("producto") Producto producto);

    // Productos vendidos en un rango de fechas
    @Query("SELECT d FROM DetalleVenta d WHERE d.fechaCreacion BETWEEN :inicio AND :fin")
    List<DetalleVenta> findByFechaCreacionBetween(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}
