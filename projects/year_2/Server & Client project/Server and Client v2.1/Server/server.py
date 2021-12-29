import sys
import os
import socket
from helperFunctions import send_file, recv_file, data_receiving, send_listing

##########################################
srv_sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    srv_sock.bind(("0.0.0.0", int(sys.argv[1])))
    print("Server with IP address: {} and port number: {} up and running.".format("0.0.0.0", int(sys.argv[1])))
    srv_sock.listen(5) #Can listen up to 5 clients
except Exception as e:
    print(e)
    exit(1) #denotes exit with some errors

'''
Server receives request from client,
depending on request a certain action is taken,
closes connection with client if error occurs,
'''
while True:
    try:
        cli_sock, cli_addr = srv_sock.accept()
        print("REQUEST FROM CLIENT WITH IP: {} AND PORT NUMBER: {} ACCEPTED".format(cli_addr[0], cli_addr[1]))

        #Handling connection lost
        connection_status = cli_sock.recv(8) #where 8 is the buffer size of the text CONTINUE 
        if connection_status == b"CONTINUE":
            
            data_stream = data_receiving(cli_sock)

            try:
                if data_stream.startswith(b"put"):

                    action_required, filename, data_to_copy = data_stream.split(b"$|$")
                    print("SUCCESS: request from {} to put data to {} accepted...".format(cli_addr, filename.decode()))
                    recv_file(filename.decode("utf-8"), data_to_copy)
                    print("SUCCESS: data send from {} added to  filename: {} ...".format(str(cli_addr),filename.decode()))
                    
                elif data_stream.startswith(b"get"):

                    action_required, filename = data_stream.split(b"$|$")
                    print("SUCCESS: request from {} to get data from {} accepted...".format(cli_addr, filename.decode()))
                    send_file(filename.decode("utf-8"), cli_sock)
                    print("SUCCESS: data from {} send to {}...".format(filename.decode(), cli_addr))
                    
                elif data_stream.startswith(b"list"):

                    send_listing(cli_sock, os.listdir())
                    print("SUCCESS: request from {} to send listing of directories fulfilled...".format(cli_addr))
                    
            except FileNotFoundError:
                
                print("FAILURE: filename not found...")
                cli_sock.send(b"ERROR")

            except Exception as e:
                
                print(e)
                cli_sock.send(b"ERROR")
                
            finally:
                
                cli_sock.close()
        else:
            
            print("FAILURE: error occured in client side...")
            cli_sock.close()
            
    finally:
        
        cli_sock.close()
       
exit(0) #denotes clean exit with no errors
