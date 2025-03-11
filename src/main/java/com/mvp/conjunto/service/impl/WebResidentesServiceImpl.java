package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.Estado;
import com.mvp.conjunto.domain.entity.ResidenteConjunto;
import com.mvp.conjunto.domain.entity.TipoResidente;
import com.mvp.conjunto.domain.repository.*;
import com.mvp.conjunto.service.WebResidentesService;
import com.mvp.conjunto.service.impl.mapper.PageMapper;
import com.mvp.conjunto.service.impl.mapper.ResidenteMapper;
import com.mvp.conjunto.web.api.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class WebResidentesServiceImpl implements WebResidentesService {

    private final ResidenteUnidadRepository residenteUnidadRepository;
    private final ResidenteConjuntoRepository residenteConjuntoRepository;
    private final ResidenteRepository residenteRepository;
    private final EstadoRepository estadoRepository;
    private final TipoResidenteRepository tipoResidenteRepository;
    private final ResidenteMapper residenteMapper;
    private final PageMapper pageMapper;

    public WebResidentesServiceImpl(ResidenteConjuntoRepository residenteConjuntoRepository, ResidenteMapper residenteMapper, PageMapper pageMapper, ResidenteUnidadRepository residenteUnidadRepository, ResidenteRepository residenteRepository, EstadoRepository estadoRepository, TipoResidenteRepository tipoResidenteRepository) {
        this.residenteConjuntoRepository = residenteConjuntoRepository;
        this.residenteMapper = residenteMapper;
        this.pageMapper = pageMapper;
        this.residenteUnidadRepository = residenteUnidadRepository;
        this.residenteRepository = residenteRepository;
        this.estadoRepository = estadoRepository;
        this.tipoResidenteRepository = tipoResidenteRepository;
    }

    @Override
    public Residentes residentes(String c, Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idConjunto.id", UUID.fromString(c));

        Page<ResidenteConjunto> residenteQuery = residenteConjuntoRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable);

        log.info("[residentes][residenteQuery]: {}", residenteQuery.getContent());

        return mapResponseResidente(residenteQuery);
    }

    private Residentes mapResponseResidente(Page<ResidenteConjunto> residenteQuery){
        Residentes residentes = new Residentes();
        List<Residente> residente = new ArrayList<>();
        residenteQuery.getContent().forEach(residenteConjunto -> {
            residente.add(residenteMapper.mapResidente(residenteConjunto.getIdResidente()));
        });
        residentes.setResidentes(residente);
        residentes.setPages(pageMapper.mapPage(residenteQuery));
        return residentes;
    }


    @Override
    public ResidenteUnidad residenteUnidad(String idResidente, String c, Integer page, Integer size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idResidente.id", UUID.fromString(idResidente));
        filtros.put("idUnidad.idConjunto.id", UUID.fromString(c));
        Page<com.mvp.conjunto.domain.entity.ResidenteUnidad> residenteQuery = residenteUnidadRepository.findAll(SolicitudRegistroSpec.filterBy(filtros), pageable);

        log.info("[residenteUnidad][residenteQuery]: {}", residenteQuery.getContent());

        return mapResponseResidenteUnidad(residenteQuery);
    }

    private ResidenteUnidad mapResponseResidenteUnidad(Page<com.mvp.conjunto.domain.entity.ResidenteUnidad> residenteQuery){
        ResidenteUnidad residenteUnidad = new ResidenteUnidad();
        List<Unidad> unidades = new ArrayList<>();
        residenteQuery.getContent().forEach(x -> {
            unidades.add(residenteMapper.mapUnidad(x.getIdUnidad()));
        });
        residenteUnidad.setUnidades(unidades);
        residenteUnidad.setPages(pageMapper.mapPage(residenteQuery));
        return residenteUnidad;
    }

    @Override
    public void residenteEstado(String id, String c, ResidenteEstadoRequest residenteEstadoRequest) {
        com.mvp.conjunto.domain.entity.ResidenteConjunto residenteConjunto = residenteConjuntoRepository.findByIdConjuntoAndIdResidente(UUID.fromString(c), UUID.fromString(id)).orElseThrow();
        Estado estado = estadoRepository.findById(UUID.fromString(residenteEstadoRequest.getIdEstado())).orElseThrow();
        log.info("[residenteEstado][residente] : {}", residenteConjunto);

        residenteConjunto.setIdEstado(estado);
        residenteConjuntoRepository.save(residenteConjunto);
    }

    @Override
    public void residenteTipo(String id, String c, ResidenteTipoRequest residenteTipoRequest) {
        com.mvp.conjunto.domain.entity.ResidenteConjunto residenteConjunto = residenteConjuntoRepository.findByIdConjuntoAndIdResidente(UUID.fromString(c), UUID.fromString(id)).orElseThrow();
        TipoResidente tipoResidente = tipoResidenteRepository.findById(UUID.fromString(residenteTipoRequest.getIdTipo())).orElseThrow();

    }
}
