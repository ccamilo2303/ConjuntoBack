package com.mvp.conjunto.service.impl.enums;

import java.util.UUID;

public enum EstadosEnum {

    PENDIENTE_PAGO(UUID.fromString("692b6a30-a9c9-4947-b044-f17765355d68"));

    private final UUID value;

    EstadosEnum(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

}
