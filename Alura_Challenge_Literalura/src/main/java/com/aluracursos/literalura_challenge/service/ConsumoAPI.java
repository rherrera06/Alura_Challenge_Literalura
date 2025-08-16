package com.aluracursos.literalura_challenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ConsumoAPI {
    @Value("${api.gutendex.url}")
    private String baseUrl;

    public String obtenerDatos(String busqueda) {
        HttpClient client = HttpClient.newHttpClient();
        String busquedaCodificada = URLEncoder.encode(busqueda, StandardCharsets.UTF_8);
        String url = baseUrl + "?search=" + busquedaCodificada;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.err.println("Error en la API: " + response.statusCode());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error al consultar la API: " + e.getMessage());
            return null;
        }
    }
}