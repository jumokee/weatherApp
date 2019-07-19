<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />



<div class="container main">
	<div class="row">
		<div class="col col-12">
			<h3>Park Survey Results</h3>
			<p>Top National Parks as ranked by visitors</p>
		</div>
	</div>

	<div class="row survey-hr align-items-center">


		<div class="col col-5 offset-3">
			<h5>Name of Park</h5>
		</div>
		<div class="col col-4">
			<h5 class="text-center">Number of Votes</h5>
		</div>
	</div>

	<c:forEach var="survey" items="${surveys}">
		<div class="row survey-result-row align-items-center">

			<div class="col col-3">
				<c:set var="code" value="${fn:toLowerCase(survey.key)}" />
				<c:url var="imgPath" value="img/parks/${code}.jpg" />
				<img class="survey-image" alt="park picture" src="${imgPath}">
			</div>
			<div class="col col-5">
				<c:forEach var="park" items="${parks}">
					<c:if test="${park.code == survey.key}">
						<h4>${park.parkName}</h4>
					</c:if>
				</c:forEach>
			</div>

			<div class="col col-4">
				<p class="text-center">${survey.value}</p>
			</div>
		</div>
	</c:forEach>

</div>




<c:import url="/WEB-INF/jsp/common/footer.jsp" />