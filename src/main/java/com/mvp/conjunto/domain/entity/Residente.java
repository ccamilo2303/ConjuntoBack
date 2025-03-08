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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "id_tipo")
    private UUID idTipo;

    @Column(name = "id_estado")
    private UUID idEstado;

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