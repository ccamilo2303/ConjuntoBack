package com.mvp.conjunto.domain.entity;

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
@Table(name = "conjunto")
public class Conjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "ciudad", length = Integer.MAX_VALUE)
    private String ciudad;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private Estado idEstado;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @OneToMany(mappedBy = "idConjunto")
    private Set<SolicitudRegistro> solicitudRegistros = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idConjunto")
    private Set<Unidad> unidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idConjunto")
    private Set<UsuarioConjunto> usuarioConjuntos = new LinkedHashSet<>();

}