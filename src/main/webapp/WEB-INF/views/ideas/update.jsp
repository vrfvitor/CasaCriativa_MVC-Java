<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix = "s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<title>CasaCriativa</title>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
	</head>
	<body>
		<div id="modal">
			<div class="content">
				<h1>Nova Ideia</h1>
				<form:form action="${ s:mvcUrl('IC#save').build() }" method="POST" onsubmit="return checkFields(event)">
	
					<div class="input-wrapper">
						<label for="title">Titulo da Ideia</label> <input type="text" name="title" value="${ idea.title }">
					</div>
	
					<div class="input-wrapper">
						<label for="category">Categoria</label> 
						<select name = "category.id">
							<c:forEach items="${ categories }" var="option">
							<c:choose>
								<c:when test="${ idea.category.id == option.id }">
									<option value="${option.id}" selected>${option.name}</option>	
								</c:when>
								<c:when test="${ idea.category.id != option.id }">
									<option value="${option.id}">${option.name}</option>	
								</c:when>
							</c:choose>
						</c:forEach>
						</select>
					</div>
	
					<div class="input-wrapper">
						<label for="linkImage">Link da Imagem</label> <input type="url" name="linkImage" value="${ idea.linkImage }">
					</div>
	
					<div class="input-wrapper">
						<label for="description">Digite uma descrição para essa Ideia</label>
						<textarea name="description" cols="30" rows="5" >${ idea.description }</textarea>
					</div>
	
					<div class="input-wrapper">
						<label for="linkIdea">Link da Ideia</label> <input type="url" name="linkIdea" value="${ idea.linkIdea }">
					</div>
					<input type="hidden" name="id" value="${ idea.id }">
					<button type="submit">Salvar</button>
					<a href="javascript:history.back()">Cancelar</a>
				</form:form>
			</div>
		</div>
		
			<script src= "<c:url value = "/resources/scripts/scripts.js"/>" /></script>
	</body>
</html>