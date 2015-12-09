<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
	<body>
	
		<h1>Smart Home</h1>
	
		<form action="login.php" id="registracija" method="POST" name="registracija" enctype="multipart/form-data">
			<label for="username">Korisničko ime:</label>
			<input type="text" name="username"  placeholder="Unesite korisničko ime" required="required" name="username" id="username" />
			<br/>
			<label for="password">Lozinka:</label>
			<input type="text" required="required" placeholder="Unesite lozinku" name="password" id="password" /><br/>
			<input type="submit"  value="Prijava" name="registracija"/>
        </form>	

		<style>
		body {background-color:white;}

		h1   {
		  color: darkgray;
		  font-size:500%;
		 margin-left:35%; 
		 margin-top:300px}
		 
		form{
			margin-left:35%;
			margin-top: 50px;
		}
		form label{
			display: inline-block;
			margin-bottom:5px;
			width: 110px;
		}
		</style>

</body>
        
</html>