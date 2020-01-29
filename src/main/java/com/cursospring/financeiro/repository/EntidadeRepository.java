package com.cursospring.financeiro.repository;

import java.util.List;

import com.cursospring.financeiro.dto.EntidadeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cursospring.financeiro.model.Entidade;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

	List<Entidade> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT e FROM Entidade e WHERE e.nome LIKE %?1% OR ?1 IS NULL")
	List<Entidade> findByNomeQuery(String nome);

	@Query("SELECT new com.cursospring.financeiro.dto.EntidadeDto(codigo, nome) FROM Entidade WHERE UPPER(nome) LIKE %?1%")
	List<EntidadeDto> findEntidadeDtoByFiltro(String nome);
	
}
