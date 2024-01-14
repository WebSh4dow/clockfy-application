package pontoEletronico.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pontoEletronico.model.HorarioTrabalho;
import pontoEletronico.repository.HorarioTrabalhoRepository;

@WebServlet("/HorarioTrabalhoServlet")
public class HorarioTrabalhoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private HorarioTrabalhoRepository horarioTrabalhoRepository;

	public void init() {
		horarioTrabalhoRepository = new HorarioTrabalhoRepository();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    List<HorarioTrabalho> horarios = new ArrayList<>();

	    for (int i = 1; i <= 3; i++) {
	        String entrada = request.getParameter("entrada" + i);
	        String saida = request.getParameter("saida" + i);

	        if (entrada != null && !entrada.isEmpty() && saida != null && !saida.isEmpty()) {
	            try {
	                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
	                Date inicio = dateFormat.parse(entrada);
	                Date fim = dateFormat.parse(saida);

	                HorarioTrabalho horario = new HorarioTrabalho(inicio, fim);
	                horarios.add(horario);
	            } catch (java.text.ParseException e) {
	                throw new RuntimeException("Erro ao converter hora", e);
	            }
	        }
	    }

	    horarioTrabalhoRepository.salvar(horarios);

	    horarios = horarioTrabalhoRepository.listarTodos();
	    request.setAttribute("horarios", horarios);
	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<HorarioTrabalho> horarios = horarioTrabalhoRepository.listarTodos();
		request.setAttribute("horarios", horarios);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}