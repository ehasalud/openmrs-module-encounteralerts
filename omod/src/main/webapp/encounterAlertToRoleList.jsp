<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="encounteralerts.title" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertList.form" />

<b class="boxHeader"><spring:message code="encounteralerts.alerttorole" /></b>

<div class="box">
<c:if test="${fn:length(encounterAlertsToRole) == 0}">
	<spring:message code="general.none"/>
</c:if>

<c:if test="${fn:length(encounterAlertsToRole) != 0}">
	<form method="post">
		<table>
			<thead>
			<tr>
				<th></th>
				<th></th>
				<th><spring:message code="general.id"/></th>
				<th><spring:message code="encounteralerts.alert.name"/></th>
				<th><spring:message code="encounteralerts.role"/></th>
				<th><spring:message code="encounteralerts.observations"/></th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="encounterAlertToRole" items="${encounterAlertsToRole}">
				<tr>
					<td><input type="checkbox" name="deleteId" value="${encounterAlertToRole.id}" /></td>
					<td><a href="encounterAlertToRoleForm.form?encounterAlertToRoleId=${encounterAlertToRole.id}">
						<img src="<c:url value='/images/edit.gif'/>" border="0"/></a>
					</td>
					<td>${encounterAlertToRole.id}</td>
					<c:if test="${encounterAlertToRole.retired == true }">
						<td><del>${encounterAlertToRole.encounterAlert.name}</del></td>
						<td><del>${encounterAlertToRole.role.name}</del></td>
						<td class="error"><spring:message code="encounteralerts.retiredEncounterAlertToRole"/></td>
					</c:if>
					<c:if test="${encounterAlertToRole.retired != true }">
						<td>${encounterAlertToRole.encounterAlert.name}</td>
						<td>${encounterAlertToRole.role.name}</td>
					</c:if>					
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<br/>
		<input type="submit" value="<spring:message code="general.delete" />"/>
	</form>
</c:if>
</div>

<br/>
<a href="encounterAlertToRoleForm.form"><spring:message code="general.add"/></a>

<%@ include file="/WEB-INF/template/footer.jsp"%>