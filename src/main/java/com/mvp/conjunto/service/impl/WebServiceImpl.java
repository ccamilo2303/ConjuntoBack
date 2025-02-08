package com.mvp.conjunto.service.impl;

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
    private final FacturaRepository facturaRepository;
    private final AvisoRepository avisoRepository;
    private final NotificacionRepository notificacionRepository;
    //private final ConceptoRepository conceptoRepository;

    @Override
    public Residente residentes(String estadoSolicitud, Integer limit, Integer offset) {

        Residente res = new Residente();
        List<ResidenteSolicitudesInner> restes = new ArrayList<>();

        residenteRepository.findByEstadoSolicitud(estadoSolicitud).ifPresent(residente -> {
            residente.forEach(residenteEntity -> {
                ResidenteSolicitudesInner residenteSolicitudesInner = new ResidenteSolicitudesInner();
                residenteSolicitudesInner.setNombre(residenteEntity.getNombre());
                residenteSolicitudesInner.setEmail(residenteEntity.getEmail());
                residenteSolicitudesInner.setTelefono(residenteEntity.getTelefono());
                residenteSolicitudesInner.setEstadoSolicitud(ResidenteSolicitudesInner.EstadoSolicitudEnum.fromValue(residenteEntity.getEstadoSolicitud()));
                restes.add(residenteSolicitudesInner);
            });
        });
        res.setSolicitudes(restes);
        return res;
    }


    @Override
    @Transactional
    public void residentesIdEstado(Long id, ResidentesIdEstadoRequest residentesIdEstadoRequest){

        residenteRepository.findById(id).ifPresent(residente -> {
            residente.setEstadoSolicitud(residentesIdEstadoRequest.getEstadoSolicitud().getValue());
            residenteRepository.save(residente);
        });

    }

    @Override
    @Transactional
    public PagoManual200Response pagoManual(PagoManualRequest pagoManualRequest){
        PagoEntity pago = new PagoEntity();

        pago.setMetodoPago(pagoManualRequest.getMetodoPago().getValue());
        pago.setMonto(BigDecimal.valueOf(pagoManualRequest.getMontoPagado()));
        pago.setFechaCreacion(Instant.now());
        pago.setIdFactura(facturaRepository.findById(pagoManualRequest.getFacturaId().longValue()).get());
        pago.setIdResidente(residenteRepository.findById(pagoManualRequest.getResidenteId().longValue()).get());

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
        aviso.setFechaCreaction(Instant.now());
        avisoRepository.save(aviso);
    }


    @Override
    @Transactional
    public void notificaciones(NotificacionesRequest notificacionesRequest){

        NotificacionEntity notificaciones = new NotificacionEntity();
        notificaciones.setIdAviso(avisoRepository.findById(notificacionesRequest.getAvisoId().longValue()).get());
        notificaciones.setIdResidente(residenteRepository.findById(notificacionesRequest.getResidenteId().longValue()).get());
        notificaciones.setFechaCreacion(Instant.now());

        notificacionRepository.save(notificaciones);
    }


    public List<Concepto> consultarConcepto(){
        List<Concepto> conceptos = new ArrayList<>();
        /*conceptoRepository.findAll().forEach(conceptoEntity -> {
            Concepto concepto = new Concepto();
            concepto.setId(conceptoEntity.getId());
            concepto.setNombre(conceptoEntity.getNombre());
            concepto.setDescripcion(conceptoEntity.getDescripcion());
            concepto.setValor(conceptoEntity.getValor().floatValue());
            conceptos.add(concepto);
        });*/
        return conceptos;
    }


}
