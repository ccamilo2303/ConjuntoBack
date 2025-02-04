package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "facturas_residentes")
public class FacturasResidente {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_residente")
    private Residente idResidente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_factura")
    private Factura idFactura;

    @Size(max = 200)
    @Column(name = "estado", length = 200)
    private String estado;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}