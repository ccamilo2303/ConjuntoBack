package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebUnidadesService;
import com.mvp.conjunto.web.api.WebUnidadesApi;
import com.mvp.conjunto.web.api.model.ResidentesUnidad;
import com.mvp.conjunto.web.api.model.UnidadConceptos;
import com.mvp.conjunto.web.api.model.UnidadCuentasCobro;
import com.mvp.conjunto.web.api.model.UnidadesConjunto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebUnidadesController implements WebUnidadesApi {

    private final WebUnidadesService webUnidadesService;

    @Override
    public ResponseEntity<Void> eliminaUnidadResidente(String idResidenteUnidad, String c) {
        webUnidadesService.eliminaUnidadResidente(idResidenteUnidad, c);
        return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<Void> unidadConcepto(String idUnidad, String idConcepto, String c) {
        webUnidadesService.unidadConcepto(idUnidad, idConcepto, c);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UnidadCuentasCobro> unidadCuentasCobro(String idUnidad, String c, Integer page, Integer size) {
        return ResponseEntity.ok(webUnidadesService.unidadCuentasCobro(idUnidad, c, page, size));
    }

    @Override
    public ResponseEntity<Void> unidadResidente(String idUnidad, String idResidente, String c) {
        webUnidadesService.unidadResidente(idUnidad, idResidente, c);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResidentesUnidad> unidadResidentes(String idUnidad, String c, Integer page, Integer size) {
        return ResponseEntity.ok(webUnidadesService.unidadResidentes(idUnidad, c, page, size));
    }

    @Override
    public ResponseEntity<UnidadConceptos> unidadeConceptos(String idUnidad, String c, Integer page, Integer size) {
        return ResponseEntity.ok(webUnidadesService.unidadeConceptos(idUnidad, c, page, size));
    }

    @Override
    public ResponseEntity<UnidadesConjunto> unidades(String c, Integer page, Integer size) {
        return ResponseEntity.ok(webUnidadesService.unidades(c, page, size));
    }
}
