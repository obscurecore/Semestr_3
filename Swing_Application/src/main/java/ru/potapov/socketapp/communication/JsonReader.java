package ru.potapov.socketapp.communication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ru.potapov.socketapp.jsonModels.payloads.*;
import ru.potapov.socketapp.models.Item;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    public static Object read(String jsonLine) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            ObjectNode objectNode = objectMapper.readValue(jsonLine, ObjectNode.class);
            JsonNode jsonNodeHeader = objectNode.get("header");
            if (jsonNodeHeader != null) {

                String header = jsonNodeHeader.asText();
                switch (header) {
                    case ("Message"):
                        return readMessage(objectNode);
                    case ("LogIn"):
                        return readLogin(objectNode);
                    case ("Demonstrate"):
                        return readShowAll(objectNode);
                    case ("Add Item"):
                        return readAddItem(objectNode);
                    case ("Delete Item"):
                        return readDeleteItem(objectNode);
                    case ("Buy"):
                        return readBuy(objectNode);
                    case ("Token"):
                        return readToken(objectNode);
                }
            } else {
                JsonNode jsonNodeData = objectNode.get("data");
                if (jsonNodeData != null) {
                    return readData(objectNode);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    private static Object readToken(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        TokenPayload tokenPayload = objectMapper.convertValue(jsonPayload, TokenPayload.class);
        return tokenPayload;
    }

    private static Object readBuy(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        BuyPayload buyPayload = objectMapper.convertValue(jsonPayload, BuyPayload.class);
        return buyPayload;
    }

    private static Object readDeleteItem(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        DeleteItemPayload deleteItemPayload = objectMapper.convertValue(jsonPayload, DeleteItemPayload.class);
        return deleteItemPayload;

    }

    private static Object readAddItem(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        AddItemPayload addItemPayload = objectMapper.convertValue(jsonPayload, AddItemPayload.class);
        return addItemPayload;
    }

    private static Object readShowAll(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        ShowAllPayload showAllPayload = objectMapper.convertValue(jsonPayload, ShowAllPayload.class);
        return showAllPayload;
    }

    private static MessagePayload readMessage(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        MessagePayload messagePayload = objectMapper.convertValue(jsonPayload, MessagePayload.class);
        return messagePayload;
    }

    private static LoginPayload readLogin(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonPayload = objectNode.get("payload");
        LoginPayload loginPayload = objectMapper.convertValue(jsonPayload, LoginPayload.class);
        return loginPayload;
    }

    private static List<Item> readData(ObjectNode objectNode) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectNode.get("data");
        List<Item> data = new ArrayList<>();
        for (JsonNode node : jsonNode) {
            data.add(objectMapper.convertValue(node, Item.class));
        }
        return data;
    }
}
