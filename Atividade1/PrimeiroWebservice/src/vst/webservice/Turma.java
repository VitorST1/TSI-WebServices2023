package vst.webservice;

public class Turma {
	private String id, nome; 
	private int ano;
	private Aluno[] alunos;
	
	public Turma(String id, String nome, int ano, Aluno[] alunos) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.alunos = alunos;
	}

	@Override
	public String toString() {
		StringBuilder alunosSB = new StringBuilder();
		for (Aluno aluno : alunos) {
			alunosSB.append("\n- ").append(aluno);
		}
		return String.format("Turma %s - %s (%d)%s", id, nome, ano, alunosSB);
	}
}
