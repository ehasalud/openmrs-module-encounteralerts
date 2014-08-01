<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="encounteralerts.editencounteralerttorole" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertToRoleList.form" />

<form method="post">
	<table>
		<c:if test="${encounterAlertToRole.id != null}">
			<tr>
				<td><spring:message code="general.id"/></td>
				<td>${encounterAlertToRole.id}</td>
			</tr>
		</c:if>
						
		<tr>
			<td><spring:message code="encounteralerts.alert.name"/></td>
			<spring:bind path="encounterAlertToRole.encounterAlert">
				<td>
					<select name="${status.expression}">
						<option value=""></option>
						<c:forEach items="${encounterAlerts}" var="alert" >
							<option value="${alert.uuid}" <c:if test="${alert.uuid == status.value}">selected</c:if>>
								${alert.name}
							</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</td>
			</spring:bind>
		</tr>
		
		<tr>
			<td><spring:message code="encounteralerts.role"/></td>
			<spring:bind path="encounterAlertToRole.role">
				<td>
					<select name="${status.expression}">
						<option value=""></option>
						<openmrs:forEachRecord name="role">
							<option value="${record.role}" <c:if test="${record.role == status.value}">selected</c:if>>
								${record.role}
							</option>
						</openmrs:forEachRecord>
					</select>
				</td>
				<td>
					<c:if test="${status.errorMessage != ''}"><span class="error">${status.errorMessage}</span></c:if>
				</td>
			</spring:bind>
		</tr>
		
	</table>
	<br/>
	<input type="submit" value="<spring:message code="general.save" />" />
	&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="<spring:message code="general.cancel" />" onClick="window.location = 'encounterAlertToRoleList.form';"/>
</form>

<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>