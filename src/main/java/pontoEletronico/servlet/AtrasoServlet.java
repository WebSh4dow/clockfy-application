package pontoEletronico.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pontoEletronico.model.Atraso;
import pontoEletronico.model.HorarioTrabalho;
import pontoEletronico.model.MarcacaoFeita;
import pontoEletronico.repository.HorarioTrabalhoRepository;
import pontoEletronico.repository.MarcacaoFeitaRepository;

@WebServlet("/AtrasoServlet")
public class AtrasoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MarcacaoFeitaRepository marcacaoFeitaRepository;
    private HorarioTrabalhoRepository horarioTrabalhoRepository;

    public void init() {
        marcacaoFeitaRepository = new MarcacaoFeitaRepository();
        horarioTrabalhoRepository = new HorarioTrabalhoRepository();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MarcacaoFeita> marcacoes = marcacaoFeitaRepository.listarTodos();
        List<HorarioTrabalho> horariosTrabalhoList = horarioTrabalhoRepository.listarTodos();

        List<Atraso> atrasos = calcularAtrasos(horariosTrabalhoList, marcacoes);
        List<Atraso> horasExtras = calcularHorasExtras(horariosTrabalhoList, marcacoes);

        request.setAttribute("atrasos", atrasos);
        request.setAttribute("horasExtras", horasExtras);
        request.setAttribute("horarios", horariosTrabalhoList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/atrasos.jsp");
        dispatcher.forward(request, response);
    }

    private List<Atraso> calcularHorasExtras(List<HorarioTrabalho> horariosTrabalho, List<MarcacaoFeita> marcacoes) {
        List<Atraso> result = new ArrayList<>();

        for (MarcacaoFeita marcacao : marcacoes) {
            for (HorarioTrabalho horario : horariosTrabalho) {
                if (marcacao.getFim().after(horario.getFim())) {
                    Atraso horaExtra = new Atraso(horario.getFim(), marcacao.getFim(),
                            calcularDiferencaMinutos(horario.getFim(), marcacao.getFim()));
                    result.add(horaExtra);
                }
            }
        }

        return result;
    }


	private List<Atraso> calcularAtrasos(List<HorarioTrabalho> horariosTrabalho, List<MarcacaoFeita> marcacoes) {
		List<Atraso> result = new ArrayList<>();

		for (MarcacaoFeita marcacao : marcacoes) {
			for (HorarioTrabalho horario : horariosTrabalho) {
				if (marcacao.getInicio().before(horario.getInicio())) {
					// Calcula o período de atraso
					Atraso atraso = new Atraso(marcacao.getInicio(), horario.getInicio(),
							calcularDiferencaMinutos(marcacao.getInicio(), horario.getInicio()));
					result.add(atraso);
				}
			}
		}

		return result;
	}
	
    private int calcularDiferencaMinutos(Date dataInicial, Date dataFinal) {
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        return (int) (diferenca / (60 * 1000));
    }
}
