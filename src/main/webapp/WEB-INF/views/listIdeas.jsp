<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:forEach items="${ ideas }" var = "idea">
	<div class="idea">
		<img src="${idea.linkImage}" alt="Imagem de ${idea.title}">
		<div class="content">
			<h3>${idea.title}</h3>
			<p>${idea.category.name}</p>
			<div class="description">${idea.description}</div>
			<div id="delete">
				<a href="${idea.linkIdea}" target="_blank">Ir para a Ideia</a>
				<div class="dropdown">
					<button class="dropbtn">+ Opções</button>
			  		<div class="dropdown-content">
			  			<form action = "${ s:mvcUrl('IC#update').arg(0, idea.id).build() }" method="POST">
					    	<button type="submit">Alterar</button>
			  			</form>
			  			<form action = "${ s:mvcUrl('IC#delete').arg(0, idea.id).build() }" method="POST">
					    	<button type="submit">Excluir</button>
			  			</form>
			  		</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>