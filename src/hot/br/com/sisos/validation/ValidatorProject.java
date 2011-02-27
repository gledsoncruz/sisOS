package br.com.sisos.validation;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.security.Identity;

import br.com.sisos.action.TecnicoAction;
import br.com.sisos.entity.Tecnico;

@Name(value = "validatorProject")
@org.jboss.seam.annotations.faces.Validator
@Scope(ScopeType.PAGE)
public class ValidatorProject implements Validator, Serializable{

	private static final long serialVersionUID = 1L;
	
	@In(create=true)
	private TecnicoAction tecnicoAction;

	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		// TODO Auto-generated method stub

	}

	public void validateEmail(FacesContext context, UIComponent toValidate,
			Object valorTela) throws ValidatorException {
		// TODO Auto-generated method stub

		String emailTela = (String) valorTela;

		if (!tecnicoAction.verificarEmailExistente(emailTela)) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Email já existe");
			throw new ValidatorException(message);
		}

	}

	public void validateLogin(FacesContext context, UIComponent toValidate,
			Object valorTela) throws ValidatorException {
		// TODO Auto-generated method stub

		String loginTela = (String) valorTela;

		if (!tecnicoAction.verificarLoginExistente(loginTela)) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Login já existe");
			throw new ValidatorException(message);
		}

	}

	public void validateCPF(FacesContext context, UIComponent toValidate,
			Object valorTela) throws ValidatorException {
		if (!Identity.instance().hasRole("admin")){

			String cpfTela = (String) valorTela;
			String cpfOriginal = cpfTela;
			cpfTela = cpfTela.replaceAll("\\.", "").replaceAll("\\-", "");

			if (!validaCPF(String.valueOf(cpfTela))) {
				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("CPF Invalido");
				throw new ValidatorException(message);
				
			} else if (!tecnicoAction.verificarCPFExistente(cpfOriginal)) {

				FacesMessage message = new FacesMessage();
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				message.setSummary("CPF já existe");
				throw new ValidatorException(message);

			}
		}
	}
	

	/**
	 * Valida CPF do usuário. Não aceita CPF's padrões como 11111111111 ou
	 * 22222222222
	 * 
	 * @param cpf
	 *            String valor com 11 dígitos
	 */
	private static boolean validaCPF(String cpf) {
		if (cpf == null || cpf.length() != 11 || isCPFPadrao(cpf))
			return false;

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) { // CPF não possui somente números
			return false;
		}

		if (!calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11)))
			return false;

		return true;
	}

	/**
	 * 
	 * @param cpf
	 *            String valor a ser testado
	 * @return boolean indicando se o usuário entrou com um CPF padrão
	 */
	private static boolean isCPFPadrao(String cpf) {
		if (cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444")
				|| cpf.equals("55555555555") || cpf.equals("66666666666")
				|| cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || cpf.equals("00000000000")) {

			return true;
		}

		return false;
	}

	private static String calcDigVerif(String num) {
		Integer primDig, segDig;
		int soma = 0, peso = 10;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		if (soma % 11 == 0 | soma % 11 == 1)
			primDig = new Integer(0);
		else
			primDig = new Integer(11 - (soma % 11));

		soma = 0;
		peso = 11;
		for (int i = 0; i < num.length(); i++)
			soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

		soma += primDig.intValue() * 2;
		if (soma % 11 == 0 | soma % 11 == 1)
			segDig = new Integer(0);
		else
			segDig = new Integer(11 - (soma % 11));

		return primDig.toString() + segDig.toString();
	}

	public void validateConfimSenha(FacesContext context,
			UIComponent toValidate, Object value) throws ValidatorException {

		// Pego o ID do campo password no f:attribute.
		String passwordId = (String) toValidate.getAttributes().get(
				"passwordId");

		// Find the actual JSF component for the client ID.
		UIInput passwordInput = (UIInput) context.getViewRoot().findComponent(
				passwordId);

		// Get its value, the entered password of the first field.
		String password = (String) passwordInput.getValue();

		String confirm = (String) value;

		if (!(confirm.equals(password))) {

			((UIInput) toValidate).setValid(false);
			SeamResourceBundle srb = new SeamResourceBundle();
			String passwordError = srb.getString("confirmPassword");
			FacesMessage message = new FacesMessage(passwordError);
			message.setSeverity(javax.faces.application.FacesMessage.SEVERITY_ERROR);
			context.addMessage(toValidate.getClientId(context), message);
		}

	}
	
	public void validateSenhaAtual(FacesContext context,
			UIComponent toValidate, Object valorTela) throws ValidatorException{
		
		// Pego o ID do campo password no f:attribute.
		String idUser = (String) toValidate.getAttributes().get(
				"passwordId");

		// Find the actual JSF component for the client ID.
		UIInput idUserInput = (UIInput) context.getViewRoot().findComponent(
				idUser);

		// Get its value, the entered password of the first field.
		Integer id = (Integer) idUserInput.getValue();
		Tecnico tecnico = tecnicoAction.carregarPorId(id);
		
		String senha = (String) valorTela;

		if (!tecnicoAction.verificarSenhaAtual(tecnico.getLogin(), senha)) {
			FacesMessage message = new FacesMessage();
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Senha não confere");
			throw new ValidatorException(message);
		}
		
	}

}
