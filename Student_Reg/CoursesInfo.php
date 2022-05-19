<?php
require "Conn.php";

$courseCode = $_POST["course"];
//$courseCode = "CO222";

$mysql_query = "SELECT * FROM courses WHERE CourseCode = '$courseCode';";

$result = mysqli_query($conn, $mysql_query);

echo "course";


if (mysqli_num_rows($result) >0){
    while ($row = $result->fetch_assoc()) {
        echo $row['CourseCredits'].",". $row['Marks4Project'].",". $row['Marks4Assignment'].",". $row['Marks4Mid'].",". $row['Marks4End'];
    }
}
else{
    echo "0";
}



$conn -> close(); // This should be checked

?>