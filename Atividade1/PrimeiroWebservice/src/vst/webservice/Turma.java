package vst.webservice;

public class Turma {
	private String id, nome; 
	private int ano;
	private Aluno[] alunos;
	private int liderIndex;

	public Turma(String id, String nome, int ano, Aluno[] alunos) {
		super();
		this.id = id;
		this.nome = nome;
		this.ano = ano;
		this.alunos = alunos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Aluno[] getAlunos() {
		return alunos;
	}

	public void setAlunos(Aluno[] alunos) {
		this.alunos = alunos;
	}
	
	public int getLiderIndex() {
		return liderIndex;
	}

	public void setLiderIndex(int liderIndex) {
		this.liderIndex = liderIndex;
	}
	
	public Aluno getLider() {
		if (alunos.length > 0)
			return alunos[getLiderIndex()];
		return null;
	}

	@Override
	public String toString() {
		StringBuilder alunosSB = new StringBuilder();
		for (Aluno aluno : alunos) {
			alunosSB.append("\n- ").append(aluno);
		}
		return String.format("Turma %s - %s (%d)%s\n", id, nome, ano, alunosSB);
	}
}
