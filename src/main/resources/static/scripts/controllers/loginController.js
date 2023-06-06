app.controller("loginCtrl",function($scope,apiHandler,$cookies){
    $scope.user={};
    $scope.doLogin=()=>{
        apiHandler.callPost(
            'user/login',
            $scope.user,
            (response)=>{
                    let token=response.dataList[0].token;
                    if(token===null || token==="")
                    {
                        Swal.fire('Any fool can use a computer')
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: "invalid token",
                        })
                        return;
                    }
                    $cookies.put("userToken",token);
                    location.href="/panel";
            },(error)=>{
            }
            )
    }
})