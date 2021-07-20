<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
  <div class="container-fluid">
    <a class="navbar-brand" href="<spring:url value='/redirectHomepage' />">Rental Car</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a href="<spring:url value='/auto' /> " class="nav-link">Parco auto</a>
        </li>
        <sec:authorize access="hasAuthority('ADMIN')">
          <li class="nav-item">
            <a href="<spring:url value='/prenotazioni/listaAllPrenotazioni' /> " class="nav-link">Tutte le prenotazioni</a>
          </li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
          <li class="nav-item">
            <a href="<spring:url value='/logout'/> " class="nav-link">Logout</a>
          </li>
        </sec:authorize>
      </ul>
    </div>
  </div>
</nav>