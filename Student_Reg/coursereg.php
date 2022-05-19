<?php
require "Conn.php";

$courseName = $_POST["courseName"];
$courseCode = $_POST["courseCode"];
$courseCredit = $_POST["courseCredit"];
$project = $_POST["project"];
$assign = $_POST["assign"];
$mid = $_POST["mid"];
$end = $_POST["end"];

$mysql_query = "INSERT INTO courses (CourseCode, CourseName, CourseCredits, Marks4Project, Marks4Assignment, Marks4Mid, Marks4End)
                VALUES('$courseCode', '$courseName', $courseCredit, $project, $assign, $mid, $end);";

// making the output massage
if ($conn->query($mysql_query) === TRUE){
    echo "Successfully Registered the Course";
}
else{
    echo "ERROR: " .$mysql_query. "<br>" .$conn->error;
}

$conn -> close(); // This should be checked

?>