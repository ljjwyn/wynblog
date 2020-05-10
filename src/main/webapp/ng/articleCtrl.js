indexApp
    .controller(
        'articleCtrl',
        function($scope, $http, $timeout,$interval) {
            /*
            新文章的相关变量集合
             */
            $scope.articlePic = "Path/28770613.jpg";
            $scope.articleAuthorPic = "Path/laopo1.jpg";
            $scope.articleAuthorName = "Yanan Wang";
            $scope.articleTitle = "The Newest Technology On This Year 2019 (Test)";
            $scope.articleLabel = "Arctic sea ice";
            $scope.articleDescribe="Even the all-powerful Pointing has no control about " +
                "the blind texts it is an almost unorthographic life One day however a " +
                "small line of blind text by the name of Lorem Ipsum decided to leave " +
                "for the far World of Grammar.";
            $scope.articleDate="Nov 28, 2018";
            $scope.heartCount=10;
            $scope.eyeCount=100;
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
            $scope.subscribeMail=function(){
                swal.fire({
                    title:"Server does not support!\nback-end does not support additional post command",
                    timer:5000
                })
            };
            $scope.getTopArticle();
            // $scope.getArticleList();
        });