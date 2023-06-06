app.controller("contentAddCtrl",[ '$scope','apiHandler','textAngularManager',function ($scope, apiHandler,textAngularManager) {
    $scope.data = {};
    $scope.addData = () => {
        if ($scope.data.key === undefined || $scope.data.key === null || $scope.data.key === "") {
            Swal.fire("please enter key");
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value === null || $scope.data.value === "") {
            Swal.fire("please enter value");
            return;
        }
        apiHandler.callPost("content/",$scope.data,(response)=>{
            $scope.changeMenu('content-list');
        },(error)=>{
        },true);
    }

}]);