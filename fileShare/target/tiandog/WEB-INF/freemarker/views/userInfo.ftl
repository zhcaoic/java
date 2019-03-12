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
        <div class="UserInfoTitle">
            <br>
            <h2> 尊敬的${username}，您的个人信息如下：</h2>
            <br>
        </div>
        <div class="UserInfoList">
            <ul class="infoList">
                <li><h3> 真实姓名     ： <#if userBasicInfo.realName??>${userBasicInfo.realName}<#else>未填写！</#if> </h3></li>
                <li><h3> 邮箱         ： <#if userBasicInfo.email??>${userBasicInfo.email}<#else>未填写！</#if> </h3></li>
                <li><h3> 手机号码     ： <#if userBasicInfo.phone??>${userBasicInfo.phone}<#else>未填写！</#if> </h3></li>
                <li><h3> 年龄         ： <#if userBasicInfo.age??>${userBasicInfo.age}<#else>未填写！</#if> </h3></li>
                <li><h3> 住址         ： <#if userBasicInfo.address??>${userBasicInfo.address}<#else>未填写！</#if> </h3></li>
                <li><h3> 职业         ： <#if userBasicInfo.occupation??>${userBasicInfo.occupation}<#else>未填写！</#if> </h3></li>
                <li><h3> 上次修改时间 ： <#if userBasicInfo.updateTime??>${userBasicInfo.updateTime?string('yyyy-MM-dd HH:mm:ss')}<#else>未填写！</#if> </h3></li>
            </ul>
        </div>
        <div class="fieldsValueIsNull">
                    <#if arrayValueIsNull??>
                        <br>
                        <p><h2> 您有${arrayValueIsNull?size}项个人信息尚未填写，分别是:  </h2></p>
                        <#list arrayValueIsNull as field>
                            <h2> ${field}   </h2>
                        </#list>
                        <h2>  请修改！ </h2>
                        &nbsp&nbsp&nbsp&nbsp
                        <a href="/pages/updateUserInfo.html"> 前往修改 </a>
                        <br>
                    <#else>
                        <br>
                        <p><h2> 恭喜！ 您的个人信息已全部填写完整！ </h2></p>
                        <br>
                    </#if>
        </div>
    </body>
</html>