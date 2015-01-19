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

package org.openmrs.module.encounteralerts.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Role;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.db.SerializedObject;
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.api.db.EncounterAlertsDAO;
import org.openmrs.module.reporting.query.encounter.definition.EncounterQuery;

/**
 * It is a default implementation of  {@link EncounterAlertsDAO}.
 */
public class HibernateEncounterAlertsDAO implements EncounterAlertsDAO {
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) throws DAOException {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() throws DAOException {
	    return sessionFactory;
    }

	@Override
	public void createEncounterAlert(EncounterAlert encounterAlert) throws DAOException {
		getSessionFactory().getCurrentSession().save(encounterAlert);
		
	}

	@Override
	public void updateEncounterAlert(EncounterAlert encounterAlert) throws DAOException {
		if (encounterAlert.isRetired()){
			encounterAlert.setRetired(false);
			encounterAlert.setRetiredBy(null);
			encounterAlert.setRetireReason(null);
		}
		
		getSessionFactory().getCurrentSession().update(encounterAlert);
		
		/** If this encounterAlert is assigned, unretired the assignments */		
		List<EncounterAlertToRole> encounterAlertsToRole = this.getEncounterAlertsToRoleByAlert(encounterAlert);
		
		for (EncounterAlertToRole ear : encounterAlertsToRole){
			ear.setRetired(false);
			ear.setRetiredBy(null);
			ear.setRetireReason(null);
		}
		
	}

	@Override
	public void deleteEncounterAlert(EncounterAlert encounterAlert) throws DAOException {
		List<EncounterAlertToRole> ear = this.getEncounterAlertsToRoleByAlert(encounterAlert);

		for (EncounterAlertToRole e : ear){
			e.setRetired(true);
		}
		
		getSessionFactory().getCurrentSession().delete(encounterAlert);		
	}

	@Override
	public EncounterAlert getEncounterAlert(Integer id) throws DAOException {
		return (EncounterAlert) getSessionFactory().getCurrentSession()
				.get(EncounterAlert.class, id);
	}

	@Override
	public List<EncounterAlert> getAllEncounterAlerts() throws DAOException {
		return getSessionFactory().getCurrentSession().createQuery("from EncounterAlert").list();
	}

	@Override
	public List<EncounterAlert> getEncounterAlertsByRole(Role role, Boolean includeRetired) throws DAOException {
		Query q = getSessionFactory().getCurrentSession()
				.createQuery("select encounterAlert from EncounterAlertToRole EAR where EAR.role = :role AND EAR.retired=:retired");
		q.setEntity("role", role);
		q.setParameter("retired", includeRetired);
		List<EncounterAlert> alerts = q.list();

		return alerts;
	}

	@Override
	public void createEncounterAlertToRole(EncounterAlertToRole alert) throws DAOException {
		getSessionFactory().getCurrentSession().save(alert);
		
	}

	@Override
	public void updateEncounterAlertToRole(EncounterAlertToRole alert) throws DAOException {
		// Check if encounterAlertToRole is retired
		if (alert.isRetired()){
			alert.setRetired(false);
			alert.setRetiredBy(null);
			alert.setRetireReason(null);
		}
		getSessionFactory().getCurrentSession().update(alert);
		
	}

	@Override
	public void deleteEncounterAlertToRole(EncounterAlertToRole alert) throws DAOException {
		getSessionFactory().getCurrentSession().delete(alert);
		
	}

	@Override
	public List<EncounterAlertToRole> getAllEncounterAlertsToRole() throws DAOException {
		return getSessionFactory().getCurrentSession().createQuery("from EncounterAlertToRole").list();
	}
	
	@Override
	public EncounterAlertToRole getEncounterAlertToRole(Integer id) throws DAOException {
		return (EncounterAlertToRole) getSessionFactory().getCurrentSession()
				.get(EncounterAlertToRole.class, id);
	}

	@SuppressWarnings("unchecked")
	private List<EncounterAlertToRole> getEncounterAlertsToRoleByAlert(EncounterAlert encounterAlert){
		return getSessionFactory().getCurrentSession()
		.createQuery("FROM EncounterAlertToRole WHERE encounterAlert=:encounterAlert")
		.setParameter("encounterAlert", encounterAlert)
		.list();
	}

	@Override
	public EncounterAlert getEncounterAlertByUuid(String uuid) throws DAOException {
		EncounterAlert ret = null;
		if (uuid != null) {
			Criteria c = sessionFactory.getCurrentSession().createCriteria(EncounterAlert.class);
			c.add(Restrictions.eq("uuid", uuid));
			ret = (EncounterAlert) c.uniqueResult();
		}
		return ret;
	}

	@Override
	public void retireAlertsWithQuery(EncounterQuery eq) {
				
		List<EncounterAlert> encountersAlerts = this.getAlertsWithQuery(eq);
		
		if (encountersAlerts == null || encountersAlerts.size() == 0){
			return;
		}
		
		for (EncounterAlert ea : encountersAlerts){
			ea.setRetired(true);
		}
		
		/** Retire encounterAlertsToRole associated to encounterAlerts */
		Query selectQuery2 = getSessionFactory().getCurrentSession()
				.createQuery("FROM EncounterAlertToRole WHERE encounterAlert IN :encounterAlert");
		selectQuery2.setParameterList("encounterAlert", encountersAlerts);
		
		List<EncounterAlertToRole> encounterAlertsToRole = selectQuery2.list();
		
		for (EncounterAlertToRole ear : encounterAlertsToRole){
			ear.setRetired(true);
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<EncounterAlert> getAlertsWithQuery (EncounterQuery eq){
		
		/** Create a SerializedObject with the same uuid*/
		SerializedObject so = new SerializedObject();
		so.setUuid(eq.getUuid());
		Query selectQuery = getSessionFactory().getCurrentSession()
				.createQuery("FROM EncounterAlert EA WHERE (EA.upQuery=:query OR EA.downQuery=:query)");
		selectQuery.setParameter("query", so);
		
		return selectQuery.list();
	}

}