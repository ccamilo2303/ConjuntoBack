package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.WebResidentesApi;
import com.mvp.conjunto.web.api.model.ResidenteEstadoRequest;
import com.mvp.conjunto.web.api.model.ResidenteTipoRequest;
import com.mvp.conjunto.web.api.model.ResidenteUnidad;
import com.mvp.conjunto.web.api.model.Residentes;
import org.springframework.http.ResponseEntity;

public interface WebResidentesService {

    public Residentes residentes(String c, Integer page, Integer size);
    public ResidenteUnidad residenteUnidad(String idResidente, String c, Integer page, Integer size);
    public void residenteEstado(String id, String c, ResidenteEstadoRequest residenteEstadoRequest);
    public void residenteTipo(String id, String c, ResidenteTipoRequest residenteTipoRequest);

}
