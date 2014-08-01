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

import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.api.db.SerializedObject;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class EncounterAlert extends BaseOpenmrsMetadata {

	private Integer id;
	private SerializedObject upQuery;
	private SerializedObject downQuery;
	
	public SerializedObject getUpQuery() {
		return upQuery;
	}

	public void setUpQuery(SerializedObject upQuery) {
		this.upQuery = upQuery;
	}

	public SerializedObject getDownQuery() {
		return downQuery;
	}

	public void setDownQuery(SerializedObject downQuery) {
		this.downQuery = downQuery;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
}