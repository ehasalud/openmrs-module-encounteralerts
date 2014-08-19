/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.encounteralerts.api;

import java.util.List;

import org.openmrs.Role;
import org.openmrs.api.OpenmrsService;
import org.openmrs.api.db.SerializedObject;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.EvaluatedEncounter;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(EncounterAlertsService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface EncounterAlertsService extends OpenmrsService {
   
	public void createEncounterAlert(EncounterAlert encounterAlert);
	
	public void updateEncounterAlert(EncounterAlert encounterAlert);
	
	public void deleteEncounterAlert(EncounterAlert encounterAlert);
		
	public EncounterAlert getEncounterAlert(Integer id);
	
	public EncounterAlert getEncounterAlertByUuid(String uuid);
	
	public List<EncounterAlert> getAllEncounterAlerts();
	
	public List<EncounterAlert> getEncounterAlertsByRole(Role role);
	
	public List<EncounterAlert> getCurrentUserEncounterAlerts();
	
	public List<EvaluatedEncounter> evaluateCurrentUserEncounterAlert(EncounterAlert alert);

	public void createEncounterAlertToRole(EncounterAlertToRole alert);
	
	public void updateEncounterAlertToRole(EncounterAlertToRole alert);
	
	public void deleteEncounterAlertToRole(EncounterAlertToRole alert);
	
	public EncounterAlertToRole getEncounterAlertToRole(Integer id);
	
	public List<EncounterAlertToRole> getAllEncounterAlertsToRole();
	
	public List<SerializedObject> getAllEncounterQueries();
	
	public SerializedObject getEncounterQueryByUuid(String uuid);
	
	public SerializedObject getEncounterQuery(Integer id);
	
}