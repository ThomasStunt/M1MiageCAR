<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset = "UTF-8">
		<title>MiageBook - Inscription</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	</head>
	<body>
	
		<%@include file="navbar.html" %>
		
		<!--  BIG SHAQ -->
		<div class="container">
			<br/><h1>Inscription</h1>
			<br/>
			<form class="form-horizontal">
				<div class="form-group">
					<label for="inputName">Nom : </label>
					<input type="name" class="form-control" id="inputName" placeholder="Nom de compte" />
				</div>
				
				<div class="form-group">
					<label for="inputFirstname">Prénom : </label>
					<input type="name" class="form-control" id="inputFirstname" placeholder="Prénom" />
				</div>
				
				<div class="form-group">
					<label for="inputPseudo">Pseudo : </label>
					<input type="name" class="form-control" id="inputPseudo" placeholder="Pseudo" />
				</div>
				
				<div class="form-group">
					<label for="inputMail">Mail : </label>
					<input type="name" class="form-control" id="inputMail" placeholder="Mail" />
				</div>
				
				<div class="form-group">
					<label for="inputPwd">Mot de passe : </label>
					<input type="password" class="form-control" id="inputPwd" placeholder="Mot de passe" />
				</div>
				
				<div class="form-group">
					<label for="inputPwd">Confirmer le mot de passe : </label>
					<input type="password" class="form-control" id="inputPwd" placeholder="Mot de passe" />
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-outline-dark">Se connecter</button>
				</div>
			</form>
		</div>
	</body>
</html>