package org.serratec.residencia.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.serratec.residencia.biblioteca.model.Livro;
import org.serratec.residencia.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;

	public Livro inserir(Livro livro) {
		return livroRepository.save(livro);
	}

	public Optional<Livro> atualizar(Long id, Livro livro) {
		Optional<Livro> opLivroBd = buscarPorId(id);

		if (opLivroBd.isPresent()) {
			Livro livroBd = opLivroBd.get();

			if (livro.getTitulo() != null)
				livroBd.setTitulo(livro.getTitulo());

			if (livro.getTipo() != null)
				livroBd.setTipo(livro.getTipo());

			if (livro.getData() != null)
				livroBd.setData(livro.getData());

			if (livro.getAutor() != null)
				livroBd.setAutor(livro.getAutor());
			
			return Optional.of(livroRepository.save(livroBd));
		}
		
		return Optional.empty();

	}

	public List<Livro> listarLivros() {
		return livroRepository.findAll();
	}

	public Optional<Livro> buscarPorId(Long id) {
		return livroRepository.findById(id);
	}
	
	public List<Livro> buscarPorTitulo(String titulo) {
		return livroRepository.findByTitulo(titulo);
	}
	
	public List<Livro> buscarPorTipo(String tipo) {
		return livroRepository.minhaBuscaPorTipo(tipo);
	}

	public void remover(Long id) {
		Optional<Livro> opLivro = livroRepository.findById(id);
		opLivro.ifPresent((livro) -> livroRepository.delete(livro));
	}

}
