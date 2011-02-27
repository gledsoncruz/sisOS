package br.com.sisos.action;

import javax.faces.application.FacesMessage;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import br.com.sisos.entity.IdentityCustomized;
import br.com.sisos.entity.Perfil;
import br.com.sisos.entity.Usuario;
import br.com.sisos.services.LoginService;
import br.com.sisos.util.Criptografia;


@Name("authenticator")
public class Authenticator extends BaseAction
{
    
	private static final long serialVersionUID = 1L;

	@Logger Log log;
    
    @In IdentityCustomized identity;
    
    @In (create = true)
	private LoginService loginService;
   
    public boolean authenticate()
    {
        log.info("authenticating #0", identity.getUsername());
        Usuario user = loginService.login(identity.getUsername(), Criptografia.criptografar(identity.getPassword()));
        if (user != null) {
			if (user.isAtivo()) {
				if (user.getPerfil().equals(Perfil.ADMIN)) {
					identity.setUsuario(user);
					identity.addRole("admin");
					log.info("USUARIO ADMINISTRADOR AUTENTICADO!: #0", identity.getUsuario().getNome());
					return true;
				} else if (user.getPerfil().equals(Perfil.TECNICO)){
					identity.setUsuario(user);
					identity.addRole("tecnico");
					log.info("USUARIO TECNICO AUTENTICADO!: #0", identity.getUsuario().getNome());					
					return true;
				} else {
					identity.setUsuario(user);
					identity.addRole("demo");
					log.info("USUARIO DEMO AUTENTICADO!: #0", identity.getUsuario().getNome());	
					return true;
				}
			} else {
				
				this.addMsgBundle(FacesMessage.SEVERITY_ERROR, "Usuário Bloqueado, entre em contato com o administrador do sistema");
				return false;
			}
		}
        
		log.info("FALHA NA AUTENTICACAO! #0", identity.getUsername());
		return false;
        
               
    }
    
    
    
    
}
