package com.mvp.conjunto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "unidad")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "interior")
    private Integer interior;

    @Column(name = "apto")
    private Integer apto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conjunto")
    private Conjunto idConjunto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private Estado idEstado;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @OneToMany(mappedBy = "idUnidad")
    private Set<CuentaCobroUnidad> cuentaCobroUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<Notificacion> notificacions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<ResidenteUnidad> residenteUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<UnidadConcepto> unidadConceptos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idUnidad")
    private Set<UnidadParqueadero> unidadParqueaderos = new LinkedHashSet<>();

}