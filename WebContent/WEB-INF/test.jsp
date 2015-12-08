<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Test</title>
	</head>
	
	<body>
		<p>This is a page generated from a JSP.</p>
		<p>
			${ requestScope.test }
			URL request parameter from inside the JSP: ${ param.author }
		</p>
		<p>
			Get bean:
			${ requestScope.coyote.firstName }
			${ requestScope.coyote.lastName }
		</p>
	</body>
</html>
