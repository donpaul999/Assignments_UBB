<?php
    require 'conectare.php';
?>


<!DOCTYPE html>
<html>
<head>
    <?php include 'layouts/includes.php'; ?>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
    <script type="text/javascript" src="resources/js/script.js"></script>
    <script>


    </script>
</head>
<body>
    <div class="container mt-10">
    <div class="row">
        <div ng-app="myApp" ng-controller="myCtrl">
            <div class="input-group mt-10 col-12" style='margin-top: 50px;'>
              <div class="form-outline mt-10">
                <input type="search" id="form-type" class="form-control" ng-model="textInput" ng-change="onInput()" placeholder="Type location"/>
                <input type="search" id="page-input" style='margin-top: 50px;' class="form-control" placeholder="Type page"/>
              </div>
            </div>
            <ul ng-bind="results" class="list-group">
                <li class="list-group-item" ng-repeat="(key, data) in locations">
                    {{ data }}
                </li>
            </ul>

            <ul ng-bind="pages" class="pagination mt-10">
                <li class="page-item" ng-init="pages={}" ng-repeat="x in pages">
                     {{ x }}
                </li>
            </ul>

            <p class="mt-10">
                {{error}}
            </p>
            <p class="mt-10">
                {{locations[6]}}
            </p>
        </div>

      <a href="/" type="button" class="btn btn-danger">Back</a>
    </div>
    </div>
</body>
</html>


