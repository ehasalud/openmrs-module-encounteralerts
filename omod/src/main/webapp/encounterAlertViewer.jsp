<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<style type="text/css">
<!--
#alertTabs li { 
	display:inline; 
	border-top: .2em;
	border-left: .2em;
	border-right: .2em;
}	
#alertTabs ul li {
	display: inline-block;
	clear: none;
	float: left;
	border-top: .2em;
	border-left: .2em;
	border-right: .2em;
	padding-left: 14px;
	border-bottom: thick solid #69EC88;
}
.current {
	background-color: #69EC88;
}
table.encounterList td{
	border-width: 2px;
	border-color: #666666;
}
-->
</style>

<script type="text/javascript">

//initTabs
$j(document).ready(function() {
	var tabs = document.getElementById("alertTabs").getElementsByTagName("a");
	if (tabs.length && tabs[0].id){
		var c = tabs[0].id;
		changeTab(c);
	}	
});

function changeTab(tabObj) {
	if (!document.getElementById || !document.createTextNode) {return;}
	if (typeof tabObj == "string")
		tabObj = document.getElementById(tabObj);

	if (tabObj) {
		var tabs = tabObj.parentNode.parentNode.getElementsByTagName('a');
		for (var i=0; i<tabs.length; i++) {
			if (tabs[i].className.indexOf('current') != -1) {
				manipulateClass('remove', tabs[i], 'current');
			}
			var divId = tabs[i].id.substring(0, tabs[i].id.lastIndexOf("Tab"));
			var divObj = document.getElementById(divId);
			if (divObj) {
				if (tabs[i].id == tabObj.id)
					divObj.style.display = "";
				else
					divObj.style.display = "none";
			}
		}
		addClass(tabObj, 'current');
	}
	return false;
}
</script>

<h2>
	<spring:message code="encounteralerts.viewer" />
</h2>

<div id="alertTabs">
	<ul>
	<c:choose>
		<c:when test="${fn:length(encounterAlertList) > 0}">
				<c:forEach var="encounterAlert" items="${encounterAlertList}">
					<li><a id="alert${encounterAlert.id}Tab" href="#" onclick="changeTab(this);">${encounterAlert.name}</a></li>
				</c:forEach>
		</c:when>
		<c:otherwise>
			<spring:message code="encounteralerts.viewer.no_alerts" />
		</c:otherwise>
	</c:choose>
	</ul>
</div>

<br>
<br>

<div id="alertContent">
	<c:choose>
		<c:when test="${fn:length(encounterAlertList) > 0}">
			<c:forEach var="encounterAlert" items="${encounterAlertList}">
				<div id="alert${encounterAlert.id}" >
					
					<c:choose>
						<c:when test="${fn:length(encounterListByAlert[encounterAlert]) > 0}">
							<table class="encounterList">
								<tr>
									<th><spring:message code="general.name" /></th>
									<th><spring:message code="encounteralerts.viewer.state" /></th>
									<th><spring:message code="Encounter.title" /></th>
								</tr>	
							
								<c:forEach var="encounter" items="${encounterListByAlert[encounterAlert]}">
									<tr>
										<td>${encounter.encounter.patient.personName }</td>
										<c:choose>
											<c:when test="${encounter.state == 1 }">
												<td bgcolor="#EF6464"><spring:message code="encounteralerts.viewer.pending" /></td>
											</c:when>
											<c:otherwise>
												<td bgcolor="#4CFA75"><spring:message code="encounteralerts.viewer.checked" /></td>
											</c:otherwise>
										</c:choose>
										<td><a href="../../module/htmlformentry/htmlFormEntry.form?encounterId=${encounter.encounter.id}"><spring:message code="encounteralerts.viewer.linktoencounter" /></a></td>
									</tr>
								
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							<i><spring:message code="encounteralerts.viewer.noencounters" /></i>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>	
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>