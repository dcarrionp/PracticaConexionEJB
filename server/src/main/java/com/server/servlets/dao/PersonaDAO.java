package com.server.servlets.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import com.server.servlets.model.Persona;
import com.server.servlets.exception.DuplicateCedulaException;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class PersonaDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void registrarPersona(Persona persona) throws DuplicateCedulaException {
        try {
            em.persist(persona);
        } catch (jakarta.persistence.EntityExistsException e) {
            throw new DuplicateCedulaException("Cédula ya agregada");
        }
    }

    public List<Persona> obtenerPersonas() {
        return em.createQuery("SELECT e FROM Persona e", Persona.class).getResultList();
    }
    
}
