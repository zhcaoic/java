<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
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
	                </div>
	                <div class="rightArea">
		                <h2>欢迎! ${username}!</h2>
                        <a href="/tiandog/logout" > 退出登录 </a>
		                &nbsp&nbsp&nbsp&nbsp
                        <a href="/tiandog/userInfoDeal"> 个人中心 </a>
	                </div>
                </div>
            <#else>
                <div class="visitor">
	                <div class="leftArea">
		                <h2>天狗网 TianDog</h2>
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
        <h1>这是首页主体部分。</h1>
    </body>
</html>