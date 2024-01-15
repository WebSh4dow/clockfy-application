<%@page import="pontoEletronico.model.HorarioTrabalho"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ponto Eletrônico</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>
    <style>
        body { padding-top: 56px; }
        .navbar { background-color: #007bff; }
        .navbar-brand { color: #ffffff !important; }
        .card { margin-top: 20px; }
        .icon { font-size: 48px; color: #007bff; }
    </style>
    <script>
        var contadorRegistros = 1;

        function adicionarRegistro() {
            var container = document.getElementById("registros-container");

            if (contadorRegistros < 3) {
                contadorRegistros++;

                var novoRegistro = document.createElement("div");
                novoRegistro.className = "registro form-group";
                novoRegistro.innerHTML = `
                    <label for="inicio${contadorRegistros}">Início:</label>
                    <input type="time" class="form-control" name="inicio${contadorRegistros}" required>
                    <label for="fim${contadorRegistros}">Saída:</label>
                    <input type="time" class="form-control" name="fim${contadorRegistros}" required>
                `;
                container.appendChild(novoRegistro);
            } else {
                $('#limiteRegistrosModal').modal('show');
            }
        }
    </script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="#"> Ponto Eletrônico </a>
    </nav>
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Horário de Trabalho</h5>
                        <form action="HorarioTrabalhoServlet" method="post">
                            <div id="registros-container">
                                <div class="registro form-group">
                                    <label for="inicio1">Início:</label>
                                    <input type="time" class="form-control" name="inicio1" required>
                                    <label for="fim1">Saída:</label>
                                    <input type="time" class="form-control" name="fim1" required>
                                </div>
                            </div>

                            <button type="button" class="btn btn-success" onclick="adicionarRegistro()">Adicionar</button>
                            <button type="submit" class="btn btn-primary">Registrar</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Marcações Feitas</h5>
                        <form action="MarcacoesFeitasServlet" method="post">
                            <button type="submit" class="btn btn-primary">Rotina de Marcações</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Atrasos e Horas Extras</h5>
                        <form action="AtrasoServlet" method="get">
                            <button type="submit" class="btn btn-primary">Visualizar Atrasos e Horas Extras</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <h2 class="mt-5">Registros de Horário de Trabalho</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Início</th>
                    <th>Saída</th>
                </tr>
            </thead>
             <tbody>
                <%
                List<HorarioTrabalho> horarios = (List<HorarioTrabalho>) request.getAttribute("horarios");
                if (horarios != null) {
                    for (HorarioTrabalho linha : horarios) {
                %>
                <tr>
                    <td><%=linha.getId()%></td>
                    <td><%=linha.getInicio()%></td>
                    <td><%=linha.getFim()%></td>
                </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>
    <div class="modal fade" id="limiteRegistrosModal" tabindex="-1" role="dialog" aria-labelledby="limiteRegistrosModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="limiteRegistrosModalLabel">Limite de Registros Atingido</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">Você atingiu o número máximo de registros (3). Não é possível adicionar mais registros.</div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
