package pontoEletronico.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pontoEletronico.model.HorarioTrabalho;
import pontoEletronico.model.MarcacaoFeita;

public class MarcacaoFeitaRepository {
	
	public MarcacaoFeitaRepository() {
	}

	public void salvar(List<MarcacaoFeita> marcacoes) {
		Transaction transaction = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			for (MarcacaoFeita marcacao : marcacoes) {
				session.save(marcacao);
			}

			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException("Erro ao salvar marcações feitas", e);
		}
	}
	
	public List<MarcacaoFeita> listarTodos() {
	    Transaction transaction = null;
	    List<MarcacaoFeita> todos = new ArrayList<>();
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        todos = session.createQuery("from MarcacaoFeita").getResultList();
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return todos;
	}

}
