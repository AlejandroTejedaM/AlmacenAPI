package com.almacen.chelas.Almacen.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pedidoID;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @Column(name = "FechaPedido", nullable = false)
    private Date fechaPedido;

    @Column(name = "Estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detallePedido;
    public Pedido(Cliente cliente, Date fechaPedido, String estado) {
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
    }

    public Pedido() {

    }

    public int getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Integer pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "pedidoID=" + pedidoID +
                ", clienteId=" + cliente +
                ", fechaPedido=" + fechaPedido +
                ", estado='" + estado + '\'' +
                '}';
    }
}
