<?php
require_once(dirname(__FILE__) .'/Baza/DB_Funkcije.class.php');
$db= new DB_Funckije();
$response = array("error" => FALSE);

if(isset($_POST['username']) && isset($_POST['lozinka']))
{

    $username=$_POST['username'];
    $password=$_POST['lozinka'];

    $user=$db->provjeriKorisnika($username, $password);
    if($user!=false)
    {
        
        $reposnse["error"]= FALSE;
        $response["username"]= $user;
        echo json_encode($response);
    }
    else
    {
        $response["error"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
}
else
{
    $response["error"] = "hallo";
        echo json_encode($response);
}

?>

