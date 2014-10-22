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

package org.openmrs.module.encounteralerts;

import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.Role;

public class EncounterAlertToRole extends BaseOpenmrsMetadata {

	private Integer id;
	private EncounterAlert encounterAlert;
	private Role role;
	
	public EncounterAlert getEncounterAlert() {
		return encounterAlert;
	}

	public void setEncounterAlert(EncounterAlert encounterAlert) {
		this.encounterAlert = encounterAlert;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
