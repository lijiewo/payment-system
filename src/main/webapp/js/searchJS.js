
function searchs(){
    // var ID = document.getElementById("ID");
    //alert("hello");
    var data={};
    // data["ID"]=ID.value;
    $.ajax({
        url: "allSearchTran",
        type: "POST",
        //contex: ,
        data: data,
        dataType: "JSON",
        async:false,
        success: function(response) {
            console.log(response);
            // setTimeout()

            $("#postTable").html("<tr style=\"font-weight: bold\"><td>付款方</td><td>收款方</td><td>交易金额</td><td>交易时间</td></tr>");
            $.each(response, function(i, item) {
                alert("hello!");
                $("#postTable").append(
                    '<tr><td>'+item.Afford+'</td><td>'+item.Recv+'</td><td>'+item.Mon+'</td><td>'+item.Time+'</td></tr>');
            });
            $("#mainPanel").show();

        },
        error: function(xhr, msg, e) {
            alert("error!");
        }

    });
}
function turnToUserList(){
    setTimeout('window.location.href="userlist.html"');
}
function searchUser(){
// var username = document.getElementById("username");
    data={};
// data["username"]=username.value;
    $.ajax({
        url: "searchUser",
        type: "POST",
//contex: ,
        data: data,
        dataType: "JSON",
        async:false,
        success: function(response) {
            console.log(response);
// setTimeout()

            $("#userTable").html("<tr style=\"font-weight: bold\"><td>用户名</td><td>余额</td></tr>");
            $.each(response, function(i, item) {

                $("#userTable").append(
                    '<tr><td>'+item.Username+'</td><td>'+item.Money+'</td></tr>');
            });
// $("#searchPanel").hide();
    $("#userPanel").show();

        },
        error: function(xhr, msg, e) {
            alert("您无此权限!");
        }

    });
}

function keySearchTran(){
    var username = document.getElementById("username");
    data={};
     data["username"]=username.value;
    $.ajax({
        url: "keySearchTran",
        type: "POST",
        //contex: ,
        data: data,
        dataType: "JSON",
        async:false,
        success: function(response) {
            console.log(response);
            // setTimeout()

            $("#postTable").html("<tr style=\"font-weight: bold\"><td>付款方</td><td>收款方</td><td>交易金额</td><td>交易时间</td></tr>");
            $.each(response, function(i, item) {

                    $("#postTable").append(
                        '<tr><td>'+item.Afford+'</td><td>'+item.Recv+'</td><td>'+item.Mon+'</td><td>'+item.Time+'</td></tr>');
            });
            $("#searchPanel").hide();
            $("#mainPanel").show();

        },
        error: function(xhr, msg, e) {
            alert("error!");
        }

    });
}
function getUserName() {
    $.ajax({
        url: "online",
        type: "GET",
        success: function(response) {
            if(response!=null) {
                $("#UserID").append(response.toString());
            }
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function getUserName() {
    $.ajax({
        url: "online",
        type: "GET",
        success: function(response) {
            if(response!=null) {
                $("#UserID").append(response.toString());
            }
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function getBalance() {
        $.ajax({
            url: "returnBalance",
            type: "GET",
            success: function(response) {
                if(response!=null) {
                    $("#Balance").append(response.toString());
                }
            },
            error: function(xhr, msg, e) {
                 alert("error!");
            }
        });
    }
function addMsg_submit() {
    var username = document.getElementById("name");

    var money = document.getElementById("money");
    var data={};
    data["receipient"]=username.value;
    // data["ID"]=ID.value;
    // data["vio"]=vio.value;
    data["money"]=money.value;
    if(confirm("转账后无法撤回，请您慎重！")) {
        $.ajax({
            url: "transfer",
            type: "POST",
            data: data,
            async: false,
            dataType: "JSON",
            success: function (response) {
                if (response === true) {
                    alert("转账成功！");
                } else {
                    alert("被转帐方不存在或您的余额不足!");
                }

            },
            error: function (xhr, msg, e) {
                alert("error!");
            }

        });
    }
}
function isAdmin() {
    $.ajax({
        url: "isAdmin",
        type: "GET",
        success: function(response) {
            if(response === true) {
                //是管理员
                //setTimeout('window.location.href="admin.html"');
            }
            else{
                //是普通用户
                //setTimeout('window.location.href="payment.html"');
            }
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function tomain() {
    setTimeout('window.location.href="main.html"');
}