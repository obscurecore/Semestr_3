package ru.potapov.socketapp.programs;

import ru.potapov.socketapp.servers.Server$;

public class StartServer {
    public static void main(String[] argv) {
        Server$ chatServer = new Server$();
        chatServer.start(6666);
    }
}
