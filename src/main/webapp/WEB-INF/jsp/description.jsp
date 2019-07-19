<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div class="container-fluid main description-container">
	<div class="row hero-img-row justify-content-center">

		<c:set var="code" value="${fn:toLowerCase(park.code)}" />
		<img src=" <c:url value="img/parks/${code}.jpg" /> ">

	</div>

	<div class="row content-row align-items-center">
		<div class="col col-12 col-md-6">
			<h2>${park.parkName }</h2>
			<p>${park.description }</p>
		</div>

		<div class="col col-12 col-md-6">

			<div class="card park-card">
				<div class="card-body">
					<h5 class="card-title text-center">
						<u>Park Info</u>
					</h5>
					<div class="row">
						<div class="col col-xs-6">
							<ul class="list-unstyled">
								<li>State: ${park.state}</li>
								<li>Acreage: ${park.acreage}</li>
								<li>Elevation: ${park.elevation}</li>
								<li>Trail Length: ${park.milesOfTrail} mi.</li>
								<li>No. of Campsites: ${park.numberOfCampsites }</li>
							</ul>
						</div>

						<div class="col col-xs-6">
							<ul class="list-unstyled">

								<li>Climate: ${park.climate }</li>
								<li>Founded: ${park.yearFounded }</li>
								<li>Yearly Visitors: ${park.annualVisitorCount}</li>
								<li>Entry Fee: <fmt:formatNumber value="${park.entryFee}"
										type="currency" /></li>
								<li>No. Of Animal Species: ${park.numberOfAnimalSpecies}</li>
							</ul>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	
	<div class="row mt-3">
	<div class="col col-12">
	<blockquote class="blockquote text-center">
  <p class="mb-0">${park.quote }</p>
  <footer class="blockquote-footer">${park.quoteSource }</footer>
	</blockquote>
	</div>
	</div>

	<div class="row weather-row align-items-center justify-content-center">

		<c:forEach var="forecast" items="${forecasts}">

			<div class="col col-6 col-md">

				<div class="card weather-card">
					<div class="card-body">
						<h5 class="card-title text-center">
							<c:if test="${forecast.day == 1}">
								<em>Today</em>
							</c:if>
						</h5>
						<div class="row align-items-center justify-content-center">
							<div class="column">
								<img class="forecast-image"
									src=" <c:url value="img/weather/${forecast.forecast}.png" /> ">
							</div>
						</div>
						<div class="row temperature-row justify-content-center">
							<div class="col">

								<div>

									<c:if test="${tempType == 'F' }">

										<p>High: ${forecast.high}&#176;F</p>

									</c:if>
									<c:if test="${tempType == 'C' }">

										<p>High: ${forecast.highC}&#176;C</p>

									</c:if>
								</div>
								<div>

									<c:if test="${tempType == 'F' }">

										<p>Low: ${forecast.low}&#176;F</p>

									</c:if>
									
									<c:if test="${tempType == 'C' }">

										<p>Low: ${forecast.lowC}&#176;C</p>

									</c:if>



								</div>
							</div>
						</div>
						<c:if test="${forecast.day == 1}">
							<div class="row advisory-row">
								<div class="col">
									<c:forEach var="phrase" items="${advisoryList}">

										<c:if test="${not empty phrase}">
											<p>${phrase}</p>
										</c:if>

									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>

		</c:forEach>

	</div>
	<div class="text-center">

		<c:url var="tempTypeURL" value="/description" />
		<form action="${tempTypeURL }" method="POST">

			<input type="hidden" value="${park.code }" name="parkCode"> <input
				type="submit" class="btn btn-secondary" value="Switch Temperature (F or C)">

		</form>




	</div>
</div>








<c:import url="/WEB-INF/jsp/common/footer.jsp" />