<?php
require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');


$handler = new fileHandler();
$json = file_get_contents('php://input');
$data= $handler->getBasicJson($json);
$handler->setUsername($data);


if($handler->saveFile($json,$handler->getUsername())!=false)
{
    $response["error"]= "false";
    if(!empty($handler->getValues()))
    {
        $response["changes"]= "true";
    }
    else
    {
        $response["changes"]= "false";
    }
    foreach($handler->getValues() as $value)
    {
        $response[$value->oldID]="$value->newID";
    }
}
else
{
    $response["error"]= "true";
}
echo json_encode($response);