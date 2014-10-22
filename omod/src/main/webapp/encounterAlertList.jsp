<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>
<style>

 table { 
  border-collapse:collapse; 
 }

</style>
<h2>
	<spring:message code="encounteralerts.title" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertList.form" />

<b class="boxHeader"><spring:message code="encounteralerts.current"/></b>
<div class="box">
	<c:if test="${fn:length(encounterAlerts) == 0}">
		<spring:message code="general.none"/>
	</c:if>
	<c:if test="${fn:length(encounterAlerts) != 0}">
		<form method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
				<tr>
					<th></th>
					<th><spring:message code="general.edit"/></th>
					<th><spring:message code="general.id"/></th>
					<th><spring:message code="encounteralerts.alert.name"/></th>
					<th><spring:message code="encounteralerts.alert.description"/></th>
					<th><spring:message code="encounteralerts.observations"/></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="encounterAlert" items="${encounterAlerts}">
					<tr>
						<td><input type="checkbox" name="deleteId" value="${encounterAlert.id}" /></td>
						<td><a href="encounterAlertForm.form?encounterAlertId=${encounterAlert.id}">
							<img src="<c:url value='/images/edit.gif'/>" border="0"/></a>
						</td>
						<td>${encounterAlert.id}</td>
						<c:if test="${encounterAlert.retired == true }">
							<td><del>${encounterAlert.name}</del></td>
							<td><del>${encounterAlert.description}</del></td>
							<td class="error"><spring:message code="encounteralerts.retiredEncounterAlert"/></td>							
						</c:if>
						<c:if test="${encounterAlert.retired != true }">
							<td>${encounterAlert.name}</td>
							<td>${encounterAlert.description}</td>
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
<a href="encounterAlertForm.form"><spring:message code="general.add"/></a>

<%@ include file="/WEB-INF/template/footer.jsp"%>