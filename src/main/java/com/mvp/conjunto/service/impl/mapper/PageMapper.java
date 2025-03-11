package com.mvp.conjunto.service.impl.mapper;

import com.mvp.conjunto.domain.entity.SolicitudRegistro;
import com.mvp.conjunto.web.api.model.Pages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(target = "size", source = "size")
    @Mapping(target = "page", source = "number")
    @Mapping(target = "nextPage", expression = "java(query.hasNext() ? query.nextPageable().getPageNumber() : null)")
    Pages mapPage(Page<?> query);

}
