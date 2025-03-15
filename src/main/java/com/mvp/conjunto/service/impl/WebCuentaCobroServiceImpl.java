package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.CuentaCobroUnidad;
import com.mvp.conjunto.domain.entity.ResidenteConjunto;
import com.mvp.conjunto.domain.repository.CuentaCobroUnidadRepository;
import com.mvp.conjunto.domain.repository.SolicitudRegistroSpec;
import com.mvp.conjunto.service.WebCuentaCobroService;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebCuentaCobroServiceImpl implements WebCuentaCobroService {

    private final CuentaCobroUnidadRepository cuentaCobroUnidadRepository;


    @Override
    public CuentasCobro cuentaCobro(Integer page, Integer size, String idUnidad, String estado, String fechaInicio, String fechaFin, String c) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idConjunto.id", UUID.fromString(c));

        Optional.of(idUnidad).ifPresent(x -> filtros.put("idUnidad.id", UUID.fromString(x)));
        Optional.of(estado).ifPresent(x -> filtros.put("idEstado.id", UUID.fromString(estado)));
        Optional.of(fechaInicio).ifPresent(x -> filtros.put("fechaInicio", fechaInicio));
        Optional.of(fechaFin).ifPresent(x -> filtros.put("fechaFin", fechaFin));

        Page<CuentaCobroUnidad> residenteQuery = cuentaCobroUnidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable);

        return null;
    }

    @Override
    public void crearCuentaCobro(String c, RegistrarCuentaCobro registrarCuentaCobro) {

    }

    @Override
    public PagoManual200Response pagoManual(String c, PagoManual pagoManual) {
        return null;
    }

    @Override
    public void cuentaCobroUnidad(String c, RegistrarCuentaCobroUnidad registrarCuentaCobroUnidad) {

    }


}
