<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/6 0006
  Time: 下午 3:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>聊天</title>
  </head>
  <body>
    <form action="chartServlet" method="post">
      <table>
        <tr>
          <td>用户名：</td>
          <td><input type="text" name="username"></td>
        </tr>
        <tr>
          <td>聊天内容：</td>
          <td><textarea cols="22" rows="5" name="content"></textarea></td>
        </tr>
        <tr>
          <td rowspan="2"><input type="submit" value="发送"></td>
        </tr>
      </table>
    </form>
  </body>
</html>
