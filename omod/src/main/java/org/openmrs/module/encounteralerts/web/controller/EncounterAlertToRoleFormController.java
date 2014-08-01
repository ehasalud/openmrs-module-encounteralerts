package org.openmrs.module.encounteralerts.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertEditor;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.openmrs.propertyeditor.RoleEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/encounteralerts/encounterAlertToRoleForm")
public class EncounterAlertToRoleFormController {

	protected final Log log = LogFactory.getLog(getClass());

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(org.openmrs.module.encounteralerts.EncounterAlert.class, 
				new EncounterAlertEditor());
		binder.registerCustomEditor(org.openmrs.Role.class, new RoleEditor());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void initForm() {
	}
	
	@ModelAttribute("encounterAlertToRole")
	protected EncounterAlertToRole populateEncounterAlertToRole(HttpServletRequest request) throws ServletException{
		if (!Context.isAuthenticated())
			return new EncounterAlertToRole();
		
		EncounterAlertToRole alert = null;
		
		String id = request.getParameter("encounterAlertToRoleId");
		if (id != null){
			EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
			alert = service.getEncounterAlertToRole(Integer.valueOf(id));
		}
		
		if (alert == null){
			alert = new EncounterAlertToRole();
		}
		
		return alert;
	}
	
	@ModelAttribute("encounterAlerts")
	protected List<EncounterAlert> populateEncounterAlerts(){
		if (!Context.isAuthenticated())
			return new ArrayList<EncounterAlert>();
		
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		return service.getAllEncounterAlerts();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String processSubmit(@ModelAttribute("encounterAlertToRole")EncounterAlertToRole alert) {
		if (Context.isAuthenticated()) {
			EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
			if (alert.getId() != null){
				log.info("Updating EncounterAlert");
				service.updateEncounterAlertToRole(alert);
			} else {
				log.info("Creating new EncounterAlert");
				service.createEncounterAlertToRole(alert);
			}
		}
		return "redirect:encounterAlertToRoleList.form";
	}
}
