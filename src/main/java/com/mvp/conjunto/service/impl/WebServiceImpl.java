package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.*;
import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.WebService;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WebServiceImpl implements WebService {

    private final ResidenteRepository residenteRepository;
    private final PagoRepository pagoRepository;
    private final FacturaunidadRepository facturaunidadRepository;
    private final AvisoRepository avisoRepository;
    private final NotificacionRepository notificacionRepository;
    private final EstadoResidenteRepository estadoResidenteRepository;
    private final UnidadRepository unidadRepository;
    private final ConceptoRepository conceptoRepository;
    private final SolicitudRepository solicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    @Override
    public Residente residentes(String estadoSolicitud, Integer limit, Integer offset) {

        Residente res = new Residente();
        List<ResidenteSolicitudesInner> restes = new ArrayList<>();
        solicitudRepository.findByIdEstado(estadoSolicitudRepository.findByEstado(estadoSolicitud)).ifPresent(solicitud -> {
            solicitud.forEach(solicitudEntity -> {
                ResidenteEntity residente = solicitudEntity.getIdResidente();


                ResidenteSolicitudesInner residenteSolicitudesInner = new ResidenteSolicitudesInner();
                residenteSolicitudesInner.setNombre(residente.getNombre());
                residenteSolicitudesInner.setEmail(residente.getEmail());
                residenteSolicitudesInner.setTelefono(residente.getTelefono());
                residenteSolicitudesInner.setEstadoSolicitud(ResidenteSolicitudesInner.EstadoSolicitudEnum.fromValue(solicitudEntity.getIdEstado().getEstado()));

                restes.add(residenteSolicitudesInner);
            });
        });


        //res.setSolicitudes(restes);
        return res;
    }


    @Override
    @Transactional
    public void residentesIdEstado(Long id, ResidentesIdEstadoRequest residentesIdEstadoRequest){

        residenteRepository.findById(id).ifPresent(residente -> {

            estadoResidenteRepository.findById(Long.valueOf(residentesIdEstadoRequest.getEstadoSolicitud().getValue()))
                    .ifPresent(estadoResidenteEntity -> {
                residente.setIdEstado(estadoResidenteEntity);
                residenteRepository.save(residente);
            });


        });

    }

    @Override
    @Transactional
    public PagoManual200Response pagoManual(PagoManualRequest pagoManualRequest){
        PagoEntity pago = new PagoEntity();

        pago.setMetodoPago(pagoManualRequest.getMetodoPago().getValue());
        pago.setValor(BigDecimal.valueOf(pagoManualRequest.getMontoPagado()));
        pago.setFechaCreacion(Instant.now());
        pago.setIdFacturaUnidad(facturaunidadRepository.findById(pagoManualRequest.getFacturaId().longValue()).get());


        pagoRepository.save(pago);

        PagoManual200Response pagoManual200Response = new PagoManual200Response();
        pagoManual200Response.setId(pago.getId());

        return pagoManual200Response;

    }


    @Override
    @Transactional
    public void avisos(AvisosRequest avisosRequest){
        AvisoEntity aviso = new AvisoEntity();
        aviso.setTitulo(avisosRequest.getTitulo());
        aviso.setContenido(avisosRequest.getContenido());
        aviso.setFechaCreacion(Instant.now());
        avisoRepository.save(aviso);
    }


    @Override
    @Transactional
    public void notificaciones(NotificacionesRequest notificacionesRequest){

        NotificacionEntity notificaciones = new NotificacionEntity();
        notificaciones.setIdAviso(avisoRepository.findById(notificacionesRequest.getAvisoId().longValue()).get());
        notificaciones.setIdUnidad(unidadRepository.findById(notificacionesRequest.getUnidadId().longValue()).get());
        notificaciones.setFechaCreacion(Instant.now());

        notificacionRepository.save(notificaciones);
    }


    public List<Concepto> consultarConcepto(){
        List<Concepto> conceptos = new ArrayList<>();
        conceptoRepository.findAll().forEach(conceptoEntity -> {
            Concepto concepto = new Concepto();
            concepto.setId(conceptoEntity.getId());
            concepto.setNombre(conceptoEntity.getNombre());
            concepto.setValor(conceptoEntity.getValor().doubleValue());
            conceptos.add(concepto);
        });
        return conceptos;
    }

    @Override
    @Transactional
    public void crearConcepto(Concepto concepto){
        ConceptoEntity conceptoEntity = new ConceptoEntity();
        conceptoEntity.setNombre(concepto.getNombre());
        conceptoEntity.setValor(BigDecimal.valueOf(concepto.getValor()));
        conceptoRepository.save(conceptoEntity);
    }

    @Override
    @Transactional
    public void actualizarConcepto(Integer id, Concepto concepto){
        conceptoRepository.findById(id.longValue()).ifPresent(conceptoEntity -> {
            conceptoEntity.setNombre(concepto.getNombre());
            conceptoEntity.setValor(BigDecimal.valueOf(concepto.getValor()));
            conceptoRepository.save(conceptoEntity);
        });
    }


}
