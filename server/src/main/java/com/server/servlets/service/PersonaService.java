package com.server.servlets.service;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.server.servlets.dao.PersonaDAO;
import com.server.servlets.exception.DuplicateCedulaException;
import com.server.servlets.model.Persona;

@Path("/persona")
public class PersonaService {
    @Inject
    private PersonaDAO personaDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarPersona(Persona persona) {
        try {
            personaDAO.registrarPersona(persona);
            return Response.status(Response.Status.CREATED).build();
        } catch (DuplicateCedulaException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces
    public List<Persona> obtenerPersonas() {
        return personaDAO.obtenerPersonas();
    }
}
