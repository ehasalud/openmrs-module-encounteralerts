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

import java.util.Date;

import org.openmrs.Encounter;

public class EvaluatedEncounter implements Comparable<EvaluatedEncounter>{

	//private static final long serialVersionUID = -8887588354175564850L;

	public static final int TO_BE_CHECKED = 1;
	public static final int CHECKED = 2;
	
	private Encounter encounter;
	private Integer state = TO_BE_CHECKED;
	
	public EvaluatedEncounter(){
	}
	
	public EvaluatedEncounter(Encounter encounter){
		this.encounter = encounter;
	}
	
	public EvaluatedEncounter(Encounter encounter, int state){
		this(encounter);
		this.state = state;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	
	public static int getToBeChecked() {
		return TO_BE_CHECKED;
	}

	public static int getChecked() {
		return CHECKED;
	}

	@Override
	public int compareTo(EvaluatedEncounter target) {
		Date thisDate = this.getEncounter().getEncounterDatetime();
		Date targetDate = target.getEncounter().getEncounterDatetime();
		
		if (thisDate == null || targetDate == null){
			return 0;
		}
		
		return thisDate.compareTo(targetDate);		
	}
	
}
