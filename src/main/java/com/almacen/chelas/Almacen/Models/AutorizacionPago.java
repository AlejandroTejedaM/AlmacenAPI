package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class AutorizacionPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autorizacionId;

    @ManyToOne
    @JoinColumn(name = "ventaId")
    private Venta venta;
    @Column(scale = 2)
    private BigDecimal monto;

    @Column()
    private Date fechaAutorizacion;

    public AutorizacionPago( BigDecimal monto, Date fechaAutorizacion) {
        this.monto = monto;
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public AutorizacionPago() {

    }

    public int getAutorizacionId() {
        return autorizacionId;
    }

    public void setAutorizacionId(int autorizacionId) {
        this.autorizacionId = autorizacionId;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    @Override
    public String toString() {
        return "AutorizacionPago{" +
                "autorizacionId=" + autorizacionId +
                ", ventaId=" + venta +
                ", monto=" + monto +
                ", fechaAutorizacion=" + fechaAutorizacion +
                '}';
    }
}
