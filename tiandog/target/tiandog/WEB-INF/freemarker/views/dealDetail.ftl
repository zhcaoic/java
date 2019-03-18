<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><#if deal??> ${deal.name} </#if></title>
        <link rel="icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="/images/logo.ico" type="image/x-icon" />
        <link rel="bookmark" href="/images/logo.ico" type="image/x-icon" />
        <link rel="stylesheet" href="/style/main.css" type="text/css" />

        <script>
	        function checkIfLoginAndAddInfo() {
		        // 检查是否登录
                <#if username??>
                    // 若已登录，则补充添加表单提交的商品信息
                    // 商品ID
                    var inputDealId = document.createElement('input');
                    inputDealId.type = "hidden";
                    inputDealId.name = "dealId";
                    inputDealId.value = ${deal.id};
                    // 用户ID
                    var inputUserId = document.createElement('input');
                    inputUserId.type = "hidden";
                    inputUserId.name = "userId";
                    inputUserId.value = ${userId};
                    // 添加信息
                    var cartForm = document.getElementById("cartForm");
                    cartForm.appendChild(inputDealId);
                    cartForm.appendChild(inputUserId);

		            return true;
                <#else>
                    // 若未登录，则先跳转登录
                    alert("请登录!");
		            window.location.assign("/pages/login.html");

		            return false;
                </#if>

	        }

	        function subDealNum() {
		        var numInput = document.getElementById("numInput");
		        var numValue = numInput.value;
		        if (numValue > 1) {
			        numInput.value = numValue - 1;
		        } else {
			        alert("最少购买一件！不能再减少了！");
		        }
	        }

	        function addDealNum() {
		        var numInput = document.getElementById("numInput");
		        var numValue = numInput.value;
		        // 最多买99件且小于库存量
                if (numValue >= 99) {
	                alert("最多购买99件！不能再增加了！");
                } else if (numValue >= ${deal.storeAmount}) {
                    alert("库存不足！仅剩 " + ${deal.storeAmount} + " 件！");
                } else {
	                numValue ++;
	                numInput.value = numValue;
                }
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

        <#if deal??>
            <div class="dealDetailTop">
	            <div class="dealDetailLeft">
		            <img src=${deal.imageList[1].sourcePath} >
	            </div>
	            <div class="dealDetailRight">
		            <h1> ${deal.name} </h1>
                    <#if deal.discount == 0>
                        <h2> 售价： ${deal.price} </h2>
                    <#else >
                        <h2> 原价： ${deal.price}   限时特价： ${curPrice}  </h2>
                    </#if>
		            <h2> 库存数量： ${deal.storeAmount}   已售数量： ${deal.saleAmount} </h2>
                    <div class="addDealToCart">
	                    <form action="/tiandog/cart/add" method="post" id="cartForm">
		                    <p>
			                    <button class="sub" type="button" onclick="subDealNum()"> - </button>
			                    <input type="text" id="numInput" name="dealNum" value="1" required/>
			                    <button class="add" type="button" onclick="addDealNum()"> + </button>
			                    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
			                    <input type="submit" value="立即购买" onclick="return checkIfLoginAndAddInfo()">
		                    </p>
	                    </form>
                    </div>
		            <h2> 品牌： ${deal.merchant.name}   产地： ${deal.merchant.address}    联系电话： ${deal.merchant.phone}</h2>
	            </div>
            </div>
            <div class="dealDetailBelow">
                <img src=${deal.imageList[2].sourcePath} >
                <h2> ${deal.detailInfo} </h2>
            </div>
        </#if>


    </body>
</html>