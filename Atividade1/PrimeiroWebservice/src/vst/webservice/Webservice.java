package vst.webservice;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.io.BufferedReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Webservice {
	
	public static void main(String[] args) {
		webservice();
	}
	
	private static void webservice() {
		List<Turma> turmas = new ArrayList<>();
		popularTurmas(turmas);
		
		try {
			ServerSocket server = abrirServidorSocket();
			
			enviarTurmasViaSocket(server, turmas);
			
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void enviarTurmasViaSocket(ServerSocket server, List<Turma> turmas) throws IOException {
		while(true) {
			String turmasString = obterTurmasString(turmas);
			
			Socket client = server.accept();
			System.out.println("Cliente conectado: " + client.getInetAddress().getHostAddress());
			PrintWriter saida = new PrintWriter(client.getOutputStream(), true);
	        saida.println(turmasString);
	        saida.println(); // Indica para o cliente o fim do envio do servidor
	        
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String lideres_JSON = in.readLine();
            	
            setarLideres(lideres_JSON, turmas);
           
            saida.close();
            in.close();
	        client.close();
		}
	}

	@SuppressWarnings("unchecked")
	private static String obterTurmasString(List<Turma> turmas) {
		JSONArray turmas_JSON = new JSONArray();
		
		for (Turma turma : turmas) {
			LinkedHashMap<String, Object> turma_JSON = new LinkedHashMap<>();
			turma_JSON.put("id", turma.getId());
			turma_JSON.put("nome", turma.getNome());
			turma_JSON.put("ano", String.valueOf(turma.getAno()));
			
			JSONArray alunos_JSON = new JSONArray();
			
			for (Aluno aluno : turma.getAlunos()) {
				LinkedHashMap<String, Object> aluno_JSON = new LinkedHashMap<>();
				aluno_JSON.put("matricula", aluno.getMatricula());
				aluno_JSON.put("nome", aluno.getNome());
				aluno_JSON.put("idade", aluno.getIdade());
				alunos_JSON.add(aluno_JSON);
			}
			
			turma_JSON.put("alunos", alunos_JSON);
			turmas_JSON.add(turma_JSON);
		}
		
		return turmas_JSON.toString().trim();
	}

	private static void setarLideres(String JSON_lideres, List<Turma> turmas) {
        JSONParser parser = new JSONParser();
        
		try {
			Object obj = parser.parse(JSON_lideres);
			
			JSONArray array_JSON = (JSONArray) obj;
		    
			for(var index = 0; index < turmas.size(); index++) {
				JSONObject obj_JSON = (JSONObject) array_JSON.get(index);
				int liderIndex = Integer.parseInt(obj_JSON.get("liderIndex").toString());
				Turma turma = turmas.get(index);
				turma.setLiderIndex(liderIndex);
		        
		        Aluno lider = turma.getLider();
		        if(lider != null)
		        	System.out.println(String.format("Turma: %s, lider: %s", turma.getNome(), lider.getNome()));
		    }
		} catch (ParseException e) {
			e.printStackTrace();
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
