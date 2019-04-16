<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册</title>
        <style>
            .title{text-align: center; font-style: oblique;}
            .main{text-align: center;}
        </style>

        <script>
            <#if inputIsNull??>
                alert("请完整填写所有注册信息！");
            </#if>

            <#if pwdNotEqual??>
                alert("确认密码不一致，请检查并重新注册！");
            </#if>

            <#if nicknameIsExisted??>
                alert("昵称已被注册！");
            </#if>

            <#if emailIsExisted??>
                alert("邮箱已被注册！");
            </#if>

            <#if cellphoneIsExisted??>
                alert("手机号码已被注册！");
            </#if>

        </script>

    </head>
    <body>
        <div class="title">
            <h2> 用户注册 </h2>
        </div>
        <div class="main">
            <form method="post" action="/test/user/register" accept-charset="UTF-8">
                <p>昵称    ： <input type="text" name="nickname" ></p>
                <p>密码    ： <input type="text" name="password" ></p>
                <p>确认密码： <input type="text" name="pwdConfirm" ></p>
                <p>邮箱    ： <input type="text" name="email" ></p>
                <p>手机号码： <input type="text" name="cellphone" ></p>
                <p><input type="submit" value="提交注册" ></p>
            </form>
        </div>
    </body>
</html>