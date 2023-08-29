package vst.webservice;

public class Aluno {
	private String matricula, nome;
	private int idade;
	
	public Aluno(String matricula, String nome, int idade) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.idade = idade;
	}

	@Override
	public String toString() {
		return String.format("%s, %d anos, matrícula %s", nome, idade, matricula);
	}
}
