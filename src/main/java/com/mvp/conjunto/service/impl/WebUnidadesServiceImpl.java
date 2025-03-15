package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.*;
import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.WebUnidadesService;
import com.mvp.conjunto.service.impl.mapper.ConceptosMapper;
import com.mvp.conjunto.service.impl.mapper.CuentaCobroMapper;
import com.mvp.conjunto.service.impl.mapper.PageMapper;
import com.mvp.conjunto.service.impl.mapper.ResidenteMapper;
import com.mvp.conjunto.web.api.model.ResidentesUnidad;
import com.mvp.conjunto.web.api.model.UnidadConceptos;
import com.mvp.conjunto.web.api.model.UnidadCuentasCobro;
import com.mvp.conjunto.web.api.model.UnidadesConjunto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WebUnidadesServiceImpl implements WebUnidadesService {


    private final UnidadRepository unidadRepository;
    private final ResidenteUnidadRepository residenteUnidadRepository;
    private final UnidadConceptoRepository unidadConceptoRepository;
    private final CuentaCobroUnidadRepository cuentaCobroUnidadRepository;
    private final ConceptoRepository conceptoRepository;
    private final ResidenteRepository residenteRepository;
    private final ResidenteMapper residenteMapper;
    private final PageMapper pageMapper;
    private final ConceptosMapper conceptosMapper;
    private final CuentaCobroMapper cuentaCobroMapper;

    @Override
    public UnidadesConjunto unidades(String c, Integer page, Integer size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idConjunto.id", UUID.fromString(c));

        return mapUnidades(unidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable));
    }

    private UnidadesConjunto mapUnidades(Page<Unidad> unidades){
        UnidadesConjunto unidadesConjunto = new UnidadesConjunto();
        unidadesConjunto.setUnidades(unidades.getContent().stream().map(residenteMapper::mapUnidad).toList());
        unidadesConjunto.setPages(pageMapper.mapPage(unidades));
        return unidadesConjunto;
    }

    @Override
    public ResidentesUnidad unidadResidentes(String idUnidad, String c, Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUnidad.id", UUID.fromString(idUnidad));
        filtros.put("idUnidad.idConjunto.id", UUID.fromString(c));

        return unidadResidentes(residenteUnidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable));
    }

    private ResidentesUnidad unidadResidentes(Page<ResidenteUnidad> residenteUnidad){
        ResidentesUnidad residentesUnidad = new ResidentesUnidad();
        residentesUnidad.setResidentes(residenteUnidad.getContent().stream().map( x -> residenteMapper.mapResidente(x.getIdResidente())).toList());
        residentesUnidad.setPages(pageMapper.mapPage(residenteUnidad));
        return residentesUnidad;
    }

    @Override
    public UnidadConceptos unidadeConceptos(String idUnidad, String c, Integer page, Integer size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUnidad.id", UUID.fromString(idUnidad));
        filtros.put("idUnidad.idConjunto.id", UUID.fromString(c));

        return mapUnidadeConceptos(unidadConceptoRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable));
    }

    private UnidadConceptos mapUnidadeConceptos(Page<UnidadConcepto> unidadConceptosQuery){
        UnidadConceptos unidadConceptos = new UnidadConceptos();
        unidadConceptos.setConceptos(unidadConceptosQuery.getContent().stream().map( x -> conceptosMapper.mapConcepto(x.getIdConcepto(), x.getFechaCreacion())).toList());
        unidadConceptos.setPages(pageMapper.mapPage(unidadConceptosQuery));
        return unidadConceptos;
    }

    @Override
    public UnidadCuentasCobro unidadCuentasCobro(String idUnidad, String c, Integer page, Integer size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUnidad.id", UUID.fromString(idUnidad));
        filtros.put("idUnidad.idConjunto.id", UUID.fromString(c));

        return mapUnidadCuentasCobro(cuentaCobroUnidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable));
    }

    private UnidadCuentasCobro mapUnidadCuentasCobro(Page<CuentaCobroUnidad> cuentaCobroUnidad){
        UnidadCuentasCobro unidadCuentasCobro = new UnidadCuentasCobro();
        unidadCuentasCobro.setCuentasCobro(cuentaCobroUnidad.getContent().stream().map(cuentaCobroMapper::mapCuentaCobro).toList());
        unidadCuentasCobro.setPages(pageMapper.mapPage(cuentaCobroUnidad));
        return unidadCuentasCobro;

    }


    @Override
    @Transactional
    public void unidadConcepto(String idUnidad, String idConcepto, String c) {

        unidadConceptoRepository.findByIdUnidadAndIdConcepto(UUID.fromString(idUnidad), UUID.fromString(idConcepto)).ifPresent(x -> {
            throw new RuntimeException("Record Exist ..");
        });

        Concepto concepto = conceptoRepository.findById(UUID.fromString(idConcepto)).orElseThrow();
        Unidad unidad = unidadRepository.findById(UUID.fromString(idUnidad)).orElseThrow();
        UnidadConcepto unidadConcepto = new UnidadConcepto();
        unidadConcepto.setIdUnidad(unidad);
        unidadConcepto.setIdConcepto(concepto);
        unidadConcepto.setFechaActualizacion(Instant.now());
        unidadConcepto.setFechaCreacion(Instant.now());
        unidadConceptoRepository.save(unidadConcepto);
    }

    @Override
    @Transactional
    public void unidadResidente(String idUnidad, String idResidente, String c) {

        residenteUnidadRepository.findByIdResidenteAndIdUnidad(UUID.fromString(idResidente), UUID.fromString(idUnidad)).ifPresent(x -> {
            throw new RuntimeException("Record Exist ..");
        });

        Residente residente = residenteRepository.findById(UUID.fromString(idResidente)).orElseThrow();
        Unidad unidad = unidadRepository.findById(UUID.fromString(idUnidad)).orElseThrow();
        ResidenteUnidad residenteUnidad = new ResidenteUnidad();
        residenteUnidad.setIdResidente(residente);
        residenteUnidad.setIdUnidad(unidad);
        residenteUnidad.setFechaActualizacion(Instant.now());
        residenteUnidad.setFechaCreacion(Instant.now());
        residenteUnidadRepository.save(residenteUnidad);

    }

    @Override
    public void eliminaUnidadResidente(String idResidenteUnidad, String c) {

    }

}
