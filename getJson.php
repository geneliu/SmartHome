<?php

require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');
$handler = new fileHandler();
$json = file_get_contents('php://input');
$data= $handler->getBasicJson($json);
$checkDates=$handler->getInfo($data);

$handler->deleteFiles($data);

$testUser=$handler->getLatestFile();

$hh= $handler->compare($checkDates,$testUser);

$jsonData=$handler->sendDataBackToUser($hh);
echo json_encode($jsonData);