<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="encounteralerts.alerttorole" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertList.form" />

<c:if test="${fn:length(encounterAlertsToRole) == 0}">
	<spring:message code="general.none"/>
</c:if>

<c:if test="${fn:length(encounterAlertsToRole) != 0}">
	<form method="post">
		<table>
			<tr>
				<th></th>
				<th></th>
				<th><spring:message code="general.id"/></th>
				<th><spring:message code="encounteralerts.alert.name"/></th>
				<th><spring:message code="encounteralerts.role"/></th>
			</tr>
			<c:forEach var="encounterAlertToRole" items="${encounterAlertsToRole}">
				<tr>
					<td><input type="checkbox" name="deleteId" value="${encounterAlertToRole.id}" /></td>
					<td><a href="encounterAlertToRoleForm.form?encounterAlertToRoleId=${encounterAlertToRole.id}">
						<img src="<c:url value='/images/edit.gif'/>" border="0"/></a>
					</td>
					<td>${encounterAlertToRole.id}</td>
					<td>${encounterAlertToRole.encounterAlert.name}</td>
					<td>${encounterAlertToRole.role.name}</td>
					
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="<spring:message code="general.delete" />"/>
	</form>
</c:if>

<br/>
<br/>
<a href="encounterAlertToRoleForm.form"><spring:message code="general.add"/></a>

<%@ include file="/WEB-INF/template/footer.jsp"%>