package ru.potapov.imposition_tasks;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class penalty {
    private final String site;
    private final int port = 80;
    private Socket socket;

    public penalty(String site) {
        this.site = site;
    }




    public void connect() {
        try {
            socket = new Socket(InetAddress.getByName(site), port);
        } catch (IOException e) {
            System.err.println("Can't connect");
        }
    }

    public String get(String uri) {
        try {
            connect();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("GET " + uri + " " + " HTTP/1.1");
            pw.println("Host: " + site);
            pw.println("");
            pw.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String s;
            String allHtml = "";
            while ((s = bufferedReader.readLine()) != null) {

                allHtml += s + "\n";
            }
            return allHtml;
        } catch (IOException e) {
            System.err.println("Connection problem");
        }
        return "";
    }

    public String post(String uri, String contentType, String contentLength, String content) {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("POST " + uri + " " + " HTTP/1.1");
            pw.println("Host: " + site);
            pw.println("Content-Type: " + contentType);
            pw.println("Content-Length:" + contentLength);
            pw.println("");
            pw.println(content);
            pw.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String s;
            String allHtml = "";
            while ((s = bufferedReader.readLine()) != null) {
                allHtml += s + "\n";
            }
            return allHtml;
        } catch (IOException e) {
            System.err.println("Connection problem");
        }
        return "";
    }

    public void fileWriter(String html) throws FileNotFoundException {
        try {
            PrintWriter writer = new PrintWriter(new File("file.txt"));
            writer.write(html);
        } catch (FileNotFoundException e) {
            System.err.println("File error");
        }
    }

    public void consoleView(String html) {
        System.out.println(html);
    }
}