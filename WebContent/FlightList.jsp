<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight List</title>
</head>
<body >
<br>
<a href=HomePage.jsp style="color:black;text-decoration:none;font-size:35px;font-weight:bold;">FlyAway</a>
<br><br>
<%
	@SuppressWarnings("unchecked")
	List<String[]> flights=(List<String[]>)session.getAttribute("flights");
	if(flights!=null){
%>
<a href="logout">Logout</a>

<h1>Available Flights</h1>

<center>
<table border="1">
<tr>
	<th>Name</th>
	<th>Time</th>
	<th>Price</th>
	<th>Book</th>
</tr>



<%
	for(String[] flight:flights){
%>
<center>
<table border="1">
<td><%=flight[0]%></td>
<td><%=flight[1]%></td>
<td><%=flight[2]%></td>
<td><a href=BookFlight.jsp>Book Now</a></td>
</tr>
</center>
</table>
</center>

<%
	}
%>


<%
	}
	else{
%>
<h1>There are no available flights</h1>
<%
	}
%>
</body>
</html>