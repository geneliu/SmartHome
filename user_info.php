<?php
require_once(dirname(__FILE__) .'/Baza/DB_Funkcije.class.php');
$db= new DB_Funckije();
$response = array("error" => FALSE);

if(isset($_POST['username']) && isset($_POST['lozinka']))
{

    $username=$_POST['username'];
    $password=$_POST['lozinka'];

    $user=$db->podaciKorisnika($username, $password);
    if($user!=false)
    {
		while($red=$user->fetch_array())
		{
			$response["error"]= "false";
			$response["username"]= $username;
			//$response["password"]= $red[2];
			$response["ime"]=$red[3];
			$response["prezime"]=$red[4];
			break;
     }
		
        echo json_encode($response);
    }
    else
    {
        $response["error"] = "true";
		$response["username"]= $username;
		$response["password"]="";
		$response["ime"]="";
		$response["prezime"]="";
        echo json_encode($response);
    }
}
else
{
    $response["error"] = "hallo";
        echo json_encode($response);
}

?>

