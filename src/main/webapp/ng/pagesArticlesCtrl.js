indexApp
    .controller(
        'pagesArticlesCtrl',
        function($scope, $http, $timeout,$interval) {
            $scope.isactive1="nav-link active";
            $scope.isactive2="nav-link";
            $scope.manageArticleShow=true;
            $scope.addArticleShow=false;
            $scope.getArticleList = function () {
                $http({
                    method : 'GET',
                    url : "article/getarticlelist"
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.resultLists = resp.data;
                    $scope.setTable($scope.resultLists);
                    console.log("$scope.resultLists",$scope.resultLists);
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            /***
             * @Describe 获取作者信息列表供新文章选择
             */
            $scope.getAuthorsList=function(){
                $http({
                    method : 'GET',
                    url : "author/getallauthor"
                }).then(function(resp, status) {
                    console.log(resp);
                    $scope.authorsList = resp.data;
                    $scope.authorsName = [];
                    for(var i=0;i<$scope.authorsList.length;i++){
                        $scope.authorsName.push($scope.authorsList[i]["authorsName"]);
                    }
                    $scope.selectedAuthorName=$scope.authorsName[0];
                }, function(resp, status) {
                    $scope.resp = resp;
                    $scope.status = status;
                });
            };
            $scope.getAuthorsList();
            $scope.confirm=function () {
                swal({
                    title: "是否确认修改文章内容\n请尤其注意是否置顶文章",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '确认',
                    cancelButtonText: '取消'
                }).then(function(isConfirm) {
                    console.log("ic", isConfirm);
                    if (isConfirm.value) {
                        console.log("$scope.articleTitle",$scope.articleTitle);
                        $http({
                            method : 'POST',
                            url : "article/updatearticle",
                            data : {
                                "articlesId":$scope.articleId,
                                "articlesTitle":$scope.articleTitle,
                                "articlesDate":$scope.articleDate,
                                "articlesLabel":$scope.selectedCategory,
                                "articlesDescribe":$scope.articleDescribe,
                                "articlesContents":$scope.articleContents,
                                "articlesIsTop":$scope.isTopNum
                            }
                        }).then(function(resp, status) {
                            console.log(resp);
                            swal.fire({
                                title:"更新完成",
                                timer:2000
                            })
                            $scope.getArticleList();
                        }, function(resp, status) {
                            $scope.resp = resp;
                            $scope.status = status;
                        });
                    }
                })
            };
            // $scope.getArticleList = function () {
            //     $http({
            //         method : 'POST',
            //         url : "article/getarticlelist"
            //     }).then(function(resp, status) {
            //         console.log(resp);
            //         $scope.resultLists = resp.data;
            //         $scope.setTable($scope.resultLists);
            //         console.log("$scope.resultLists",$scope.resultLists);
            //     }, function(resp, status) {
            //         $scope.resp = resp;
            //         $scope.status = status;
            //     });
            // };
            function myEditor(){
                var ue = UE.getEditor('container');
                ue.ready(function() {
                    //设置编辑器的内容
                    //ue.setContent('hello');
                    //获取html内容，返回: <p>hello</p>
                    var html = ue.getContent();
                    console.log(html);
                    //获取纯文本内容，返回: hello
                    var txt = ue.getContentTxt();
                    console.log(txt);
                });
            }
            myEditor();
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
                        ,data: {
                            flag: 0
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


            /***
             * @Describe:添加新的文章
             */
            $scope.newArticleTitle=undefined;
            $scope.newArticleDate=undefined;
            $scope.newArticleDescribe=undefined;
            $scope.newArticleContents=undefined;
            $scope.newContent=undefined;
            $scope.confirmAdd=function(){
                swal({
                    title: "是否添加新文章\n请尤其注意是否置顶文章",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: '确认',
                    cancelButtonText: '取消'
                }).then(function(isConfirm) {
                    console.log("ic", isConfirm);
                    if (isConfirm.value) {
                        var ue = UE.getEditor('container');
                        ue.ready(function() {
                            //设置编辑器的内容
                            //ue.setContent('hello');
                            //获取html内容，返回: <p>hello</p>
                            var html = ue.getContent();
                            $scope.newContent = ue.getContent();
                            console.log(html);
                            //获取纯文本内容，返回: hello
                            var txt = ue.getContentTxt();
                            console.log(txt);
                        });
                        if($scope.selectedIsTop==="是"){
                            $scope.isTopNum = 1;
                        }else {
                            $scope.isTopNum = 0;
                        }
                        console.log("$scope.articleTitle",$scope.newArticleTitle);
                        console.log("$scope.newArticleDate",$scope.newArticleDate);
                        console.log("$scope.newArticleDescribe",$scope.newArticleDescribe);
                        console.log("$scope.newArticleContents",$scope.newContent);
                        console.log("$scope.isTopNum",$scope.isTopNum);
                        console.log("$scope.articlePic",$scope.articlePic);
                        console.log("$scope.selectedCategory",$scope.selectedCategory);
                        console.log("$scope.authorsName",$scope.authorsName.indexOf($scope.selectedAuthorName)+1);
                        $http({
                            method : 'POST',
                            url : "article/insertarticle",
                            data : {
                                "articlesAuthorId":$scope.authorsName.indexOf($scope.selectedAuthorName)+1,
                                "articlesTitle":$scope.newArticleTitle,
                                "articlesDate":$scope.newArticleDate,
                                "articlesLabel":$scope.selectedCategory,
                                "articlesDescribe":$scope.newArticleDescribe,
                                "articlesContents":$scope.newContent,
                                "articlesIsTop":$scope.isTopNum,
                                "articlesPic":$scope.articlePic
                            }
                        }).then(function(resp, status) {
                            console.log(resp);
                            swal.fire({
                                title:"添加新文章完成",
                                timer:2000
                            })
                        }, function(resp, status) {
                            $scope.resp = resp;
                            $scope.status = status;
                        });
                    }
                });

            };
            $scope.getArticleList();
            $scope.showManageArticle=function () {
                $scope.isactive1="nav-link active";
                $scope.isactive2="nav-link";
                $scope.manageArticleShow=true;
                $scope.addArticleShow=false;
            };
            $scope.showAddArticle=function () {
                $scope.isactive1="nav-link";
                $scope.isactive2="nav-link active";
                $scope.manageArticleShow=false;
                $scope.addArticleShow=true;
            };
            $scope.categoryList = ["科幻小说","论文","期刊","研究报告","杂文"];
            $scope.selectedCategory=$scope.categoryList[0];
            $scope.isTopList=["是","否"];
            $scope.selectedIsTop = $scope.isTopList[0];
            $scope.updateArticles=function(data){
                $scope.articleId = data["articlesId"];
                $scope.articleTitle=data["articlesTitle"];
                $scope.articleDate=data["articlesDate"];
                $scope.selectedCategory=data["articlesLabel"];
                $scope.articleDescribe=data["articlesDescribe"];
                $scope.articleContents=data["articlesContents"];
                if($scope.selectedIsTop==="是"){
                    data["articlesIsTop"]=1;
                    $scope.isTopNum = 1;
                }else {
                    data["articlesIsTop"]=0;
                    $scope.isTopNum = 0;
                }
                console.log("data", data);
            };
            $scope.setTable=function(tableData) {
                //使用layui的表格
                layui.use('table', function(){
                    var table = layui.table;

                    //第一个实例
                    table.render({
                        elem: '#demo'
                        ,height: 400
                        ,data: tableData
                        ,title: '文章信息表'
                        ,page: true //开启分页
                        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
                        ,totalRow: true //开启合计行
                        ,cols: [[ //表头
                            // {type: 'checkbox', fixed: 'left'}
                            {field: 'articlesId', title: '文章编号', width:100, sort: true, fixed: 'left'}
                            ,{field: 'articlesAuthorId', title: '作者编号', width:100}
                            ,{field: 'articlesTitle', title: '文章标题', width: 200}
                            ,{field: 'articlesDate', title: '文章日期', width:200}
                            ,{field: 'articlesHeartCount', title: '收藏次数', width: 100}
                            ,{field: 'articlesEyeCount', title: '浏览次数', width:150}
                            ,{field: 'articlesIsTop', title: '置顶文章', width: 200}
                            ,{field: 'authorsName', title: '作者姓名', width: 150, fixed:'right'}
                            ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
                        ]]
                    });
                    table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                        var data = obj.data //获得当前行数据
                            ,layEvent = obj.event; //获得 lay-event 对应的值
                        if(layEvent === 'detail'){
                            layer.msg('查看文章');
                            document.cookie="articlesId="+data["articlesId"];
                            window.open("articleinfo.html");
                        } else if(layEvent === 'del'){
                            layer.confirm('真的删除行么', function(index){
                                //obj.del(); //删除对应行（tr）的DOM结构
                                //layer.close(index);
                                //向服务端发送删除指令
                            });
                        } else if(layEvent === 'edit'){
                            layer.confirm('确认编辑后请点击右侧修改文章！', function(index){
                                console.log(index);
                                $scope.updateArticles(data);
                                layer.close(index);
                            });
                        }
                    });

                });
            }
        });