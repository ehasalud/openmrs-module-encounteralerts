<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="encounteralerts.title" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertList.form" />

<h3><spring:message code="encounteralerts.current"/></h3>
<c:if test="${fn:length(encounterAlerts) == 0}">
	<spring:message code="general.none"/>
</c:if>
<c:if test="${fn:length(encounterAlerts) != 0}">
	<form method="post">
		<table>
			<tr>
				<th></th>
				<th></th>
				<th><spring:message code="general.id"/></th>
				<th><spring:message code="encounteralerts.alert.name"/></th>
				<th><spring:message code="encounteralerts.alert.description"/></th>
			</tr>
			<c:forEach var="encounterAlert" items="${encounterAlerts}">
				<tr>
					<td><input type="checkbox" name="deleteId" value="${encounterAlert.id}" /></td>
					<td><a href="encounterAlertForm.form?encounterAlertId=${encounterAlert.id}">
						<img src="<c:url value='/images/edit.gif'/>" border="0"/></a>
					</td>
					<td>${encounterAlert.id}</td>
					<td>${encounterAlert.name}</td>
					<td>${encounterAlert.description}</td>
					
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="<spring:message code="general.delete" />"/>
	</form>
</c:if>

<br/>
<br/>
<a href="encounterAlertForm.form"><spring:message code="general.add"/></a>

<%@ include file="/WEB-INF/template/footer.jsp"%>