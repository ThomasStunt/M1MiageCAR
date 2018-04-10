<nav class="navbar bg-dark">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="home.jsp" style="color: white;">MiageBook</a>
		</div>
<% if (session.getAttribute("name") != null) { %>
	    <div class="btn-toolbar">
			<button type="button" class="btn btn-outline-light" onclick="location.href = 'register.jsp'">S'inscrire</button>
			 &nbsp;
			<button type="button" class="btn btn-outline-light" onclick="location.href = 'connect.jsp'">Se connecter</button>
		</div>	
<% } else {%>
		<span class="navbar-text">
			<font color="white">Connected as : <% request.getSession().getAttribute("name"); %></font>
		</span>
		<div class="btn-toolbar">
			<button type="button" class="btn btn-outline-light" onclick="disconnect()">Se déconnecter</button>
		</div>
<% }; %>
			
	</div>
</nav>