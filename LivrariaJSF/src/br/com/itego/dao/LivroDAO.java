package br.com.itego.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.itego.modelo.Livro;
import br.com.itego.util.JPAUtil;

public class LivroDAO {
	
	
	
	public void gravar(Livro livro) {
		new DAO<Livro>(Livro.class).gravar(livro);
	}
	
	public void atualizar(Livro livro) {
		new DAO<Livro>(Livro.class).atualiza(livro);
	}
	
	public void excluir(Livro livro) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		Livro l = em.find(Livro.class, livro.getId());
		em.remove(l);
		em.getTransaction().commit();
		em.close();
		//new DAO<Livro>(Livro.class).remove(livro);
	}
	
	
	public Long quantidadeLivrosJPQL() { // Questão 1;
		
		EntityManager em = new JPAUtil().getEntityManager();
		String jqpl = "select count(l) from Livro l";
		Query query = em.createQuery(jqpl);
		Long quantidade = (Long) query.getSingleResult();
		em.close();
		return quantidade;
	}
	
	public List<Livro> getLivrosJPQL() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "select l from Livro l order by l.titulo";
		Query query = em.createQuery(jpql);
		//em.close();
		return query.getResultList();
	}

}
