import os

def send_file(filename, cli_sock, action_required=0):
    with open(filename, "rb") as f:
        cli_sock.send(b"CONTINUE") #informs server of no major issues found
        data = f.read()
        if action_required == 0:
            cli_sock.sendall(data + "$~$".encode("utf-8"))
        else:
            cli_sock.sendall(action_required + "$|$".encode("utf-8") + filename + "$|$".encode("utf-8") + data + "$~$".encode("utf-8"))

def recv_file(filename, data_stream):
    with open(filename, "xb") as f:
        f.write(data_stream)

def data_receiving(cli_sock):
    data, data_stream = bytearray(1), bytearray()
    
    while "$~$".encode("utf-8") not in data:
        data = cli_sock.recv(4096)
        data_stream += data

    return data_stream.replace(b"$~$", b"")

def send_listing(cli_sock, directories):
    list_of_directories, list_to_client = os.listdir(), ""
    for element in list_of_directories:
        list_to_client += element + ","
    cli_sock.send(b"CONTINUE")
    cli_sock.sendall(list_to_client.encode("utf-8") + "$~$".encode("utf-8")) 

