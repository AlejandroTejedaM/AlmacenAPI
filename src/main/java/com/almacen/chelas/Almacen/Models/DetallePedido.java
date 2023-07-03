package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity

public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detallePedidoId;
    @ManyToOne
    @JoinColumn(name = "productoId")
    private Producto producto;
    @ManyToOne
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;
    @Column(nullable = false)
    private Integer cantidad;
    @Column(scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    public DetallePedido(Pedido pedidoId, Producto productoId, Integer cantidad, BigDecimal precioUnitario) {
        this.pedido = pedidoId;
        this.producto = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public DetallePedido() {
    }

    public Integer getDetallePedidoId() {
        return detallePedidoId;
    }

    public void setDetallePedidoId(Integer detallePedidoId) {
        this.detallePedidoId = detallePedidoId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        return "DetallePedido{" +
                "detallePedidoId=" + detallePedidoId +
                ", pedidoId=" + pedido +
                ", productoId=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}
