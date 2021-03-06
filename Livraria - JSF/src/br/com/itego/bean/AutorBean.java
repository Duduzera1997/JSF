package br.com.itego.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.itego.dao.AutorDAO;
import br.com.itego.dao.DAO;
import br.com.itego.modelo.Autor;
import br.com.itego.util.JPAUtil;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public void gravar() {
		if (this.getAutor().getId() == null) {
			new AutorDAO().gravar(autor);
			this.autor = new Autor();
		} else {
			new AutorDAO().atualizar(autor);
			//throw new ValidatorException(new FacesMessage("Autor Alterado com Sucesso!"));
		}
		
		
	}
	
	public Autor getAutor() {
		return autor;
	}
	/*public void gravar(Autor autor) {
		if (this.getAutor().getId() == null) {
			new AutorDAO().gravar(autor);
			this.autor = new Autor();
		} else {
			new AutorDAO().atualizar(autor);
		}
	}*/
	
	public List<Autor> getAutoresJPQL() {
		return new AutorDAO().getAutoresJPQL();
	}
	public void excluir(Autor autor) {
		new AutorDAO().excluir(autor);
	}
	
	public void prepararAlteracao(Autor autor) {
		this.autor = autor;
	}
	
}
