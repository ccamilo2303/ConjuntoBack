package com.mvp.conjunto.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "avisos")
public class AvisoEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "titulo", length = Integer.MAX_VALUE)
    private String titulo;

    @Column(name = "contenido", length = Integer.MAX_VALUE)
    private String contenido;

    @Column(name = "fecha_creaction")
    private Instant fechaCreaction;

}