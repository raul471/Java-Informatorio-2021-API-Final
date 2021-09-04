package com.informatorio.apitiendafinal.controller;


import com.informatorio.apitiendafinal.entity.Usuario;
import com.informatorio.apitiendafinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repositoty;

    @GetMapping(value = "/usuarios")
    public Iterable<Usuario> getUsuarios(){
        return repositoty.findAll();
    }

    @GetMapping(value = "/usuarios/ciudad")
    public List<Usuario> getUsuariosPorCiudad(@RequestParam("ciudad") String ciudad) {
        return repositoty.findByCiudad(ciudad);
    }

    @GetMapping(value = "/usuarios/")
    public List<Usuario>
    getUsuariosPorFecha(@RequestParam("fechaDeAlta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                        LocalDate fechaDeAlta) {
        return repositoty.findAllWithFechaDeAltaAfter(fechaDeAlta);
    }


    @GetMapping(value = "/usuarios/{id}")
    public Usuario getUsuariosPorId(@PathVariable("id") Long id) {
        return repositoty.findById(id).get();
    }


    @PostMapping(value = "/usuarios")
    public Usuario postUsuarios(@RequestBody Usuario usuario){
        return repositoty.save(usuario);
    }


    @PutMapping(value = "/usuarios/{id}")
    public Usuario putUsuarios(@PathVariable("id") Long id, @RequestBody  Usuario usuario){
        Usuario nusuario = repositoty.findById(id).get();
        nusuario.setNombre(usuario.getNombre());
        nusuario.setApellido(usuario.getApellido());
        nusuario.setEmail(usuario.getEmail());
        nusuario.setPassword(usuario.getPassword());
        nusuario.setFechaDeAlta(usuario.getFechaDeAlta());
        nusuario.setCuidad(usuario.getCiudad());
        nusuario.setProvincia(usuario.getProvincia());
        nusuario.setPais(usuario.getPais());
        return repositoty.save(nusuario);
    }

    @DeleteMapping(value = "usuarios/{id}")
    public void deletUsuarios(@PathVariable("id") Long id){
        repositoty.deleteById(id);
    }

}
