package ru.potapov.WebServer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FIle<E> {
    public static int p = 0;
    //private final String FILE = "src\\SmoothSort\\smooth.txt";

    File createfile() throws IOException {

            File file = new File(p + "file.txt");
            file.createNewFile();
            return file;

        }
        void writeFile(File file,String s) throws IOException {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s + "\n");
            fileWriter.close();

    }



    public int []   read(int p) {
        ArrayList<Integer> list = new ArrayList();

        try {

            File file = new File(p + "file.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextInt()) {
                list.add(sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        int[] arr = list.stream().mapToInt(i->i).toArray();
        return  arr;
    }
/*
    public int readFromIndex(int i) {
        return arr[i];
    }

*/


/*

        InputStream input = new FileInputStream("file.txt");
        int size = input.available();

        for (int j = 0; j < size; j++) {
            System.out.print((char) input.read() + " "); // Чтение текстового файла посимвольно
        }
        input.close();
*/




}
