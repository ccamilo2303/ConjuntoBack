package com.mvp.conjunto.domain.repository;

import com.mvp.conjunto.domain.entity.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, UUID> {
}