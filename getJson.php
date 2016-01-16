<?php

require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');
$handler = new fileHandler();
$json = file_get_contents('php://input');
$data= $handler->getBasicJson($json);



$handler->setUsername($data);

$handler->checkIfFolderExist($handler->getUsername());

$data= $handler->checkForBigIds($data);

$checkDates=$handler->getInfo($data);

$handler->deleteFiles($data);

$testUser=$handler->getLatestFile();

$hh= $handler->compare($checkDates,$testUser,$data);

$jsonData=$handler->sendDataBackToUser($hh);


$diffs= array();

foreach($handler->getValues() as $value)
{

    // $response["diffs"][$value->oldID]="$value->newID";
    // $response["diffs"][$value->oldID]="$value->newID";
    $pep['old_id']= "$value->oldID";
    $pep['new_id']= "$value->newID";
    array_push($diffs,$pep);
}
$jsonData["diffs"]=$diffs;
$jsonData["deleted"]= array();

echo json_encode($jsonData);
