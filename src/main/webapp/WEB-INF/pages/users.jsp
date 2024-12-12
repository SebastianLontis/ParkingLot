<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<t:pageTemplate pageTitle="Users">
  <h1>Users</h1>
  <div class="container text-center">
    <c:if test="${pageContext.request.isUserInRole('WRITE_USERS')}">
    <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/AddUser">Add User</a>
    </c:if>
    <c:forEach var="user" items="${users}">
      <div class="row">
        <div class="col">
            ${user.email}
        </div>
        <div class="col">
            ${user.name}
        </div>
      </div>
    </c:forEach>
  </div>
</t:pageTemplate>