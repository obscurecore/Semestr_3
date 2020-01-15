package ru.potapov.Threads;

import org.w3c.dom.NodeList;

import javax.xml.xpath.*;
import java.util.Arrays;


public class Main {
    private static final String EXPRESSION = "HOST";
    public static void main(String[] args) throws Exception {
        start(args);



    }
    static void start(String[] args) throws Exception {
        //String[] arr = {"https://images.newscientist.com/wp-content/uploads/2019/04/08111018/screenshot-2019-04-08-10.24.34.jpg"};
        String[] srt = Sorting.GetArr(args, ".*", true);
        DownloadFile downloadFile1 = new DownloadFile();
        ThreadPool threadPool = new ThreadPool.Builder(srt).setN(2).build();
        threadPool.GPool(downloadFile1);
    }
}
