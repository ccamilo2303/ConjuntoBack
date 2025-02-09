package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {

}
