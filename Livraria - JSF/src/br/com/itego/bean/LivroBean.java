package br.com.itego.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.itego.dao.AutorDAO;
import br.com.itego.dao.DAO;
import br.com.itego.dao.LivroDAO;
import br.com.itego.modelo.Autor;
import br.com.itego.modelo.Livro;

@ManagedBean
@ViewScoped
public class LivroBean {
	
	private Livro livro = new Livro();
	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void gravar() {
		if(livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("É Necessário que o Livro tenha um Autor!"));
			return;
		}
		if (this.getLivro().getId() == null) {
			new LivroDAO().gravar(this.getLivro());
		} else {
			new LivroDAO().atualizar(this.getLivro());
		}
		
		throw new ValidatorException(new FacesMessage("Livro Alterado com sucesso!7"));
	}

	public Livro getLivro() {
		return livro;
	}
	public void addAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscarPorId(this.autorId);
		this.livro.adicionarAutor(autor);
	}
	
	public List<Autor> autoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public List<Livro> listar() {
		return new DAO<Livro>(Livro.class).listar();
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listar();
	}
	
	public void formatarISBN(FacesContext fc, UIComponent component, Object value) {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBN Inválido! Deve-se começar com o Número 1!"));
		}
	}
	
	public List<Livro> getLivroJPQL() {
		return new LivroDAO().getLivrosJPQL();
	} 
	
	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public void excluir(Livro livro) {
		new LivroDAO().excluir(livro);
	}
	
	public void removerAutor(Autor autor) {
		this.getLivro().removerAutor(autor);
	}
	
	public void prepararAlteracao(Livro livro) {
		this.livro = livro;
	}
	
	
}
