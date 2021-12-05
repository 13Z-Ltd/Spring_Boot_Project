<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@  taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">

	<div class="col-md-8 col-md-offset-2">
		<div class="errors">
			<form:errors path="profile.*" />
		</div>

		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="palen-titel">Edit Your 'About' Text</div>
			</div>

			<form:form modelAttribute="profile">


				<div class="form-group">
					<form:textarea path="about" name="about" rows="20" cols="50"></form:textarea>
				</div>

				<input type="submit" name="submit" value="Save" />
			</form:form>

		</div>

	</div>
</div>

<script
	src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js"
	referrerpolicy="origin"></script>

<script>

	tinymce.init({
		selector : 'textarea'
	});
	
</script>