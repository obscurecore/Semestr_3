package ru.potapov.socketapp.servers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.potapov.socketapp.jsonModels.payloads.*;
import ru.potapov.socketapp.services.AuthService;
import ru.potapov.socketapp.services.GoodService;
import ru.potapov.socketapp.services.OrderHistoryService;
import ru.potapov.socketapp.communication.JsonParser;
import ru.potapov.socketapp.communication.JsonReader;
import ru.potapov.socketapp.jsonModels.JsonObject;
import ru.potapov.socketapp.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Server$ {
    private ServerSocket serverSocket;
    private List<ClientHandler> clients;
    private Algorithm algorithm;

    public Server$() {
        this.clients = new ArrayList<>();
        this.algorithm = Algorithm.HMAC512("super_secret_ket");

        System.out.println("START");
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (; ; ) {
            try {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                clientHandler.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;
        private ObjectMapper objectMapper;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
            clients.add(this);
            this.objectMapper = new ObjectMapper();
            try {
                this.reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                this.writer = new PrintWriter(this.clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("New Client");
        }

        @Override
        public void run() {
            login();
            writer.println(JsonParser.parse("Welcome", null));
            try {
                String jsonLine;
                boolean isLogout = false;
                while ((jsonLine = reader.readLine()) != null && !isLogout) {
                    ObjectNode node = objectMapper.readValue(jsonLine, ObjectNode.class);
                    DecodedJWT decodedJWT = verify(node.get("token").asText());
                    if (decodedJWT == null) {
                        writer.println("Please login again");
                        run();
                    }
                    int id_user = decodedJWT.getClaim("sub").asInt();
                    int id_role = decodedJWT.getClaim("role").asInt();
                    String login = decodedJWT.getClaim("login").asString();
                    switch (node.get("header").asText()) {
                        case "Login":
                            writer.println(JsonParser.parse("Seriously? i think, it's bollocks, cause you've already signed", null));
                            break;
                        case "Logout":
                            System.out.println(decodedJWT.getClaim("login").asString() + " disconnected");
                            writer.println(JsonParser.parse("Good buy", null));
                            isLogout = true;
                            break;
                        case "Show all":
                            /*writer.println(JsonParser.parse(new GoodService().showAll(),null));*/
                            writer.println(JsonParser.parseData(new GoodService().showAll()));
                            break;
                        case "Buy":
                            if (id_role == 1) {
                                BuyPayload buyPayload = (BuyPayload) JsonReader.read(jsonLine);
                                if (new OrderHistoryService().buy(id_user, buyPayload.getId_good())) {
                                    writer.println(JsonParser.parse("The item has been bought", null));
                                } else {
                                    writer.println(JsonParser.parse("The item has't been bought", null));
                                }
                            } else
                                writer.println(JsonParser.parse("No access", null));
                            break;

                        case "Add good":
                            if (id_role == 2) {
                                AddItemPayload addItemPayload = (AddItemPayload) JsonReader.read(jsonLine);
                                if (new GoodService().addGood(addItemPayload.getGoodName(), addItemPayload.getPrice())) {
                                    writer.println(JsonParser.parse("The item has been added", null));
                                } else {
                                    writer.println(JsonParser.parse("The item has't been added", null));
                                }
                            } else
                                writer.println(JsonParser.parse("No access", null));
                            break;
                        case "Delete good":
                            if (id_role == 2) {
                                DeleteItemPayload deleteItemPayload = (DeleteItemPayload) JsonReader.read(jsonLine);
                                if (new GoodService().deleteGood(deleteItemPayload.getId_good())) {
                                    writer.println(JsonParser.parse("The item has been removed", null));
                                } else {
                                    writer.println(JsonParser.parse("The item has't been removed", null));
                                }
                            } else
                                writer.println(JsonParser.parse("No access", null));
                            break;
                        default:
                            writer.println(JsonParser.parse("Unknown command", null));
                            break;
                    }
                }

            } catch (IOException e) {
                throw new IllegalStateException(e);
            }


            clients.remove(this);
            try {
                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
/*            try {
                jsonLine = reader.readLine();
                while (jsonLine != null) {
                    if (checkForCorrectSigh(jsonLine)) {
                        ObjectNode objectNode = objectMapper.readValue(jsonLine, ObjectNode.class);
                        String header = objectNode.get("header").asText();
                        switch (header) {
                            case "Login":
                                writer.println(JsonParser.parse("Already login"));
                                break;
                            case "Logout":
                                System.out.println(user.getLogin() + " disconnected");
                                writer.println(JsonParser.parse("Nice meeting you"));
                                clients.remove(this);

                                reader.close();
                                writer.close();
                                clientSocket.close();
                                return;
                            case "Message":
                                MessagePayload message = (MessagePayload) JsonReader.read(jsonLine);
                                messageDAO.insertMessage(message.getMessage(), user.getId());
                                System.out.println(user.getLogin() + ": " + message.getMessage());
                                for (ClientHandler clientHandler : clients) {
                                    PrintWriter pw = new PrintWriter(clientHandler.clientSocket.getOutputStream(), true);
                                    pw.println(JsonParser.parse(user.getLogin() + ": " + message.getMessage()));
                                }
                                break;
                            case "Command":
                                CommandPayload command = (CommandPayload) JsonReader.read(jsonLine);
                                if (command.getCommand().equals("get-messages")) {
                                    String response = GettingMessages.get(command.getPage(), command.getSize());
                                    writer.println(JsonParser.parse("============================================"));
                                    writer.println(JsonParser.parse("         Page №" + command.getPage()));
                                    System.out.println(response);
                                    writer.println(response);
                                    writer.println(JsonParser.parse("============================================"));
                                } else {
                                    writer.println(JsonParser.parse("No such command"));
                                }
                                break;

                        }
                    }

                    reader.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }*/
        }

        private String generateToken(Integer id, String login, Integer role_id) {
            JsonObject jsonObject = new JsonObject();
            String token = JWT.create()
                    .withClaim("sub", id)
                    .withClaim("role", role_id)
                    .withClaim("login", login)
                    .withIssuer("obscure")
                    .sign(algorithm);
            jsonObject.setHeader("Token");
            jsonObject.setToken(null);
            jsonObject.setPayload(new TokenPayload().setToken(token));
            try {
                return objectMapper.writeValueAsString(jsonObject);
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
            }
        }

        private void login() {
            try {
                System.out.println("Hmmm?");
                User user = null;
                writer.println(JsonParser.parse("Write login/password", null));
                String jsonAuthData;
                while (Objects.isNull(user) && (jsonAuthData = reader.readLine()) != null) {
                    System.out.println(jsonAuthData);
                    ObjectNode objectNode = objectMapper.readValue(jsonAuthData, ObjectNode.class);
                    System.out.println("TEST");
                    if (objectNode.get("header").asText().equals("Login")) {
                        System.out.println("LOGIN");
                        LoginPayload loginPayload = (LoginPayload) JsonReader.read(jsonAuthData);
                        user = AuthService.auth(loginPayload.getLogin(), loginPayload.getPassword());
                        System.out.println("LOGIN"+ loginPayload.getLogin());
                        System.out.println("PASSWOed"+loginPayload.getPassword());
                        if (Objects.isNull(user))
                            writer.println(JsonParser.parse("Incorrect login or password", null));
                        else
                            writer.println(generateToken(user.getId(), user.getLogin(), user.getRoleId()));
                    } else {
                        writer.println(JsonParser.parse("Need auth", null));
                    }
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private DecodedJWT verify(String token) {
            try {
                return JWT.require(algorithm)
                        .withIssuer("obscure")
                        .build()
                        .verify(token);
            } catch (JWTVerificationException exception) {
                return null;
            }
        }

    }
}
