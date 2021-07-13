<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Update auto</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <header id="header" class="header-panel">
        <jsp:include page="../layouts/template/header.jsp"></jsp:include>
    </header>

  <div class="container-fluid">
      <h2>Modifica auto</h2>

      <c:url var="addAction" value="/auto/addAuto/save"></c:url>
      <form:form action="${addAction}">
          <form:input id="id" path="id" type="hidden" value="${updateAuto.id}"/>
          <table class="table">
            <tbody>
                <tr>
                    <td><label class="col-form-label" for="casacostruttrice">Casa costruttrice</label></td>
                    <td><form:input id="casacostruttrice" path="casacostruttrice" type="text" class="form-control" value="${updateAuto.casacostruttrice}"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="modello">Modello</label></td>
                    <td><form:input id="modello" path="modello" type="text" class="form-control" value="${updateAuto.modello}"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="targa">Targa</label></td>
                    <td><form:input id="targa" path="targa" type="text" class="form-control" value="${updateAuto.targa}"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="immatricolazione">Immatricolazione</label></td>

                    <fmt:formatDate value="${updateAuto.immatricolazione}" var="dateString" pattern="yyyy-MM" />
                    <td><form:input id="immatricolazione" path="immatricolazione" type="month" class="form-control" value="${dateString}"/></td>
                </tr>

                <tr>
                    <td><label class="col-form-label" for="categoria">Categoria</label></td>
                    <td>
                        <form:select id="categoria" path="categoria" class="form-select">
                            <c:forEach items="${listaCategorie}" var="categorie" varStatus="status">
                                <c:choose>
                                    <c:when test="${categorie.categoria eq updateAuto.categoria.categoria}">
                                        <option value="${categorie.categoria}" selected="selected">${categorie.categoria}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${categorie.categoria}">${categorie.categoria}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select>
                    </td>
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
