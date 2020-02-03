package com.cursospring.financeiro.repository;

import com.cursospring.financeiro.dto.EntidadeDto;
import com.cursospring.financeiro.model.Entidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

	List<Entidade> findByNomeContainingIgnoreCase(String nome);
	
	@Query("SELECT e FROM Entidade e WHERE UPPER(e.nome) LIKE %?1% ORDER BY e.nome")
	Page<Entidade> findByNomeQuery(String nome, Pageable pageable);

	@Query("SELECT new com.cursospring.financeiro.dto.EntidadeDto(codigo, nome) FROM Entidade WHERE UPPER(nome) LIKE %?1%")
	List<EntidadeDto> findEntidadeDtoByFiltro(String nome);

}
