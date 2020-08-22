package org.serratec.residencia.biblioteca.repository;

import java.util.List;

import org.serratec.residencia.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LivroRepository extends JpaRepository<Livro, Long> {

	
	public List<Livro> findByTitulo(String titulo);
	
	@Query("select l from Livro l where l.tipo = :tipoDoLivro")
	public List<Livro> minhaBuscaPorTipo(@Param("tipoDoLivro") String tipo);
	
}
