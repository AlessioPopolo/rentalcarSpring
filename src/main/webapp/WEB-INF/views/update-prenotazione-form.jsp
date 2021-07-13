<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Update prenotazione</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <header id="header" class="header-panel">
        <jsp:include page="../layouts/template/header.jsp"></jsp:include>
    </header>

  <div class="container-fluid">
      <h2>Modifica prenotazione</h2>

      <c:url var="addAction" value="/prenotazioni/addPrenotazione/save"></c:url>
      <form:form action="${addAction}">
          <form:input id="id" path="id" type="hidden" value="${updatePrenotazione.id}"/>
          <form:input id="utente" path="utente.id" type="hidden" value="${updatePrenotazione.utente.id}"/>
          <table class="table">
            <tbody>
                <tr>
                    <td><label class="col-form-label" for="automezzo">Automezzo</label></td>
                    <td>
                        <form:select id="automezzo" path="automezzo.id" class="form-select">
                            <c:forEach items="${listaAutoCategoria}" var="auto" varStatus="status">
                                <c:choose>
                                    <c:when test="${auto.id eq updatePrenotazione.automezzo.id}">
                                        <option value="${auto.id}" selected="selected">${auto.casacostruttrice} ${auto.modello}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${auto.id}">${auto.casacostruttrice} ${auto.modello}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="startdate">Data di inizio</label></td>
                    <fmt:formatDate value="${updatePrenotazione.startdate}" var="dateStringStart" pattern="yyyy-MM-dd" />
                    <td><form:input id="startdate" path="startdate" type="date" class="form-control" value="${dateStringStart}"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="enddate">Data di fine</label></td>
                    <fmt:formatDate value="${updatePrenotazione.enddate}" var="dateStringEnd" pattern="yyyy-MM-dd" />
                    <td><form:input id="enddate" path="enddate" type="date" class="form-control" value="${dateStringEnd}"/></td>
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
