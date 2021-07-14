<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Inserimento prenotazione</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <header id="header" class="header-panel">
        <jsp:include page="../layouts/template/header.jsp"></jsp:include>
    </header>

  <div class="container-fluid">
      <h2>Aggiungi prenotazione</h2>

      <c:url var="addAction" value="/prenotazioni/addPrenotazione/save"></c:url>
      <form:form action="${addAction}">
          <form:input id="utente" path="utente.id" type="hidden" class="form-control" value="${userId}"/>
          <table class="table">
            <tbody>
                <tr>
                    <td><label class="col-form-label" for="automezzo">Automezzo</label></td>
                    <td>
                        <form:select id="automezzo" path="automezzo.id" class="form-select" required="required">
                            <form:option value="">-- Scegli un automezzo --</form:option>
                            <c:forEach items="${listaAuto}" var="auto" varStatus="status">
                                <option value="${auto.id}">${auto.casacostruttrice} ${auto.modello}</option>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="startdate">Data di inizio</label></td>
                    <td><form:input id="startdate" path="startdate" type="date" class="form-control" required="required"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="enddate">Data di fine</label></td>
                    <td><form:input id="enddate" path="enddate" type="date" class="form-control" required="required"/></td>
                </tr>

                <tr>
                    <td><label></label></td>
                    <td>
                        <input type="submit" id="btnSave" class="btn btn-primary" value="Save"/>
                        <button type="button" class="btn btn-danger" onclick="history.back()">Back</button>
                    </td>
                </tr>

            </tbody>
          </table>
      </form:form>
  </div>
</body>
</html>
