<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>json交互测试</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
/* 请求json，输出json */
function requestJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/requestJson.action',
		contentType:'application/json;charset=utf-8',//数据格式是json
		data:'{"name":"手机","price":999}',
		success:function(data){
			alert(data);
		}
	});
	
}

/* 请求key/value，输出json */
function responseJson(){
	
	$.ajax({
		type:'post',
		url:'${pageContext.request.contextPath }/responseJson.action',
		data:'name=手机&price=999',
		success:function(data){
			alert(data);
		}
	});
	
}
</script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求json，输出json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出json"/>
</body>
</html>