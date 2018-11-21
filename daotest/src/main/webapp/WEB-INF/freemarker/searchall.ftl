<html lang="en-US">
    <head>
        <meta charset="UTF-8">
        <title>查询结果</title>
    </head>
    <body>
        <p>集体信息查询结果:</p>
        <br>
        <#list students as student>
            <p>姓名: ${student.studentName}</p>
            <p>学号: ${student.studentId}</p>
            <p>住址: ${student.studentAddress}</p>
            <br>
        </#list>
    </body>
</html>