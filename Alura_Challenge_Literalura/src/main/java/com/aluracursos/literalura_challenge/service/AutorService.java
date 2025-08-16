package com.aluracursos.literalura_challenge.service;

import com.aluracursos.literalura_challenge.model.Autor;
import com.aluracursos.literalura_challenge.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEnAno(Integer ano) {
        return autorRepository.buscarAutoresVivosEnAno(ano);
    }
}