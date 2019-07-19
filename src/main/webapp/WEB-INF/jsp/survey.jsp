<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="surveyURL" value="/survey" />

<div class="container main form-container">


	<form:form action="${surveyURL}" method="POST" modelAttribute="survey">

		<div class="row justify-content-center align-items-center">
			<div class="col col-12 col-sm-7">
				<div class="form-group">
					<label class="font-weight-bold" for="parkCode">Choose your favorite park</label> <br />
					<form:select path="parkCode">

						<c:forEach var="park" items="${parks}">
							<form:option value="${park.code}" label="${park.parkName }" />
						</c:forEach>

					</form:select>
					<form:errors path="parkCode" cssClass="error" />
				</div>
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col col-12 col-sm-7">
				<div class="form-group">
					<label class="font-weight-bold" for="email">Email</label> <br />
					<form:input path="email" placeholder="example@domain.com" />
					<form:errors path="email" cssClass="error" />
				</div>
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col col-12 col-sm-7">
				<div class="form-group">
					<label class="font-weight-bold" for="state">State of Residence</label> <br />
					<form:select path="state">
						<form:options items="${states}" />
					</form:select>
					<form:errors path="state" cssClass="error" />
				</div>
			</div>
		</div>

		<div class="row justify-content-center align-items-center">
			<div class="col col-12 col-sm-7">
				<div class="form-group">
					<label class="font-weight-bold" for="activityLevel">Activity Level</label> <br />
					<span><form:radiobutton path="activityLevel" value=" Inactive"
						label="Inactive" /></span>
					<span><form:radiobutton path="activityLevel" value=" Sedentary"
						label="Sedentary" /></span>
					<span><form:radiobutton path="activityLevel" value=" Active"
						label="Active" /></span>
					<span><form:radiobutton path="activityLevel" value=" Extremely Active"
						label="Extremely Active" /></span>
					<form:errors path="activityLevel" cssClass="error" />
				</div>
			</div>
		</div>
		<div class="row justify-content-center align-items-center">
			<div class="col col-12 col-sm-7">
				<div class="form-group">
					<input type="submit" class="btn btn-primary" value="Submit" />
				</div>
			</div>
		</div>
	</form:form>

</div>



<c:import url="/WEB-INF/jsp/common/footer.jsp" />