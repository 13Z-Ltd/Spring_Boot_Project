<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="row">

	<div class="col-md-8 col-md-offset-2">
	
		<!--<c:out value="${statusUpdate}" />-->
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="palen-titel">Add a Status Update</div>
			</div>

			<!--  <div class="panel-body">-->
			
				<form:form modelAttribute="statusUpdate">
					<div class="errors">
						<form:errors path="text" />
					</div>
					<div class="form-group">
						<form:textarea path="text" name="text" rows="20" cols="50"></form:textarea>
					</div>
					
					<input type="submit" name="submit" value="Add Status" />
				</form:form>
			<!-- </div> -->
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="palen-titel">Status update added on <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s" value="${latestStatusUpdate.added}" /></div>
			</div>

			<div class="panel-body">
			
				<!--<c:out value="${latestStatusUpdate.text}"></c:out>-->
				${latestStatusUpdate.text}
			</div>
		</div>
	</div>
</div>

<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>

    <script>
      tinymce.init({
        selector: 'textarea',
        plugins: 'link',
        menubar: 'insert'
      });
    </script>