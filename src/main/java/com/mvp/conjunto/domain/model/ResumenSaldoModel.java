package com.mvp.conjunto.domain.model;

import lombok.Data;

@Data
public class ResumenSaldoModel {

    private String nombre;
    private Integer id;
    private String estado;
    private Float saldoFavor;
    private Float saldoMora;

}
