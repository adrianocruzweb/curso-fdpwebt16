package br.com.fabricadeprogramador.fabricaweb.model;

public class Usuario {

	private Integer id;
	private String nome;
	private String email;
	
	//Construtores
	
	public Usuario() {}
	
	public Usuario(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	//Getters e Setters
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	//toString
	
	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
