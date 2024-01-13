package com.airhacks.ping.boundary.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airhacks.ping.boundary.model.Registro;
import com.airhacks.ping.boundary.service.RegistroService;

@WebServlet("/registros")
public class RegistroServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private RegistroService registroService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String registroId = request.getParameter("id");
		if (registroId != null) {
			Long id = Long.parseLong(registroId);
			Registro registro = registroService.buscarRegistroPorId(id);
			response.getWriter().write("Registro encontrado: " + registro);
		} else {
			response.getWriter().write("ID do Registro não fornecido");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String inicioStr = request.getParameter("inicio");
		String fimStr = request.getParameter("fim");

		if (inicioStr != null && fimStr != null) {
			Registro novoRegistro = new Registro();
			novoRegistro.setInicio(parseDate(inicioStr));
			novoRegistro.setFim(parseDate(fimStr));
			registroService.salvarRegistro(novoRegistro);
			response.getWriter().write("Registro salvo com sucesso");
		} else {
			response.getWriter().write("Horários de início e fim não fornecidos");
		}
	}

	private Date parseDate(String timeStr) {

		return new Date();
	}
}