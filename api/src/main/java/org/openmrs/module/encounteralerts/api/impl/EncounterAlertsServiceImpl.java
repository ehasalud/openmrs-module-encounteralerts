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
package org.openmrs.module.encounteralerts.api.impl;

import java.util.List;

import org.openmrs.Role;
import org.openmrs.api.db.SerializedObject;
import org.openmrs.api.db.SerializedObjectDAO;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.api.EncounterAlertsService;
import org.openmrs.module.encounteralerts.api.db.EncounterAlertsDAO;
import org.openmrs.module.reporting.query.encounter.definition.EncounterQuery;

/**
 * It is a default implementation of {@link EncounterAlertsService}.
 */
public class EncounterAlertsServiceImpl extends BaseOpenmrsService implements EncounterAlertsService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private EncounterAlertsDAO dao;
	private SerializedObjectDAO sodao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(EncounterAlertsDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public EncounterAlertsDAO getDao() {
	    return dao;
    }

	@Override
	public void createEncounterAlert(EncounterAlert encounterAlert) {
		dao.createEncounterAlert(encounterAlert);
		
	}

	@Override
	public void updateEncounterAlert(EncounterAlert encounterAlert) {
		dao.updateEncounterAlert(encounterAlert);
		
	}

	@Override
	public void deleteEncounterAlert(EncounterAlert encounterAlert) {
		dao.deleteEncounterAlert(encounterAlert);
	}

	@Override
	public EncounterAlert getEncounterAlert(Integer id) {
		return dao.getEncounterAlert(id);
	}

	@Override
	public List<EncounterAlert> getAllEncounterAlerts() {
		return dao.getAllEncounterAlerts();
	}

	@Override
	public List<EncounterAlert> getEncounterAlertsByRole(Role role) {
		return dao.getEncounterAlertsByRole(role);
	}

	@Override
	public List<EncounterAlert> getCurrentUserEncounterAlerts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEncounterAlertToRole(EncounterAlertToRole alert) {
		dao.createEncounterAlertToRole(alert);
	}

	@Override
	public void updateEncounterAlertToRole(EncounterAlertToRole alert) {
		dao.updateEncounterAlertToRole(alert);
	}

	@Override
	public void deleteEncounterAlertToRole(EncounterAlertToRole alert) {
		dao.deleteEncounterAlertToRole(alert);
	}

	@Override
	public List<EncounterAlertToRole> getAllEncounterAlertsToRole() {
		return dao.getAllEncounterAlertsToRole();
	}

	@Override
	public List<SerializedObject> getAllEncounterQueries() {
		return sodao.getAllSerializedObjects(EncounterQuery.class, false);
	}

	@Override
	public SerializedObject getEncounterQueryByUuid(String uuid) {
		return sodao.getSerializedObjectByUuid(uuid);
	}
	
	@Override
	public SerializedObject getEncounterQuery(Integer id) {
		return sodao.getSerializedObject(id);
	}

	public SerializedObjectDAO getSodao() {
		return sodao;
	}

	public void setSodao(SerializedObjectDAO sodao) {
		this.sodao = sodao;
	}

	@Override
	public EncounterAlertToRole getEncounterAlertToRole(Integer id) {
		return dao.getEncounterAlertToRole(id);
	}

	@Override
	public EncounterAlert getEncounterAlertByUuid(String uuid) {
		return dao.getEncounterAlertByUuid(uuid);
	}

}