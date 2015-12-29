<?php

class UserInfo
{

    public $remoteID;
    public $date;

    function __construct($houseID,$date) {

        $this->remoteID= $houseID;
        $this->date= $date;


    }
}