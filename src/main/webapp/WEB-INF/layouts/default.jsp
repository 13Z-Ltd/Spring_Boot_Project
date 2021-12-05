<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/> 	

<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script src="/js/bootstrap.min.js"></script>

<script src="/webjars/sockjs-client/sockjs.min.js" ></script>
<script src="/webjars/stomp-websocket/stomp.min.js" ></script>

<title><tiles:insertAttribute name="title" /></title>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${contextRoot}/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextRoot}/css/main.css">

<!-- This is only for javascript tagging on the profile -->
<script src="${contextRoot}/js/jquery-ui.min.js"></script>
<script src="${contextRoot}/js/tag-it.min.js"></script>

<script src="${contextRoot}/js/connectionmanager.js"></script>

<link rel="stylesheet" href="${contextRoot}/css/jquery.tagit.css">

<tiles:insertAttribute name="chatnotifications"></tiles:insertAttribute>
<tiles:insertAttribute name="chatviewscript" ignore="true"></tiles:insertAttribute>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Raleway:400,500,500i,700,800i"
	rel="stylesheet">


</head>
<body>
	<nav class="navbar navbar-expand-sm   navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item"><a class="nav-link" href="${contextRoot}/">Spring Boot Tutorial
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${contextRoot}/about">About</a></li>
				<li class="nav-item dropdown dmenu"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Our Service </a>
					<div class="dropdown-menu sm-menu">
						<a class="dropdown-item" href="#">service2</a> <a
							class="dropdown-item" href="#">service 2</a> <a
							class="dropdown-item" href="#">service 3</a>
					</div></li>
				<!-- <li class="nav-item"><a class="nav-link" href="#">Contact
						Us</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Call</a></li> -->
			</ul>
			<ul class="nav navbar-nav navbar-middle">

				<sec:authorize access="!isAuthenticated()">

					<li><a href="${contextRoot}/login">Login</a></li>
					<li><a href="${contextRoot}/register">Register</a></li>

				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<li><a href="${contextRoot}/profile">Profile</a></li>
					<li><a href="javascript:$('#logoutForm').submit();">Logout</a></li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Status </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a href="${contextRoot}/addstatus">Add Status</a></li>
							<li><a href="${contextRoot}/viewstatus">View Status
									Updates</a></li>
						</ul></li>
				</sec:authorize>

			</ul>
			<div class="social-part navbar-right">
				<i class="fa fa-facebook" aria-hidden="true"></i> <i
					class="fa fa-twitter" aria-hidden="true"></i> <i
					class="fa fa-instagram" aria-hidden="true"></i>
			</div>
		</div>
	</nav>

	<c:url var="logoutLink" value="/logout" />
	<form id="logoutForm" method="post" action="${logoutLink}">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="container">
		<tiles:insertAttribute name="content" />
	</div>
	
	<sec:authorize access="isAuthenticated()">
	<script type="text/javascript">
		connectionManager.connect();
	</script>
	</sec:authorize>

</body>
</html>