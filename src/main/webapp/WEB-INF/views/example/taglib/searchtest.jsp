<%@ page language = "java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
	<title>Example Search</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
<div class="container">
	<h2>게시물 목록</h2>
	<form action="" method="get">
		<div class="mb-3 row">
			<label for="exampleFormControlInput1" class="col-sm-2 col-form-label">종류</label>
			<div class="col-sm-10">
				<c:forEach var="boardType" items="${boardTypes}" varStatus="status">
					<div class="form-check form-check-inline">
						<input class="form-check-input" name="boardTypes" type="checkbox" value="${boardType.code()}" ${paramMap.boardTypes == 'A' ? 'checkd="checked"' : ''} id="flexCheckDefault">
						<label class="form-check-label" for="flexCheckDefault">
								${boardType.label()}
						</label>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="mb-3 text-center">
			<button type="submit" class="btn btn-primary">검색하기</button>
		</div>
	</form>
	<table class="table">
		<th>First</th>
		<th>Last</th>
		<th>Handle</th>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>3</td>
		</tr>
	</table>
</div>

<c:forEach var="id" items="${ids}">
	<p>${id}</p>
</c:forEach>
</body>
</html>