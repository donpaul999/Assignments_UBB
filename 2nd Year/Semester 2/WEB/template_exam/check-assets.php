<?php
require 'conectare.php';
session_start();
if(!isset($_SESSION['username']))
    return header("location:index.php");

$sql ="SELECT * FROM assets where userid=".$_SESSION['userid'];
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
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Value</th>
    </tr>
    </thead>
    <tbody>
    <?php
        $i = 0;
        while ($row = mysqli_fetch_object($result)) {
            $i += 1;
            echo '<tr '.(($row->value > 10) ? 'style=color:red;' : '').'> 
                   <th scope="row">'.$i.'</th>
                   <td>'.$row->name.'</td>
                   <td>'.$row->description.'</td>
                   <td>'.$row->value.'</td>
                  </tr>';
        }
        ?>
    </tbody>
    </table>

    <div class="form-group mb-2">
        <input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" required>
    </div>
    <div class="form-group mb-2">
        <input type="text" class="form-control" id="description" name="description" placeholder="Enter Description" required>
    </div>
    <div class="form-group mb-3">
        <input type="number" class="form-control" id="value" name="value" placeholder="Enter Value" required>
    </div>

    <button type="submit" onclick="addAsset()" class="btn btn-primary">Submit</button> </br>
    <a href="javascript:history.back()" type="button" class="btn btn-danger mt-5">Back</a>
</div>
</body>
</html>
