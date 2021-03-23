<?php

require 'conectare.php';
$result = '';
$location = '';
$country = '';
$description = '';
$targets = '';
$costs = '';
try{

    $id = $_GET['id'];
    $sql ="SELECT * FROM destinations where id=".$id;

    $result = mysqli_query($conectare, $sql);
    $result = mysqli_fetch_object($result);
    
}
catch (Exception $e) {
    session_start();
    $_SESSION['message'] = $e->getMessage();
    return header("location:/");
}
?>

<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
</head>
<body>
    <div class="container">
    <div class="row">
    <form method="POST" action="backend/modify-destination-backend.php">
      <input type="hidden" name="id" value="<?php echo $id; ?>" />
      <div class="form-group">
        <label for="location">Location</label>
        <input type="text" class="form-control" id="location" name="location" placeholder="Enter Location" value="<?php echo $result->name; ?>" required>
      </div>
      <div class="form-group">
        <label for="country">Country</label>
        <input type="text" class="form-control" id="country" name="country" placeholder="Enter Country" value="<?php echo $result->country; ?>" required>
      </div>
     <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description" name="description" placeholder="Enter Description" value="<?php echo $result->description; ?>" required>
      </div>
     <div class="form-group">
        <label for="targets">Tourist Targets</label>
        <input type="text" class="form-control" id="targets" name="targets" placeholder="Enter Targets" value="<?php echo $result->targets; ?>" required>
      </div>
      <div class="form-group">
        <label for="costs">Costs</label>
        <input type="number" class="form-control" id="costs" name="costs" value="<?php echo $result->cost;?>" required>
      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
      <a href="/" type="button" class="btn btn-danger">Back</a>
    </form>
    </div>
    </div>
</body>
</html>


