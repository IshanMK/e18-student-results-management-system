<?php
require "Conn.php";     // Emberding the connection php file

$username = $_POST["user_name"];
$password = $_POST["password"];

// Query to check username and password
$mysql_query1 = "SELECT * FROM user WHERE RegNo LIKE '$username' and Password LIKE '$password';";
$mysql_query2 = "SELECT user.RegNo FROM user JOIN admins on admins.RegNo = user.RegNo WHERE user.RegNo = '$username';";

$result1 = mysqli_query($conn, $mysql_query1);
$result2 = mysqli_query($conn, $mysql_query2);

if (mysqli_num_rows($result1)>0){
    if(mysqli_num_rows($result2)>0){
        echo "1";
    }
    else{
        echo "0";
    }
    echo "Login Success";
}
else {
    echo "0Login Failed";
}

$conn->close();

?>