package org.serratec.residencia.biblioteca;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.serratec.residencia.biblioteca.model.Livro;
import org.serratec.residencia.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {

	@Autowired
	private LivroService livroService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Livro postLivro(@Valid @RequestBody Livro livro) {
		return livroService.inserir(livro);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Livro> putLivro(@PathVariable Long id, @Valid @RequestBody Livro livro) {
		Optional<Livro> opLivroAtualizado = livroService.atualizar(id, livro);
		if(opLivroAtualizado.isPresent()) {
			return ResponseEntity.ok(opLivroAtualizado.get());
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping
	public ResponseEntity<List<Livro>> getLivros() {
		List<Livro> livros = livroService.listarLivros();

		if (livros.size() > 0) {
			return ResponseEntity.ok(livros);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> getPorId(@PathVariable Long id) {
		Optional<Livro> optionalLivro = livroService.buscarPorId(id);
		
		if(optionalLivro.isPresent()) {
			return ResponseEntity.ok(optionalLivro.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarTitulo")
	public ResponseEntity<List<Livro>> getPorTitulo(@RequestParam String titulo) {
		List<Livro> livros = livroService.buscarPorTitulo(titulo);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscarTipo")
	public ResponseEntity<List<Livro>> getPorTipo(@RequestParam String tipo) {
		List<Livro> livros = livroService.buscarPorTipo(tipo);
		
		if(!livros.isEmpty()) {
			return ResponseEntity.ok(livros);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		livroService.remover(id);
	}

}
