package com.mvp.conjunto.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "conjuntos")
public class Conjunto {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @Column(name = "nombre", length = 200)
    private String nombre;

    @Size(max = 200)
    @Column(name = "ubicacion", length = 200)
    private String ubicacion;

    @Size(max = 200)
    @Column(name = "estado", length = 200)
    private String estado;

    @Size(max = 200)
    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

}