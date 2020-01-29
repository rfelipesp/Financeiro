package com.cursospring.financeiro.repository;

import com.cursospring.financeiro.model.TipoDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoDePagamentoRepository extends JpaRepository<TipoDePagamento, Long> {

}
