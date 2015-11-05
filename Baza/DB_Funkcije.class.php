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
    
    
}