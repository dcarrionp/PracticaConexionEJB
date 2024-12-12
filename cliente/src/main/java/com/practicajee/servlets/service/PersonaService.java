package com.practicajee.servlets.service;

import com.practicajee.servlets.model.Persona;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PersonaService {

    private static final String BASE_URL = "http://localhost:8080/cliente/api/estudiantes";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public PersonaService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public String registrarEstudiante(Persona estudiante) throws Exception {
        String json = mapper.writeValueAsString(estudiante);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201) {
            return "Estudiante registrado con éxito";
        } else if (response.statusCode() == 409) {
            return "Cédula ya agregada";
        } else {
            throw new Exception("Error al registrar estudiante: " + response.body());
        }
    }

    public List<Persona> obtenerEstudiantes() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            return mapper.readValue(response.body(), new TypeReference<List<Persona>>() {});
        } else {
            throw new Exception("Error al obtener estudiantes: " + response.body());
        }
    }
    
}