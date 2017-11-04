<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="homepage.css" rel="stylesheet"/>
</head>
<body>
<h1>Welcome to CitySafety</h1>
<form method="post" action="./CalculateSafety" selected="">
<p id="title">Select the City in Connecticut:</p> 
<select id="cities" name="city">
 <option value="Hartford">Hartford</option> 
 <option value="Stamford">Stamford</option>
 <option value="Bridgeport">Bridgeport</option>
 <option value="Greenwich">Greenwich</option>
 <option value="Norwalk">Norwalk</option>
 <option value="Waterbury">Waterbury</option>
 <option value="Danbury">Danbury</option>
 <option value="Fairfield">Fairfield</option>
 <option value="Milford">Milford</option>
 <option value="Westport">Westport</option>
 <option value="New Britian">New Britian</option>
 <option value="Hamden">Hamden</option>
 <option value="Manchester">Manchester</option>
</select>
</br></br>
<input type="submit" value="Check Rating" />
<p class="disc" style="color:white">**Greater the Rank Safer the city**</p>
</form>
<% 
String msg=(String)request.getAttribute("result"); 
   if (msg == null ) 
   {
	   msg = "";
	   }
%>
<h3 id="msg"><%=msg%></h3>
 
</body>
</html>