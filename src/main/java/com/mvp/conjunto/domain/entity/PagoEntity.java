package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "pago")
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura_unidad")
    private FacturaUnidadEntity idFacturaUnidad;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "metodo_pago", length = Integer.MAX_VALUE)
    private String metodoPago;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}