app.controller('GetFilesController', function ($scope, $http, apiHandler) {

    $scope.allFiles = [];

    $scope.showTable=false;

    $scope.getAllFiles = function () {

        // REST URL:
        let url = "utils/upload/getAllFiles";

        apiHandler.callGet(url, (response) => {
            $scope.allFiles = response.dataList;
            $scope.showTable=true;
        },(Error)=>{

        },true)

    };
});