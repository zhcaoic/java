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
                <br>
            <#else>
                <h2> 欢迎! 游客! </h2>
                <br>
                <form method="post" action="/login.html">
                    <input type="submit" value="请登录">
                </form>
                <br>
            </#if>
        </div>
        <div class="usercenter">
            <h2> 个人信息 </h2>
            <br>
            <#if username??>
                <#if username = user.username>
                    <p>姓名： ${user.username}</p>
                    <p>年龄： ${user.age}</p>
                    <p>国籍： ${user.address}</p>
                </#if>
            <#else>
                <p> 暂无个人信息! </p>
            </#if>
        </div>
    </body>
</html>