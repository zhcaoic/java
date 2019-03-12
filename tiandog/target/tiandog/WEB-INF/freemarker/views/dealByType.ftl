<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> 全部${type}类商品 </title>
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


        <div class="dealByTypeTitle">
            <h1> 商品分类查询结果：以下为所有${type}类的商品 </h1>
        </div>
        <div class="dealForShow">
            <#if dealList??>
                <#list dealList as dl>
                    <div class="dealForShowImage">
	                    <a href="/tiandog/deal/${dl.id}"><img src=${dl.imageList[0].sourcePath} ></a>
                    </div>
                    <div class="dealForShowName">
	                    <a href="/tiandog/deal/${dl.id}"><h2> ${dl.name} </h2></a>
                    </div>
                </#list>
            </#if>
        </div>
        <div class="dealByTypeBelow">
            <#if page == 1 >
	            <h2> 第${page}页 </h2>
                <h2> 共${maxPage}页 </h2>
	            &nbsp&nbsp&nbsp&nbsp
	            <a href="/tiandog/deal/${type}/1"> 首页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${nextPage}"> 下一页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${maxPage}"> 最后一页 </a>
            <#elseif page == maxPage >
                <h2> 第${page}页 </h2>
                <h2> 共${maxPage}页 </h2>
	            &nbsp&nbsp&nbsp&nbsp
	            <a href="/tiandog/deal/${type}/1"> 首页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${prePage}"> 上一页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${maxPage}"> 最后一页 </a>
            <#else>
                <h2> 第${page}页 </h2>
                <h2> 共${maxPage}页 </h2>
	            &nbsp&nbsp&nbsp&nbsp
	            <a href="/tiandog/deal/${type}/1"> 首页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${prePage}"> 上一页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${nextPage}"> 下一页 </a>
	            &nbsp&nbsp&nbsp&nbsp
                <a href="/tiandog/deal/${type}/${maxPage}"> 最后一页 </a>
            </#if>
        </div>
    </body>
</html>
