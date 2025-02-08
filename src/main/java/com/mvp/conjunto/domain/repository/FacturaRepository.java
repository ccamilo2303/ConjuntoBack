package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.model.ResumenSaldoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {

    @Query(nativeQuery = true, value = """
            select r.nombre, r.id,
            case when (sum(f.total) - sum(p.monto)) = 0 then 'Al dia' when sum(f.total) - sum(p.monto) > 0 then 'Saldo pendiente' else 'Saldo a favor' end "estado",
            case when (sum(f.total) - sum(p.monto)) < 0 then (sum(f.total) - sum(p.monto)) else 0 end "saldoFavor",
            case when (sum(f.total) - sum(p.monto)) > 0 then (sum(f.total) - sum(p.monto)) else 0 end "saldoMora"
            from facturas f\s
            left join pagos p\s
            on f.id = p.id_factura\s
            left join residentes r\s
            on f.id_residente  = r.id\s
            where r.id = :idUsuario
            group by r.nombre , r.id""")
    Optional<List<ResumenSaldoModel>> resumenSaldo(@Param("idUsuario") Long idUsuario);
}
