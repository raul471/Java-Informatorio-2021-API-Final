package com.informatorio.apitiendafinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String generado;

    @Enumerated(EnumType.STRING)
    private EstadoCarrito estadoCarrito;

    @CreationTimestamp
    private LocalDate fechaCreacion;
    @Transient
    private Integer CantidadProductos;
    @Transient
    private Double total;
    @Transient
    private String nombreDeUsuario;

    /*Relaciones*/
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenerado() {
        return generado;
    }

    public void setGenerado(String generado) {
        this.generado = generado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getCantidadProductos() {
        return productos.size();
    }

    public Double getTotal() {
        Double subtotal = new Double(0);
        for (Producto p:productos) {
            subtotal +=(p.getPrecioUnitario());
        }
        return subtotal;
    }

    public String getNombreDeUsuario() {
        return usuario.getNombre();
    }

    public List<Producto> getProducto() {
        return productos;
    }

    public void setProducto(List<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.getCarrito().add(this);
    }
    public void removeProducto(Producto producto) {
        productos.remove(producto);
        producto.getCarrito().remove(this);
    }

    public EstadoCarrito getEstadoCarrito() {
        return estadoCarrito;
    }

    public void setEstadoCarrito(EstadoCarrito estadoCarrito) {
        this.estadoCarrito = estadoCarrito;
    }
}
