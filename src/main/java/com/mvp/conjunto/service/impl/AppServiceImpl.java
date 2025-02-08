package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.AppService;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppServiceImpl implements AppService {

    private final ConjuntoRepository conjuntoRepository;
    private final ResidenteRepository residenteRepository;
    private final FacturaRepository facturaRepository;
    private final PagoRepository pagoRepository;
    private final AvisoRepository avisoRepository;

    @Override
    public List<FacturaResponse> residentesFacturas() {
        List<FacturaResponse> res = new ArrayList<>();
        log.info("Obteniendo facturas de residentes");
        facturaRepository.findAll().forEach(factura -> {
            FacturaResponse facturaResponse = new FacturaResponse();
            facturaResponse.setId(factura.getId());
            facturaResponse.setTotal(factura.getTotal().floatValue());
            facturaResponse.setEstado(factura.getEstado());
            facturaResponse.setConceptos(factura.getConceptoFacturas().stream().map(conceptoFactura -> {
                ConceptoFactura facturaResponseConceptosInner = new ConceptoFactura();
                facturaResponseConceptosInner.setConcepto(conceptoFactura.getConcepto());
                facturaResponseConceptosInner.setMonto(BigDecimal.valueOf(conceptoFactura.getValor()));
                return facturaResponseConceptosInner;
            }).toList());

            res.add(facturaResponse);
        });
        return res;
    }

    @Override
    public ResidenteRegistroResponse residentesRegistro(ResidenteRegistroRequest residenteRegistroRequest) {

        residenteRepository.findByEmail(residenteRegistroRequest.getEmail()).ifPresent(residente -> {
            throw new RuntimeException("El email ya se encuentra registrado");
        });

        ResidenteEntity residente = new ResidenteEntity();
        residente.setNombre(residenteRegistroRequest.getNombre());
        residente.setEmail(residenteRegistroRequest.getEmail());
        //residente.setTelefono(residenteRegistroRequest.getTelefono());
        residente.setConjunto(residente.getConjunto());
        residente.setApto(residenteRegistroRequest.getApto().intValue());
        residente.setInterior(residenteRegistroRequest.getInterior().intValue());
        residente.setTipo(residenteRegistroRequest.getTipo().getValue());
        residente.setFechaCreacion(Instant.now());

        Integer id = residenteRepository.save(residente).getId();
        ResidenteRegistroResponse residenteRegistroResponse = new ResidenteRegistroResponse();
        residenteRegistroResponse.setId(id);
        return residenteRegistroResponse;
    }

    @Override
    public UsuariosEstadoCuenta200Response usuariosEstadoCuenta() {
        UsuariosEstadoCuenta200Response usuariosEstadoCuenta200Response = new UsuariosEstadoCuenta200Response();
        facturaRepository.resumenSaldo(1L).ifPresent(resumenSaldoModels -> {
            resumenSaldoModels.forEach(resumenSaldoModel -> {
                usuariosEstadoCuenta200Response.setUsuarioId(resumenSaldoModel.getId());
                usuariosEstadoCuenta200Response.setNombre(resumenSaldoModel.getNombre());
                usuariosEstadoCuenta200Response.setEstado(resumenSaldoModel.getEstado());
                usuariosEstadoCuenta200Response.setSaldoAFavor(resumenSaldoModel.getSaldoFavor());
                usuariosEstadoCuenta200Response.setSaldoEnMora(resumenSaldoModel.getSaldoMora());
                usuariosEstadoCuenta200Response.setTotalDeuda(null);
            });
        });
        return usuariosEstadoCuenta200Response;
    }

    @Override
    public UsuariosHistorialPagos200Response usuariosHistorialPagos(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        UsuariosHistorialPagos200Response usuariosHistorialPagos200Response = new UsuariosHistorialPagos200Response();
        pagoRepository.findByIdResidente(1L).ifPresent(pagos -> {
            usuariosHistorialPagos200Response.setTotal(pagos.size());
            usuariosHistorialPagos200Response.setPagos(pagos.stream().map(pago -> {
                UsuariosHistorialPagos200ResponsePagosInner usuariosHistorialPagos200ResponsePagosInner = new UsuariosHistorialPagos200ResponsePagosInner();
                usuariosHistorialPagos200ResponsePagosInner.setId(pago.getId());
                usuariosHistorialPagos200ResponsePagosInner.setMonto(pago.getMonto().floatValue());
                usuariosHistorialPagos200ResponsePagosInner.setMetodoPago(pago.getMetodoPago());
                //usuariosHistorialPagos200ResponsePagosInner.setFechaPago(pago.getFechaCreacion());
                usuariosHistorialPagos200ResponsePagosInner.setFacturaId(pago.getIdFactura().getId());
                return usuariosHistorialPagos200ResponsePagosInner;
            }).toList());
        });
        return usuariosHistorialPagos200Response;
    }

    @Override
    public HistorialAvisosResponse avisosHistorial(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        HistorialAvisosResponse historialAvisosResponse = new HistorialAvisosResponse();
        List<Aviso> avisos = new ArrayList<>();
        avisoRepository.findAll().forEach(avisoRes -> {
            Aviso aviso = new Aviso();
            aviso.setId(avisoRes.getId());
            aviso.setTitulo(avisoRes.getTitulo());
            aviso.setContenido(avisoRes.getContenido());
            avisos.add(aviso);
        });
        historialAvisosResponse.setAvisos(avisos);
        historialAvisosResponse.setTotal(avisos.size());
        return historialAvisosResponse;
    }

    @Override
    public ConjuntosRes conjuntos(String nombre, String ubicacion, String estado, Integer limit, Integer offset) {
        Pageable pageable = Pageable.ofSize(limit).withPage(offset);
        ConjuntosRes res = new ConjuntosRes();
        res.setConjuntos(conjuntoRepository.findAll(pageable).stream().map(conjunto -> {
            ConjuntosResConjuntosInner conjuntosResConjuntosInner = new ConjuntosResConjuntosInner();
            conjuntosResConjuntosInner.setId(conjunto.getId());
            conjuntosResConjuntosInner.setNombre(conjunto.getNombre());
            conjuntosResConjuntosInner.setUbicacion(conjunto.getUbicacion());
            //conjuntosResConjuntosInner.setEstado(conjunto.getEstado());
            //conjuntosResConjuntosInner.setFechaCreacion(conjunto.getFechaCreacion());
            return conjuntosResConjuntosInner;
        }).toList());

        res.setTotal(res.getConjuntos().size());

        return res;
    }
}
