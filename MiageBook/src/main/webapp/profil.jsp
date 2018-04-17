<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Profil - <% out.println(session.getAttribute("psd")); %></title>
		<%@include file="header.html" %>
	</head>
	<body>
		<%@include file="navbar.jsp" %>
		
		<div class="container">
			<br/><h2>Informations personnelles</h2>
			<div class="container">
				<div id="nom"><label>Nom : </label></div>
				<div id="prenom"><label>Prénom : </label></div>
				<div id="pseudo"><label>Pseudo : </label></div>
				<div id="mail"><label>Mail : </label></div>
			</div>
		</div>
	</body>
</html>