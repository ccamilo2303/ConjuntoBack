package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "concepto")
public class ConceptoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idConcepto")
    private Set<ConceptoFacturaEntity> conceptoFacturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idConcepto")
    private Set<UnidadConceptoEntity> unidadConceptos = new LinkedHashSet<>();

}