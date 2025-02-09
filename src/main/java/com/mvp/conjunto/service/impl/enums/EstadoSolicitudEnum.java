package com.mvp.conjunto.service.impl.enums;

public enum EstadoSolicitudEnum {

    PENDIENTE(1);

    private final int value;

    EstadoSolicitudEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
