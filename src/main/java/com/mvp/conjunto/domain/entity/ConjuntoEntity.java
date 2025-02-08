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
@Table(name = "conjunto")
public class ConjuntoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = Integer.MAX_VALUE)
    private String nombre;

    @Column(name = "ubicacion", length = Integer.MAX_VALUE)
    private String ubicacion;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_conjunto")
    private EstadoConjuntoEntity idEstadoConjunto;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idConjunto")
    private Set<ResidenteEntity> residentes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idConjunto")
    private Set<UnidadEntity> unidads = new LinkedHashSet<>();

}