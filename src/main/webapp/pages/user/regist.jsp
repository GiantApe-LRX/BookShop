<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <%--静态包含 base，css样式，jQuery文件--%>
    <%@include file="/pages/common/head.jsp"%>


    <style type="text/css">

        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
	<script>
		$(function(){
		    $("#username").blur(function () {
                var username = this.value;

                $.getJSON("user", "action=ajaxExistsUsername&username="+username, function (data) {
                    if (data.existUsername){
                        $("span.errorMsg").text("用户名已存在");
                    }else{
                        $("span.errorMsg").text("用户名可用");
                    }
                });
            });
		    $("#code_img").click(function () {
                this.src = "${basepath}kaptchaServlet?d="+new Date();
            });
			$("#sub_btn").click(function () {
				var usernameText = $("#username").val();
				var usernamePatt = /^\w{5,12}$/;
				if(!usernamePatt.test(usernameText)){
					$("span.errorMsg").text("用户名不合法");
					return false;
				}
				$("span.errorMsg").text("");
				var passwordText = $("#password").val();
				var passwordPatt = /^\w{5,12}$/;
				if(!passwordPatt.test(passwordText)){
					$("span.errorMsg").text("密码不合法");
					return false;
				}
				$("span.errorMsg").text("");

				var repwdText = $("#repwd").val();
				if (repwdText != passwordText){
					$("span.errorMsg").text("确认密码和密码不一致");
					return false;
				}
				$("span.errorMsg").text("");

				var emailText = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailPatt.test(emailText)){
					$("span.errorMsg").text("邮箱格式不合法");
					return false;
				}
				$("span.errorMsg").text("");

				var codeText = $("#code").val();
				codeText = $.trim(codeText);
				if(codeText == null || codeText == ""){
					$("span.errorMsg").text("验证码不能为空");
					return false;
				}
				$("span.errorMsg").text("");


			});

		});
	</script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">${requestScope.msg}</span>
                </div>
                <div class="form">
                    <form action="user" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
							   autocomplete="off" tabindex="1"
                               name="username" id="username" value="${requestScope.username}"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
							   autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
							   autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
							   autocomplete="off" tabindex="1"
                               name="email" id="email" value="${requestScope.email}"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" id="code" name="code"/>
                        <img id="code_img" alt="" src="kaptchaServlet" style="width: 110px; height: 30px; float: right; margin-right: 40px">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--静态包含页脚内容--%>
<%@include file="/pages/common/foot.jsp" %>
</body>
</html>