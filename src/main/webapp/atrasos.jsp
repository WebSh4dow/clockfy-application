<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="pontoEletronico.model.Atraso"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Lista de Atrasos e Horas Extras</title>
</head>
<body>

<div class="container mt-5">
    <h2>Atrasos e Horas Extras</h2>
    <div class="row">
        <div class="col-md-6">
            <h3>Atrasos</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Data</th>
                    <th>Hora de Entrada</th>
                    <th>Hora de Saída</th>
                    <th>Atraso (horas)</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Atraso> atrasos = (List<Atraso>) request.getAttribute("atrasos");
                    if (atrasos != null) {
                        for (Atraso atraso : atrasos) {
                            int atrasoEmMinutos = atraso.getAtrasoMinutos();
                            int horas = atrasoEmMinutos / 60;
                            int minutos = atrasoEmMinutos % 60;
                %>
                            <tr class="atraso">
                                <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(atraso.getData()) %></td>
                                <td><%= new java.text.SimpleDateFormat("HH:mm:ss").format(atraso.getHoraEntrada()) %></td>
                                <td><%= new java.text.SimpleDateFormat("HH:mm:ss").format(atraso.getHoraSaida()) %></td>
                                <td><%= horas %> horas <%= minutos %> minutos</td>
                            </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        <div class="col-md-6">
            <h3>Horas Extras</h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Data</th>
                    <th>Hora de Entrada</th>
                    <th>Hora de Saída</th>
                    <th>Hora Extra (horas)</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Atraso> horasExtras = (List<Atraso>) request.getAttribute("horasExtras");
                    if (horasExtras != null) {
                        for (Atraso horaExtra : horasExtras) {
                            int horasEmMinutos = horaExtra.getAtrasoMinutos();
                            int horas = horasEmMinutos / 60;
                            int minutos = horasEmMinutos % 60;
                %>
                            <tr class="horaExtra">
                                <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(horaExtra.getData()) %></td>
                                <td><%= new java.text.SimpleDateFormat("HH:mm:ss").format(horaExtra.getHoraEntrada()) %></td>
                                <td><%= new java.text.SimpleDateFormat("HH:mm:ss").format(horaExtra.getHoraSaida()) %></td>
                                <td><%= horas %> horas <%= minutos %> minutos</td>
                            </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
        
        
        
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
