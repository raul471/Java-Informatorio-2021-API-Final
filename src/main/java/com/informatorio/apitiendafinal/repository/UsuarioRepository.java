package com.informatorio.apitiendafinal.repository;

import com.informatorio.apitiendafinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findByCiudad(String ciudad);

    @Query("select a from Usuario a where a.fechaDeAlta >= :fechaDeAlta")
    List<Usuario> findAllWithFechaDeAltaAfter(LocalDate fechaDeAlta);

}
