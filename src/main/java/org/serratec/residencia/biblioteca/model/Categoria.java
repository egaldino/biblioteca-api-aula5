package org.serratec.residencia.biblioteca.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="categoria-sequence-generator")
	@SequenceGenerator(name = "categoria-sequence-generator", sequenceName="categoriaSequence")
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@OneToMany(targetEntity = Livro.class, mappedBy = "categoria")
	private List<Livro> livros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
