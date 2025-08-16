package com.aluracursos.literalura_challenge.repository;

import com.aluracursos.literalura_challenge.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :ano AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento >= :ano)")
    List<Autor> buscarAutoresVivosEnAno(Integer ano);
}