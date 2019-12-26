package ru.potapov.socketapp.programs;


import ru.potapov.socketapp.clients.Client;

import java.util.Scanner;

/**
 * implementation of thin client
 */
public class StartClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("/login,/logout,/buy,/add, /delete, /show_all");
        System.out.println("log: \"/logout\"");
        System.out.println("buy: /buy *id_good*");
        System.out.println("добавить: /add *good_name*  *good_price*");
        System.out.println("удалить: /delete *id_good*");
        System.out.println("посмотреть всё: /show_all");
        System.out.println();


        Client chatClient = new Client();
        chatClient.startConnection("127.0.0.1", 6666);



        while (true) {
            chatClient.sendMessage(sc.nextLine());
        }
    }
}
