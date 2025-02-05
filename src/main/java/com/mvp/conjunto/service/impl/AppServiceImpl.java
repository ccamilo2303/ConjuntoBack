package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.ResidenteEntity;
import com.mvp.conjunto.domain.repository.ConjuntoRepository;
import com.mvp.conjunto.domain.repository.ResidenteRepository;
import com.mvp.conjunto.service.AppService;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AppServiceImpl implements AppService {

    private final ConjuntoRepository conjuntoRepository;
    private final ResidenteRepository residenteRepository;

    @Override
    public List<FacturaResponse> residentesFacturas() {
        return null;
    }

    @Override
    public ResidenteRegistroResponse residentesRegistro(ResidenteRegistroRequest residenteRegistroRequest) {

        residenteRepository.findByEmail(residenteRegistroRequest.getEmail()).ifPresent(residente -> {
            throw new RuntimeException("El email ya se encuentra registrado");
        });

        ResidenteEntity residente = new ResidenteEntity();
        residente.setNombre(residenteRegistroRequest.getNombre());
        residente.setEmail(residenteRegistroRequest.getEmail());
        residente.setTelefono(residenteRegistroRequest.getTelefono());
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
        return null;
    }

    @Override
    public UsuariosHistorialPagos200Response usuariosHistorialPagos(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        return null;
    }

    @Override
    public HistorialAvisosResponse avisosHistorial(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) {
        return null;
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
