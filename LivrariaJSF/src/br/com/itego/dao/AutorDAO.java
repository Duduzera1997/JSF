package br.com.itego.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.itego.modelo.Autor;
import br.com.itego.modelo.Livro;
import br.com.itego.util.JPAUtil;

public class AutorDAO {
	
	
	public void excluir(Autor autor) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Autor a = em.find(Autor.class, autor.getId());
		em.remove(a);
		em.getTransaction().commit();
		em.close();
	}
	
	public void atualizar(Autor autor) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Autor a = em.find(Autor.class, autor.getId());
		a = autor;
		em.merge(a);
		em.getTransaction().commit();
		em.close();
	}
	
	public void gravar(Autor autor) {
		new DAO<Autor>(Autor.class).gravar(autor);
	}
	
	public List<Object[]> getAutoresDoLivroJPQL() {  //Quest�o 2;
		EntityManager em = new JPAUtil().getEntityManager();
		
		String jpql = "select a.nome, count(*) from Livro l left join l.autores a group by a.nome";
		Query query = em.createQuery(jpql);
		//query.setParameter("pLivro", livro.getAutores());
		List<Object[]> rst = query.getResultList();
		
		
		return rst;
	}
	
	public List<Livro> getAutoresTitulosJPQL() { // Quest�o 3;
		EntityManager em = new JPAUtil().getEntityManager();
		
		String jpql = "SELECT l from Livro l inner join l.autores a ";
		Query query = em.createQuery(jpql);
		//query.setParameter("pLivro", livro.getAutores());
		List<Livro> livros = query.getResultList();
		
		
		return livros;
	}
	
	public List<Autor> listarAutoresJPQL() { // Quest�o 4;
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "select a from Autor a order by a.nome";
		Query query = em.createQuery(jpql);
		//em.close();
		return query.getResultList();
	}

	public List<Autor> getAutoresJPQL() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "select a from Autor a order by a.nome";
		Query query = em.createQuery(jpql);
		//em.close();
		return query.getResultList();
	}
	
	public Autor buscarPorId(Integer autorId) {
		return new DAO<Autor>(Autor.class).buscarPorId(autorId);
	}
}
