<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>购物车</title>
        <link rel="icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="bookmark" href="/images/logo.ico" type="image/x-icon" />
        <link rel="stylesheet" href="/style/main.css" type="text/css" />
        
        <script>

            var tempSum = 0;
            
            function getSumByDeal(price, num) {
                var sum = price * num;
                tempSum = tempSum + sum;
                return sum;
            }

            function getSumForAll() {
                return tempSum;
            }
            
        </script>
        
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

        <div class="cartBody">
            <div class="cartTop">
                <h2> ${username} 您好！ 您的购物车内容如下： </h2>
                <br>
            </div>
            <div class="cartMain">
                <#if cartList??>
                    <#list cartList as cl>
                        <p>
                            <#list dealList as dl>
                                <#if cl.cartDealId == dl.id>
                                    <img src=${dl.imageList[0].sourcePath}>
                                    &nbsp&nbsp&nbsp
                                    <h3> 商品名：${dl.name}    单价：${dl.price}    数量：${cl.cartDealCount}
                                        小计：<script> document.write(getSumByDeal(${dl.price}, ${cl.cartDealCount})) </script> </h3>
                                </#if>
                            </#list>
                        </p>
                    </#list>
                    <p> <h2> 购物车商品总额为：<script> document.write(getSumForAll()) </script> </h2> </p>
                <#else>
                    <h2> Sorry！ 您当前暂未选购商品添加至购物车！ </h2>
                    <br>
                    <a href="/tiandog/index"> 去看看其他商品 </a>
                </#if>
            </div>
        </div>
    </body>
</html>