package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.model.ConceptoCrear;
import com.mvp.conjunto.web.api.model.Conceptos;

public interface WebConceptosService {

    void actualizarConcepto(String c, String id, ConceptoCrear conceptoCrear);
    Conceptos consultarConcepto(String c);
    void crearConcepto(ConceptoCrear conceptoCrear, String c);
    void eliminarConcepto(String id, String c);

}
