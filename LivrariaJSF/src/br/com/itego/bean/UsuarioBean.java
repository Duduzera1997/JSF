package br.com.itego.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.itego.dao.UsuarioDAO;
import br.com.itego.modelo.Usuario;


@ManagedBean
@ViewScoped
public class UsuarioBean {
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	public String logar() {
		Usuario user = new UsuarioDAO().verificarLogin(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		
		if(user != null) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", user);
			return "livro?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário ou senha Incorretos!"));
		return "login?faces-redirect=true";
	}
	
	public String efetuarLogout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
