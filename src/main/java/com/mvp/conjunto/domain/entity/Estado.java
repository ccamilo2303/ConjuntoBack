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
@Table(name = "estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @OneToMany(mappedBy = "idEstado")
    private Set<Administrador> administradors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<AdministradorConjunto> administradorConjuntos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<Conjunto> conjuntos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<CuentaCobroUnidad> cuentaCobroUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<ResidenteConjunto> residenteConjuntos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<ResidenteUnidad> residenteUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<SolicitudRegistro> solicitudRegistros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<Unidad> unidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstado")
    private Set<UnidadParqueadero> unidadParqueaderos = new LinkedHashSet<>();

}