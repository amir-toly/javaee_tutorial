<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<label for="customerLastName">Last name <span class="required">*</span></label>
<input type="text" id="customerLastName" name="customerLastName" value='<c:out value="${ requestScope.customer.lastName }" />' size="20" maxlength="20" />
<span class="error">${ requestScope.form.errors['customerLastName'] }</span>
<br />

<label for="customerFirstName">First name</label>
<input type="text" id="customerFirstName" name="customerFirstName" value='<c:out value="${ requestScope.customer.firstName }" />' size="20" maxlength="20" />
<span class="error">${ requestScope.form.errors['customerFirstName'] }</span>
<br />

<label for="customerAddress">Shipping address <span class="required">*</span></label>
<input type="text" id="customerAddress" name="customerAddress" value='<c:out value="${ requestScope.customer.address }" />' size="20" maxlength="60" />
<span class="error">${ requestScope.form.errors['customerAddress'] }</span>
<br />

<label for="customerPhoneNumber">Phone number <span class="required">*</span></label>
<input type="text" id="customerPhoneNumber" name="customerPhoneNumber" value='<c:out value="${ requestScope.customer.phoneNumber }" />' size="20" maxlength="20" />
<span class="error">${ requestScope.form.errors['customerPhoneNumber'] }</span>
<br />

<label for="customerEmailAddress">Email address</label>
<input type="email" id="customerEmailAddress" name="customerEmailAddress" value='<c:out value="${ requestScope.customer.email }" />' size="20" maxlength="60" />
<span class="error">${ requestScope.form.errors['customerEmailAddress'] }</span>
<br />

<label for="customerPictureFile">Profile picture</label>
<input type="file" id="customerPictureFile" name="customerPictureFile" />
<span class="error">${ requestScope.form.errors['customerPictureFile'] }</span>
<br />
