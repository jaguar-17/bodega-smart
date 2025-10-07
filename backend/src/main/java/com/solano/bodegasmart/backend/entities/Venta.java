package com.solano.bodegasmart.backend.entities;

import com.solano.bodegasmart.backend.enums.MetodoPago;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MetodoPago metodoPagoVenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotalVenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal descuentoVenta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalVenta;

    @Column(length = 100)
    private String descripcionVenta;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario vendedor;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;
}
