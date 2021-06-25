<?php

require '../conectare.php';
session_start();

$req_arr = $_POST['req_arr'];
print_r ($req_arr);

foreach ($req_arr as $val) {
    $team = $val['team'];
    $player = $val['player'];
    $sql ="SELECT * from Players where name= '".$player."'";
    $result = mysqli_query($conectare, $sql);
    $count = mysqli_num_rows($result);
    if($count == 0) {
        continue;
    }
    $uid = $_SESSION['userid'];

    $sql ="SELECT * from Teams where name= '".$team."'";
    $result = mysqli_query($conectare, $sql);
    $count = mysqli_num_rows($result);
    if($count == 0) {
        $sql ="INSERT INTO Teams (captain_id, name, members) VALUES (".$uid.",'$team', '$player')";
    }
    else {
        $sql = "UPDATE Teams SET members = CONCAT(members, ' ".$player."') WHERE name = '".$team."'";
    }
    echo $sql;
    $result = mysqli_query($conectare, $sql);
}
?>
