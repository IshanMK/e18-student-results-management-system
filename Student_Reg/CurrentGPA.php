<?php
require "Conn.php";

$regNo = $_POST["regNo"];
//$regNo = 18028;

$mysql_query1 = "SELECT SUM(GPA) FROM usergrade WHERE RegNo = $regNo AND Marks4End != 0;";
$mysql_query2 = "SELECT SUM(CourseCredits) FROM courses WHERE CourseCode IN (SELECT CourseCode FROM usergrade WHERE RegNo = $regNo AND Marks4End != 0);";

$result1 = mysqli_query($conn, $mysql_query1);
$result2 = mysqli_query($conn, $mysql_query2);

echo "gpa";

$gpaSum = 1;
$creditSum = 1;


if (mysqli_num_rows($result1) >0 && mysqli_num_rows($result2) >0){
    while ($row1 = $result1->fetch_assoc()) {
        $gpaSum = (double)$row1["SUM(GPA)"];
    }
    while ($row2 = $result2->fetch_assoc()) {
        $creditSum = (double)$row2["SUM(CourseCredits)"];
    }
        $gpa = $gpaSum/$creditSum;
        echo $gpa;
    
}
else{
    echo "0";
}



$conn -> close(); // This should be checked

?>