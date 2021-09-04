package com.informatorio.apitiendafinal.controller;


import com.informatorio.apitiendafinal.entity.Producto;
import com.informatorio.apitiendafinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoRepository repository;

    @GetMapping(value = "/productos")
    public Iterable<Producto> getProductos(){
        return repository.findAll();
    }


    @PostMapping(value = "/productos")
    public Producto postProductos(@RequestBody Producto producto){
        return repository.save(producto);
    }


    @PutMapping(value = "/productos/{id}")
    public Producto putProductos(@PathVariable("id") Long id, @RequestBody  Producto producto){
        Producto nproductos = repository.findById(id).get();
        nproductos.setNombre(producto.getNombre());
        nproductos.setDescripcion(producto.getDescripcion());
        nproductos.setContenido(producto.getContenido());
        nproductos.setPrecioUnitario(producto.getPrecioUnitario());
        nproductos.setFechaCreacion(producto.getFechaCreacion());
        nproductos.setPublicado(producto.getPublicado());
        return repository.save(nproductos);

    }

    @DeleteMapping(value = "productos/{id}")
    public void deletProductos(@PathVariable("id") Long id){
        repository.deleteById(id);
    }

    @GetMapping(value = "/productos/")
    public List<Producto> getProductosPorPublicado(@RequestParam("publicado") Boolean publicado) {
        return repository.findByPublicado(publicado);
    }

    @GetMapping(value = "/productos/nombre")
    public List<Producto> getProductosPorNombre(@RequestParam("nombre") String nombre) {
        return repository.findByNombreContaining(nombre);
    }


}
