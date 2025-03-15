package com.mvp.conjunto.web.rest;

import com.mvp.conjunto.service.WebCuentaCobroService;
import com.mvp.conjunto.web.api.WebCuentaCobroApi;
import com.mvp.conjunto.web.api.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class WebCuentaCobroController implements WebCuentaCobroApi {

    private final WebCuentaCobroService webCuentaCobroService;

    @Override
    public ResponseEntity<PagoManual200Response> pagoManual(String c, PagoManual pagoManual) {
        return ResponseEntity.ok(webCuentaCobroService.pagoManual(c, pagoManual));
    }

    @Override
    public ResponseEntity<Void> cuentaCobroUnidad(String c, RegistrarCuentaCobroUnidad registrarCuentaCobroUnidad) {
        webCuentaCobroService.cuentaCobroUnidad(c, registrarCuentaCobroUnidad);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CuentasCobro> cuentaCobro(Integer page, Integer size, String idUnidad, String estado, String fechaInicio, String fechaFin, String c) {
        return ResponseEntity.ok(webCuentaCobroService.cuentaCobro(page, size, idUnidad, estado, fechaInicio, fechaFin, c));
    }

    @Override
    public ResponseEntity<Void> crearCuentaCobro(String c, RegistrarCuentaCobro registrarCuentaCobro) {
        webCuentaCobroService.crearCuentaCobro(c, registrarCuentaCobro);
        return ResponseEntity.ok().build();
    }
}
