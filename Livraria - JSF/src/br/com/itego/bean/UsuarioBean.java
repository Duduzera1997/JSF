package br.com.itego.bean;

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
		if(new UsuarioDAO().verificarLogin(this.usuario)) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			return "livro";
		}
		
		return "login";
	}
}
