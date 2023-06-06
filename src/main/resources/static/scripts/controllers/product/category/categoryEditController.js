app.controller("categoryEditCtrl", function ($scope, apiHandler, $rootScope) {
    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.editData = () => {
        if ($rootScope.uploadedFile != undefined && $rootScope.uploadedFile != null && $rootScope.uploadedFile != "")
            $scope.data.image = $rootScope.uploadedFile;
        if ($scope.data.title === undefined || $scope.data.title === null || $scope.data.title === "") {
            Swal.fire("please enter title");
            return;
        }
        if ($scope.data.enable === undefined || $scope.data.enable === null) {
            Swal.fire("please set enable");
            return;
        }
        if ($scope.data.image === undefined || $scope.data.image === null || $scope.data.image === "") {
            Swal.fire("please upload an image");
            return;
        }

        apiHandler.callPut("productCategory/", $scope.data, (response) => {
            $scope.changeMenu('category-list');
        }, (error) => {

        }, true);
    }
    $scope.getData = () => {
        apiHandler.callGet("productCategory/" + $scope.id, (response) => {
            $scope.data = response.dataList[0];
        }, (error) => {

        }, true);
    }
    $scope.getData();
});