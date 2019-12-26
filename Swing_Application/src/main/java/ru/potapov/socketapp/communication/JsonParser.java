package ru.potapov.socketapp.communication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.potapov.socketapp.jsonModels.Data;
import ru.potapov.socketapp.jsonModels.JsonObject;
import ru.potapov.socketapp.jsonModels.payloads.*;

import java.util.Arrays;

public class JsonParser {
    public static String parse(String str, String token) {

        if (str.charAt(0) == '/') {
            String[] arg = str.split(" ");
            System.out.println(Arrays.toString(arg));
            switch (arg[0]) {
                case ("/login"):
                    return parseLogin(arg, token);
                case ("/logout"):
                    return parseLogout(arg, token);
//                case ("/command"):
//                    return parseCommand(arg);
                case ("/buy"):
                    return parseBuy(arg, token);
                case ("/add"):
                    return parseAdd(arg, token);
                case ("/delete"):
                    return parseDelete(arg, token);
                case ("/show_all"):
                    System.out.println("SHOW");
                    return parseShowAll(arg, token);
                default:
                    return null;
            }

        } else {
            return parseMessage(str, token);
        }

    }

    public static String parseData(Data data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String mapper(JsonObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private static String parseAdd(String[] arg, String token) {
        if (arg.length == 3) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.setHeader("Add good");
            jsonObject.setToken(token);
            jsonObject.setPayload(new AddItemPayload()
                    .setGoodName(arg[1])
                    .setPrice(Integer.parseInt(arg[2])));
            return mapper(jsonObject);
        }
        return null;
    }

    private static String parseDelete(String[] arg, String token) {
        if (arg.length == 2) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.setHeader("Delete good");
            jsonObject.setToken(token);
            jsonObject.setPayload(new DeleteItemPayload().setId_good(Integer.parseInt(arg[1])));
            return mapper(jsonObject);
        }
        return null;
    }

    private static String parseShowAll(String[] arg, String token) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.setHeader("Show all");
        jsonObject.setToken(token);
        jsonObject.setPayload(new ShowAllPayload());
        return mapper(jsonObject);
    }

    private static String parseBuy(String[] arg, String token) {
        if (arg.length == 2) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.setHeader("Buy");
            jsonObject.setToken(token);
            jsonObject.setPayload(new BuyPayload().setId_good(Integer.parseInt(arg[1])));
            return mapper(jsonObject);
        }
        return null;
    }


    private static String parseLogin(String[] args, String token) {
        if (args.length == 3) {
            JsonObject jsonObject = new JsonObject();

            LoginPayload loginPayload = new LoginPayload();
            loginPayload.setLogin(args[1]);
            loginPayload.setPassword(args[2]);

            jsonObject.setHeader("Login");
            jsonObject.setPayload(loginPayload);
            jsonObject.setToken(token);
            System.out.println(jsonObject);
            return mapper(jsonObject);
        }
        return null;
    }

    private static String parseLogout(String[] args, String token) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.setHeader("Logout");
        jsonObject.setPayload(new LogoutPayload());
        jsonObject.setToken(token);
        return mapper(jsonObject);
    }


    private static String parseMessage(String str, String token) {
        JsonObject jsonObject = new JsonObject();

        MessagePayload messagePayload = new MessagePayload();
        messagePayload.setMessage(str);

        jsonObject.setHeader("Message");
        jsonObject.setPayload(messagePayload);
        jsonObject.setToken(token);
        return mapper(jsonObject);
    }
}
