<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu">
	<p><a href='<c:url value="/createCustomer" />'>Create a new customer</a></p>
	<p><a href='<c:url value="/createOrder" />'>Create a new order</a></p>
	<p><a href='<c:url value="/listCustomers" />'>List customers</a></p>
	<p><a href='<c:url value="/listOrders" />'>List orders</a></p>
</div>
