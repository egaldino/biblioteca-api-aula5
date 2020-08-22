package org.serratec.residencia.biblioteca.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LIVRO")
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="livro-sequence-generator")
	@SequenceGenerator(name = "livro-sequence-generator", sequenceName="livroSequence")
	private Long id;
	
	@NotNull
	@Size(min = 5, max = 30)
	@Column(nullable = false, length = 30)
	private String titulo;
	
	@NotNull
	@Size(min = 3, max = 20)
	@Column(nullable = false, length = 20)
	private String tipo;
	
	@NotNull
	@Size(min = 3, max = 40)
	@Column(nullable = false, length = 40)
	private String autor;
	
	@Past
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@ManyToOne
	// select c.* from LIVRO l join CATEGORIA c on l.id_categoria = c.id where l.id = ?
	@JoinColumn(name="id_categoria", referencedColumnName = "id")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
