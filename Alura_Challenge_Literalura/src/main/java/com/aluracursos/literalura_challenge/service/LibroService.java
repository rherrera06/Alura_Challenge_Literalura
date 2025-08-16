package com.aluracursos.literalura_challenge.service;

import com.aluracursos.literalura_challenge.dto.DatosLibro;
import com.aluracursos.literalura_challenge.dto.DatosRespuesta;
import com.aluracursos.literalura_challenge.exception.LibroNoEncontradoException;
import com.aluracursos.literalura_challenge.model.Autor;
import com.aluracursos.literalura_challenge.model.Libro;
import com.aluracursos.literalura_challenge.repository.AutorRepository;
import com.aluracursos.literalura_challenge.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LibroService {

    @Autowired private ConsumoAPI consumoAPI;
    @Autowired private ConvierteDatos conversor;
    @Autowired private LibroRepository libroRepository;
    @Autowired private AutorRepository autorRepository;

    @Transactional
    public Libro buscarYGuardarLibro(String titulo) {
        String json = consumoAPI.obtenerDatos(titulo);
        DatosRespuesta respuesta = conversor.obtenerDatos(json, DatosRespuesta.class);

        if (respuesta == null || respuesta.resultados().isEmpty()) {
            throw new LibroNoEncontradoException("No se encontró ningún libro con el título '" + titulo + "'.");
        }

        DatosLibro datosLibro = respuesta.resultados().get(0);

        return libroRepository.findByTituloContainsIgnoreCase(datosLibro.titulo())
                .orElseGet(() -> {
                    Autor autor = autorRepository.findByNombreIgnoreCase(datosLibro.autores().get(0).nombre())
                            .orElseGet(() -> autorRepository.save(new Autor(datosLibro.autores().get(0))));
                    Libro libro = new Libro(datosLibro);
                    libro.setAutor(autor);
                    return libroRepository.save(libro);
                });
    }

    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}