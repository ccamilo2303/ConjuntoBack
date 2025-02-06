package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.AvisoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisoRepository extends JpaRepository<AvisoEntity, Long> {

}
