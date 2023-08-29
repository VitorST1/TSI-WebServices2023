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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return String.format("%s, %d anos, matrícula %s", nome, idade, matricula);
	}
}
