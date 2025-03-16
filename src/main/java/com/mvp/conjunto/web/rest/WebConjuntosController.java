package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebConjuntosService;
import com.mvp.conjunto.web.api.WebConjuntosApi;
import com.mvp.conjunto.web.api.model.ConjuntosRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebConjuntosController implements WebConjuntosApi {

    private  final WebConjuntosService webConjuntosService;

    @Override
    public ResponseEntity<ConjuntosRes> conjuntosDisponibles() {
        return ResponseEntity.ok(webConjuntosService.conjuntosDisponibles());
    }
}
