package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.Administrador;
import com.mvp.conjunto.domain.entity.Conjunto;
import com.mvp.conjunto.domain.repository.AdministradorRepository;
import com.mvp.conjunto.service.WebConjuntosService;
import com.mvp.conjunto.web.api.model.ConjuntosRes;
import com.mvp.conjunto.web.api.model.ConjuntosResConjuntosInner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebConjuntosServiceImpl implements WebConjuntosService {

    private final AuthService authService;
    private final AdministradorRepository administradorRepository;

    @Override
    public ConjuntosRes conjuntosDisponibles() {
        log.info("[conjuntosDisponibles] Get conjuntos disponibles: {}", authService.getCurrentUserUid());
        Administrador administrador = administradorRepository.findByIdFirebase(authService.getCurrentUserUid()).orElseThrow();
        ConjuntosRes res = new ConjuntosRes();
        List<ConjuntosResConjuntosInner> conjuntos = new ArrayList<>(administrador.getAdministradorConjuntos().stream().map(x -> {
            ConjuntosResConjuntosInner conjuntosResConjuntosInner = new ConjuntosResConjuntosInner();
            Conjunto conjunto = x.getIdConjunto();
            conjuntosResConjuntosInner.setDireccion(conjunto.getDireccion());
            conjuntosResConjuntosInner.setNombre(conjunto.getNombre());
            conjuntosResConjuntosInner.setId(conjunto.getId().toString());
            conjuntosResConjuntosInner.setEstado(conjunto.getIdEstado().getNombre());
            conjuntosResConjuntosInner.setUbicacion(conjunto.getCiudad());
            return conjuntosResConjuntosInner;
        }).toList());

        res.setConjuntos(conjuntos);
        return res;
    }
}
