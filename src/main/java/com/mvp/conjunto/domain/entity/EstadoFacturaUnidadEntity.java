package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "estado_factura_unidad")
public class EstadoFacturaUnidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idEstado")
    private Set<FacturaUnidadEntity> facturaUnidads = new LinkedHashSet<>();

}