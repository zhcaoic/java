<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>个人中心</title>
        <link rel="icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="bookmark" href="/images/logo.ico" type="image/x-icon" />
        <link rel="stylesheet" href="/style/main.css" type="text/css" />
    </head>
    <body>
        <div class="topBar">
                        <#if username??>
                            <div class="member">
                                <div class="leftArea">
                                    <h2>菠萝网 FileShare</h2>
                                </div>
                                <div class="rightArea">
                                    <h2>欢迎! ${username}!</h2>
                                    <a href="/fileShare/logout" > 退出登录 </a>
                                    &nbsp&nbsp&nbsp&nbsp
                                    <a href="/fileShare/userInfoDeal"> 个人中心 </a>
                                </div>
                            </div>
                        <#else>
                            <div class="visitor">
                                <div class="leftArea">
                                    <h2>菠萝网 FileShare</h2>
                                </div>
                                <div class="rightArea">
                                    <h2>欢迎来到菠萝网！</h2>
                                    <a href="/pages/login.html" > 登录 </a>
                                    &nbsp&nbsp&nbsp&nbsp
                                    <a href="/pages/register.html" > 注册 </a>
                                </div>
                            </div>
                        </#if>
        </div>
        <div class="br1">
            <br>
            <br>
            <br>
        </div>
        <div class="br2">
            <br>
        </div>
        <h1>这是主体部分。</h1>
        <div class="createUserInfoTitle">
            <br>
            <h2> 您未填写过个人资料，请完善您的个人信息!</h2>
            <br>
        </div>
        <div class="createUserInfoForm">
            <form method="post" action="/fileShare/createUserInfoDeal" accept-charset="UTF-8">
                <p>真实姓名  ： <input type="text" name="realName" ></p>
                <p>邮箱      ： <input type="text" name="email" ></p>
                <p>手机号码  ： <input type="text" name="phone" ></p>
                <p>年龄      ： <input type="text" name="age" ></p>
                <p>住址      ： <input type="text" name="address" ></p>
                <p>职业      ： <input type="text" name="occupation" ></p>
                <p><input type="submit" value="创建个人资料" ></p>
            </form>
        </div>
    </body>
</html>