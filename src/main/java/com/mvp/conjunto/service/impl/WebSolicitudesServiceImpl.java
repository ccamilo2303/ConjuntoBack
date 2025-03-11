package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.Estado;
import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import com.mvp.conjunto.domain.repository.EstadoRepository;
import com.mvp.conjunto.domain.repository.SolicitudRegistroRepository;
import com.mvp.conjunto.domain.repository.SolicitudRegistroSpec;
import com.mvp.conjunto.service.WebSolicitudesService;
import com.mvp.conjunto.service.impl.exception.ApiException;
import com.mvp.conjunto.service.impl.mapper.PageMapper;
import com.mvp.conjunto.service.impl.mapper.SolicitudesMapper;
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

    private final SolicitudRegistroRepository solicitudRegistroRepository;
    private final EstadoRepository estadoRepository;

    private final PageMapper pageMapper;
    private final SolicitudesMapper solicitudesMapper;

    public WebSolicitudesServiceImpl(SolicitudRegistroRepository SolicitudRegistroRepository, PageMapper pageMapper, SolicitudesMapper solicitudesMapper, EstadoRepository estadoRepository) {
        this.solicitudRegistroRepository = SolicitudRegistroRepository;
        this.pageMapper = pageMapper;
        this.solicitudesMapper = solicitudesMapper;
        this.estadoRepository = estadoRepository;
    }


    @Override
    public Solicitud solicitudes(String c, String estadoSolicitud, Integer page, Integer size) {
        log.info("[WebSolicitudesServiceImpl][solicitudes][idConjunto]: {}", c);

        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Page<SolicitudRegistro> solicitudesQuery = solicitudRegistroRepository.findAll(SolicitudRegistroSpec.filterBy(estadoSolicitud, c), pageable);


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

        return solicitud;
    }


    @Transactional
    @Override
    public void solicitudEstado(String id, String c, SolicitudEstadoRequest solicitudEstadoRequest) {

        log.info("[WebSolicitudesServiceImpl][solicitudEstado][idConjunto]: {}", c);

        SolicitudRegistro solicitud = solicitudRegistroRepository.findById(UUID.fromString(id)).orElseThrow(() -> new ApiException("Solicitud no encontrada"));
        Estado estado = estadoRepository.findById(UUID.fromString(solicitudEstadoRequest.getEstadoSolicitud())).orElseThrow(() -> new ApiException("Estado no encontrado"));
        solicitudEstadoRequest.getMotivoRechazo().ifPresent(solicitud::setComentario);
        solicitud.setIdEstado(estado);
        solicitudRegistroRepository.save(solicitud);
    }
}
