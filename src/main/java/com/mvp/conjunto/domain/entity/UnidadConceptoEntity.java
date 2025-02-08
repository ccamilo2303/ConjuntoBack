package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "unidad_concepto")
public class UnidadConceptoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad")
    private UnidadEntity idUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_concepto")
    private ConceptoEntity idConcepto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}