<%@page import="com.ticket.MutipleThread"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>ticket</title>
<style type="text/css">
    .h_li {
       float:left;
       list-style:none;
       margin-left: 10px;
    }
</style>
<script src="assets/js/jquery-2.0.3.min.js"></script>
<script src="assets/js/jquery.form.js"></script>
</head>
<body>
<%String destination=request.getParameter("mySelect2");
  String departure = request.getParameter("mySelect1");
  int first_currentTicket = MutipleThread.getCurrentSeatByTrip(Integer.parseInt(departure), Integer.parseInt(destination), 1);
  int second_currentTicket = MutipleThread.getCurrentSeatByTrip(Integer.parseInt(departure), Integer.parseInt(destination), 2);
%>
<form action="confirmServlet" id="ticketForm" method="post">
<%-- rest: <span id="currentTicketSpan"><%=ticket %></span> tickets
<input type="button" onclick="confirm()" value="Purchase"> --%>
<table width="98%" border=1 id="info">
    <tr>
        <td align="center">车次信息</td>
        <td align="center">发/到站</td>
        <td align="center">参考价</td>
    </tr>
    <tr>
        <td align="center">133</td>
        
        <td>
            <ul style="list-style:none;" >
                <li><span>始： 成都</span></li>
            </ul>
            <ul style="list-style:none;">
                <li><span>终： 北京</span></li>
            </ul>
        </td>
        <td id="content">
            <ul style="list-style:none;"><li class="h_li"><span>一等座： 900RMB</span></li><li class="h_li"><span id="first_currentTickets"></span></li><li><input type="button" value="预订" onclick="confirm(1)" style="margin-left:10px;"></li></ul>
            <ul style="list-style:none;"><li class="h_li"><span>二等座： 700RMB</span></li><li class="h_li"><span id="second_currentTickets"></span></li><li><input type="button" value="预订" onclick="confirm(2)" style="margin-left:10px;"></li></ul>
        </td>
    </tr>
</table>
</form>
<script type="text/javascript">
		jQuery(function($) {
			var departure = <%=departure%>;
			var destination = <%=destination%>;
			var first_currentTickets = <%=first_currentTicket%>;
			var second_currentTickets = <%=second_currentTicket%>;
			if(departure==null||destination==null||destination<=departure||first_currentTickets==-99||second_currentTickets==-99) {
				alert("无此班次");
				window.location.href="choose.jsp";
			}
			//alert(currentTickets);
			//alert(departure);
			//alert(destination);
			//var currentTicketSpan = $("#currentTicketSpan").html();
			//$("#currentTicketSpan").html("100");
			//alert(currentTicketSpan);
			$("#first_currentTickets").text("余"+first_currentTickets+"张");
			$("#second_currentTickets").text("余"+second_currentTickets+"张");
			//info.append("<tr><td align='center'>车次信息</td><td align='center'>发/到站</td><td align='center'>参考价</td></tr>");
			//info.append("<tr><td align='center'>K133</td><td></tr>");
		});
</script>
<script type="text/javascript">
 function confirm(grade) {
	 var first = <%=departure%>;
	 var second = <%=destination%>;
	// alert(first);
	 //alert(second);
	 $('#ticketForm').ajaxSubmit({
	     type: "post",
	     url: "confirmServlet",
	     data:{'first':first,'second':second,'grade':grade},
	     success: function(result){
	    	 //var par='<%=request.getParameter("current")%>';
	    	 alert("购买成功");
	    	 window.location.reload();     
         },
	     
	     error : function(XMLHttpRequest, textStatus, errorThrown) {
	    	 alert("出错了");
         }
	 });
 }
 </script>
</body>
</html>