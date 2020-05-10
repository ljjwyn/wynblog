indexApp
    .controller(
        'articleInfoCtrl',
        function($scope, $http, $timeout,$interval) {
            /*
            新文章的相关变量集合
             */
            function getCookie(name) {
                var arr=document.cookie.split('; ');
                console.log("arr", arr);
                for(var i = 0 ; i < arr.length; i ++){
                    var arr2=arr[i].split('=');
                    if(arr2[0]==name){
                        return arr2[1];
                    }
                }
                return '';
            }
            $scope.articleId = getCookie("articlesId");
            $scope.articlePic = undefined;
            $scope.articleAuthorPic = undefined;
            $scope.articleAuthorName = undefined;
            $scope.articleTitle = undefined;
            $scope.articleLabel = undefined;
            $scope.articleDescribe = undefined;
            $scope.articlesContents = undefined;
            $scope.articleDate = undefined;
            $scope.heartCount = undefined;
            $scope.eyeCount = undefined;
            $scope.getArticleInfo = function () {
                $http({
                    method : 'POST',
                    url : "article/getaarticle",
                    data:{"articleId":$scope.articleId}
                }).then(function(resp, status) {
                    console.log(resp);
                    var resultList = resp.data;
                    $scope.articlePic = resultList["articlesPic"];
                    $scope.articleAuthorPic = resultList["authorsPic"];
                    $scope.articleAuthorName = resultList["authorsName"];
                    $scope.articleTitle = resultList["articlesTitle"];
                    // document.getElementById("articleTitle").innerHTML = $scope.articleTitle;
                    $scope.articleLabel = resultList["articlesLabel"];
                    $scope.articleDescribe = resultList["articlesDescribe"];
                    // document.getElementById("articleTitle").innerHTML = $scope.articleTitle;
                    $scope.articlesContents = resultList["articlesContents"];
                    document.getElementById("articlesContents").innerHTML = $scope.articlesContents;
                    $scope.articleDate = resultList["articlesDate"];
                    $scope.heartCount = resultList["articlesHeartCount"];
                    $scope.eyeCount = resultList["articlesEyeCount"];
                    console.log("$scope.articlePic",$scope.articlePic);
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.getTopArticle = function () {
                $http({
                    method : 'GET',
                    url : "article/gettoparticle"
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.topArticleTitle = resp.data["articlesTitle"];
                    $scope.topArticlePic = resp.data["articlesPic"];
                    $scope.topAuthorsName = resp.data["authorsName"];
                    $scope.topArticleDate = resp.data["articlesDate"];
                    $scope.topArticlesEyeCount = resp.data["articlesEyeCount"];
                    $scope.articleTopId=resp.data["articlesId"];
                    console.log("$scope.resultLists",$scope.resultLists);
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.goToTopArticle=function(){
                document.cookie="articlesId="+$scope.articleTopId;
                window.location.href = 'articleinfo.html';
            };
            $scope.subscribeMail=function(){
                swal.fire({
                    title:"Server does not support!\nback-end does not support additional post command",
                    timer:5000
                })
            };
            $scope.getArticleInfo();
            $scope.getTopArticle();
            /**
             * @Describe:更新访问次数
             */
            function updateEyeCount(){
                $http({
                    method : 'POST',
                    url : "article/updateeyecount",
                    data:{"articleId":$scope.articleId}
                }).then(function(resp, status) {
                    console.log(resp);
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            }
            updateEyeCount();
            $scope.iconHeart="icon-heart";
            $scope.updateHeartCount = function(){
                console.log("$scope.articleId",$scope.articleId);
                $http({
                    method : 'POST',
                    url : "article/updateheartcount",
                    data:{"articleId":$scope.articleId}
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.iconHeart="icon-star";
                    $scope.heartCount=resp.data["newHeartCount"];
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
        });