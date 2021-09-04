package com.informatorio.apitiendafinal.repository;

import com.informatorio.apitiendafinal.entity.Carrito;
import com.informatorio.apitiendafinal.entity.EstadoCarrito;
import com.informatorio.apitiendafinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    List<Carrito> findByEstadoCarrito(EstadoCarrito estadoCarrito);

}
