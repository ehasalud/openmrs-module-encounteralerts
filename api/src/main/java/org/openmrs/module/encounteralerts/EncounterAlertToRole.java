package org.openmrs.module.encounteralerts;

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Role;

public class EncounterAlertToRole extends BaseOpenmrsObject {

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
