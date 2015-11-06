<?php
require_once '/Baza/DB_Funkcije.class.php';
$db= new DB_Funckije();
$response = array("error" => FALSE);

$json = file_get_contents('php://input');
$userData = json_decode($json);
$greska=$db->registrirajKorisnika($userData->{"username"}, $userData->{"password"}, $userData->{"ime"}, $userData->{"prezime"});

if($greska=="true")
{
    
    $response["error"] = "false";
        echo json_encode($response);
}
else if($greska=="false")
{
    
    $response["error"]= "true";
    echo json_encode($response);
}
else if($greska=="exist")
{
       $response["error"]= "exist";
       echo json_encode($response);
}
    
?>

