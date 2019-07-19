<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<div class="container main">

	<c:forEach var="park" items="${parks }">
	<c:set var="code" value="${fn:toLowerCase(park.code)}" />
		<div class="row park-info-row align-items-center">
			<div class="col col-xs-5">
				<img src=" <c:url value="img/parks/${code}.jpg" /> " > 
			</div>
			<div class="col col-xs-7">
				<h2>${park.parkName }</h2>
				<p>${park.description }</p>
				<p class="text-right"><a href="<c:url value="/description?parkCode=${park.code}" />">More Info...</a>
			</div>
		</div>
	</c:forEach>

</div>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />