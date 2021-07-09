<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!doctype html>
<html>
<head>
  <title>Title</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
  <div class="container-fluid">
    <h2>Auto List</h2>

    <a href="<spring:url value='/auto/addAuto' /> " class="btn btn-success">New Auto</a>

    <table class="table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Casa costruttrice</th>
        <th>Modello</th>
        <th>Targa</th>
        <th>Immatricolazione</th>
        <th>Categoria</th>
        <th>Azioni</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${auto}" var="tempAuto">
        <tr>
          <td>${tempAuto.id}</td>

          <td>${tempAuto.casacostruttrice}</td>

          <td>${tempAuto.modello}</td>

          <td>${tempAuto.targa}</td>

          <td><fmt:formatDate value="${tempAuto.immatricolazione}" type="date" pattern="MM-yyyy"/></td>

          <td>${tempAuto.categoria.categoria}</td>

          <td class="form-inline">
            <form:form method="GET" action="">
              <input type="submit" class="btn btn-primary" value="UPDATE"/>
            </form:form>
            <form:form method="POST" action="/rentalcarSpring_war_exploded/auto/delete/${tempAuto.id}">
              <button type="submit" class="btn btn-danger" onclick="if (!(confirm('Vuoi eliminare questo automezzo?'))) return false">
                Delete
              </button>
            </form:form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>
