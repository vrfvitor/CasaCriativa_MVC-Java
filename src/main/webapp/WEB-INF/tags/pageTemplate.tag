<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyId" required="false" %>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<title>${ title } - CasaCriativa</title>
	</head>

	<body id="${bodyId}">
	
    	<jsp:doBody />

		<%@ include file="/WEB-INF/views/modal.jsp" %>

    	<script src= "<c:url value = "/resources/scripts/scripts.js"/>"/></script>

    </body>
</html>

