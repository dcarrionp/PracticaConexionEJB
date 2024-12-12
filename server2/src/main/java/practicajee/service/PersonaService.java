package practicajee.service;

import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import practicajee.dao.PersonaDAO;
import practicajee.exception.DuplicateCedulaException;
import practicajee.model.Persona;

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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> obtenerPersonas() {
        return personaDAO.obtenerPersonas();
    }
}
