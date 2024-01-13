package com.airhacks.ping.boundary;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.airhacks.ping.boundary.model.Test;

public class HibernateTest {
	
	public static void main(String[] args) {
        // Configuração da sessão do Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Cria uma pessoa e salva no banco de dados
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Test test = new Test();
            test.setTestExample("John Doe");

            session.save(test);

            session.getTransaction().commit();
        }

        // Recupera uma pessoa do banco de dados
        try (Session session = sessionFactory.openSession()) {
            Test testRer = session.get(Test.class, 1L); // Supondo que o ID 1 exista
            System.out.println("Retrieved Person: " + testRer.getTestExample());
        }

        // Fecha a fábrica de sessões
        sessionFactory.close();
    }

}
