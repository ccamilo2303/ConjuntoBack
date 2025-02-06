package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pagos")
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_residente")
    private ResidenteEntity idResidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private FacturaEntity idFactura;

    @Column(name = "monto")
    private Integer monto;

    @Column(name = "metodo_pago", length = Integer.MAX_VALUE)
    private String metodoPago;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}