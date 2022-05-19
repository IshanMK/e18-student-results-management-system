<?php
require "Conn.php";     // Emberding the connection php file

$username = "Jeewantha";
$password = "root";

// Query to check username and password
$mysql_query = "SELECT * FROM student WHERE username LIKE '$username' and password LIKE '$password';";
$result = mysqli_query($conn, $mysql_query);

if (mysqli_num_rows($result)>0){
    while ($row = $result->fetch_assoc()){
        echo $row["RegNo"];
    }
}
else {
    echo "Login Failed";
}

$conn->close();

?>