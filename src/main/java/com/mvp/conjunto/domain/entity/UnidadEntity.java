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
@Table(name = "unidad")
public class UnidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "interior")
    private Integer interior;

    @Column(name = "apto")
    private Integer apto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conjunto")
    private ConjuntoEntity idConjunto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idUnidad")
    private Set<FacturaUnidadEntity> facturaUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<NotificacionEntity> notificacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<ResidenteUnidadEntity> residenteUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<UnidadConceptoEntity> unidadConceptos = new LinkedHashSet<>();

}