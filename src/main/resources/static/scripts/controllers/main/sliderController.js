app.controller("sliderCtrl", function ($scope, mainApiHandler) {
    $scope.sliderList = [];

    $scope.getSliderData = () => {
        mainApiHandler.callGet("slider/", (response) => {
            $scope.sliderList = response.dataList;
            setTimeout(initSlider, 100);
        })
    }

    initSlider = () => {
        $(".top-slider").square1({
            caption: 'none',
            theme: 'light'
        });
    }
    $scope.getSliderData();

});