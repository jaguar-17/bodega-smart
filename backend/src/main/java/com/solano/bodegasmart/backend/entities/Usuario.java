package com.solano.bodegasmart.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solano.bodegasmart.backend.enums.Rol;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tbl_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 255)
    private String contrasenaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rolUsuario;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_modificacion", nullable = false)
    private LocalDateTime fechaModificacion;
}
