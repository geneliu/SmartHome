<?php

class DB_Funckije{
    
    private $baza;
    function __construct() {
        require_once '/Baza/baza.class.php';
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
    
    
}