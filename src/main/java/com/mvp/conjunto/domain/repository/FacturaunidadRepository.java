package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.FacturaUnidadEntity;
import com.mvp.conjunto.domain.model.ResumenSaldoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaunidadRepository extends JpaRepository<FacturaUnidadEntity, Long> {

    @Query(nativeQuery = true, value = """
            select r.nombre, r.id,
            case when (sum(f.total) - sum(p.valor)) = 0 then 'Al dia' when sum(f.total) - sum(p.valor) > 0 then 'Saldo pendiente' else 'Saldo a favor' end "estado",
            case when (sum(f.total) - sum(p.valor)) < 0 then (sum(f.total) - sum(p.valor)) else 0 end "saldoFavor",
            case when (sum(f.total) - sum(p.valor)) > 0 then (sum(f.total) - sum(p.valor)) else 0 end "saldoMora"
            from factura_unidad f
            left join pago p
            on f.id = p.id_factura_unidad\s
            left join residente_unidad ru\s
            on ru.id_unidad = f.id_unidad\s
            left join residente r\s
            on r.id = ru.id_residente\s
            where r.id = :idUsuario
            group by r.nombre , r.id """)
    Optional<List<ResumenSaldoModel>> resumenSaldo(@Param("idUsuario") Long idUsuario);
}
