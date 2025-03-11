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
@Table(name = "residente")
public class Residente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;

    @JoinColumn(name = "id_tipo")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoResidente idTipo;

    @JoinColumn(name = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    private Estado idEstado;

    @Column(name = "id_firebase", length = Integer.MAX_VALUE)
    private String idFirebase;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @OneToMany(mappedBy = "idResidente")
    private Set<ResidenteConjunto> residenteConjuntos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idResidente")
    private Set<ResidenteUnidad> residenteUnidads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idResidente")
    private Set<SolicitudRegistro> solicitudRegistros = new LinkedHashSet<>();

}