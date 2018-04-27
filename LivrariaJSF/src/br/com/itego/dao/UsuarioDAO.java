package br.com.itego.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.itego.modelo.Usuario;
import br.com.itego.util.JPAUtil;

public class UsuarioDAO {
	
	public Usuario verificarLogin(Usuario usuario) {
		
		try {
			EntityManager em = new JPAUtil().getEntityManager();
			
			String jpql = "select u from Usuario u "
					+ "where u.email = :pEmail "
					+ "and u.senha = :pSenha";
			TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
			query.setParameter("pEmail", usuario.getEmail());
			query.setParameter("pSenha", usuario.getSenhaCriptografada());
			
			Usuario user = query.getSingleResult();
			em.close();
			return user;
		} catch (NoResultException e) {
			return null;
		}
		
	}

}
