<?php
require_once(dirname(__FILE__) .'/DB_Funkcije.class.php');

class fileHandler{


    private $target_dir ="./Files/";
    private $baza;


    function __construct() {

        $this->baza=new DB_Funckije();
    }
    public function saveFile($jsonFile,$username)
    {
        $successfull= true;
        $user_dir= $this->target_dir . $username. "/";
        $this->checkIfFolderExist($username);
        $today= date("Y-m-d_H:i:s");

        $target_file = $user_dir . $today . ".txt";

        $fileToSave= fopen($target_file,"w") or die($successfull=false);
        fwrite($fileToSave,$jsonFile);


        $this->baza->saveToFileTable($target_file,$username);

        return $successfull;
    }

    public function checkIfFolderExist($username)
    {
        $user_dir= $this->target_dir . $username;
        if(!is_dir($user_dir))
        {
            mkdir($user_dir,0777,true);
        }


    }

    public function readFile($username)
    {
        $path=$this->baza->getLastFile($username);
        $file= file_get_contents($path);
        return json_decode($file,true);


    }

    public function getListOfDates($data)
    {
        foreach($data->houses as $houses)
        {
            echo $houses->remoteID;
            echo $houses->last_modified;
        }
    }

    public function getBasicJson($json)
    {
        $data=json_decode($json);
    }

    public function compareDates()
    {

    }





}