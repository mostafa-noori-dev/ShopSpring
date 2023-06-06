app.controller("colorAddCtrl", function ($scope, apiHandler) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.name === undefined || $scope.data.name === null || $scope.data.name === "") {
            Swal.fire("please enter name");
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value === null || $scope.data.value === "") {
            Swal.fire("please enter value");
            return;
        }
        apiHandler.callPost("color/",$scope.data,(response)=>{
            $scope.changeMenu('color-list');
        },(error)=>{

        },true);
    }

});