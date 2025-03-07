package com.mvp.conjunto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSolicitudesServiceImpl {/*implements WebSolicitudesService {

    private final SolicitudRepository solicitudRepository;
    private final EstadoSolicitudRepository estadoSolicitudRepository;

    @Override
    public Solicitud solicitudes(String c, Integer estadoSolicitud, Integer page, Integer size) {

        //solicitudRepository.findByIdConjunto()

        return null;
    }


    @Transactional
    @Override
    public void solicitudEstado(Integer id, String c, SolicitudEstadoRequest solicitudEstadoRequest) {
        SolicitudEntity solicitud = solicitudRepository.findById(id.longValue()).orElseThrow(() -> new ApiException("Solicitud no encontrada"));
        EstadoSolicitudEntity estadoSolicitudEntity = estadoSolicitudRepository.findById(solicitudEstadoRequest.getEstadoSolicitud().getValue().longValue()).orElseThrow(() -> new ApiException("Estado no encontrado"));
        solicitudEstadoRequest.getMotivoRechazo().ifPresent(solicitud::setComentario);
        solicitud.setIdEstado(estadoSolicitudEntity);
        solicitudRepository.save(solicitud);

    }*/
}
