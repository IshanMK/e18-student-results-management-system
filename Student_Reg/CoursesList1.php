<?php
require "Conn.php";

$command = $_POST["command"];


$mysql_query = "SELECT CourseCode FROM courses;";

$result = $conn->query($mysql_query);

echo "1";

if ($command == 1){
    if (mysqli_num_rows($result) >0){
        while ($row = $result->fetch_assoc()){
            echo $row["CourseCode"].",";
        }
    }
    else{
        echo "0";
    }
}


$conn -> close(); // This should be checked

?>