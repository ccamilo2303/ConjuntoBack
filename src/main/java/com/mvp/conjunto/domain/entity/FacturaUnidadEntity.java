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
@Table(name = "factura_unidad")
public class FacturaUnidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_unidad")
    private UnidadEntity idUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado")
    private EstadoFacturaUnidadEntity idEstado;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "total")
    private Integer total;

    @Column(name = "fecha_creacion")
    private Instant fechaCreacion;

    @OneToMany(mappedBy = "idFactura")
    private Set<ConceptoFacturaEntity> conceptoFacturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idFacturaUnidad")
    private Set<PagoEntity> pagos = new LinkedHashSet<>();

}