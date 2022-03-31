package com.vivo.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivo.minhasfinancas.model.entity.Lançamento;

public interface LancamentoRepository extends JpaRepository<Lançamento, Long>{

}
