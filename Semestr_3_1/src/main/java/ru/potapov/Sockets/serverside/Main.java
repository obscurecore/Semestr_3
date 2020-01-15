package ru.potapov.Sockets.serverside;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(8189);){
            while (true){
                Socket socket = new ServerSocket().accept();
              //  new Thread(new MyServer(socket)).start();
            }
        }
        //old version
        // all resources my pc will be calculate until reach the end and thread(serverSocket.accept is being blocked)
        /*

        try (ServerSocket serverSocket = new ServerSocket(8189);
             Socket socket = serverSocket.accept();
             Scanner scanner = new Scanner(socket.getInputStream())) {
            //OutputStream outputStream = socket.getOutputStream();
            //InputStream inputStream = socket.getInputStream();
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            //printWriter.println("hello");
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                printWriter.println("you've send " + str);
                System.out.println(str);
                if (str.equals("exit")) {
                    break;
                }
            }
        }

         */

    }
}
