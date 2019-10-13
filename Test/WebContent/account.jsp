<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
	<link rel=icon type="image/png" href="title.png"/>
</head>

<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

<style>

.sidenav {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 230px;
    left: 0;
    background-color: #111;
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 25px;
    color: #818181;
    display: block;
    transition: 0.3s;
}

.sidenav a:hover {
    color: #f1f1f1;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
  
 .new{
  background: #fff;
    font-display: block;
    font-size: 40px;
    padding-left: 10px;
    padding-bottom: 10px;
 
 }
 table {
    width:100%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 15px;
    text-align: left;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color: #fff;
}
table#t01 th {
    background-color: black;
    color: white;
}
 
}

</style>

<body>

	<link href="viewbar.css" rel ="stylesheet"/>
    <h1><center> <img src="title.png" alt="Logo" style="width: 40px;height: 40px;">FAA Courier Service </center></h1>
    
    <nav class="nav">
        <div class="nav-mobile">
        <ul class="left">
            <li><a href="index.html"><i class="fa fa-home"></i>Home</a></li>
            <li><a href="track.html"><i class="fa fa-fire"></i>Tracking</a></li>
            <li><a href="account.html"><i class="fa fa-drupal"></i>My Account</a></li>
            <li><a href="about.html"><i class="fa fa-gratipay"></i>About Us</a></li>
            <li><a href="contact.html"><i class="fa fa-envelope"></i>Contact</a></li>
        </ul>     
    
        
        </div> 
    
    </nav>
    
      <div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a href="create.html">Create an order</a>
  <a href="order.html">Track an order</a>
  <a href="delete.html">Cancel an order</a>
  <a href="#">Contact</a>
</div>
    <div class="head"> 
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Options </span>
	</div>
<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
	 
	 <p> Welcome ${user}. Here are your account details: </p>
	 <table id ="t01">
	 <tr>
	 	<th> First Name </th>
	 	<td> ${first} </td>
	 </tr>
	 <tr>
	 	<th> Last Name </th>
	 	<td> ${last} </td>
	 </tr>
	 <tr>
	 	<th> Date of Birth </th>
	 	<td> ${dob} </td>
	 </tr>
	 <tr>
	 	<th> Email id: </th>
	 	<td> ${email} </td>
	 </tr>
	 </table>
	 

</body>
</html>