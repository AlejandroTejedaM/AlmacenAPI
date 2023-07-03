package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productoId;
    @Column(nullable = false, length = 100)
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "tipoCervezaId")
    private TipoCerveza tipoCerveza;
    @Column(scale = 2)
    private BigDecimal precio;
    @Column(length = 255)
    private String descripcion;
    @Column()
    private Integer stock;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetallePedido> detallePedido;

    public Producto(String nombre, TipoCerveza tipoCerveza, BigDecimal precio, String descripcion, Integer stock) {
        this.nombre = nombre;
        this.tipoCerveza = tipoCerveza;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    public Producto() {

    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCerveza getTipoCerveza() {
        return tipoCerveza;
    }

    public void setTipoCerveza(TipoCerveza tipoCerveza) {
        this.tipoCerveza = tipoCerveza;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "productoId=" + productoId +
                ", nombre='" + nombre + '\'' +
                ", tipoCerveza=" + tipoCerveza +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                '}';
    }
}
