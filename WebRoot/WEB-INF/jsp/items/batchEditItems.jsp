<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	function batchEditItems(){
		//提交form
		document.intemsForm.action="${pageContext.request.contextPath }/items/batchEditItems.action";
		document.intemsForm.submit();
	}

	function batchEditItemsSubmit(){
		//提交form
		document.intemsForm.action="${pageContext.request.contextPath }/items/batchEditItemsSubmit.action";
		document.intemsForm.submit();
	}

</script>
<title>查询商品列表</title>
</head>
<body>
	<form name="intemsForm" action="${pageContext.request.contextPath}/items/batchEditItems.action"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td>商品名称：<input name="itemsCustom.name"></td>
				<td><input type="button" value="查询" onclick="batchEditItems()"/></td>
				<td><input type="button" value="批量修改提交" onclick="batchEditItemsSubmit()"/></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
			</tr>
			<c:forEach items="${itemsCustomList}" var="itemsCustom" varStatus="status">
				<tr>
					<td style="display:none"><input type="hidden" name="itemsCustomList[${status.index }].id" value="${itemsCustom.id}"/></td>
					<td><input name="itemsCustomList[${status.index }].name" value="${itemsCustom.name}"/></td>
					<td><input name="itemsCustomList[${status.index }].price" value="${itemsCustom.price}"/></td>
					<td><input name="itemsCustomList[${status.index }].createtime" value="<fmt:formatDate value="${itemsCustom.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
					<td><input name="itemsCustomList[${status.index }].detail" value="${itemsCustom.detail}"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>

</html>