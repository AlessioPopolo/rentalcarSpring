<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-3">
  <div class="container-fluid">
    <a class="navbar-brand" href="<spring:url value='/' />">Rental Car</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a href="<spring:url value='/auto' /> " class="nav-link">Parco auto</a>
        </li>
        <li class="nav-item">
          <a href="<spring:url value='/#' /> " class="nav-link">Profilo utente</a>
        </li>
      </ul>
    </div>
  </div>
</nav>