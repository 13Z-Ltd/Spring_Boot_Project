<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>

<c:url var="url" value="/viewstatus" />

<div class="row">

	<div class="col-md-8 col-md-offset-2">
	
		<jwp:pagination url="${url}" page="${page}" size="10" />
	
		<c:forEach var="statusUpdate" items="${page.content}">
		
			<c:url var="editlink" value="/editstatus?id=${statusUpdate.id}"/>
			<c:url var="deletelink" value="/deletestatus?id=${statusUpdate.id}"/>

			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="palen-titel">
						Status update added on
						<fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
							value="${statusUpdate.added}" />
					</div>
				</div>

				<div class="panel-body">					
					<!--<c:out value="${statusUpdate.text}"></c:out>-->
					<div>${statusUpdate.text}</div>
					
					<div class="edit-links pull-right">
						<a href="${editlink}">edit</a> | <a onclick="return confirm('Really delete this status?');" href="${deletelink}">delete</a>
					</div>
					
					
				</div>
			</div>

		</c:forEach>
	</div>
</div>