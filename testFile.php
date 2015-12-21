<?php
require_once(dirname(__FILE__) .'/Baza/fileHandler.class.php');

$handler = new fileHandler();
$haloo="dasdsdsa";
echo $handler->saveFile($haloo,"6");