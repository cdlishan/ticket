<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script src="assets/js/jquery-2.0.3.min.js"></script>
<script src="assets/js/jquery.form.js"></script>
<script type="text/javascript">
function searchTickets() {
$('#chooseForm').submit();
}
</script>
</head>
<body>
<form action="ticket.jsp" id="chooseForm">
<select id="mySelect1" name="mySelect1">  
<option value="0">成都</option>  
<option value="1">德阳</option>  
<option value="2">宝鸡</option>  
<option value="3">西安</option>  
<option value="4">太行山</option>
<option value="5">太原</option>
<option value="6">洛阳</option>
<option value="7">郑州</option>
<option value="8">邯郸</option>
<option value="9">秦皇岛</option>
<option value="10">石家庄</option>
<option value="11">北京</option>
</select>  
<select id="mySelect2" name="mySelect2"> 
<option value="1">德阳</option>  
<option value="2">宝鸡</option>  
<option value="3">西安</option>  
<option value="4">太行山</option>
<option value="5">太原</option>
<option value="6">洛阳</option>
<option value="7">郑州</option>
<option value="8">邯郸</option>
<option value="9">秦皇岛</option>
<option value="10">石家庄</option>
<option value="11">北京</option>
</select>
<input type="button" value="查询" onclick="searchTickets()">
</form>
<script type="text/javascript">
		jQuery(function($) {
			//$('#mySelect2').hide();
			//alert($('#mySelect1 option:selected').val());
		});
</script>
</body>
</html>