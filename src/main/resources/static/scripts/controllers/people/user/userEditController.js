app.controller("userEditCtrl", function ($scope, apiHandler,$rootScope) {
    $scope.data={};
    $scope.id=$rootScope.dataId;
    $scope.editData = () => {
        if ($scope.data.lastName === undefined || $scope.data.lastName === null || $scope.data.lastName === "") {
            Swal.fire("please enter lastname");
            return;
        }
        if ($scope.data.email === undefined || $scope.data.email === null || $scope.data.email === "") {
            Swal.fire("please enter email");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable === null) {
            Swal.fire("please set enable");
            return;
        }

        apiHandler.callPut("user/",$scope.data,(response)=>{
            $scope.changeMenu('user-list');
        },(error)=>{

        },true);
    }
    $scope.getData=()=>{
        apiHandler.callGet("user/"+$scope.id,(response)=>{
            $scope.data=response.dataList[0];
        },(error)=>{

        },true);
    }
    $scope.getData();
});