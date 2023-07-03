package com.almacen.chelas.Almacen.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clienteId;

    @Column(name = "ApeMat", nullable = false, length = 65)
    private String apeMat;

    @Column(name = "ApePat", nullable = false, length = 65)
    private String apePat;

    @Column(name = "Nombre", nullable = false, length = 65)
    private String nombre;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Telefono", length = 20)
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String apeMat, String apePat, String nombre, String direccion, String telefono) {
        this.apeMat = apeMat;
        this.apePat = apePat;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Cliente() {

    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteID(int clienteID) {
        this.clienteId = clienteID;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "clienteId=" + clienteId +
                ", apeMat='" + apeMat + '\'' +
                ", apePat='" + apePat + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
