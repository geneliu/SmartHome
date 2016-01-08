<?php
require_once(dirname(__FILE__) .'/DB_Funkcije.class.php');
require_once(dirname(__FILE__) .'/UserInfo.php');
require_once(dirname(__FILE__) .'/houseID.php');


class fileHandler{


    private $target_dir ="./Files/";
    private $baza;
    private $username;
    private $values= array();

    /**
     * @return array
     */
    public function getValues()
    {
        return $this->values;
    }


    function __construct() {

        $this->baza=new DB_Funckije();
    }
    public function saveFile($jsonFile,$username)
    {


        $successfull= true;
        $jsonFile=$this->checkForBigIds($jsonFile);
       // var_dump($jsonFile);
        $user_dir= $this->target_dir . $username. "/";
        $this->checkIfFolderExist($username);
        $today= date("Y-m-d_H:i:s");

        $target_file = $user_dir . $today . ".txt";

        $fileToSave= fopen($target_file,"w") or die($successfull=false);
        fwrite($fileToSave,$jsonFile);


        $this->baza->saveToFileTable($target_file,$username);

        if($successfull==false) return false;
        if(!empty($this->getValues())) return $this->values;
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

    public function checkForBigIds($jsonFIle)
    {
        $data = json_decode($jsonFIle);
        $i=0;
        $newIDs= array();
        $newData= $this->readFile($this->username);
        foreach($data->houses as $house)
        {
            if($house->remoteID >=10000)
            {
              if($newData != false)  $id=$this->getFreeID($newData,$newIDs);
                else $id=$i+1;
                array_push($this->values,new houseID($house->remoteID,$id));
                $data->houses[$i]->remoteID= "$id";
                array_push($newIDs,$id);

            }
            $i++;
        }
        return json_encode($data);
    }

    public function getFreeID($data,$newIDs)
    {
        $temp=false;
        $i=1;
        while($temp==false) {
            $exist=false;
            foreach ($data->houses as $house) {
                if ($i == $house->remoteID || in_array($i,$newIDs)) {
                        $exist=true;
                }

            }
            if($exist==false) $temp=true;
            else $i++;
        }

        return $i;
    }


    public function readFile($username)
    {
        $path=$this->baza->getLastFile($username);
        if($path !=false) $file= file_get_contents($path);
       return json_decode($file);


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
            return $data;
    }

    public function getInfo($data)
    {
        $info = array();
        $this->username=$data->username;
        foreach($data->houses as $house)
        {


            array_push($info,new UserInfo($house->remoteID,$house->last_modified));
            echo $house->remoteID;
            echo " ";

        }
        echo "\n";
        return $info;
    }

    public function getLatestFile()
    {

        $LatestFile=$this->readFile($this->username);
        $transform=$this->getInfo($LatestFile);
        return $transform;
    }

    public function compare($fileReceived,$latestUserFile)
    {
        $remoteIDs= array();
        foreach($latestUserFile as $v)
        {
            if($this->compareDates($fileReceived,$v->remoteID,$v->date) != false)
            {
                array_push($remoteIDs,$v->remoteID);
                echo $v->remoteID;
            }

        }

        return $remoteIDs;
    }

    public function compareDates($fileReceived,$remotedID,$date)
    {
        $temp= false;
        foreach($fileReceived as $v)
        {

            if($v->remoteID==$remotedID && $v->date < $date)
                return true;
            if($v->remoteID==$remotedID) $temp=true;

        }
        if($temp==false) return true;
        return false;
    }

    public function sendDataBackToUser($ids)
    {

        $i=0;
        $latestFile=$this->readFile($this->username);
        foreach($latestFile->houses as $house)
        {
            if($this->isRemotedIdInList($ids,$house->remoteID) == false)
            {
                unset($latestFile->houses[$i]);
            }
            $i++;
        }
        return $latestFile;
    }

    public function isRemotedIdInList($listID,$id)
    {
        foreach($listID as $item)
        {
            if($item==$id) return true;
        }
        return false;
    }

    /**
     * @return mixed
     */
    public function getUsername()
    {
        return $this->username;
    }

    public function setUsername($json)
    {
        $this->username=$json->username;
    }

}