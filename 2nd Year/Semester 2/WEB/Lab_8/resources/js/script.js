var app = angular.module('myApp', []);
app.controller('myCtrl', ['$scope','$http', function ($scope, $http){
    var inputMin = 2;
    $scope.onInput = function() {
        $scope.pages = '';
        $scope.error = '';
        $scope.locations = '';
        if($scope.textInput.length >= inputMin) {
            $http({
                method : "GET",
                url : "backend/get-destinations-by-name.php",
                params: {
                    term : $scope.textInput
                }
            }).then(function mySuccess(response) {
                $scope.locations = response.data;
                console.log(response.data);
                $scope.error = "All good";
            }, function myError(response) {
                $scope.error = response.statusText;
            });
        }
    }
}]);




/*
$(document).ready(function(){
        $('#form-type').on("keyup", function(){
            var inputVal = $(this).val();
            var resultDropdown = $("#result");
            if(inputVal.length > 1){
                $.get("../../backend/get-destinations-by-name.php", {term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        });
        
        $('#page-input').on("keyup", function(){
            var inputVal = $('#form-type').val();
            var pageVal = $(this).val();
            var resultDropdown = $("#result");
            if(inputVal.length > 1){
                $.get("../../backend/get-destinations-by-name.php", {page: pageVal, term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        });

});


$('.page-link').on("click", function(){
            var inputVal = $('#form-type').val();
            var pageVal = $(this).id;
            var resultDropdown = $("#result");
            console.log(inputVal);
            console.log(pageVal);
            console.log(resultDropdown);
            if(inputVal.length > 1){
                $.get("../../backend/get-destinations-by-name.php", {page: pageVal, term: inputVal}).done(function(data){
                    // Display the returned data in browser
                    resultDropdown.html(data);
                });
            } else{
                resultDropdown.empty();
            }
        });

*/