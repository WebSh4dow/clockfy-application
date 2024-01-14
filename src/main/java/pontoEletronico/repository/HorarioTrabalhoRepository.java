package pontoEletronico.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.google.protobuf.TextFormat.ParseException;
import pontoEletronico.model.HorarioTrabalho;
import java.util.List;


public class HorarioTrabalhoRepository {

	public HorarioTrabalhoRepository() {
	}

	public void salvar(List<HorarioTrabalho> horarios)  {
	    Transaction transaction = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        for (HorarioTrabalho horario : horarios) {
	            session.save(horario);
	        }

	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); 
	        throw new RuntimeException("Erro ao salvar horários de trabalho", e);
	    }
	}

	public List<HorarioTrabalho> listarTodos() {
		Transaction transaction = null;
		List<HorarioTrabalho> todos = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object

			todos = session.createQuery("from HorarioTrabalho").getResultList();

			// commit transaction
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