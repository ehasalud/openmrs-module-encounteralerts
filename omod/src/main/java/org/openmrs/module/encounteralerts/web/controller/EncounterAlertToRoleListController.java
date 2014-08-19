package org.openmrs.module.encounteralerts.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.EvaluatedEncounter;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/encounteralerts/encounterAlertToRoleList")
public class EncounterAlertToRoleListController {

protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(method = RequestMethod.GET)
	protected void initForm() {		
	}
	
	@ModelAttribute("encounterAlertsToRole")
	protected List<EncounterAlertToRole> populateEncounterAlertsToRole(){
		if (!Context.isAuthenticated())
			return new ArrayList<EncounterAlertToRole>();
		
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		return service.getAllEncounterAlertsToRole();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String processSubmit(HttpServletRequest request) {
		String[] toDelete = request.getParameterValues("deleteId");
		if (toDelete == null){
			return null;
		}
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		for (String s : toDelete) {
			Integer id = Integer.valueOf(s);
			service.deleteEncounterAlertToRole(service.getEncounterAlertToRole(id));
		}
		return "redirect:encounterAlertToRoleList.form";
	}
	
}
