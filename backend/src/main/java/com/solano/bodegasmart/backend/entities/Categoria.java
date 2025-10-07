package com.solano.bodegasmart.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tbl_categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(nullable = false, unique = true, length = 100)
    private String nombreCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoriaProducto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion;
}
