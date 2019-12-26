package ru.potapov.Sockets.clientside;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try(Socket socket= new Socket()) {//to don't close
            socket.connect(new InetSocketAddress("india.colorado.edu",13),2000);
            socket.connect(new InetSocketAddress("localhost",8189));

            Scanner scanner = new Scanner(socket.getInputStream());// answer as input stream
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }


        }
    }
}
