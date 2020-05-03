<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix = "s" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
	
<tags:pageTemplate title="Home">

	<div id="container">

		<section id="intro">
			<header>
				<a href="#">
				<img src="<c:url value="/resources/img/logo.png" />" alt="logo da empresa casa criativa">
				</a>
			</header>

			<main>
				<p>Ficar em casa em períodos longos, não deve ser nada fácil.</p>

				<p>
					Por isso, iremos catalogar ideias: <strong>brincadeiras,
						jogos, filmes, livros, cursos, dicas </strong> e tudo que for necessário
					para tornar esse momento mais interessante.
				</p>

				<p>Comece clicando em ver ideias para ver as ideias cadastradas
					e contribua adicionando a sua ideia.</p>
			</main>

			<section id="buttons">
				<button onclick="onOff()">+ Adicionar Ideias</button>
				<a href="/casacriativa/pageIdeas" class="buttons"> Ver Ideias </a>
			</section>

			<footer>
				D️esenvolvido por <a href="https://www.linkedin.com/in/vrfvitor/" target="_blank">Vitor R.</a>
			</footer>
		</section>

		<section id="last-ideas">
			<%@ include file="/WEB-INF/views/listIdeas.jsp" %>
		</section>

	</div>
	
</tags:pageTemplate>