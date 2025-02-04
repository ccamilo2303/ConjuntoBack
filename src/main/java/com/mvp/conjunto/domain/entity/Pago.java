package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_residente")
    private Residente idResidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura idFactura;

    @Column(name = "monto")
    private Integer monto;

    @Size(max = 200)
    @Column(name = "metodo_pago", length = 200)
    private String metodoPago;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}