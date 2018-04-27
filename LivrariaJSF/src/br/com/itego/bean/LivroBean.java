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
	private List<Livro> livros;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Livro getLivro() {
		return livro;
	}
	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public void gravar() {
		if(livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("� Necess�rio que o Livro tenha um Autor!"));
			return;
		}
		LivroDAO dao = new LivroDAO();
		if (this.getLivro().getId() == null) {
			new LivroDAO().gravar(this.getLivro());
		} else {
			new LivroDAO().atualizar(this.getLivro());
		}
		this.livros = dao.getLivrosJPQL();
		this.livro = new Livro();
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
	
	public List<Livro> getLivrosJPQL() {
		if (this.livros == null) {
			this.livros = new LivroDAO().getLivrosJPQL();
		}
		return livros;
	} 
	
	public String formAutor() {
		return "autor?faces-redirect=true";
	}
	
	public void excluir(Livro livro) {
		LivroDAO dao = new LivroDAO();
		dao.excluir(livro);
		this.livros = dao.getLivrosJPQL();
	}
	
	public void removerAutor(Autor autor) {
		this.getLivro().removerAutor(autor);
	}
	
	public void prepararAlteracao(Livro livro) {
		this.livro = livro;
	}
	
	
}
