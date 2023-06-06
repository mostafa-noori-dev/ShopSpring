app.controller("productCtrl", function ($scope, mainApiHandler) {
    $scope.newProductList = [];
    $scope.popualrProductList = [];

    $scope.getNewProductData = () => {
        mainApiHandler.callGet("product/newProducts", (response) => {
            $scope.newProductList = response.dataList;
        })
    }
    $scope.getPopularProductData = () => {
        mainApiHandler.callGet("product/popularProducts", (response) => {
            $scope.popularProductList = response.dataList;
        })
    }
    $scope.getNewProductData();
    $scope.getPopularProductData();

});