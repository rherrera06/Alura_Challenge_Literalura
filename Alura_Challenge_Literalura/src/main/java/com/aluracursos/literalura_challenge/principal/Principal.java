package com.aluracursos.literalura_challenge.principal;

import com.aluracursos.literalura_challenge.exception.LibroNoEncontradoException;
import com.aluracursos.literalura_challenge.model.Autor;
import com.aluracursos.literalura_challenge.model.Libro;
import com.aluracursos.literalura_challenge.service.AutorService;
import com.aluracursos.literalura_challenge.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    @Autowired private LibroService libroService;
    @Autowired private AutorService autorService;

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \nElija la opción a través de su número:
                    1 - Buscar libro por su título
                    2 - Listado de libros registrados
                    3 - Listado de  autores registrados
                    4 - Listad0 de autores vivos en un determinado año
                    5 - Listado libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // Consumir nueva línea
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.nextLine(); // Limpiar el buffer
                continue;
            }

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Cerrando la aplicación...");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var titulo = teclado.nextLine();
        try {
            Libro libro = libroService.buscarYGuardarLibro(titulo);
            System.out.println("\n--- Libro Encontrado y Guardado ---");
            System.out.println(libro);
        } catch (LibroNoEncontradoException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nOcurrió un error inesperado: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {
        System.out.println("\n--- Libros Registrados ---");
        List<Libro> libros = libroService.listarTodos();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        System.out.println("\n--- Autores Registrados ---");
        List<Autor> autores = autorService.listarTodos();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para buscar autores vivos:");
        try {
            var anio = teclado.nextInt();
            teclado.nextLine();
            List<Autor> autores = autorService.listarAutoresVivosEnAno(anio);
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio + ".");
            } else {
                System.out.println("\n--- Autores Vivos en el año " + anio + " ---");
                autores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un año válido.");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        var idioma = teclado.nextLine();
        List<Libro> libros = libroService.listarPorIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + idioma + "'.");
        } else {
            System.out.println("\n--- Libros en idioma '" + idioma + "' ---");
            libros.forEach(System.out::println);
        }
    }
}