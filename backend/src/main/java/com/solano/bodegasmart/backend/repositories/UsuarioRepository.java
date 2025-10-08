package com.solano.bodegasmart.backend.repositories;

import com.solano.bodegasmart.backend.entities.Usuario;
import com.solano.bodegasmart.backend.enums.Estado;
import com.solano.bodegasmart.backend.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por nombre de usuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    // Buscar usuario ACTIVO por nombre de usuario (para login)
    Optional<Usuario> findByNombreUsuarioAndEstadoUsuario(String nombreUsuario, Estado estado);

    // Buscar usuarios por estado
    List<Usuario> findByEstadoUsuario(Estado estado);

    // Buscar usuarios por rol
    List<Usuario> findByRolUsuario(Rol rol);

    // Buscar usuarios ACTIVOS por rol
    List<Usuario> findByRolUsuarioAndEstadoUsuario(Rol rol, Estado estado);

    // Verificar si existe un usuario por nombre
    boolean existsByNombreUsuario(String nombreUsuario);

    // Verificar si existe un usuario ACTIVO por nombre
    boolean existsByNombreUsuarioAndEstadoUsuario(String nombreUsuario, Estado estado);
}
