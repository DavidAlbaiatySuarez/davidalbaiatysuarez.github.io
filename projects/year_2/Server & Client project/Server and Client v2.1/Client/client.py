import sys
import socket
from helperFunctions import send_file, recv_file, data_receiving

########################################
try:
    host = sys.argv[1]
    if host != "localhost":
        print("Host must be localhost")
        exit(1) #denotes exit with some errors

    port_num = int(sys.argv[2])
except IndexError:
    print("No argument provided.")
    exit(1)

try:
    action_required = sys.argv[3]
except IndexError:
    print("No type request given")
    exit(1) 

try:
    filename = str(sys.argv[4])
    if len(filename) > 259:
        raise Exception("Filename too long, max 259 characters")
except IndexError:
    #assume type requested is list since no filename given (we then check if this was actually the case)
    filename = ""
############################################

cli_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
#Connection to server i.e.: localhost and 2000 (as port number)
cli_sock.connect((host, int(sys.argv[2])))

'''
Client sends appropiate-checked request to server,
receives the specified content from server
'''
try:
    if action_required == "put":

        send_file(filename.encode("utf-8"), cli_sock, action_required.encode("utf-8"))
        print("SUCCESS: data send to server...")
        
    elif action_required == "get":

        cli_sock.send(b"CONTINUE") #informs server of no major issues found
        cli_sock.sendall("{}$|${}$~$".format(action_required, filename).encode("utf-8"))
        print("SUCCESS: get request for {} send to server...".format(filename))

        connection_status = cli_sock.recv(8)
        if connection_status == b"CONTINUE":
            data_stream = data_receiving(cli_sock)
            recv_file(filename, data_stream)
            print("SUCCESS: data written to {}...".format(filename))
        else:
            raise Exception("FAILURE: error occured in server side...")
            
    elif action_required == "list":

        cli_sock.send(b"CONTINUE") #informs server of no major issues found
        cli_sock.sendall("list$~$".encode("utf-8"))

        connection_status = cli_sock.recv(8) #no major issues found from server side
        if connection_status == b"CONTINUE":
            data_stream = data_receiving(cli_sock)
            for element in data_stream.split(b","):
                print(element.decode("utf-8"))
        else:
            
            raise Exception("FAILURE: error occured in server side...")

    else:
        
        raise Exception("The type request is invalid. Only valid types: put,get,list")
    
except FileNotFoundError:

    print("FAILURE: filename {} not found /filename not given".format(filename))
 
finally:
    
    cli_sock.close()







