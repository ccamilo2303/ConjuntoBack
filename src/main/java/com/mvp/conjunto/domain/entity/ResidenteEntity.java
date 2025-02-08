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
@Table(name = "residente")
public class ResidenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private TipoResidenteEntity idTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conjunto")
    private ConjuntoEntity idConjunto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private EstadoResidenteEntity idEstado;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idResidente")
    private Set<ResidenteUnidadEntity> residenteUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idResidente")
    private Set<SolicitudEntity> solicituds = new LinkedHashSet<>();

}