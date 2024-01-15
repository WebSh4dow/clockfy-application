package pontoEletronico.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import pontoEletronico.model.HorarioTrabalho;
import pontoEletronico.model.MarcacaoFeita;
import pontoEletronico.model.Registro;

import java.util.Properties;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;

	 public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/pdv");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "root");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");

	                settings.put(Environment.SHOW_SQL, "true");

	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

	                settings.put(Environment.HBM2DDL_AUTO, "update");

	                configuration.setProperties(settings);
	                
	                configuration.addAnnotatedClass(HorarioTrabalho.class);
	                configuration.addAnnotatedClass(MarcacaoFeita.class);
	                configuration.addAnnotatedClass(Registro.class);

	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                        .applySettings(configuration.getProperties()).build();
	                
	                System.out.println("Hibernate Java Config serviceRegistry created");
	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	                return sessionFactory;

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}
