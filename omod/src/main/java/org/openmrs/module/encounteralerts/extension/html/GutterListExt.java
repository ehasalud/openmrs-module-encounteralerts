package org.openmrs.module.encounteralerts.extension.html;

import org.openmrs.module.web.extension.LinkExt;

public class GutterListExt extends LinkExt {

	@Override
	public String getLabel() {
		return "encounteralerts.panel.title";
	}

	@Override
	public String getRequiredPrivilege() {
		// TODO 
		return "";
	}

	@Override
	public String getUrl() {
		return "module/encounteralerts/encounterAlertViewer.form";
	}

}
