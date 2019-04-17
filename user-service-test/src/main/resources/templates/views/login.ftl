<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>

        <style>
            .title{text-align: center; font-style: oblique;}
            .main{text-align: center;}
        </style>

        <script>
            <#if paramIsEmpty??>
                alert("请完整填写用户名或密码！");
            </#if>

            <#if loginFail??>
                alert("登录失败！请确认用户名或密码是否填写正确，以及是否有访问权限！");
            </#if>

            // 将跳转登录页面前的页面URL添加至post参数列表中
            function addPreUrlToLoginForm() {
                var preUrl = document.referrer;
                var urlInput = document.createElement('input');
                urlInput.setAttribute("type", "hidden");
                urlInput.setAttribute("name", "preUrl");
                urlInput.setAttribute("value", preUrl);
                var loginForm = document.getElementById("loginForm");
                loginForm.appendChild(urlInput);
            }
        </script>

    </head>
    <body>
        <div class="title">
            <h2> 用户登录 </h2>
        </div>
        <div class="main">
            <form method="post" action="/test/user/login" id="loginForm">
                <p> 用户名 ： <input type="text" name="username" placeholder="username"></p>
                <p> 密码   ： <input type="text" name="password" placeholder="password"></p>
                <p> <input type="submit" value="登录" onclick="addPreUrlToLoginForm()">
            </form>
        </div>
    </body>
</html>