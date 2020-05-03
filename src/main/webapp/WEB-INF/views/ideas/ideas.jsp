<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>


<tags:pageTemplate title="Ideias" bodyId = "page-ideas">

	<div id="container">

		<header>
			<a href="${ s:mvcUrl('IC#home').build() }"> <img
				src="<c:url value="/resources/img/logo.png" />"
				alt="logo da empresa casa criativa">
			</a>
			
			<form action="${ s:mvcUrl('IC#read').build() }">
					<input type="text" placeholder="Palavra chave..." name="search">
				<div>
				<select name = "categoryId">
						<option value="${null}">Categoria...</option>						
					<c:forEach items="${ categories }" var="option">
						<option value="${option.id}">${option.name}</option>	
					</c:forEach>
				</select>
					<button type="submit">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</form>
			<nav>
				<li><a href="${ s:mvcUrl('IC#pageIdeas').build() }">Inicio</a></li>
				<li><a href="#" onclick="onOff()" class="buttons">Nova Ideia</a></li>
			</nav>
		</header>

		<main>
			<h4>${ result }</h4>
			<section id="title">
				<p>Em Exibição</p>
				<h1>Ideias</h1>
			</section>

			<section id="ideas">
				<%@ include file="/WEB-INF/views/listIdeas.jsp" %>
			</section>
		</main>

		<footer>
			D️esenvolvido por <a href="https://www.linkedin.com/in/vrfvitor/"
				target="_blank">Vitor R.</a>
		</footer>

	</div>

</tags:pageTemplate>