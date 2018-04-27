package br.com.itego.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idProduto;
	private String nome;
	private int estoque;
	private String codigo;
	private int minimo;
	private double valorVenda;
	private String descricao;
	
}
