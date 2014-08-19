package org.openmrs.module.encounteralerts.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.api.context.Context;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EvaluatedEncounter;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/encounteralerts/encounterAlertViewer")
public class EncounterAlertViewerController {
	
	@RequestMapping(method = RequestMethod.GET)
	protected void initForm(){
	}
	
	@ModelAttribute("encounterAlertList")
	protected List<EncounterAlert> populateEncounterAlertList(){
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		return service.getCurrentUserEncounterAlerts();
	}
	
	@ModelAttribute("encounterListByAlert")
	protected Map<EncounterAlert,List<EvaluatedEncounter>> populateEncounterListByAlert(){
		List<EncounterAlert> alertList = populateEncounterAlertList();
		HashMap<EncounterAlert, List<EvaluatedEncounter>> result = 
				new HashMap<EncounterAlert, List<EvaluatedEncounter>>();
		
		EncounterAlertsService service = (EncounterAlertsService) Context.getService(EncounterAlertsService.class);
		for (EncounterAlert alert : alertList){
			result.put(alert, service.evaluateCurrentUserEncounterAlert(alert));
		}
		
		return result;
	}
	
}
