<%@ page session="true" %>
<%
	boolean cook = true;
	String psd = null;
    Cookie cookie = null;
	Cookie[] cookies = null;
	cookies = request.getCookies();
    if( cookies != null) {
    	for (int i = 0; i < cookies.length; i++){
        	cookie = cookies[i];
        	if(cookie.getName().contentEquals("psd"))
         		psd = cookie.getValue();
     	}
    } else {
    	cook = false;
    }
%>
<nav class="navbar bg-dark">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="home.jsp" style="color: white;">MiageBook</a>
		</div>
<% if (!cook) { %>
	    <div class="btn-toolbar">
			<button type="button" class="btn btn-outline-light" onclick="location.href = 'register.jsp'">S'inscrire</button>
			 &nbsp;
			<button type="button" class="btn btn-outline-light" onclick="location.href = 'connect.jsp'">Se connecter</button>
		</div>	
<% } else {%>
		<span class="navbar-text">
			<font color="white">Connected as : <a href="profil.jsp"><% out.println(psd); %></a></font>
		</span>
		<div class="btn-toolbar">
			<button type="button" class="btn btn-outline-light" onclick="disconnect()">Se déconnecter</button>
		</div>
<% }; %>
			
	</div>
</nav>