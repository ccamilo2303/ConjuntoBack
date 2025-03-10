package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import com.mvp.conjunto.domain.repository.SolicitudRegistroRepository;
import com.mvp.conjunto.domain.repository.SolicitudRegistroSpec;
import com.mvp.conjunto.service.WebSolicitudesService;
import com.mvp.conjunto.service.mapper.PageMapper;
import com.mvp.conjunto.service.mapper.SolicitudesMapper;
import com.mvp.conjunto.web.api.model.Solicitud;
import com.mvp.conjunto.web.api.model.SolicitudEstadoRequest;
import com.mvp.conjunto.web.api.model.SolicitudSolicitudesInner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class WebSolicitudesServiceImpl implements WebSolicitudesService {

    private final SolicitudRegistroRepository SolicitudRegistroRepository;

    private final PageMapper pageMapper;
    private final SolicitudesMapper solicitudesMapper;

    public WebSolicitudesServiceImpl(SolicitudRegistroRepository SolicitudRegistroRepository, PageMapper pageMapper, SolicitudesMapper solicitudesMapper) {
        this.SolicitudRegistroRepository = SolicitudRegistroRepository;
        this.pageMapper = pageMapper;
        this.solicitudesMapper = solicitudesMapper;
    }


    @Override
    public Solicitud solicitudes(String c, String estadoSolicitud, Integer page, Integer size) {

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<SolicitudRegistro> solicitudesQuery = SolicitudRegistroRepository.findAll(SolicitudRegistroSpec.filterBy(estadoSolicitud, c), pageable);


        return mapResponse(solicitudesQuery);

    }

    private Solicitud mapResponse(Page<SolicitudRegistro> solicitudesQuery){
        Solicitud solicitud = new Solicitud();
        List<SolicitudSolicitudesInner> solicitudes = new ArrayList<>();

        solicitudesQuery.getContent()
                .stream()
                .map(solicitudesMapper::mapSolicitud)
                .forEach(solicitudes::add);

        solicitud.setPages(pageMapper.mapPage(solicitudesQuery));
        solicitud.setSolicitudes(solicitudes);
        log.info("Solicitudes encontradas: {}", solicitudesQuery.getTotalElements());

        return solicitud;
    }


    @Transactional
    @Override
    public void solicitudEstado(String id, String c, SolicitudEstadoRequest solicitudEstadoRequest) {
        //SolicitudRegistro solicitud = SolicitudRegistroRepository.findById(id.longValue()).orElseThrow(() -> new ApiException("Solicitud no encontrada"));
        /*EstadoSolicitudEntity estadoSolicitudEntity = estadoSolicitudRepository.findById(solicitudEstadoRequest.getEstadoSolicitud().getValue().longValue()).orElseThrow(() -> new ApiException("Estado no encontrado"));
        solicitudEstadoRequest.getMotivoRechazo().ifPresent(solicitud::setComentario);
        solicitud.setIdEstado(estadoSolicitudEntity);
        solicitudRepository.save(solicitud);*/

    }
}
