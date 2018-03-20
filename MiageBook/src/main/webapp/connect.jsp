<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset = "UTF-8">
		<title>MiageBook - Connexion</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	
		<%@include file="navbar.html" %>
			
		<!-- BIG SHAQ -->
		<div class="container">
			<br/><h1>Connexion</h1>
			<br/>
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputName">Pseudo</label>
					<input type="name" class="form-control" id="inputName" placeholder="Pseudo" />
				</div>
				
				<div class="form-group">
					<label for="inputPwd">Mot de passe</label>
					<input type="password" class="form-control" id="inputPwd" placeholder="Mot de passe" />
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-outline-dark">Se connecter</button>
				</div>
			</form>
		</div>
	</body>
</html>