package com.mvp.conjunto.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tipo_residente")
public class TipoResidenteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tipo", length = Integer.MAX_VALUE)
    private String tipo;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idTipo")
    private Set<ResidenteEntity> residentes = new LinkedHashSet<>();

}