package com.mvp.conjunto.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "concepto_conjunto")
public class ConceptoConjunto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_concepto", nullable = false)
    private Concepto idConcepto;

    @ManyToOne
    @JoinColumn(name = "id_conjunto", nullable = false)
    private Conjunto idConjunto;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;
}
