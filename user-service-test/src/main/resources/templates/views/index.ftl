<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户服务首页</title>
        <style>
            .title{text-align: center; font-style: oblique;}
            .main{text-align: center;}
        </style>
    </head>
    <body>
        <div class="title">
            <h2> 用户服务中心 </h2>
        </div>
        <div class="main">
            <#if userNumber?? && nickname??>
                <p><h3> 欢迎 ${nickname}， ${userNumber}！ </h3></p>
                <p>
                    <a href="/test/pages/login.html"> 登录 </a>
                    <a href="/test/pages/register.html"> 注册 </a>
                    <a href="/test/user/logout"> 注销 </a>
                </p>
            <#else>
                <p><h3> 欢迎 游客！ </h3></p>
                <p>
                    <a href="/test/pages/login.html"> 登录 </a>
                    <a href="/test/pages/register.html"> 注册 </a>
                </p>
            </#if>
            <p><h2> 历史访问量：${indexCount} </h2></p>
        </div>
    </body>
</html>