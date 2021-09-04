package com.informatorio.apitiendafinal.controller;

import com.informatorio.apitiendafinal.entity.Carrito;
import com.informatorio.apitiendafinal.entity.EstadoCarrito;
import com.informatorio.apitiendafinal.entity.Orden;
import com.informatorio.apitiendafinal.entity.Usuario;
import com.informatorio.apitiendafinal.repository.CarritoRepository;
import com.informatorio.apitiendafinal.repository.OrdenRepository;
import com.informatorio.apitiendafinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrdenController {
    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private OrdenRepository ordenRepository;


    @GetMapping(value = "/orden")
    public Iterable<Orden> getOrden(){
        return ordenRepository.findAll();
    }


    @PostMapping(value = "/usuarios/{id}/carritos/{CarritoId}/orden")
    public Orden postOrden(@PathVariable("id") Long id,
                              @PathVariable("CarritoId") Long CarritoId,
                              @RequestBody Orden orden ){
        Usuario usuario = usuarioRepository.findById(id).get();
        Carrito carrito = carritoRepository.findById(CarritoId).get();
        if (carrito.getCantidadProductos() !=0){
            carrito.setOrden(orden);
            orden.setCarrito(carrito);
            orden.setCantidadProductos(carrito.getCantidadProductos());
            orden.setTotalOrden(carrito.getTotal());
            orden.setNombreDeUsuario(carrito.getNombreDeUsuario());
            carrito.setEstadoCarrito(EstadoCarrito.CERRADO);
            return ordenRepository.save(orden);
        }
        return null;
    }

}
