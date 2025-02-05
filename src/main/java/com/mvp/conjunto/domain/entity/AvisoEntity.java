package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "avisos")
public class AvisoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "titulo", length = 200)
    private String titulo;

    @Size(max = 200)
    @Column(name = "contenido", length = 200)
    private String contenido;

    @Column(name = "fecha_creaction")
    private Instant fechaCreaction;

}