<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html>
<head>
  <title>Lista prenotazioni</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

  <header id="header" class="header-panel">
    <jsp:include page="../layouts/template/header.jsp"></jsp:include>
  </header>

  <div class="container-fluid">
    <h2>Prenotazioni totali</h2>

    <table class="table">
      <thead>
      <tr>
        <th>ID prenotazione</th>
        <th>Utente</th>
        <th>Automezzo</th>
        <th>Inizio</th>
        <th>Fine</th>
        <th>Azioni</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${listaPrenotazioni}" var="tempPrenotazione">
        <tr>
          <td>${tempPrenotazione.id}</td>

          <td>${tempPrenotazione.utente.id} ${tempPrenotazione.utente.nome} ${tempPrenotazione.utente.cognome}</td>

          <td>
              ${tempPrenotazione.automezzo.casacostruttrice} ${tempPrenotazione.automezzo.modello} ${tempPrenotazione.automezzo.categoria.categoria}
              immatricolata nel
              <fmt:formatDate value="${tempPrenotazione.automezzo.immatricolazione}" type="date" pattern="MM-yyyy"/>
          </td>

          <td><fmt:formatDate value="${tempPrenotazione.startdate}" type="date" pattern="dd-MM-yyyy"/></td>

          <td><fmt:formatDate value="${tempPrenotazione.enddate}" type="date" pattern="dd-MM-yyyy"/></td>

          <td class="input-group">
            <form:form method="POST" action="/prenotazioni/updatePrenotazione/${tempPrenotazione.id}/${tempPrenotazione.automezzo.categoria.categoria}" cssClass="me-2">
              <input type="submit" class="btn btn-primary" value="UPDATE"/>
            </form:form>
            <form:form method="POST" action="/prenotazioni/delete/${tempPrenotazione.id}/${tempPrenotazione.utente.id}">
              <button type="submit" class="btn btn-danger" onclick="if (!(confirm('Vuoi eliminare questa prenotazione?'))) return false">
                Delete
              </button>
            </form:form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <button type="button" class="btn btn-danger" onclick="history.back()">Back</button>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
