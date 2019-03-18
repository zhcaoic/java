<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>添加成功</title>
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
                        <h2>天狗网 TianDog</h2>
                        <a href="/tiandog/index"> 回到首页 </a>
                    </div>
                    <div class="rightArea">
                        <h2>欢迎! ${username}!</h2>
                        <a href="/tiandog/logout" > 退出登录 </a>
                        &nbsp&nbsp&nbsp&nbsp
                        <a href="/tiandog/userInfoDeal"> 个人中心 </a>
                        &nbsp&nbsp&nbsp&nbsp
                        <a href="/tiandog/cart/show"> 购物车 </a>
                    </div>
                </div>
            <#else>
                <div class="visitor">
                    <div class="leftArea">
                        <h2>天狗网 TianDog</h2>
                        <a href="/tiandog/index"> 回到首页 </a>
                    </div>
                    <div class="rightArea">
                        <h2>欢迎来到天狗网！</h2>
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
        <div class="indexTitle">
            <h1>如果不是真的喜欢，谁又会来舔狗网买东西呢？</h1>
        </div>

        <div class="addToCartSuccessInfo">
            <#if dealName?? && preUrl?? && dealCount??>
                <h2> 商品 ${dealName} （共 ${dealCount} 件）添加至购物车成功！</h2>
                <br>
                <a href=${preUrl}> 继续购物 </a>
                &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/cart/show"> 查看购物车 </a>
            <#else>
                <h2> 出错了！ </h2>
            </#if>
        </div>
    </body>
</html>