<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>首页</title>
    </head>
    <body>
        <div class="topBar">
            <#if username??>
                <h2> 欢迎! ${username}!</h2>
                <br>
                <form method="post" action="/test/logout">
                    <input type="submit" value="退出登录">
                </form>
            <#else>
                <h2> 欢迎! 游客! </h2>
                <br>
                <form method="post" action="/login.html">
                    <input type="submit" value="请登录">
                </form>
            </#if>
        </div>
    </body>
</html>