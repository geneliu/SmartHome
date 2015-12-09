<?php
require_once(dirname(__FILE__) .'/Baza/DB_Funkcije.class.php');
$db= new DB_Funckije();
$response = array("error" => FALSE);

if(isset($_POST['username']) && isset($_POST['password']))
{

    $username=$_POST['username'];
    $password=$_POST['password'];

    $user=$db->login($username, $password);
    if($user!=false)
    {
		$response["error"]= "false";
		$response["username"]= $username;
		//$response["password"]= $user[2];
		$response["name"]=$user[3];
		$response["surname"]=$user[4];    
		
        echo json_encode($response);
    }
    else
    {
        $response["error"] = "true";
		$response["username"]= $username;
		$response["password"]="";
		$response["name"]="";
		$response["surname"]="";
        echo json_encode($response);
    }
}
else
{
    //$response["error"] = "hello";
        echo "<h1>Smart Home</h1>";
		//json_encode($response);
}

?>

