<?php
require "Conn.php";

$courseCode = $_POST["courseCode"];
$marks4assign = $_POST["marks4assignment"];
$marks4project = $_POST["marks4project"];
$marks4mid = $_POST["marks4mid"];
$marks4end = $_POST["marks4end"];
$regNo = $_POST["regNo"];
$gpa = $_POST["gpa"];

$mysql_query = "INSERT INTO usergrade (RegNo, CourseCode, Marks4Project, Marks4Assignment, Marks4Mid, Marks4End, GPA)
                VALUES($regNo,'$courseCode', $marks4project, $marks4assign, $marks4mid, $marks4end, $gpa);";

// making the output massage
if ($conn->query($mysql_query) === TRUE){
    echo "Successfully Added the marks";
}
else{
    echo "ERROR: " .$mysql_query. "<br>" .$conn->error;
}

$conn -> close(); // This should be checked

?>