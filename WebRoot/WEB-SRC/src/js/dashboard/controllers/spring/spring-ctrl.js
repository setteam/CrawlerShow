/**
 back.primary-ctrl.js
 **/
angular.module('Dashboard',["ng.ueditor","tm.pagination"]).controller("springCtrl",["$scope","$http",function($scope,$http){

//generalCrawlerCtrl----------------------------------------------------------------------------------------------------
    //菜单的伸缩
    $scope.toggle=true;

    $scope.toggleSidebar = function()
    {
        $scope.toggle = ! $scope.toggle;
    };

    //IP和projectName的全局变量
    $scope.projectName="http://192.168.1.123:8080/SpringTest/article/";

    $scope.articleData={
        title:"" ,
        content: "",
        commentNum:null,
        newSource: "",
        id: null,
        keyword: [],
        imagePath: [],
        abstruct: "",
        time: "",
        similarNews:[],
        repeatedNews:[],
        articleId:null,
        words: null
    };

    //初始化header
    $scope.curPage = "一览";

    //点击sidebar之后改变header
    $scope.changeCurPage = function(str)
    {
        $scope.curPage=str;
        //如果是点击新建文章就清除文章里的数据
        if(str=="文章/新建"||str=="快拍成都/新建"){
            clearNewArticleData();
        }
    };
    $scope.testLog=function(){
        $scope.getSpringData();
    };
    $scope.refreshSpringData=function(){

    };

    $scope.springData=null;
    //获得springTest的数据，返回到是一个json----------------------------------------------------------------------------
    $scope.getSpringData=function(){
        var url=$scope.projectName+"all";
        console.log(url);
//        $http.get(url).success(function(data){
//            console.log(data);
////            $scope.springData=data;
//        });
    };

    //检查表的内容 数据若是NULL则显示"无",数组若是空则显示"无数据",转化时间戳为日期显示
    $scope.checkIfNull=function(str)
    {
        var checkedStr;
        if(str==null||str==""){
            checkedStr="无";
        }else{
            checkedStr=str;
        }
        return checkedStr;
    };
    $scope.checkIfEmpty=function(arr)
    {
        var checkedStr;
        if(arr.length==0){
            checkedStr="无数据";
            return checkedStr;
        }else{
            return arr;
        }
    };
    $scope.checkNum=function(str)
    {
        var checkedStr;
        if(str==null||str==""||str=="0"){
            checkedStr="0";
            return checkedStr;
        }else{
            return str;
        }
    };
    $scope.dateStringToDate=function(dateStr)
    {
        if(dateStr==null||dateStr==""){
            return "无";
        }else{
            var date=new Date(Date(dateStr));
            return date.toDateString();
        }
    };



}]);