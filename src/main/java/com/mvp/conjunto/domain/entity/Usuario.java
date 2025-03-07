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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    private TipoUsuario idTipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private Estado idEstado;

    @Column(name = "id_firebase", length = Integer.MAX_VALUE)
    private String idFirebase;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @OneToOne(mappedBy = "idUsuario")
    private UsuarioAdministrador usuarioAdministrador;

    @OneToMany(mappedBy = "idUsuario")
    private Set<UsuarioConjunto> usuarioConjuntos = new LinkedHashSet<>();

    @OneToOne(mappedBy = "idUsuario")
    private UsuarioResidente usuarioResidente;

}