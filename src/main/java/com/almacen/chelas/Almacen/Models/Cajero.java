package com.almacen.chelas.Almacen.Models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cajero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cajeroId;
    @Column(name = "ApeMat", nullable = false, length = 65)
    private String apeMat;

    @Column(name = "ApePat", nullable = false, length = 65)
    private String apePat;

    @Column(name = "Nombre", nullable = false, length = 65)
    private String nombre;
    @Column(scale = 2, nullable = false)
    private BigDecimal salario;

    @OneToMany(mappedBy = "cajero", cascade = CascadeType.ALL)
    private List<Venta> ventas = new ArrayList<>();
    

    public Cajero(String apeMat, String apePat, String nombre, BigDecimal salario) {
        this.apeMat = apeMat;
        this.apePat = apePat;
        this.nombre = nombre;
        this.salario = salario;
    }

    public Cajero() {

    }


    public int getCajeroId() {
        return cajeroId;
    }

    public void setCajeroId(Integer cajeroId) {
        this.cajeroId = cajeroId;
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

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Cajero{" +
                "cajeroId=" + cajeroId +
                ", apeMat='" + apeMat + '\'' +
                ", apePat='" + apePat + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                '}';
    }
}
