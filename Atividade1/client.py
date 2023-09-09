import socket
import json
import random

HOST = '127.0.0.1'
PORT = 5000
BUFFER = 1024

# Printa os dados da turma e seu lider
def printTurmas(turmas):
    for turma in turmas:
        alunosString = ''
        for aluno in turma['alunos']:
            alunosString += f"\n{aluno['nome']}, {aluno['idade']} anos, matrícula {aluno['matricula']}"
        print(f"Turma {turma['nome']} - {turma['id']} {turma['ano']}{alunosString}")

        if 'liderIndex' in turma:
            print(f"Líder: {turma['alunos'][turma['liderIndex']]['nome']}\n")
        
def elegerLider(turmas):
    lideres = []
    for turma in turmas:
        if(len(turma['alunos'])):
            liderIndex = random.randint(0, len(turma['alunos'])- 1)
            turma['liderIndex'] = liderIndex
            lideres.append({'liderIndex': liderIndex})
        else:
            lideres.append(None)
    return lideres


def enviarLideres(lideres, socket):
    socket.send(json.dumps(lideres).encode())

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    data = bytearray()
    while True:
        aux = s.recv(BUFFER)

        if not aux or aux == b'\n' or aux == b'\r\n':
            break

        data += aux

    turmas = json.loads(data.decode())
    lideres = elegerLider(turmas)
    printTurmas(turmas)
    enviarLideres(lideres, s)