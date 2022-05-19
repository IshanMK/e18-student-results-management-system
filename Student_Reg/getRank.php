<?php
require "Conn.php";

$regNo = $_POST["regNo"];
//$regNo = 18028;

$mysql_query = "SELECT finalgpa, userrank FROM rankofbatch WHERE RegNo = $regNo;";

$result = mysqli_query($conn, $mysql_query);

echo "Rank";


if (mysqli_num_rows($result) >0){
    while ($row = $result->fetch_assoc()) {
        echo $row['finalgpa'].",". $row['userrank'];
    }
}
else{
    echo "0";
}



$conn -> close(); // This should be checked

?>