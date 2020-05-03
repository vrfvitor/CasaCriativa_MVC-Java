<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="modal" class="hide">
	<div class="content">
		<h1>Nova Ideia</h1>
		<form action="${ s:mvcUrl('IC#save').build() }" method="POST" onsubmit="return checkFields(event)">
			<div class="input-wrapper">
				<label for="title">Titulo da Ideia</label> <input type="text" name="title">
			</div>
					<div class="input-wrapper">
						<label for="category">Categoria</label> 
						<select name = "category.id">
							<c:forEach items="${ categories }" var="option">
								<option value="${option.id}">${option.name}</option>	
							</c:forEach>
						</select>
					</div>
			<div class="input-wrapper">
				<label for="linkImage">Link da Imagem</label> <input type="url" name="linkImage">
			</div>
			<div class="input-wrapper">
				<label for="description">Digite uma descrição para essa Ideia</label>
				<textarea name="description" cols="30" rows="5"></textarea>
			</div>
			<div class="input-wrapper">
				<label for="linkIdea">Link da Ideia</label> <input type="url" name="linkIdea">
			</div>
			<button type="submit">Salvar</button>
			<a href="#" onclick="onOff()">Cancelar</a>
		</form>
	</div>
</div>
