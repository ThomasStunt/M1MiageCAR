<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MiageBook - Connexion</title>
		
		<%@include file="header.html" %>
		
	</head>
	<body>
	
		<%@include file="navbar.jsp" %>
			
		<!-- BIG SHAQ -->
		<div class="container">
			<br/><h1>Connexion</h1>
			<br/>
			<form class="form-horizontal" method="POST" action="connect">
				<div class="form-group">
					<label for="inputPseudo">Pseudo</label>
					<input class="form-control" name="inputPseudo" id="inputPseudo" placeholder="Pseudo" />
				</div>
				
				<div class="form-group">
					<label for="inputPwd">Mot de passe</label>
					<input type="password" class="form-control" name="inputPwd" id="inputPwd" placeholder="Mot de passe" />
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-outline-dark">Se connecter</button>
				</div>
			</form>
		</div>
	</body>
</html>