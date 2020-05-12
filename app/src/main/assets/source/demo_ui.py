import socket
import os
print("outer text")
def print_ip():
    hostname = socket.gethostname()
    IPAddr = socket.gethostbyname(hostname)
    print("Your Computer Name is:" + hostname)
    return  "Your Computer IP Address is:" + IPAddr + " <> " + hostname


def print_function():
    return  os.popen("cd /etc ; ls").read()

def run_command(text):
    print("TEXT "+text)
    st=os.popen(str(text)).readlines()
    print(st)
    return  st