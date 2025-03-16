package com.mvp.conjunto.service.impl;

import com.mvp.conjunto.domain.entity.Concepto;
import com.mvp.conjunto.domain.entity.ConceptoConjunto;
import com.mvp.conjunto.domain.entity.Conjunto;
import com.mvp.conjunto.domain.repository.ConceptoConjuntoRepository;
import com.mvp.conjunto.domain.repository.ConceptoRepository;
import com.mvp.conjunto.domain.repository.ConjuntoRepository;
import com.mvp.conjunto.service.WebConceptosService;
import com.mvp.conjunto.service.impl.mapper.ConceptosMapper;
import com.mvp.conjunto.web.api.model.ConceptoCrear;
import com.mvp.conjunto.web.api.model.Conceptos;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebConceptosServiceImpl implements WebConceptosService {

    private final ConjuntoRepository conjuntoRepository;
    private final ConceptoConjuntoRepository conceptoConjuntoRepository;
    private final ConceptoRepository conceptoRepository;
    private final ConceptosMapper conceptosMapper;


    @Override
    public Conceptos consultarConcepto(String c) {
        return mapConsultarConcepto(conceptoConjuntoRepository.findByIdConjunto_Id(UUID.fromString(c)));
    }

    private Conceptos mapConsultarConcepto(List<ConceptoConjunto> conceptoConjuntoList){
        Conceptos conceptos = new Conceptos();
        conceptos.setConceptos(conceptoConjuntoList.stream().map(x -> conceptosMapper.mapConcepto(x.getIdConcepto(), x.getFechaCreacion())).toList());
        return conceptos;
    }

    @Override
    @Transactional
    public void crearConcepto(ConceptoCrear conceptoCrear, String c) {
        Conjunto conjunto = conjuntoRepository.findById(UUID.fromString(c)).orElseThrow();
        Concepto concepto = new Concepto();
        concepto.setNombre(conceptoCrear.getNombre());
        concepto.setValor(BigDecimal.valueOf(conceptoCrear.getValor()));
        concepto.setFechaCreacion(Instant.now());
        concepto.setFechaActualizacion(Instant.now());
        conceptoRepository.save(concepto);

        ConceptoConjunto conceptoConjunto = new ConceptoConjunto();
        conceptoConjunto.setIdConjunto(conjunto);
        conceptoConjunto.setIdConcepto(concepto);
        conceptoConjunto.setFechaCreacion(Instant.now());
        conceptoConjunto.setFechaActualizacion(Instant.now());
        conceptoConjuntoRepository.save(conceptoConjunto);
    }

    @Override
    public void actualizarConcepto(String c, String id, ConceptoCrear conceptoCrear) {
        Concepto concepto = conceptoRepository.findById(UUID.fromString(id)).orElseThrow();
        concepto.setNombre(conceptoCrear.getNombre());
        concepto.setValor(BigDecimal.valueOf(conceptoCrear.getValor()));
        concepto.setFechaActualizacion(Instant.now());
        conceptoRepository.save(concepto);
    }


    @Override
    public void eliminarConcepto(String id, String c) {

    }
}
