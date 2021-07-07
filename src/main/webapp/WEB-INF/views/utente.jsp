<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h2>Users List</h2>
    <table>
        <tr>
            <td><strong>ID</strong></td>
            <td><strong>Nome</strong></td>
            <td><strong>Cognome</strong></td>
            <td><strong>Data di nascita</strong></td>
        </tr>
        <c:forEach items="${utenti}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.nome}</td>
                <td>${user.cognome}</td>
                <td>${user.datadinascita}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
