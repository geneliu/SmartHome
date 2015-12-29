<?php
require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');


$handler = new fileHandler();
$json = file_get_contents('php://input');
$data= $handler->getBasicJson($json);
$handler->setUsername($data);


if($handler->saveFile($json,$handler->getUsername())==true)
{
    $response["error"]= "false";
}
else
{
    $response["error"]= "true";
}
echo json_encode($response);