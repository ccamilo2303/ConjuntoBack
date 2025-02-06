package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "concepto_factura")
public class ConceptoFacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "concepto", length = Integer.MAX_VALUE)
    private String concepto;

    @Column(name = "valor")
    private Integer valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private FacturaEntity idFactura;

    @Column(name = "fecha_creaction")
    private Instant fechaCreaction;

}