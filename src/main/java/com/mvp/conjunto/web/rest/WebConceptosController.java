package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebConceptosService;
import com.mvp.conjunto.web.api.WebConceptosApi;
import com.mvp.conjunto.web.api.model.ConceptoCrear;
import com.mvp.conjunto.web.api.model.Conceptos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebConceptosController implements WebConceptosApi {

    private final WebConceptosService webConceptosService;

    @Override
    public ResponseEntity<Void> actualizarConcepto(String c, String id, ConceptoCrear conceptoCrear) {
        webConceptosService.actualizarConcepto(c, id, conceptoCrear);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Conceptos> consultarConcepto(String c) {
        return ResponseEntity.ok(webConceptosService.consultarConcepto(c));
    }

    @Override
    public ResponseEntity<Void> crearConcepto(String c, ConceptoCrear conceptoCrear) {
        webConceptosService.crearConcepto(conceptoCrear, c);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> eliminarConcepto(String id, String c) {
        webConceptosService.eliminarConcepto(id, c);
        return ResponseEntity.ok().build();
    }
}
