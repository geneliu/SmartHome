<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <form action="user_info.php" id="registracija" method="POST" name="registracija" enctype="multipart/form-data">
                    <label for="username">Korisničko ime</label>
                    <input type="text" name="username"  placeholder="username" required="required" name="username" id="username" />
                    <br/>
                    <label for="lozinka">Lozinka</label>
                    <input type="text" required="required" name="lozinka" id="lozinka" /><br/>
                    <input type="submit"  value="login" name="registracija"/>
                </form>
        
</html>
