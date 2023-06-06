app.controller("invoiceDetailsCtrl", function ($scope, apiHandler,$rootScope) {
    $scope.dataId=0;
    $scope.data={};
    $scope.totalPrice=0;
    $scope.totalCount=0;
    $scope.getData = () => {
        let url = "invoice/getInfo/"+$scope.dataId;
        apiHandler.callGet(url, (response) => {
            $scope.data = response.dataList[0];
            for(let i=0;i<$scope.data.orderItems.length;i++){
                $scope.totalCount+=$scope.data.orderItems[i].count;
                $scope.totalPrice+=$scope.data.orderItems[i].price;
            }
        }, (error) => {

        }, true);
    }

    $scope.init=()=>{
        $scope.dataId=$rootScope.invoiceId;
        $scope.getData();
    }

    $scope.init();
});