package com.mvp.conjunto.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import com.mvp.conjunto.domain.entity.ResidenteEntity;
import com.mvp.conjunto.domain.entity.ResidenteUnidadEntity;
import com.mvp.conjunto.domain.entity.SolicitudEntity;
import com.mvp.conjunto.domain.entity.UnidadEntity;
import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.AppService;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppServiceImpl implements AppService {

    private final TipoResidenteRepository tipoResidenteRepository;

    private final ConjuntoRepository conjuntoRepository;
    private final ResidenteRepository residenteRepository;
    private final FacturaunidadRepository facturaunidadRepository;
    private final PagoRepository pagoRepository;
    private final AvisoRepository avisoRepository;
    private final UnidadRepository unidadRepository;
    private final EstadoResidenteRepository estadoResidenteRepository;
    private final SolicitudRepository solicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;
    private final ResidenteUnidadRepository residenteUnidadRepository;
    private final EstadoResidenteUnidadRepository estadoResidenteUnidadRepository;

    private final AuthService authService;



    @Override
    public List<FacturaResponse> residentesFacturas() {
        List<FacturaResponse> res = new ArrayList<>();
        log.info("Obteniendo facturas de residentes");
        facturaunidadRepository.findAll().forEach(factura -> {
            FacturaResponse facturaResponse = new FacturaResponse();
            facturaResponse.setId(factura.getId());
            facturaResponse.setTotal(factura.getTotal().floatValue());
            facturaResponse.setEstado(factura.getIdEstado().getEstado());
            facturaResponse.setConceptos(factura.getConceptoFacturas().stream().map(conceptoFactura -> {
                ConceptoFactura facturaResponseConceptosInner = new ConceptoFactura();
                facturaResponseConceptosInner.setConcepto(conceptoFactura.getIdConcepto().getNombre());
                facturaResponseConceptosInner.setMonto(conceptoFactura.getValor());
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

        residente.setIdConjunto(conjuntoRepository.findById(residenteRegistroRequest.getConjunto().longValue())
                .orElseThrow(() -> new RuntimeException("Conjunto no encontrado")));

        Optional<UnidadEntity> unidad =  unidadRepository.findByInteriorAndAptoAndIdConjunto(residenteRegistroRequest.getInterior().intValue(), residenteRegistroRequest.getApto().intValue(), residente.getIdConjunto());
        unidad.orElseThrow(() -> new RuntimeException("Unidad no encontrada"));

        residente.setIdTipo(tipoResidenteRepository.findByTipo(residenteRegistroRequest.getTipo().getValue()).orElseThrow(() -> new RuntimeException("Tipo de residente no encontrado")));

        residente.setNombre(residenteRegistroRequest.getNombre());
        residente.setEmail(residenteRegistroRequest.getEmail());
        residente.setTelefono(residenteRegistroRequest.getTelefono());
        residente.setIdEstado(estadoResidenteRepository.findById(2L).get());


        ResidenteUnidadEntity residenteUnidad = new ResidenteUnidadEntity();
        residenteUnidad.setIdResidente(residente);
        residenteUnidad.setIdUnidad(unidad.get());
        residenteUnidad.setFechaCreacion(Instant.now());
        residenteUnidad.setIdEstado(estadoResidenteUnidadRepository.findById(2L).get());
        residente.getResidenteUnidads().add(residenteUnidad);

        residente.setFechaCreacion(Instant.now());

        Integer id = residenteRepository.save(residente).getId();
        ResidenteRegistroResponse residenteRegistroResponse = new ResidenteRegistroResponse();
        residenteRegistroResponse.setId(id);

        residenteUnidadRepository.save(residenteUnidad);

        SolicitudEntity solicitudEntity = new SolicitudEntity();
        solicitudEntity.setIdResidente(residente);
        solicitudEntity.setComentario("Registro de residente");
        solicitudEntity.setDescripcion("Pendiente");
        solicitudEntity.setFechaCreacion(Instant.now());
        solicitudEntity.setIdEstado(estadoSolicitudRepository.findById(1L).get());

        solicitudRepository.save(solicitudEntity);

        return residenteRegistroResponse;
    }

    @Override
    public UsuariosEstadoCuenta200Response usuariosEstadoCuenta() {
        UsuariosEstadoCuenta200Response usuariosEstadoCuenta200Response = new UsuariosEstadoCuenta200Response();


        facturaunidadRepository.resumenSaldo(1L).ifPresent(resumenSaldoModels -> {
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

        residenteRepository.findById(1L).ifPresent(residente -> {
            residente.getResidenteUnidads()
                    .stream()
                    .forEach(x -> {
                        x.getIdUnidad().getFacturaUnidads().stream().forEach(factura -> {
                            pagoRepository.findByIdFacturaUnidad(factura).ifPresent(pagos -> {
                                usuariosHistorialPagos200Response.setTotal(pagos.size());
                                usuariosHistorialPagos200Response.setPagos(pagos.stream().map(pago -> {
                                    UsuariosHistorialPagos200ResponsePagosInner usuariosHistorialPagos200ResponsePagosInner = new UsuariosHistorialPagos200ResponsePagosInner();
                                    usuariosHistorialPagos200ResponsePagosInner.setId(pago.getId());
                                    usuariosHistorialPagos200ResponsePagosInner.setMonto(pago.getValor().floatValue());
                                    usuariosHistorialPagos200ResponsePagosInner.setMetodoPago(pago.getMetodoPago());
                                    //usuariosHistorialPagos200ResponsePagosInner.setFechaPago(pago.getFechaCreacion());
                                    usuariosHistorialPagos200ResponsePagosInner.setFacturaId(pago.getIdFacturaUnidad().getId());
                                    return usuariosHistorialPagos200ResponsePagosInner;
                                }).toList());
                            });
                        });
                    });
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
        log.info("Info del usuario: {}", authService.getCurrentUserUid());




        Pageable pageable = Pageable.ofSize(limit).withPage(offset);
        ConjuntosRes res = new ConjuntosRes();
        res.setConjuntos(conjuntoRepository.findAll(pageable).stream().map(conjunto -> {
            ConjuntosResConjuntosInner conjuntosResConjuntosInner = new ConjuntosResConjuntosInner();
            conjuntosResConjuntosInner.setId(conjunto.getId());
            conjuntosResConjuntosInner.setNombre(conjunto.getNombre());
            conjuntosResConjuntosInner.setUbicacion(conjunto.getUbicacion());
            conjuntosResConjuntosInner.setEstado(ConjuntosResConjuntosInner.EstadoEnum.fromValue(conjunto.getIdEstadoConjunto().getEstado()));
            //conjuntosResConjuntosInner.setFechaCreacion(conjunto.getFechaCreacion());
            return conjuntosResConjuntosInner;
        }).toList());

        res.setTotal(res.getConjuntos().size());

        return res;
    }
}
