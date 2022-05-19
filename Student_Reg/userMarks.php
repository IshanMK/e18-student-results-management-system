<?php
require "Conn.php";

$courseCode = $_POST["course"];
$regNo = $_POST["regNo"];
/* $courseCode = "CO224";
$regNo = 17345; */

$mysql_query = "SELECT Marks4Project, Marks4Assignment, Marks4Mid FROM usergrade WHERE CourseCode = '$courseCode' AND RegNo = $regNo;";
$mysql_query2 = "SELECT Marks4End FROM courses WHERE CourseCode = '$courseCode';";

$result = mysqli_query($conn, $mysql_query);
$result2 = mysqli_query($conn, $mysql_query2);

echo "Marks";


if (mysqli_num_rows($result) >0){
    while ($usermarks = $result->fetch_assoc()) {
        echo $usermarks['Marks4Project'].",". $usermarks['Marks4Assignment'].",". $usermarks['Marks4Mid'];
    }

    while ($coursemark = $result2->fetch_assoc()){
        echo ",".$coursemark['Marks4End'];
    }
}
else{
    echo "0";
}



$conn -> close(); // This should be checked

?>