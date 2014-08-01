package org.openmrs.module.encounteralerts.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.SerializedObject;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.SerializedObjectEditor;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/encounteralerts/encounterAlertForm")
public class EncounterAlertFormController {
	
	protected final Log log = LogFactory.getLog(getClass());

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(org.openmrs.api.db.SerializedObject.class, new SerializedObjectEditor());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void initForm() {
	}
	
	@ModelAttribute("encounterAlert")
	protected EncounterAlert populateEncounterAlert(HttpServletRequest request) throws ServletException{
		if (!Context.isAuthenticated())
			return new EncounterAlert();
		
		EncounterAlert alert = null;
		
		String id = request.getParameter("encounterAlertId");
		if (id != null){
			EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
			alert = service.getEncounterAlert(Integer.valueOf(id));
		}
		
		if (alert == null){
			alert = new EncounterAlert();
		}
		
		return alert;
	}
	
	@ModelAttribute("upQueries")
	protected List<SerializedObject> populateUpQueries(){
		if (!Context.isAuthenticated())
			return new ArrayList<SerializedObject>();
		
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		return service.getAllEncounterQueries();
	}
	
	@ModelAttribute("downQueries")
	protected List<SerializedObject> populateDownQueries(){
		if (!Context.isAuthenticated())
			return new ArrayList<SerializedObject>();
		
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		return service.getAllEncounterQueries();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String processSubmit(@ModelAttribute("encounterAlert")EncounterAlert alert) {
		if (Context.isAuthenticated()) {
			EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
			if (alert.getId() != null){
				log.info("Creating new EncounterAlert");
				service.updateEncounterAlert(alert);
			} else {
				log.info("Creating new EncounterAlert");
				service.createEncounterAlert(alert);
			}
		}
		return "redirect:encounterAlertList.form";
	}
}
