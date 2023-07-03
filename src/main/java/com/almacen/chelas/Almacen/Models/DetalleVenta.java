package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleVentaId;
    @ManyToOne
    @JoinColumn(name = "productoId")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "ventaId")
    private Venta venta;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(nullable = false)
    private BigDecimal precioUnitario;

    public DetalleVenta(Producto producto, Venta venta, Integer cantidad, BigDecimal precioUnitario) {
        this.producto = producto;
        this.venta = venta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetalleVenta() {
    }

    public Integer getDetalleVentaId() {
        return detalleVentaId;
    }

    public void setDetalleVentaId(Integer detalleVentaId) {
        this.detalleVentaId = detalleVentaId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" + "detalleVentaId=" + detalleVentaId + ", productoId=" + producto + ", ventaId=" + venta + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + '}';
    }
}
