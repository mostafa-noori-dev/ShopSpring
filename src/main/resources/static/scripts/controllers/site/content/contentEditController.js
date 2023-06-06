app.controller("contentEditCtrl", function ($scope, apiHandler,$rootScope) {
    $scope.data={};
    $scope.id=$rootScope.dataId;
    $scope.editData = () => {
        if ($scope.data.key === undefined || $scope.data.key === null || $scope.data.key === "") {
            Swal.fire("please enter key");
            return;
        }
        if ($scope.data.value === undefined || $scope.data.value === null || $scope.data.value === "") {
            Swal.fire("please enter value");
            return;
        }

        apiHandler.callPut("content/",$scope.data,(response)=>{
            $scope.changeMenu('content-list');
        },(error)=>{

        },true);
    }
    $scope.getData=()=>{
        apiHandler.callGet("content/"+$scope.id,(response)=>{
            $scope.data=response.dataList[0];
        },(error)=>{

        },true);
    }
    $scope.getData();
});