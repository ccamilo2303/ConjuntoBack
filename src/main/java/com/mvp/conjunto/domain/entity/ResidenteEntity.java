package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "residentes")
public class ResidenteEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;

    @Column(name = "tipo", length = Integer.MAX_VALUE)
    private String tipo;

    @Column(name = "conjunto")
    private Integer conjunto;

    @Column(name = "interior")
    private Integer interior;

    @Column(name = "apto")
    private Integer apto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conjunto")
    private ConjuntoEntity idConjunto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}