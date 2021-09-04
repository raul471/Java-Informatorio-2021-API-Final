package com.informatorio.apitiendafinal.repository;


import com.informatorio.apitiendafinal.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
