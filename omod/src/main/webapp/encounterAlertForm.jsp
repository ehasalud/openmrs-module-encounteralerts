<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="encounteralerts.editencounteralert" />
</h2>

<openmrs:require privilege="Manage Encounter Alerts" otherwise="/login.htm" redirect="/module/encounteralerts/encounterAlertList.form" />

<form method="post">
	<table>
		<c:if test="${encounterAlert.id != null}">
			<tr>
				<td><spring:message code="general.id"/></td>
				<td>${encounterAlert.id}</td>
			</tr>
		</c:if>
		<tr>
			<td>
				<spring:message code="encounteralerts.alert.name"/>
			</td>
			<td>
				<spring:bind path="encounterAlert.name">
					<c:choose>
						<c:when test="${status.value != null}">
							<input name="${status.expression}" type="text" value="${status.value}"/>	
						</c:when>
						<c:otherwise>
							<input name="${status.expression}" type="text"/>
						</c:otherwise>
					</c:choose>
				</spring:bind>
			</td>
		</tr>
		
		<tr>
			<td>
				<spring:message code="encounteralerts.alert.description"/>
			</td>
			<td>
				<spring:bind path="encounterAlert.description">
					<c:choose>
						<c:when test="${status.value != null}">
							<input name="${status.expression}" type="text" value="${status.value}"/>	
						</c:when>
						<c:otherwise>
							<input name="${status.expression}" type="text"/>
						</c:otherwise>
					</c:choose>
				</spring:bind>
			</td>
		</tr>
		
		<tr>
			<td><spring:message code="encounteralerts.alert.upquery"/></td>
			<spring:bind path="encounterAlert.upQuery">
				<td>
					<select name="${status.expression}">
						<option value=""></option>
						<c:forEach items="${upQueries}" var="upQuery" >
							<option value="${upQuery.uuid}" <c:if test="${upQuery.uuid == status.value}">selected</c:if>>
								${upQuery.name}
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
			<td><spring:message code="encounteralerts.alert.downquery"/></td>
			<spring:bind path="encounterAlert.downQuery">
				<td>
					<select name="${status.expression}">
						<option value=""></option>
						<c:forEach items="${downQueries}" var="downQuery" >
							<option value="${downQuery.uuid}" <c:if test="${downQuery.uuid == status.value}">selected</c:if>>
								${downQuery.name}
							</option>
						</c:forEach>
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
	<input type="button" value="<spring:message code="general.cancel" />" onClick="window.location = 'encounterAlertList.form';"/>
</form>

<br/><br/>

<%@ include file="/WEB-INF/template/footer.jsp" %>