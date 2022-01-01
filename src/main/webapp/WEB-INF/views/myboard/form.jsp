<%@ page language = "java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Example</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
		  rel="stylesheet"
		  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
		  crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<form id="form" method="post" action="/save">
			<input type="hidden" name="boardSeq" value="${board == null ? 0 : board.boardSeq}"/>
	<div class="row mb-3">
		<label for="title" class="col-sm-2 col-form-label"><spring:message code="board.title"/></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="title" value="${board.title}" id="title" placeholder="<spring:message code="placeholder.required"/>">
		</div>
	</div>
	<div class="row mb-3">
		<label for="contents" class="col-sm-2 col-form-label"><spring:message code="board.contents"/></label>
		<div class="col-sm-10">
			<input type="text" class="form-control" name="content" id="contents" placeholder="<spring:message code="placeholder.required"/>">
		</div>
	</div>
	<button type="button" onclick="save();" class="btn btn-primary"><spring:message code="button.save"/></button>
</form>
	</div>
	<script>
		$(function () {
			save = function() {
				$.ajax({
					url: '/${menuType}/save',
					type: 'post',
					data: $("#form").serialize(),
					dataType: 'json',
					success: function(response) {
						console.log(response);
						if (response.code == 'SUCCESS') {
							alert(response.message);
							location.href="http://localhost:18080/"+response.path;
							//	location.href= '/ ${menuType}' + data;
						} else {
							alert(response.message);
						}
					}
				});
			}
		});
	</script>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</body>

</html>