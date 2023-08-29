package vst.webservice;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Webservice {
	
	public static void main(String[] args) {
		webservice();
	}
	
	private static void webservice() {
		List<Turma> turmas = new ArrayList<>();
		popularTurmas(turmas);
		
		StringBuilder turmasSB = new StringBuilder();
		
		for (Turma turma : turmas) {
			turmasSB.append(turma).append("\n\n");
		}
		
		try {
			ServerSocket server = abrirServidorSocket();
			
			enviarTurmasViaSocket(server, turmasSB.toString().trim());
			
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void enviarTurmasViaSocket(ServerSocket server, String turmas) throws IOException {
		while(true) {
			Socket client = server.accept();
			System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());
			PrintWriter saida = new PrintWriter(client.getOutputStream(), true);
	        saida.println(turmas);
	        saida.close();
	        client.close();
		}
	}

	private static ServerSocket abrirServidorSocket() throws IOException {
		final int PORT = 5000;
		
		ServerSocket server = new ServerSocket(PORT);
		System.out.println("Servidor ouvindo a porta " + PORT);
		
		return server;
	}

	private static void popularTurmas(List<Turma> turmas) {
		Aluno[] alunosArray1 = {
			new Aluno("1000", "Vítor", 21),
			new Aluno("1001", "Aluno 2", 21),
			new Aluno("1002", "Aluno 3", 20),
			new Aluno("1003", "Aluno 4", 20),
			new Aluno("1004", "Aluno 5", 27),
		};
		
		Aluno[] alunosArray2 = {
				new Aluno("2000", "Aluno 1", 20),
				new Aluno("2001", "Aluno 2", 30),
				new Aluno("2002", "Aluno 3", 20),
				new Aluno("2003", "Aluno 4", 22),
				new Aluno("2004", "Aluno 5", 20),
		};
		
		turmas.add(new Turma("1", "WebServices", 2023, alunosArray1));
		turmas.add(new Turma("2", "TOO", 2023, alunosArray2));
	}
}
