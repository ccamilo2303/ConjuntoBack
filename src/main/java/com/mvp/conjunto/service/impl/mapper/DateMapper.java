package com.mvp.conjunto.service.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

//@Mapper(componentModel = "spring")
public interface DateMapper {


    public default String mapDate(Instant date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                .withZone(ZoneId.systemDefault());
        return formatter.format(date);
    }

}
