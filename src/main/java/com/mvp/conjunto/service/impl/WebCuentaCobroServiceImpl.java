package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.*;
import com.mvp.conjunto.domain.entity.Concepto;
import com.mvp.conjunto.domain.entity.Unidad;
import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.WebCuentaCobroService;
import com.mvp.conjunto.service.impl.enums.EstadosEnum;
import com.mvp.conjunto.service.impl.mapper.CuentaCobroMapper;
import com.mvp.conjunto.service.impl.mapper.PageMapper;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebCuentaCobroServiceImpl implements WebCuentaCobroService {

    private final ConceptoCuentaCobroRepository conceptoCuentaCobroRepository;
    private final CuentaCobroUnidadRepository cuentaCobroUnidadRepository;
    private final UnidadRepository unidadRepository;
    private final EstadoRepository estadoRepository;
    private final ConceptoRepository conceptoRepository;
    private final PagoRepository pagoRepository;
    private final MetodoPagoRepository metodoPagoRepository;
    private final PageMapper pageMapper;
    private final CuentaCobroMapper cuentaCobroMapper;


    @Override
    public CuentasCobro cuentaCobro(Integer page, Integer size, String idUnidad, String estado, String fechaInicio, String fechaFin, String c) {
        log.info("[WebCuentaCobroServiceImpl][cuentaCobro][idConjunto]: {}", idUnidad);
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUnidad.idConjunto.id", UUID.fromString(c));

        Optional.ofNullable(idUnidad).ifPresent(x -> filtros.put("idUnidad.id", UUID.fromString(x)));
        Optional.ofNullable(estado).ifPresent(x -> filtros.put("idEstado.id", UUID.fromString(estado)));
        Optional.ofNullable(fechaInicio).ifPresent(x -> filtros.put("fechaInicio", fechaInicio));
        Optional.ofNullable(fechaFin).ifPresent(x -> filtros.put("fechaFin", fechaFin));

        return mapCuentaCobro(cuentaCobroUnidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable));
    }

    private CuentasCobro mapCuentaCobro(Page<CuentaCobroUnidad> residenteQuery){
        CuentasCobro cuentasCobro = new CuentasCobro();
        cuentasCobro.setPages(pageMapper.mapPage(residenteQuery));
        cuentasCobro.setCuentasCobro(residenteQuery.getContent().stream().map(cuentaCobroMapper::mapCuentaCobro).toList());
        return cuentasCobro;
    }

    @Override
    public void crearCuentaCobro(String c, RegistrarCuentaCobro registrarCuentaCobro) {
        List<Unidad> unidades = unidadRepository.findByIdConjunto_Id(UUID.fromString(c)).orElseThrow();
        Instant fechaInicio = castDate(registrarCuentaCobro.getFechaInicio());
        Instant fechaFin = castDate(registrarCuentaCobro.getFechaFin());
        Estado estadoPendientePago = estadoRepository.findById(EstadosEnum.PENDIENTE_PAGO.getValue()).orElseThrow();

        List<UUID> cuentaCobroUnidades = cuentaCobroUnidadRepository.findByFechaInicioAndFechaFinAndUnidadIds(fechaInicio, fechaFin, unidades.stream().map(Unidad::getId).toList())
                .orElseThrow()
                .stream()
                .map(x -> x.getIdUnidad().getId())
                .toList();

        unidades.forEach(x -> {
            if(!cuentaCobroUnidades.contains(x.getId()))
                createCuentaCobro(x, estadoPendientePago, fechaInicio, fechaFin, x.getUnidadConceptos().stream().map(UnidadConcepto::getIdConcepto).toList());
        });

        log.info("[crearCuentaCobro][cuentaCobroUnidades]: {}", cuentaCobroUnidades);
    }

    @Transactional
    private void createCuentaCobro(Unidad x, Estado estadoPendientePago, Instant fechaInicio, Instant fechaFin, List<Concepto> conceptos) {
        double total = conceptos.stream().map(Concepto::getValor).mapToDouble(BigDecimal::doubleValue).sum();
        CuentaCobroUnidad cuentaCobroUnidad = new CuentaCobroUnidad();

        cuentaCobroUnidad.setIdUnidad(x);
        cuentaCobroUnidad.setIdEstado(estadoPendientePago);
        cuentaCobroUnidad.setFechaInicio(fechaInicio);
        cuentaCobroUnidad.setFechaFin(fechaFin);
        cuentaCobroUnidad.setTotal(BigDecimal.valueOf(total));
        cuentaCobroUnidad.setFechaCreacion(Instant.now());
        cuentaCobroUnidad.setFechaActualizacion(Instant.now());
        log.info("[createCuentaCobro][CuentaCobroUnidad]: {}", cuentaCobroUnidad);
        cuentaCobroUnidadRepository.save(cuentaCobroUnidad);

        conceptos.forEach(uc -> {
            ConceptoCuentaCobro conceptoCuentaCobro = new ConceptoCuentaCobro();
            conceptoCuentaCobro.setIdConcepto(uc);
            conceptoCuentaCobro.setValor(uc.getValor());
            conceptoCuentaCobro.setFechaCreacion(Instant.now());
            conceptoCuentaCobro.setFechaActualizacion(Instant.now());
            conceptoCuentaCobro.setIdCuentaCobro(cuentaCobroUnidad);
            conceptoCuentaCobroRepository.save(conceptoCuentaCobro);
        });

    }

    private Instant castDate(String fecha){
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(fecha).toInstant();
        } catch (Exception e){
            throw new RuntimeException("Error al castear la fecha");
        }
    }

    @Override
    public void cuentaCobroUnidad(String c, RegistrarCuentaCobroUnidad registrarCuentaCobroUnidad) {
        Unidad unidad = unidadRepository.findById(UUID.fromString(registrarCuentaCobroUnidad.getIdUnidad())).orElseThrow();
        Instant fechaInicio = castDate(registrarCuentaCobroUnidad.getFechaInicio());
        Instant fechaFin = castDate(registrarCuentaCobroUnidad.getFechaFin());
        Estado estadoPendientePago = estadoRepository.findById(EstadosEnum.PENDIENTE_PAGO.getValue()).orElseThrow();
        cuentaCobroUnidadRepository.findByFechaInicioAndFechaFinAndUnidadId(fechaInicio, fechaFin, unidad.getId())
                .ifPresent(x -> {
                    throw new RuntimeException("Ya existe una cuenta de cobro para la unidad");
                });
        List<Concepto> conceptos = conceptoRepository.findByIdIn(registrarCuentaCobroUnidad.getIdConcepto().stream().map(UUID::fromString).toList());
        createCuentaCobro(unidad, estadoPendientePago, fechaInicio, fechaFin, conceptos);
    }

    @Override
    @Transactional
    public PagoManual200Response pagoManual(String c, PagoManual pagoManual) {
        Pago pago = new Pago();
        CuentaCobroUnidad cuentaCobro = cuentaCobroUnidadRepository.findById(UUID.fromString(pagoManual.getIdCuenta())).orElseThrow();
        MetodoPago metodoPago = metodoPagoRepository.findByNombre(pagoManual.getMetodoPago().getValue()).orElseThrow();

        pago.setIdCuentaCobroUnidad(cuentaCobro);
        pago.setValor(BigDecimal.valueOf(pagoManual.getMontoPagado()));
        pago.setIdMetodoPago(metodoPago);
        pago.setFechaCreacion(Instant.now());
        pago.setFechaActualizacion(Instant.now());
        pagoRepository.save(pago);

        return new PagoManual200Response().id(pago.getId().toString());
    }




}
