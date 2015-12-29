<?php

require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');
$handler = new fileHandler();
$json = file_get_contents('php://input');
$data= $handler->getBasicJson($json);
$checkDates=$handler->getInfo($data);
$testUser=$handler->getLatestFile();

echo "\n Different files: ";
$hh= $handler->compare($checkDates,$testUser);
$jsonData=$handler->sendDataBackToUser($hh);
echo "\n";
echo json_encode($jsonData);