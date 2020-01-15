package ru.potapov.WebServer;

import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String request;
    private String data;
    private static File file;
    private static FIle fIle;

    public void work() throws IOException {
        try {
            serverSocket = new ServerSocket(1234);
            socket = serverSocket.accept();
            BufferedOutputStream outToClient = null;


            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            try {
                request = in.readLine();
                if (request.contains("1.1")) {
                    if (request.contains(" / ")) {
                        if (request.contains("GET") || request.contains("POST")) {
                            while (in.readLine().equals("\n")) {
                                data = in.readLine();
                                if (data == null) {
                                    out.write("HTTP/1.1 400 Bad Request ");
                                    out.flush();
                                    out.close();
                                    break;
                                }
                            }
                            data = in.readLine();
                            if (request.equals("GET")) {
                                out.write("HTTP/1.1 200 OK");
                                out.write("Date: Sun, 18 Oct 2012 10:36:20 GMT");
                                out.write("");
                              
                                byte[] mybytearray = new byte[(int) file.length()];

                                FileInputStream fis = null;

                                try {
                                    fis = new FileInputStream(file);
                                } catch (FileNotFoundException ex) {
                                    // Do exception handling
                                }
                                BufferedInputStream bis = new BufferedInputStream(fis);

                                try {
                                    bis.read(mybytearray, 0, mybytearray.length);
                                    outToClient.write(mybytearray, 0, mybytearray.length);
                                    outToClient.flush();
                                    outToClient.close();

                                    // File sent
                                    return;
                                } catch (IOException ex) {
                                    // exception handling
                                }
                            }
                        }
                        if (request.equals("POST")) {
                            fIle.writeFile(file, data);
                            out.write("HTTP/1.1 200 OK");
                            out.write("Date: Sun, 18 Oct 2012 10:36:20 GMT");
                            out.write("");
                            out.write("Written!");
                            out.flush();
                            out.close();
                        }
                    } else {
                        out.write("HTTP/1.1 405 Method Not Allowed");
                        out.write("Allow: GET, POST");
                        out.flush();
                        out.close();
                    }
                } else {
                    out.write("HTTP/1.1 404 Not Found");
                    out.flush();
                    out.close();
                }
            } catch (IIOException e) {
                out.write("HTTP/1.1 500 Internal Server Error");
                out.flush();
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            file = fIle.createfile();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}