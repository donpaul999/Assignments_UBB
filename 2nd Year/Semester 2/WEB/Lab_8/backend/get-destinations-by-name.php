<?php
require '../conectare.php';


$page_selected = 1;  
if ($_REQUEST['page'] != 0) {  
        $page_selected = $_GET['page'];  
}

$results_per_page = 4;  
$page_first_result = ($page_selected - 1) * $results_per_page;



if(isset($_REQUEST["term"])){
    $data = array();
    $results = array();
    $pages = array();
    $sql = "SELECT * FROM destinations WHERE name LIKE ?";
    if($stmt = mysqli_prepare($conectare, $sql)){
        mysqli_stmt_bind_param($stmt, "s", $param_term);

        $param_term = $_REQUEST["term"] . '%';

        if(mysqli_stmt_execute($stmt)){
            $result = mysqli_stmt_get_result($stmt);
            $number_of_result = mysqli_num_rows($result);  
        
            $number_of_page = ceil ($number_of_result / $results_per_page);  
            
            $query = "SELECT * FROM destinations WHERE name LIKE '$param_term' LIMIT " . $page_first_result . ',' . $results_per_page; 
            
            $new_result = mysqli_query($conectare, $query);  
            
            if(mysqli_num_rows($new_result) > 0){
                while($row = mysqli_fetch_array($new_result, MYSQLI_ASSOC)){
                    $s["id"] = $row['id'];
                    $s["name"] = $row['name'];
                    array_push($results, $s);
                }
            } else{
            }
            
            for($page = 1; $page<= $number_of_page; $page++) {
               $s["nr"] = $page;
               $s["selected"] = 0;
               if($page == $page_selected)
                 $s["selected"] = 1;
               array_push($pages, $s);
            }
            
        } else{
            echo "ERROR: Could not able to execute $sql. ";
        }
    mysqli_stmt_close($stmt);
    $data['locations'] = $results;
    $data['pages'] = $pages;
    }
   echo json_encode($data);

}


?>