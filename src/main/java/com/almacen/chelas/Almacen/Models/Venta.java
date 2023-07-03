package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ventaId;
    @Column(scale = 2)
    private BigDecimal total;
    @Column()
    private Date fechaVenta;
    @ManyToOne
    @JoinColumn(name = "cajeroId")
    private Cajero cajero;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detallesVenta;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<AutorizacionPago> autorizacionPagos;

    public Venta(BigDecimal total, Date fechaVenta) {
        this.total = total;
        this.fechaVenta = fechaVenta;
    }

    public Venta() {
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public void setCajero(Cajero cajero) {
        this.cajero = cajero;
    }

    @Override
    public String toString() {
        return "Venta{" + "ventaId=" + ventaId + ", Total=" + total + ", fechaVenta=" + fechaVenta + ", cajero=" + cajero + '}';
    }
}
