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
        $diffs= array();
    }
    else
    {
        $response["changes"]= "false";
    }
    foreach($handler->getValues() as $value)
    {

       // $response["diffs"][$value->oldID]="$value->newID";
       // $response["diffs"][$value->oldID]="$value->newID";
       $pep['old_id']= "$value->oldID";
        $pep['new_id']= "$value->newID";
        array_push($diffs,$pep);
    }
    if(!empty($handler->getValues()))
    {
        $response["diffs"]=$diffs;
    }
}
else
{
    $response["error"]= "true";
}
echo json_encode($response);