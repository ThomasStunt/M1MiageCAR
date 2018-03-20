<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset = "UTF-8">
		<title>MiageBook - Connexion</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	</head>
	<body>
	
		<%@include file="navbar.html" %>
			
			<!-- BIG SHAQ -->
			<div class="container">
				<h1>Connexion</h1>
				<br/>
				<form class="form-horizontal">
					<div class="form-group">
						<label for="inputName">Nom de compte</label>
						<input type="name" class="form-control" id="inputName" placeholder="Nom de compte" />
					</div>
					
					<div class="form-group">
						<label for="inputPwd">Mot de passe</label>
						<input type="password" class="form-control" id="inputPwd" placeholder="Mot de passe" />
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-default">Se connecter</button>
					</div>
				</form>
			</div>
	</body>
</html>