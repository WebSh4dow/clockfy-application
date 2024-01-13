package com.airhacks.ping.boundary.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airhacks.ping.boundary.model.Registro;

@Stateless
public class RegistroService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void salvarRegistro(Registro registro) {
        entityManager.persist(registro);
    }

    public Registro buscarRegistroPorId(Long id) {
        return entityManager.find(Registro.class, id);
    }
}
