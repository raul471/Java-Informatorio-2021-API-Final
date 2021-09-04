package com.informatorio.apitiendafinal.controller;

import com.informatorio.apitiendafinal.entity.Carrito;
import com.informatorio.apitiendafinal.entity.EstadoCarrito;
import com.informatorio.apitiendafinal.entity.Producto;
import com.informatorio.apitiendafinal.entity.Usuario;
import com.informatorio.apitiendafinal.repository.CarritoRepository;
import com.informatorio.apitiendafinal.repository.ProductoRepository;
import com.informatorio.apitiendafinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarritoController {

    @Autowired
    private CarritoRepository carritoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/carritos")
    public Iterable<Carrito> getCarritos(){
        return carritoRepository.findAll();
    }

    @GetMapping(value = "/carritos/estado")
    public Iterable<Carrito> getCarritosEstado(@RequestParam("estado") EstadoCarrito estado){
        return carritoRepository.findByEstadoCarrito(estado);
    }


    @PostMapping(value = "/usuarios/{id}/carritos")
    public Carrito postCarritos(@PathVariable("id") Long id,
                                @RequestBody Carrito carrito){
        Usuario usuario = usuarioRepository.findById(id).get();
        carrito.setUsuario(usuario);
        carrito.setEstadoCarrito(EstadoCarrito.ABIERTO);
        return carritoRepository.save(carrito);
    }
    @PutMapping(value = "/usuarios/{id}/carritos/{carritoId}/productos/{productoId}")
    public Carrito putCarritos(@PathVariable("id") Long id,
                               @PathVariable("carritoId") Long carritoId,
                               @PathVariable("productoId") Long productoId){
        Usuario usuario = usuarioRepository.findById(id).get();
        Carrito carrito = carritoRepository.findById(carritoId).get();
        Producto producto = productoRepository.findById(productoId).get();
        if(producto.getPublicado() == true){
            carrito.agregarProducto(producto);
            return carritoRepository.save(carrito);
        }
        return null;
    }
    @DeleteMapping(value = "/usuarios/{id}/carritos/{carritoId}/productos/{productoId}")
    public Carrito deletProductosDeCarrito(@PathVariable("id") Long id,
                                           @PathVariable("carritoId") Long carritoId,
                                           @PathVariable("productoId") Long productoId){
        Usuario usuario = usuarioRepository.findById(id).get();
        Carrito carrito = carritoRepository.findById(carritoId).get();
        Producto producto = productoRepository.findById(productoId).get();
        carrito.removeProducto(producto);
        return carritoRepository.save(carrito);
    }

    @DeleteMapping(value = "/carritos/{id}")
    public void deletCarrito(@PathVariable("id") Long id){
        carritoRepository.deleteById(id);
    }

}
