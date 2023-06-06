app.controller("productsCtrl", function ($scope, mainApiHandler,$rootScope) {
    $rootScope.page="Products";
    $scope.categoryList = [];
    $scope.productsList = [];
    $scope.selectedFilter="";
    $scope.selectedTitle="";
    $scope.getCategoryList = () => {
        mainApiHandler.callGet("productCategory", (response) => {
            $scope.categoryList = response.dataList;
        })
    }

    $scope.getNewProductData = () => {
        mainApiHandler.callGet("product/newProducts", (response) => {
            $scope.productsList = response.dataList;
        })
    }
    $scope.getPopularProductData = () => {
        mainApiHandler.callGet("product/popularProducts", (response) => {
            $scope.productsList = response.dataList;
        })
    }

    $scope.changeFilter=(filter)=>{
        $scope.selectedFilter=filter;
        switch (filter){
            case "popular":
                $scope.selectedTitle="Popular Products";
                $scope.getPopularProductData();
                break;
            case "new":
                $scope.selectedTitle="New Products";
                $scope.getNewProductData();
                break;
            case "cheapest":
                $scope.selectedTitle="Cheapest Products";
                break;
            case "expensive":
                $scope.selectedTitle="Expensive Products";
                break;
        }
    }

    $scope.changeFilter("popular");
    $scope.getCategoryList();
});