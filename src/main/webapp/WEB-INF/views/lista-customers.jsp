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
    <header id="header" class="header-panel">
        <jsp:include page="../layouts/template/header.jsp"></jsp:include>
    </header>

    <h2>Users List</h2>

    <div class="container-fluid">
        <form:form method="get" action="/utente/search/">
            <div class="row row-cols-2 mb-3">
                <div class="col">
                    <label class="form-label">Cerca customers:</label>
                </div>
                <div class="col"></div>

                <div class="col">
                    <input class="form-control" type="text" name="theSearchName" />
                </div>
                <div class="col">
                    <input type="submit" value="search" class="btn btn-secondary" />
                </div>
            </div>
        </form:form>

        <a href="<spring:url value='/utente/addCustomer' /> " class="btn btn-success">New Customer</a>

        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Data di nascita</th>
                    <th>Ruolo</th>
                    <th>Azioni</th>
                    <th>Prenotazioni</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${utenti}" var="user">
                    <tr>
                        <td>${user.id}</td>

                        <td>${user.nome}</td>

                        <td>${user.cognome}</td>

                        <td><fmt:formatDate value="${user.datadinascita}" type="date" pattern="dd-MM-yyyy"/></td>

                        <td>${user.ruolo.ruolo}</td>

                        <td class="input-group">
                            <form:form method="POST" action="/utente/updateCustomer/${user.id}" cssClass="me-2">
                                <input type="submit" class="btn btn-primary" value="UPDATE"/>
                            </form:form>
                            <form:form method="POST" action="/utente/delete/${user.id}">
                                <button type="submit" class="btn btn-danger" onclick="if (!(confirm('Vuoi eliminare questo venditore?'))) return false">
                                    Delete
                                </button>
                            </form:form>
                        </td>
                        <td>
                            <form:form method="POST" action="/prenotazioni/visualizza/${user.id}">
                                <input type="submit" class="btn btn-success" value="Visualizza prenotazioni"/>
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
