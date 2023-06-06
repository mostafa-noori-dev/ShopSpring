app.controller("blogAddCtrl", function ($scope, apiHandler,$rootScope) {
    $scope.data = {};
    $scope.addData = () => {
        $scope.data.image=$rootScope.uploadedFile;
        if ($scope.data.title === undefined || $scope.data.title === null || $scope.data.title === "") {
            Swal.fire("please enter title");
            return;
        }
        if ($scope.data.subtitle === undefined || $scope.data.subtitle === null || $scope.data.subtitle === "") {
            Swal.fire("please enter subtitle");
            return;
        }
        if ($scope.data.description === undefined || $scope.data.description === null || $scope.data.description === "") {
            Swal.fire("please enter description");
            return;
        }
        if ($scope.data.status === undefined || $scope.data.status === null) {
            Swal.fire("please set status");
            return;
        }
        if($scope.data.image===undefined || $scope.data.image===null || $scope.data.image===""){
            Swal.fire("please upload an image");
            return;
        }
        apiHandler.callPost("blog/",$scope.data,(response)=>{
            $scope.changeMenu('blog-list');
        },(error)=>{

        },true);
    }

});