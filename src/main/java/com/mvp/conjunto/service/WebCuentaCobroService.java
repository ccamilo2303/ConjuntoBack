package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.model.*;

public interface WebCuentaCobroService {

    PagoManual200Response pagoManual(String c, PagoManual pagoManual);
    void cuentaCobroUnidad(String c, RegistrarCuentaCobroUnidad registrarCuentaCobroUnidad);
    CuentasCobro cuentaCobro(Integer page, Integer size, String idUnidad, String estado, String fechaInicio, String fechaFin, String c);
    void crearCuentaCobro(String c, RegistrarCuentaCobro registrarCuentaCobro);

}
