package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "residentes")
public class Residente {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "nombre", length = 200)
    private String nombre;

    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "telefono")
    private Integer telefono;

    @Size(max = 200)
    @Column(name = "tipo", length = 200)
    private String tipo;

    @Column(name = "conjunto")
    private Integer conjunto;

    @Column(name = "interior")
    private Integer interior;

    @Column(name = "apto")
    private Integer apto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conjunto")
    private Conjunto idConjunto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}