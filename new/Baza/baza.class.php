<?php
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Baza {
   /*
    const server = "localhost";
    const baza = "api";
    const korisnik = "root";
    const lozinka = "";
  
    
    const server = "50.62.209.157:3306";
    const baza = "air";
    const korisnik = "Xz0mbieX";
    const lozinka = "temp4ret";
    */
   
    const server = "mysql.hostinger.hr";
    const baza = "u649007527_air";
    const korisnik = "u649007527_zmb";
    const lozinka = "temp4ret";
    
    function spojiDB() {       
        $mysqli = new mysqli(self::server, self::korisnik, self::lozinka, self::baza);
        if($mysqli->connect_errno){
            echo "Neuspješno spajanje na bazu: " .$mysqli->connect_errno. ", " .$mysqli->connect_error;
        }
        mysqli_set_charset($mysqli, "utf8");
        return $mysqli;
    }
    
    function zatvoriDB($veza)
    {
        mysqli_close($veza);
    }
    
    function selectDB($upit) {
        $veza = self::spojiDB();
        
        $rezultat = $veza->query($upit) or trigger_error("Greška kod upita:  {$upit}"
                    ."Greška: " .$veza->error . " " .E_USER_ERROR);
        if(!$rezultat){
            $rezultat = null;
        }
       self::zatvoriDB($veza);
        return $rezultat;
    }
    
    function updateDB($upit, $skripta="") {
        $veza = self::spojiDB();
        $rezultat = null;
        
        if($rezultat = $veza->query($upit)){
            self::zatvoriDB($veza);
            if($skripta != ""){
                header("Location: $skripta");
            }
            return true;
    } else {
        echo "Pogreška: " .$veza->error;
       self::zatvoriDB($veza);
        return false;
        }
    }
    
  
    
    
    
}

?>