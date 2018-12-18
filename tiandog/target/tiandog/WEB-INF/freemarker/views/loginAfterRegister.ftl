<!DOCTYPE html>
<html>
    <head>
	    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <title>登录</title>
	    <link rel="icon" href="/images/logo.ico" type="image/x-icon" />
	    <link rel="shortcut icon" href="/images/logo.ico" type="image/x-icon" />
	    <link rel="bookmark" href="/images/logo.ico" type="image/x-icon" />
	    <link rel="stylesheet" href="/style/main.css" type="text/css" />
    </head>
    <body>
        <div class="loginTitle">
            <br>
            <h2> 注册成功！请登录： </h2>
            <br>
        </div>
        <div class="loginForm">
            <form method="post" action="/tiandog/login">
                <p> 用户名 ： <input type="text" name="username" placeholder="username"></p>
                <p> 密码   ： <input type="text" name="password" placeholder="password"></p>
                <p> <input type="submit" value="登录"></p>
            </form>
        </div>
    </body>
</html>