<%--
  Created by IntelliJ IDEA.
  User: 云淡风轻
  Date: 2018/3/26
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div title="批量添加" data-options="closable:false">
    <div id="pnlImport" class="easyui-panel" title=" "
         data-options="resizable:true,closed:false,fit:true,tools:'#tools_c$#_2'">
        <form id="updata" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td colspan="2">
                        <input id="upfile" name="upfile" type="file">
                    </td>
                </tr>

            </table>
        </form>
    </div>
    </div>
</body>
</html>
