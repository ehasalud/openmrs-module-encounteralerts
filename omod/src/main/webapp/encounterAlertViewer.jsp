<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>
<style type="text/css">
<!--

#alertTabs {
	font-size: 0.8em;
	margin: 10px auto 7px auto;
	padding-top: 5px;
	padding-left: 5px;
	padding-bottom: 2px;
	border-bottom: 1px solid navy;
	width: 99%;
}

#alertTabs ul, #alertTabs li { 
	display: inline;
	list-style-type: none;
	padding: 0px 0px 0px 0px;
}	

#alertTabs a:hover {
	text-decoration: underline;
}

#alertTabs a:link, #alertTabs a:visited {
	border: 1px solid navy;
	font-size: small;
	font-weight: bold;
	margin-right: 8px;
	padding: 2px 10px 2px 10px;
	text-decoration: none;
	color: navy;
	background: #E0E0F0;
}

#alertTabs a:link.active, #alertTabs a:visited.active {
	border-bottom: 1px solid #FFFFFF;
}

#alertTabs a.current, #alertTabs a:link.current, #alertTabs a:visited.current, #alertTabs a.current:hover {
	background: #FFFFFF;
	border-bottom: 1px solid #FFFFFF;
	color: navy;
	text-decoration: none;
}

table.encounterList td{
	border-width: 2px;
	border-color: #666666;
}

table.encounterList tbody tr:hover{
	 background-color: #d8daf9;
	 cursor: pointer
}
-->
</style>

<script type="text/javascript">

/** initTabs */
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

<script>
$j(document).ready(function () {
	
	$j(".clickableRow").click(function() {
        window.document.location = $j(this).attr("href");
  	});
  	
});
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


<div id="alertContent">
	<c:choose>
		<c:when test="${fn:length(encounterAlertList) > 0}">
			<b class="boxHeader">
				<spring:message code="encounteralerts.viewer.list"/>						
			</b>
			<c:forEach var="encounterAlert" items="${encounterAlertList}">
				<div id="alert${encounterAlert.id}" >
					<div class="box">				
					<c:choose>
						<c:when test="${fn:length(encounterListByAlert[encounterAlert]) > 0}">
							<table class="encounterList" style="width:100%">
								<thead>
									<tr>
										<th style="width:35%"><spring:message code="general.name" /></th>
										<th style="width:15%"><spring:message code="Encounter.datetime" /></th>
										<!-- <th style="width:15%"><spring:message code="Especialista" /></th> -->
										<th style="width:20%"><spring:message code="Encounter.location" /></th>
										<th style="width:10%"><spring:message code="Departamento" /></th>
										<th style="width:15%"><spring:message code="Encounter.type"/></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="encounter" items="${encounterListByAlert[encounterAlert]}">
										<c:choose>
											<c:when test="${encounter.state == 1 }">
												<tr class="pending clickableRow" href="../../module/htmlformentry/htmlFormEntry.form?encounterId=${encounter.encounter.id}">
											</c:when>
											<c:otherwise>
												<tr class="revised clickableRow" href="../../module/htmlformentry/htmlFormEntry.form?encounterId=${encounter.encounter.id}">
											</c:otherwise>
										</c:choose>	
											<td>${encounter.encounter.patient.personName }</td>
											<td>${fn:split(encounter.encounter.encounterDatetime, ' ')[0]}</td>
											<%-- <c:set var="lastperson" scope="session" value=""/>
											<c:forEach var="observation" items="${ encounter.encounter.obs }">
												<c:if test="${observation.concept.conceptId eq 161}">
													<c:set var="lastperson" scope="session" value="${ observation.creator }"/>
												</c:if>
											</c:forEach>
											<td><c:out value="${lastperson}"/></td> --%>
											<td>${encounter.encounter.location }</td>
											<c:choose>
												<c:when test='${ encounter.encounter.patient.personAddress.stateProvince == "16 Alta Verapaz" }'>
													<td bgcolor="#7FFFD4"><spring:message code="Alta Verapaz" /></td>
												</c:when>
												<c:otherwise>
													<td bgcolor="#4CFA75"><spring:message code="San Marcos" /></td>
												</c:otherwise>
											</c:choose>
											<td>${encounter.encounter.encounterType.name }</td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>
<%-- 									${ encounterListByAlert[encounterAlert][1].encounter.encounterId } --%>
								</tfoot>
							</table>
						</c:when>
						<c:otherwise>
							<i><spring:message code="encounteralerts.viewer.noencounters" /></i>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
		
		</c:otherwise>
	</c:choose>	
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>