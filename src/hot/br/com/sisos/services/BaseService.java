package br.com.sisos.services;
import java.io.Serializable;

import org.hibernate.Session;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

public abstract class BaseService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Logger
	protected Log log;

	@In
	protected Session hibernateSession;
	
	@In(create=true)
	protected Renderer renderer;
	
	
	
	

}
