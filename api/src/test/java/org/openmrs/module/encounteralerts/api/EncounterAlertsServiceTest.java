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

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Role;
import org.openmrs.api.context.Context;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * Tests {@link ${EncounterAlertsService}}.
 */
public class  EncounterAlertsServiceTest extends BaseModuleContextSensitiveTest {
	
	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(EncounterAlertsService.class));
	}
	
	@Test
	public void shouldGetEncounterAlert() throws Exception {
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertRecords.xml");
		
		EncounterAlertsService service = Context.getService(EncounterAlertsService.class);
		
		EncounterAlert alertById = service.getEncounterAlert(1);
		Assert.assertNotNull(alertById);
		Assert.assertEquals(alertById.getUuid(), "0d4873f5-d516-4a88-b0e9-dceb807258d5");
		
		EncounterAlert alertByUuid = service.getEncounterAlertByUuid("279d398c-fb18-486f-b2dc-6097ad750d6a");
		Assert.assertNotNull(alertByUuid);
		Assert.assertEquals(2, (int) alertByUuid.getId());
	}
	
	@Test
	public void shouldGetAllEncouterAlert() throws Exception {
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertRecords.xml");
		
		EncounterAlertsService service = Context.getService(EncounterAlertsService.class);
		
		List<EncounterAlert> list = service.getAllEncounterAlerts();
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void shouldGetEncounterAlerstByRole() throws Exception {
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertRecords.xml");
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertToRoleRecords.xml");

		EncounterAlertsService service = Context.getService(EncounterAlertsService.class);

		Role alerted = new Role("Alerted");
		List<EncounterAlert> list = service.getEncounterAlertsByRole(alerted, true);
		Assert.assertEquals(2, list.size());
		
		List<EncounterAlert> activeList = service.getEncounterAlertsByRole(alerted, false);
		Assert.assertEquals(1, activeList.size());
		Assert.assertEquals("279d398c-fb18-486f-b2dc-6097ad750d6a", activeList.get(0).getUuid());
	}
	
	@Test
	public void shouldGetCurrentUserEncounterAlerts() throws Exception{
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertRecords.xml");
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertToRoleRecords.xml");

		Context.authenticate("testuser", "Testuser123");
		
		EncounterAlertsService service = Context.getService(EncounterAlertsService.class);
		
		List<EncounterAlert> list = service.getCurrentUserEncounterAlerts(true);
		Assert.assertEquals(2, list.size());
		
		List<EncounterAlert> activeList = service.getCurrentUserEncounterAlerts(false);
		Assert.assertEquals(1, activeList.size());
		Assert.assertEquals("279d398c-fb18-486f-b2dc-6097ad750d6a", activeList.get(0).getUuid());
	}
	
	@Test
	public void shouldGetCurrentUserEncounterAlerts_multipleRoles() throws Exception{
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertRecords.xml");
		executeDataSet("org/openmrs/module/encounteralerts/include/EncounterAlertToRoleRecords.xml");
		executeDataSet("org/openmrs/module/encounteralerts/include/AddExtraRole.xml");
		
		Context.authenticate("testuser", "Testuser123");

		EncounterAlertsService service = Context.getService(EncounterAlertsService.class);
		
		List<EncounterAlert> list = service.getCurrentUserEncounterAlerts(true);
		Assert.assertEquals(3, list.size());
		
		List<EncounterAlert> activeList = service.getCurrentUserEncounterAlerts(false);
		Assert.assertEquals(2, activeList.size());
	}
}
