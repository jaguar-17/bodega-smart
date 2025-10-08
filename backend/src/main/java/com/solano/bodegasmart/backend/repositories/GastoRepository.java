package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.Gasto;
import com.solano.bodegasmart.backend.entities.Usuario;
import com.solano.bodegasmart.backend.enums.CategoriaGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {

    // Buscar gastos por categoría
    List<Gasto> findByCategoriaGasto(CategoriaGasto categoriaGasto);

    // Buscar gastos por usuario
    List<Gasto> findByUsuarioGasto(Usuario usuario);

    // Buscar gastos entre fechas
    List<Gasto> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);

    // Total de gastos por categoría
    @Query("SELECT SUM(g.montoGasto) FROM Gasto g WHERE g.categoriaGasto = :categoria")
    BigDecimal sumMontoByCategoria(@Param("categoria") CategoriaGasto categoria);

    // Total de gastos por usuario
    @Query("SELECT SUM(g.montoGasto) FROM Gasto g WHERE g.usuarioGasto = :usuario")
    BigDecimal sumMontoByUsuario(@Param("usuario") Usuario usuario);

    // Total de gastos del día
    @Query("SELECT SUM(g.montoGasto) FROM Gasto g WHERE DATE(g.fechaCreacion) = CURRENT_DATE")
    BigDecimal sumTotalGastosDelDia();

    // Total de gastos entre fechas
    @Query("SELECT SUM(g.montoGasto) FROM Gasto g WHERE g.fechaCreacion BETWEEN :inicio AND :fin")
    BigDecimal sumTotalGastosByFecha(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);

    // Gastos del día
    @Query("SELECT g FROM Gasto g WHERE DATE(g.fechaCreacion) = CURRENT_DATE ORDER BY g.fechaCreacion DESC")
    List<Gasto> findGastosDelDia();
}
