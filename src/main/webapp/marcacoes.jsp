<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="pontoEletronico.model.MarcacaoFeita"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Marcações Feitas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h2>Marcações Feitas</h2>

        <!-- Formulário para adicionar novas marcações -->
        <form action="MarcacoesFeitasServlet" method="post">
            <div class="form-group">
                <label for="entrada">Entrada:</label>
                <input type="time" class="form-control" name="inicio" required>
                <label for="fim">Saída:</label>
                <input type="time" class="form-control" name="fim" required>
            </div>
            <button type="submit" class="btn btn-primary">Adicionar Marcação</button>
        </form>

        <h2 class="mt-5">Registros de Marcações Feitas</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Entrada</th>
                    <th>Saída</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<MarcacaoFeita> marcacoes = (List<MarcacaoFeita>)request.getAttribute("marcacoes");
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("HH:mm");

                    if (marcacoes != null) {
                        for (MarcacaoFeita marcacao : marcacoes) { 
                %>
                <tr>
                    <td><%=marcacao.getId()%></td>
                    <td><%=outputDateFormat.format(marcacao.getInicio())%></td>
                    <td><%=outputDateFormat.format(marcacao.getFim())%></td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
