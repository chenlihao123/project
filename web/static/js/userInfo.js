$(function () {

    initStudentInfo();
});



function initStudentInfo() {
    $.ajax({
        url: "http://localhost:8080/onlinelearning/stu.do",
        type: "POST",
        data:{
            action: "getStudentInfo"
        },
        success:function (data) {

            let jsonObj =  JSON.parse(data);
            console.log(jsonObj)
            if (!isEmpty(jsonObj.imgPath)) {
                let imgPath = jsonObj.imgPath;
                imgPath = imgPath.substring(2);
                imgPath = "http://localhost:8080" + imgPath;
                $(".logo img").attr("src",imgPath);
                let str =`<a   style="position: relative;width: 110px;text-align: center">${jsonObj.realName}<img onclick="stuOut();" style="position: absolute; left: 80px;top: 13px" width="13px" height="13px" src="../static/images/exit1.png"></a><span>|</span><a href="">消息通知</a>`;
                $(".topbar-info").html(str);
            }
        }
    })
}
function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
function stuOut() {
    $.ajax({
        url:"http://localhost:8080/onlinelearning/stu.do",
        type: "POST",
        data:{
            action: "logout"
        }
    });
    window.location.href ="http://localhost:8080/onlinelearning/index.html";
}