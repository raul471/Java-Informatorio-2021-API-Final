package com.informatorio.apitiendafinal.repository;

import com.informatorio.apitiendafinal.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductoRepository extends CrudRepository<Producto,Long> {

    List<Producto> findByPublicado(Boolean publicado);

    List<Producto> findByNombreContaining(String nombre);

}
