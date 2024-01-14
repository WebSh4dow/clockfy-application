package pontoEletronico.servlet;

import java.io.IOException;
import java.text.ParseException;
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

import pontoEletronico.model.MarcacaoFeita;
import pontoEletronico.repository.MarcacaoFeitaRepository;

@WebServlet("/MarcacoesFeitasServlet")
public class MarcacoesFeitasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MarcacaoFeitaRepository marcacaoFeitaRepository;

    public void init() {
        marcacaoFeitaRepository = new MarcacaoFeitaRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        List<MarcacaoFeita> marcacoes = (List<MarcacaoFeita>) request.getSession().getAttribute("marcacoes");

        if (marcacoes == null) {
            marcacoes = new ArrayList<>();
        }

        String entradaStr = request.getParameter("inicio");
        String saidaStr = request.getParameter("fim");

        if (entradaStr != null && !entradaStr.isEmpty() && saidaStr != null && !saidaStr.isEmpty()) {
            try {
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("HH:mm");

                Date entradaDate = inputDateFormat.parse(entradaStr);
                Date saidaDate = inputDateFormat.parse(saidaStr);

                MarcacaoFeita novaMarcacao = new MarcacaoFeita(entradaDate, saidaDate);
                marcacoes.add(novaMarcacao);
            } catch (ParseException e) {
                throw new RuntimeException("Erro ao converter hora", e);
            }
        }
        marcacaoFeitaRepository.salvar(marcacoes);
        
        marcacoes = marcacaoFeitaRepository.listarTodos();
        request.setAttribute("marcacoes", marcacoes);
	    request.getRequestDispatcher("/marcacoes.jsp").forward(request, response);

    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MarcacaoFeita> marcacoes = marcacaoFeitaRepository.listarTodos();
        request.setAttribute("marcacoes", marcacoes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/marcacoes.jsp");
        dispatcher.forward(request, response);
    }
}