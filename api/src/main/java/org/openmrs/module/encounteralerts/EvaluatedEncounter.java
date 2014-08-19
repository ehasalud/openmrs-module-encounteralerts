package org.openmrs.module.encounteralerts;

import java.util.List;

import org.openmrs.Encounter;

public class EvaluatedEncounter extends Encounter {

	private static final long serialVersionUID = -8887588354175564850L;

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
	
}
