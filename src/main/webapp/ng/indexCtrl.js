
indexApp
    .controller(
        'indexCtrl',
        function($scope, $http, $timeout,$interval) {
            $scope.imagesUrl = undefined;// 主任头像链接区，数据库更改。
            $scope.authorName = undefined;// 主人的姓名，变量留数据库改参数。
            $scope.authorDescribe = undefined;
            $scope.backgroundPic = "Path/28770613.jpg";
            $scope.getUserInfo = function () {
                $http({
                    method : 'GET',
                    url : "userinfo/getuserinfo"
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.imagesUrl = resp.data["userPic"];// 主任头像链接区，数据库更改。
                    $scope.authorName = resp.data["userName"];// 主人的姓名，变量留数据库改参数。
                    $scope.authorDescribe = resp.data["userDes"];
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.getArticleList = function () {
                $http({
                    method : 'GET',
                    url : "article/getarticlelist"
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.resultLists = resp.data;
                    console.log("$scope.resultLists",$scope.resultLists);
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.getDetail=function(id){
                document.cookie="articlesId="+id;
                window.location.href = 'articleinfo.html';
            };
            //$scope.getArticleList();
            $scope.getUserInfo();
        });