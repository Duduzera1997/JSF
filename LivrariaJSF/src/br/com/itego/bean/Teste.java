package br.com.itego.bean;

import java.util.ArrayList;

import java.util.List;
import br.com.itego.dao.AutorDAO;
import br.com.itego.dao.LivroDAO;
import br.com.itego.modelo.Autor;
import br.com.itego.modelo.Livro;

public class Teste {

	/*
	 * No projeto LivrariaJSF de vocês crie uma classe de teste (Caso ela já exista,
	 * apenas reutilize-a) para rodar (Java Application) as seguintes consultas
	 * (crie um método para cada):
	 * 
	 * 1) Traga a Quantidade de Livros Cadastrados // ~~~~~~~~~~~~~~~~~~~~~~OK! 
	 * 2) Traga Todos os autores e
	 * a quantidade de livros que cada um escreveu (traga também os que não
	 * escreveram) 
	 * 3) Traga todos os autores e o título dos livros que escreveram.
	 * 4) Traga os autores ordenador de forma crescente por nome. ~~~~~~~~~~~~~~~~// OK!
	 * 
	 * Para próxima aula: Envie como resposta a esse e-mail o arquivo de teste afim
	 * de compararmos as soluções com as dos colegas. No corpo do e-mail, comente
	 * sobre quais foram as dificuldades, se passou por alguma situação de exceção
	 * de Lazy ou outras exceções.
	 */

	public static void main(String[] args) {
		
		// Para executar, basta chamar os métodos dentro deste main, Ex: getQuantidadeLivrosJPQL();
		// getQuantidadeLivrosJPQL(); // Questão 1;
		 getAutoresDoLivroJPQL(); // Questão 2;
 		// getAutoresTitulosJPQL(); // Questão 3;
		//listarAutoresJPQL(); // Questão 4;

	}

	public static void getQuantidadeLivrosJPQL() { // Questão 1;

		Long qtde = new LivroDAO().quantidadeLivrosJPQL();

		System.out.println("Quantidade de Livros Cadastrados: " + qtde);
	}

	public static void getAutoresDoLivroJPQL() { // Questão 2;

		AutorDAO dao = new AutorDAO();
		List<Object[]> rst = dao.getAutoresDoLivroJPQL();
		for (Object[] autoresdolivro  : rst) {
				System.out.println("Nome do Autor: " + autoresdolivro[0] + " \nQuantidade de Livros: " + autoresdolivro[1]);

		}
	}
	
	public static void getAutoresTitulosJPQL() { // Questão 3;
		AutorDAO dao = new AutorDAO();
		List<Livro> livros =  dao.getAutoresTitulosJPQL();
		
		for (Livro lvr : livros) {
			for (Autor atr : lvr.getAutores()) {
				System.out.println("Nome do Autor: " + atr.getNome() + "\nTítulo do Livro: " + lvr.getTitulo());
			}
			
		}
		
	}

	public static void listarAutoresJPQL() { // Questão 4;
		List<Autor> autores = new AutorDAO().listarAutoresJPQL();
		for (Autor a : autores) {
			System.out.println("Nome do Autor: " + a.getNome());
		}
	}
	
	

}
