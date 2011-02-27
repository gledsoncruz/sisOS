package br.com.sisos.action;

import java.io.Serializable;

import javax.faces.application.FacesMessage.Severity;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;


public abstract class BaseAction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Logger
	public Log log;
	
	@In
	public FacesMessages facesMessages;
	
	public void addMsgBundle(Severity severity, String key) {
		facesMessages.addFromResourceBundle(severity, key);
	}
	

	

}
