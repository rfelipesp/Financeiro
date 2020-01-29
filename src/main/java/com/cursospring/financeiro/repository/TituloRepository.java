package com.cursospring.financeiro.repository;

import com.cursospring.financeiro.model.Entidade;
import com.cursospring.financeiro.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursospring.financeiro.model.Titulo;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    @Query("SELECT t FROM Titulo t JOIN FETCH t.tipoDePagamento  WHERE UPPER(t.descricao) LIKE %?1% " +
                        "AND (t.entidade = ?2 OR ?2 IS NULL) " +
                        "AND (t.tipo = ?3 OR ?3 IS NULL)")
    List<Titulo> findByFiltro(String descricao, Entidade entidade, Tipo tipo);


}
