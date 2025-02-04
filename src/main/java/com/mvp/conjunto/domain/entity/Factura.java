package com.mvp.conjunto.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "total")
    private Integer total;

    @Size(max = 200)
    @Column(name = "estado", length = 200)
    private String estado;

    @Column(name = "fecha_creaction")
    private Instant fechaCreaction;

}