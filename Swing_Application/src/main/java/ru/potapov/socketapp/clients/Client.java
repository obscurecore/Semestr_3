package ru.potapov.socketapp.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.potapov.socketapp.communication.JsonParser;
import ru.potapov.socketapp.communication.JsonReader;
import ru.potapov.socketapp.jsonModels.payloads.MessagePayload;
import ru.potapov.socketapp.jsonModels.payloads.TokenPayload;
import ru.potapov.socketapp.models.Item;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String token;

    public void startConnection(String ip, Integer port) {
        try {
            clientSocket = new Socket(ip, port);
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            token = null;
            new Thread(receiveMessage).start();

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        String jsonLine = JsonParser.parse(message, token);
        if (jsonLine == null) {
            System.out.println("STRING"+message);
            System.out.println("Неизвестная комманда");
        } else {
            writer.println(jsonLine);
        }
    }

    private Runnable receiveMessage = () -> {
        ObjectMapper objectMapper = new ObjectMapper();
        boolean isLogout = false;
        while (!isLogout) {
            try {
                String jsonLine = reader.readLine();
                ObjectNode objectNode = objectMapper.readValue(jsonLine, ObjectNode.class);

                if (objectNode.get("header") != null) {
                    String header = objectNode.get("header").asText();
                    switch (header) {
                        case ("Message"):
                            MessagePayload messagePayload = (MessagePayload) JsonReader.read(jsonLine);
                            System.out.println(messagePayload.getMessage());
                            if (messagePayload.getMessage().equals("Nice meeting you"))
                                isLogout = true;
                            break;
                        case ("Token"):
                            TokenPayload tokenPayload = (TokenPayload) JsonReader.read(jsonLine);
                            this.token = tokenPayload.getToken();
                            break;
                    }

                } else {
                    if (objectNode.get("data") != null) {
                        List<Item> data = (List<Item>) JsonReader.read(jsonLine);
                        for (Item item : data) {
                            System.out.println(item.getId() + "     " + item.getName() + "      " + item.getPrice());
                        }
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };
}
