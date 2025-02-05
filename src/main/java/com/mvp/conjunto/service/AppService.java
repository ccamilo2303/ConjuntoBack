package com.mvp.conjunto.service;

import com.mvp.conjunto.web.api.model.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface AppService {


    public List<FacturaResponse> residentesFacturas();


    public ResidenteRegistroResponse residentesRegistro(ResidenteRegistroRequest residenteRegistroRequest) ;


    public UsuariosEstadoCuenta200Response usuariosEstadoCuenta();


    public UsuariosHistorialPagos200Response usuariosHistorialPagos(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) ;


    public HistorialAvisosResponse avisosHistorial(LocalDate fechaInicio, LocalDate fechaFin, Integer limit, Integer offset) ;


    public ConjuntosRes conjuntos(String nombre, String ubicacion, String estado, Integer limit, Integer offset);
}
