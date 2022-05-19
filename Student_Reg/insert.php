<?php
require "Conn.php";

$password = $_POST["password"];
$firstname = $_POST["firstname"];
$lastname = $_POST["lastname"];
$regno = $_POST["regno"];
$email = $_POST["email"];
$field = $_POST["field"];

$mysql_query = "INSERT INTO user (password, firstname, lastname, regno, email, field) VALUES('$password', '$firstname', '$lastname', '$regno', '$email', '$field');";

// making the output massage
if ($conn->query($mysql_query) === TRUE){
    echo "Successfully Registered";
}
else{
    echo "ERROR: " .$mysql_query. "<br>" .$conn->error;
}

$conn -> close(); // This should be checked

?>