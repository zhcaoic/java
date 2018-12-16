<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>登录</title>
    </head>
    <body>
        <div class="logintitle">
            <br>
            <h2>  注册/更新用户信息成功! 请登录： </h2>
            <br>
        </div>
        <div class="login">
            <form method="post" action="/tiandog/login">
                <p> 用户名 ： <input type="text" name="username" placeholder="username"></p>
                <p> 密码   ： <input type="text" name="password" placeholder="password"></p>
                <p> <input type="submit" value="登录"></p>
            </form>
        </div>
    </body>
</html>