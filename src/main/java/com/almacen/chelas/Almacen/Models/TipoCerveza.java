package com.almacen.chelas.Almacen.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TipoCerveza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tipoCervezaId;
    @Column(length = 35)
    private String nombre;

    @Column(length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "tipoCerveza", cascade = CascadeType.ALL)
    private List<Producto> list = new ArrayList<>();

    public TipoCerveza(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public TipoCerveza() {

    }

    public Integer getTipoCervezaId() {
        return tipoCervezaId;
    }

    public void setTipoCervezaId(Integer tipoCervezaId) {
        this.tipoCervezaId = tipoCervezaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoCerveza{" +
                "tipoCervezaId=" + tipoCervezaId +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
