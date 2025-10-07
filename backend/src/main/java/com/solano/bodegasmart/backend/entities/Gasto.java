package com.solano.bodegasmart.backend.entities;

import com.solano.bodegasmart.backend.enums.CategoriaGasto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tbl_gasto")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gasto")
    private Long idGasto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CategoriaGasto categoriaGasto;

    @Column(nullable = false, length = 100)
    private String descripcionGasto;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montoGasto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuarioGasto;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion;
}
