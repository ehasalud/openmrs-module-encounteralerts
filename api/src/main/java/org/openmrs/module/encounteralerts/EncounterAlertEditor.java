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

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.SerializedObject;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.springframework.util.StringUtils;

public class EncounterAlertEditor extends PropertyEditorSupport {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public EncounterAlertEditor(){	
	}
	
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text)) {
			EncounterAlertsService service = Context.getService(EncounterAlertsService.class);
			try {
				EncounterAlert object = service.getEncounterAlert(Integer.valueOf(text)); 
				setValue(object);
			}
			catch (Exception ex) {
				EncounterAlert object = service.getEncounterAlertByUuid(text);
				setValue(object);
				if (object == null) {
					log.error("Error setting text: " + text, ex);
					throw new IllegalArgumentException("Encounter alert not found: " + ex.getMessage());
				}
			}
		} else {
			setValue(null);
		}
	}
	
	@Override
	public String getAsText() {
		EncounterAlert so = (EncounterAlert) getValue();
		if (so == null) {
			return "";
		} else {
			return so.getUuid();
		}
	}

}
