package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.Usuario;
import com.solano.bodegasmart.backend.entities.Venta;
import com.solano.bodegasmart.backend.enums.MetodoPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {

    // Buscar ventas por vendedor
    List<Venta> findByVendedor(Usuario vendedor);

    // Buscar ventas por método de pago
    List<Venta> findByMetodoPagoVenta(MetodoPago metodoPago);

    // Ventas entre fechas
    List<Venta> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

    // Ventas del día
    @Query("SELECT v FROM Venta v WHERE DATE(v.fechaCreacion) = CURRENT_DATE ORDER BY v.fechaCreacion DESC")
    List<Venta> findVentasDelDia();

    // Total vendido del día
    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE DATE(v.fechaCreacion) = CURRENT_DATE")
    BigDecimal sumTotalVentasDelDia();

    // Total vendido entre fechas
    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.fechaCreacion BETWEEN :inicio AND :fin")
    BigDecimal sumTotalVentasByFecha(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Total vendido por vendedor en un rango de fechas
    @Query("SELECT COALESCE(SUM(v.totalVenta), 0) FROM Venta v WHERE v.vendedor = :vendedor AND v.fechaCreacion BETWEEN :inicio AND :fin")
    BigDecimal sumTotalByVendedorAndFecha(@Param("vendedor") Usuario vendedor, @Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Contar ventas del día
    @Query("SELECT COUNT(v) FROM Venta v WHERE DATE(v.fechaCreacion) = CURRENT_DATE")
    Long countVentasDelDia();

    // Ventas por vendedor en el día
    @Query("SELECT v FROM Venta v WHERE v.vendedor = :vendedor AND DATE(v.fechaCreacion) = CURRENT_DATE ORDER BY v.fechaCreacion DESC")
    List<Venta> findVentasDelDiaByVendedor(@Param("vendedor") Usuario vendedor);
}
