package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.ResidenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidenteRepository extends JpaRepository<ResidenteEntity, Long> {


    Optional<ResidenteEntity> findByEmail(String email);

}
