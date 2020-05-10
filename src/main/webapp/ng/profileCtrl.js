/**
 * itemInfo的控制器
 *
 * @author lijiajie
 */
indexApp
    .controller(
        'profileCtrl',
        function($scope, $http, $timeout,$interval) {
            //$scope.url="http://127.0.0.1:3001/";
            //$scope.url="http://119.167.221.16:23000/";
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
            $scope.userEmail='';
            $scope.password='';
            $scope.userName = getCookie("userName");
            $scope.userId=getCookie("userId");
            /***
             * 获取author表的数据，主要是作者的头像
             */
            $scope.getUserInfo=function(){
                // getaauthor
                console.log("$scope.userId",$scope.userId);
                $http({
                    method : 'POST',
                    url : "userinfo/getloaduser",
                    data:{"userId":$scope.userId}
                }).then(function(resp, status) {
                    console.log(resp);
                    var resultList = resp.data;
                    $scope.authorPic = resultList["userPic"];
                    $scope.authorName = resultList["userName"];
                    $scope.userEmail=resp.data['userEmail'];
                    $scope.password=resp.data['password'];
                    $scope.phoneNum=resp.data['phoneNum'];
                    $scope.userDes=resp.data['userDes'];
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.getUserInfo();
            $scope.logout=function () {
                window.location.href = 'logout';
            };
            $scope.searchtest='';
            // $scope.testform=function(e){
            //     var keycode = window.event?e.keyCode:e.which;
            //     if(keycode==13) {
            //         console.log("searchtest",$scope.searchtest);
            //         document.cookie="entityName="+$scope.searchtest;
            //         window.open("pages-search.html");
            //     }
            // };
            $scope.phoneNum = '';
            $scope.userDes='';
            $scope.updateInfo=function () {
                $http({
                    url : 'userinfo/updateinfo',
                    method : 'POST',
                    data : {
                        "phoneNum": $scope.phoneNum,
                        "userDes": $scope.userDes,
                        "userId": $scope.userId
                    }
                }).then(function(resp, status) {
                    $scope.status = status;
                    swal.fire({
                        title:"用户信息更新完成！",
                        timer:2000
                    });
                    $scope.getUserInfo();
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };

            /**
             * @Describe 获取头像文件夹下的所有图片的名称。
             */
            $scope.pictureName=undefined;
            $scope.picturesNameList=undefined;
            function getPictureName() {
                $http({
                    url : 'upload/getpicturelist',
                    method : 'GET'
                }).then(function(resp, status) {
                    $scope.status = status;
                    $scope.picturesNameList=resp.data["pictureNameList"];
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            }
            getPictureName();


            $scope.updatePicture=function (pictureName) {
                console.log("pictureName",pictureName);
                $http({
                    url : 'userinfo/updatepicture',
                    method : 'POST',
                    data : {
                        "userPic":pictureName,
                        "userId":$scope.userId
                    }
                }).then(function(resp, status) {
                    $scope.status = status;
                    swal.fire({
                        title:"更新图片完成",
                        timer:2000
                    });
                    console.log(resp.data);
                    $scope.getUserInfo();
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            /**
             * pages-picture的各种函数，主要是实现上传图片选择图片的操作
             */
            function uploadPic(){
                layui.use('upload', function() {
                    var $ = layui.jquery
                        , upload = layui.upload;

                    //普通图片上传
                    var uploadInst = upload.render({
                        elem: '#test1'
                        , title: 'ceshi1'
                        , url: 'upload/fileupload' //改成您自己的上传接口
                        , method: 'post'
                        , data: {
                            flag: 1
                        }
                        , before: function (obj) {
                            //预读本地文件示例，不支持ie8
                            obj.preview(function (index, file, result) {
                                $('#demo1').attr('src', result); //图片链接（base64）
                            });
                        }
                        , done: function (res) {
                            //如果上传失败
                            if (res.code > 0) {
                                return layer.msg('上传失败');
                            } else {
                                $scope.articlePic = res.pictureName;
                                console.log("$scope.articlePic",$scope.articlePic);
                                getPictureName();
                                return layer.msg('上传成功');
                            }
                            //上传成功
                        }
                        , error: function () {
                            //演示失败状态，并实现重传
                            var demoText = $('#demoText');
                            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                            demoText.find('.demo-reload').on('click', function () {
                                uploadInst.upload();
                            });
                        }
                    });
                    //绑定原始文件域
                    upload.render({
                        elem: '#test20'
                        ,url: 'upload/fileupload' //改成您自己的上传接口
                        ,done: function(res){
                            layer.msg('上传成功');
                            console.log(res)
                        }
                    });
                });
            }
            uploadPic();
        });