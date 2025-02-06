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
@Table(name = "facturas")
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_residente")
    private ResidenteEntity idResidente;

    @Column(name = "fecha_inicio")
    private Instant fechaInicio;

    @Column(name = "fecha_fin")
    private Instant fechaFin;

    @Column(name = "total")
    private Integer total;

    @Column(name = "estado", length = Integer.MAX_VALUE)
    private String estado;

    @Column(name = "fecha_creaction")
    private Instant fechaCreaction;

    @OneToMany(mappedBy = "idFactura")
    private Set<ConceptoFacturaEntity> conceptoFacturas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idFactura")
    private Set<PagoEntity> pagos = new LinkedHashSet<>();

}