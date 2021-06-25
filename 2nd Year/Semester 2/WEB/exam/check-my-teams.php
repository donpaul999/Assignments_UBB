<?php
require 'conectare.php';
session_start();
if(!isset($_SESSION['name']))
    return header("location:index.php");

$sql ="SELECT * FROM Teams WHERE members LIKE '%".$_SESSION['name']."%'";
$result = mysqli_query($conectare, $sql);

?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
    <script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body>
<div class="container text-center">
    <table class="table" id="myTable">
        <thead>
        <tr>
            <th scope="col">Nr. crt</th>
            <th scope="col">Captain</th>
            <th scope="col">Name</th>
            <th scope="col">Description</th>
            <th scope="col">Members</th>
        </tr>
        </thead>
        <tbody>
        <?php
        $i = 0;
        while ($row = mysqli_fetch_object($result)) {
            $sql ="SELECT * from Players where id= '".$row->captain_id."'";
            $result2 = mysqli_query($conectare, $sql);
            $user = mysqli_fetch_array($result2);
            $i += 1;
            echo '<tr> 
                   <th scope="row">'.$i.'</th>
                   <td>'.$user['name'].'</td>
                   <td>'.$row->name.'</td>
                   <td>'.$row->description.'</td>
                   <td>'.$row->members.'</td>
                  </tr>';
        }
        ?>
        </tbody>
    </table>


    <a href="javascript:history.back()" type="button" class="btn btn-danger mt-5">Back</a>
</div>
</body>
</html>
