<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a>
	</li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/encounterAlertList") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/encounteralerts/encounterAlertList.form"><spring:message
				code="encounteralerts.manage" /></a>
	</li>
	
	<li
		<c:if test='<%= request.getRequestURI().contains("/encounterAlertToRoleList") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/encounteralerts/encounterAlertToRoleList.form"><spring:message
				code="encounteralerts.alerttorole" /></a>
	</li>
	
</ul>

