package com.example.filmesapi.controller;

import com.example.filmesapi.model.Filme;
import com.example.filmesapi.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    // POST /filmes -> 201
    @PostMapping
    public ResponseEntity<Filme> criar(@Valid @RequestBody Filme filme) {
        Filme filmeCriado = filmeService.criar(filme);
        URI location = URI.create("/filmes/" + filmeCriado.getId());
        return ResponseEntity.created(location).body(filmeCriado);
    }

    // GET /filmes -> 200
    @GetMapping
    public ResponseEntity<List<Filme>> listarTodos() {
        return ResponseEntity.ok(filmeService.listarTodos());
    }

    // GET /filmes/{id} -> 200
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.buscarPorId(id));
    }

    // PUT /filmes/{id} -> 201
    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @Valid @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.atualizar(id, filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeAtualizado);
    }

    // DELETE /filmes/{id} -> 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
