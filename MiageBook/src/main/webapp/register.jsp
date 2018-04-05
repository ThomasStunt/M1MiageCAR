<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MiageBook - Inscription</title>
	
		<%@include file="header.html" %>
		
		<script src="js/register.js"></script>
	</head>
	<body>
	
		<%@include file="navbar.html" %>
		
		<!--  BIG SHAQ -->
		<div class="container">
			<br/><h1>Inscription</h1>
			<br/>
			<form class="form-horizontal" id="registerForm" method="POST" action="register">
				
				<div class="form-group">
					<label for="inputName">Nom : </label>
					<input class="form-control" name="inputName" placeholder="Nom" />
				</div>
				
				<div class="form-group">
					<label for="inputFirstname">Prénom : </label>
					<input class="form-control" name="inputFirstname" placeholder="Prénom" />
				</div>
				
				<div class="form-group">
					<label for="inputPseudo">Pseudo : </label>
					<input class="form-control" name="inputPseudo" id="inputPseudo" placeholder="Pseudo" />
				</div>
				
				<div class="form-group">
					<label for="inputMail">Mail : </label>
					<input class="form-control" name="inputMail" placeholder="Mail" />
				</div>
				
				<div class="form-group">
					<label for="inputPwd">Mot de passe : </label>
					<input type="password" class="form-control" name="inputPwd" id="inputPwd" placeholder="Mot de passe" />
				</div>
				
				<div class="form-group">
					<label for="confPwd">Confirmer le mot de passe : </label>
					<input type="password" class="form-control" name="confPwd" id="confPwd" placeholder="Mot de passe" />
				</div>	
				
				<div class="form-group">
					<button id="sbmButton" class="btn btn-outline-dark" onclick="btnSubmit()">S'inscrire</button>
				</div>
			</form>
			
			<div id="errorDiv" hidden></div>
		</div>
	</body>
</html>