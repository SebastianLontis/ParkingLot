<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="AddCar">
  <div class="container mt-5">
    <h2>Add Car</h2>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/AddCar">
      <div class="mb-3">
        <label for="license_plate" class="form-label">License Plate</label>
        <input type="text" class="form-control" id="license_plate" name="license_plate" required>
        <div class="invalid-feedback">
          Please enter a valid license plate.
        </div>
      </div>

      <div class="mb-3">
        <label for="parking_spot" class="form-label">Parking Spot</label>
        <input type="text" class="form-control" id="parking_spot" name="parking_spot" required>
        <div class="invalid-feedback">
          Please enter a valid parking spot.
        </div>
      </div>

      <div class="mb-3">
        <label for="owner" class="form-label">Owner</label>
        <select class="form-select" id="owner" name="owner_id" required>
          <option value="">Choose...</option>
            <c:forEach var="user" items="${users}" varStatus="status">
              <option value="${user.id}">${user.name}</option>
            </c:forEach>
        </select>
        <div class="invalid-feedback">
          Please select an owner.
        </div>
      </div>

      <button type="submit" class="btn btn-primary">Save</button>
    </form>
  </div>
</t:pageTemplate>