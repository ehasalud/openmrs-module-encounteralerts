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
import org.openmrs.module.encounteralerts.EncounterAlert;
import org.openmrs.module.encounteralerts.EncounterAlertToRole;
import org.openmrs.module.encounteralerts.api.db.EncounterAlertsDAO;

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
		getSessionFactory().getCurrentSession().update(encounterAlert);
		
	}

	@Override
	public void deleteEncounterAlert(EncounterAlert encounterAlert) throws DAOException {
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
	public List<EncounterAlert> getEncounterAlertsByRole(Role role) throws DAOException {
		Query q = getSessionFactory().getCurrentSession()
				.createQuery("select encounterAlert from EncounterAlertToRole where role = :role");
		q.setEntity("role", role);
		List<EncounterAlert> alerts = q.list();

		return alerts;
	}

	@Override
	public void createEncounterAlertToRole(EncounterAlertToRole alert) throws DAOException {
		getSessionFactory().getCurrentSession().save(alert);
		
	}

	@Override
	public void updateEncounterAlertToRole(EncounterAlertToRole alert) throws DAOException {
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
}