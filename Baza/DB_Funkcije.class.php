<?php

class DB_Funckije{
    
    private $baza;
    function __construct() {
        require_once(dirname(__FILE__) .'/baza.class.php');
        $this->baza=new Baza();
    }
    
    public function provjeriKorisnika($username,$password)
    {
     $q="Select * from users where username='".$username."' and password='".$password."';";
     $u=$this->baza->selectDB($q);
     while($red=$u->fetch_array())
     {
         
         return $red[1];
     }
     return false;
    }
    public function registrirajKorisnika($username,$password,$ime,$prezime)
    {
        $q="Select * from users where username='".$username."'";
        $rez=$this->baza->selectDB($q);
         if($rez->num_rows != 0){
             return "exist";
         }
        
        
        $q="Insert into users(username,password,ime,prezime) values('".$username."','".$password."','".$ime."','"
                .$prezime."')";
        $result=$this->baza->updateDB($q);
        if($result!=false) {
            return "true";
        }
        return "false";
        
    }
	
	public function podaciKorisnika($username,$password){
		$provjera = $this->provjeriKorisnika($username,$password);
		
		if($provjera != false){
			$q = "Select * from users where username ='".$username."'; ";
			$podaci = $this->baza->selectDB($q);
			return $podaci;
		}
		return false;
		
	}
	public function login($username,$password){
		$q="Select * from users where username='".$username."' and password='".$password."';";
		$podaci = $this->baza->selectDB($q);
		if($podaci != null){
			while($red=$podaci->fetch_array()){			
				return $red;
			}
		}
		return false;
		
	}
    public function saveToFileTable($path,$username)
    {
        $id=$this->getUserID($username);
        $today = date("Y-m-d H:i:s");
        $q="insert into files(path,date,user) values('".$path."','".$today."','".$id."')";
        $this->baza->updateDB($q);
    }
    public function getUserID($username)
    {
        $q= "Select id from users where username='".$username."'";
        $podaci = $this->baza->selectDB($q);
        if($podaci != null){
            while($red=$podaci->fetch_array()){
                $id=$red['id'];
                return $id;
            }
        }
        return false;
    }

    public function getLastFile($username)
    {
        $id=$this->getUserID($username);
        $q="select path from files where user='".$id."' Group by id Desc Limit 1";
        $podaci = $this->baza->selectDB($q);
        if($podaci != null){
            while($red=$podaci->fetch_array()){
                return $red['path'];
            }
        }
        return false;
    }


    
}