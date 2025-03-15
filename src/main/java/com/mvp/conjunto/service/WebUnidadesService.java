package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.model.ResidentesUnidad;
import com.mvp.conjunto.web.api.model.UnidadConceptos;
import com.mvp.conjunto.web.api.model.UnidadCuentasCobro;
import com.mvp.conjunto.web.api.model.UnidadesConjunto;

public interface WebUnidadesService {

    void eliminaUnidadResidente(String idResidenteUnidad, String c);

    void unidadConcepto(String idUnidad, String idConcepto, String c);

    UnidadCuentasCobro unidadCuentasCobro(String idUnidad, String c, Integer page, Integer size);

    void unidadResidente(String idUnidad, String idResidente, String c);

    ResidentesUnidad unidadResidentes(String idUnidad, String c, Integer page, Integer size);

    UnidadConceptos unidadeConceptos(String idUnidad, String c, Integer page, Integer size);

    UnidadesConjunto unidades(String c, Integer page, Integer size);

}
