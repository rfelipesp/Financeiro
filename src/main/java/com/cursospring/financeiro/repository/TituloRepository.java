package com.cursospring.financeiro.repository;

import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.model.Tipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursospring.financeiro.model.Titulo;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    @Query(value = "SELECT t FROM Titulo t JOIN FETCH t.tipoDePagamento  WHERE UPPER(t.descricao) LIKE %?1% " +
                            "AND (t.entidade = ?2 OR ?2 IS NULL) " +
                            "AND (t.tipo = ?3 OR ?3 IS NULL)",
            countQuery = "SELECT COUNT (t1) FROM Titulo AS t1 " +
                            "WHERE UPPER(t1.descricao) LIKE %?1% " +
                            "AND (t1.entidade = ?2 OR ?2 IS NULL) " +
                            "AND (t1.tipo = ?3 OR ?3 IS NULL)")
    Page<Titulo> findByFiltro(String descricao, Entidade entidade, Tipo tipo, Pageable pageable);


}
