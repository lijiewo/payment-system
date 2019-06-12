

function c_o_li_data() {
    var user = document.getElementById('username');
    var password = document.getElementById('password');
    var data = {};
    data["username"] = user.value;
    data["password"] = password.value;
    return data;
}
function c_o_li_submit() {
        var data = c_o_li_data();

        $.ajax({
            url: "login",
            type: "POST",
            //contex: ,
            data: data,
            dataType:"JSON",
            async: false,
            success: function(response) {
                if (response === true) {
                    alert("登录成功");
                    $.ajax({
                        url: "isAdmin",
                        type: "GET",
                        success: function(response) {
                            if(response === true) {
                                setTimeout('window.location.href="admin.html"');
                            }
                            else{
                                setTimeout('window.location.href="payment.html"');
                            }
                        },
                        error: function(xhr, msg, e) {
                            alert("error!");
                        }
                    });


                } else {
                    alert("账号或密码错误");
                }
                 setTimeout('window.location.href="index.html"', 1000);
            },
            error: function(xhr, msg, e) {
               alert(msg);
            }
        });
}

function  register_data() {
    var username = document.getElementById("register_username");
    var pass=document.getElementById("register_password");
    var passconfirm=document.getElementById("pass_confirm");

    var reg = /[A-Za-z0-9]{6,12}/;
    if(reg.test(username) === false || reg.test(pass) === false || reg.test(passconfirm) === false) {
        alert("密码必须由6-12位字母、数字组成");
        return;
    }
    else if(pass.value!==passconfirm.value) {
        alert("两次密码不一样！");
        return;
    }
    var data = {};
    data["username"] = username.value;
    data["password"] = pass.value;
   return data;
}

function register_submit() {
    var data = register_data();
    if(data["username"]==null)return;
    $.ajax({
        url: "register",
        type: "POST",
        //contex: ,
        data: data,
        dataType: "JSON",
        async: false,
        success: function(response) {
            if (response === true) {
                alert("注册成功！");
            } else {
                alert("账号已存在");
            }
            // setTimeout('window.location.href="index.html"', 3000);
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function logout() {
    alert("hello!");
    $.ajax({
        url: "logout",
        type: "GET",
        dataType: "JSON",
        success: function(response) {
            if (response === true) {
                setTimeout('window.location.href="index.html"', 1000);
            } else {
                alert("登出失败!");
            }
            // setTimeout('window.location.href="index.html"', 3000);
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}

function online() {
    $.ajax({
        url: "online",
        type: "GET",
        success: function(response) {
                if(response!=null) {
                    $("#UserID").innerHTML(response.toString()
                                             + '<b class="caret"></b>');
                }
        },
        error: function(xhr, msg, e) {
            alert("error!");
        }
    });
}
function updatePass() {
    var oldpas = document.getElementById("oldpas");
    var pass1 = document.getElementById("pass1");
    var pass2 = document.getElementById("pass2");
    var reg = /[A-Za-z0-9]{6,12}/;
    if(reg.test(pass1) === false || reg.test(pass2) === false) {
        alert("密码必须由6-12位字母、数字组成");
        return;
    }
    if (pass1.value !== pass2.value) {
        alert("两次密码输入不一致!")
        pass1.text("");
        pass2.text("");
    }
    else {
        var data = {};
        data["oldPwd"] = oldpas.value;
        data["newPwd"] = pass1.value;
        $.ajax({
            url: "updatePassword",
            type: "POST",
            data: data,
            dataType: "JSON",
            async: false,
            success: function (response) {
                if (response === true) {
                    alert("密码修改成功!");
                    setTimeout('window.location.href="index.html"');
                }
                else{
                    alert("旧密码输入错误！修改密码失败");
                }
            },
            error: function(xhr, msg, e) {
                alert("error!");
            }
        });
    }
}
