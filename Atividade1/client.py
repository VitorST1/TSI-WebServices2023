import socket

HOST = '127.0.0.1'
PORT = 5000

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.connect((HOST, PORT))
    data = bytearray()
    while True:
        aux = s.recv(100)

        if not aux:
            break

        data += aux

    print(data.decode())