<?php
require '../conectare.php';


$page_selected = 1;  
if (isset ($_REQUEST['page'])) {  
        $page_selected = $_GET['page'];  
}

$results_per_page = 4;  
$page_first_result = ($page_selected - 1) * $results_per_page;



if(isset($_REQUEST["term"])){
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
            
            echo '<ul class="list-group">';
            if(mysqli_num_rows($new_result) > 0){
                while($row = mysqli_fetch_array($new_result, MYSQLI_ASSOC)){
                    echo "<li class='list-group-item'>" . $row["name"] . "</li>";
                }
            } else{
            }
            echo '</ul>';
            echo '<ul class="pagination mt-10">';
            for($page = 1; $page<= $number_of_page; $page++) {  
               //add onclick function
               if($page == $page_selected)
                echo '<li class="page-item active"><p class="page-link" onclick=pageClicked(this) id="'.$page.'">'.$page.'</p></li>';
               else
                echo '<li class="page-item"><p class="page-link" onclick=pageClicked(this) id="'.$page.'">'.$page.'</p></li>';
            }
            echo '</ul>';
        } else{
            echo "ERROR: Could not able to execute $sql. " . mysqli_error($link);
        }
    mysqli_stmt_close($stmt);
    }

}


?>