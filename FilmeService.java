package com.example.filmesapi.service;

import com.example.filmesapi.exception.ResourceNotFoundException;
import com.example.filmesapi.model.Filme;
import com.example.filmesapi.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme criar(Filme filme) {
        filme.setId(null);
        return filmeRepository.save(filme);
    }

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id: " + id));
    }

    public Filme atualizar(Long id, Filme filmeAtualizado) {
        Filme filmeExistente = buscarPorId(id);

        filmeExistente.setTitulo(filmeAtualizado.getTitulo());
        filmeExistente.setDiretor(filmeAtualizado.getDiretor());
        filmeExistente.setGenero(filmeAtualizado.getGenero());
        filmeExistente.setAnoLancamento(filmeAtualizado.getAnoLancamento());
        filmeExistente.setDuracaoMinutos(filmeAtualizado.getDuracaoMinutos());

        return filmeRepository.save(filmeExistente);
    }

    public void deletar(Long id) {
        Filme filme = buscarPorId(id);
        filmeRepository.delete(filme);
    }
}
