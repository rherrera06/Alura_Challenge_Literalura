# 📚 LiterAlura - Catálogo de Libros

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)
![Maven](https://img.shields.io/badge/Maven-red)

**LiterAlura** es una aplicación de consola desarrollada en Java con el framework Spring Boot. Su principal objetivo es interactuar con la API de [Gutendx](https://gutendex.com/) para buscar libros, y persistir la información relevante en una base de datos PostgreSQL. Este proyecto fue desarrollado como parte del Challenge de Alura Latam y el programa Oracle Next Education (ONE).

## ✨ Características Principales

-   🔍 **Búsqueda de Libros por Título**: Consume la API de Gutendx para encontrar libros por su título.
-   💾 **Persistencia de Datos**: Guarda automáticamente los libros y autores encontrados en una base de datos PostgreSQL, evitando registros duplicados.
-   📖 **Listado de Libros**: Muestra todos los libros que han sido registrados en la base de datos.
-   👥 **Listado de Autores**: Presenta una lista de todos los autores registrados.
-   📅 **Filtro de Autores por Año**: Permite listar autores que estaban vivos en un determinado año.
-   🌍 **Filtro de Libros por Idioma**: Muestra una lista de todos los libros registrados en un idioma específico (español, inglés, francés, portugués).

## 🛠️ Tecnologías Utilizadas

-   **Java 17**
-   **Spring Boot**
-   **Spring Data JPA**: Para la persistencia de datos y comunicación con la base de datos.
-   **PostgreSQL**: Como sistema de gestión de base de datos.
-   **Maven**: Como gestor de dependencias y construcción del proyecto.
-   **Jackson Databind**: Para la serialización y deserialización de datos JSON.

## 🚀 Cómo Empezar

Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local.

### Prerrequisitos

-   Tener instalado un **JDK (Java Development Kit)**, versión 17 o superior.
-   Tener [PostgreSQL](https://www.postgresql.org/download/) instalado y en ejecución.
-   Un IDE de Java como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [Eclipse](https://www.eclipse.org/).

### Instalación y Configuración

1.  **Clona el repositorio** (o usa tu copia local del proyecto).

2.  **Crea la base de datos** en PostgreSQL. Puedes usar el siguiente comando SQL:
    ```sql
    CREATE DATABASE db_literalura;
    ```
    *(Nota: Si usaste un nombre diferente, asegúrate de que coincida con el de tu archivo de configuración).*

3.  **Configura tus credenciales** en el archivo `application.properties`:
    **Ubicación:** `src/main/resources/application.properties`
    ```properties
    # Reemplaza con tu usuario y contraseña de PostgreSQL
    spring.datasource.username=tu_usuario_postgres
    spring.datasource.password=tu_contraseña_postgres
    ```

4.  **Ejecuta la aplicación**: La forma más sencilla es a través de tu IDE, ejecutando la clase `LiteraluraApplication.java`. Alternativamente, puedes usar Maven en la terminal:
    ```bash
    ./mvnw spring-boot:run
    ```

## 🎮 Uso de la Aplicación

Al iniciar, la aplicación te presentará un menú interactivo en la consola. Simplemente ingresa el número de la opción que deseas ejecutar.