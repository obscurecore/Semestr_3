package ru.potapov;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLSplit {

    public static void splitUsingRegex(String url) {
        System.out.println("Split");
        System.out.println();
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(url);
        matcher.find();

        String protocol = matcher.group(1);
        String domain = matcher.group(2);
        String port = matcher.group(3);
        String uri = matcher.group(4);
        System.out.println(url);
        System.out.println("protocol: " + (protocol != null ? protocol : ""));
        System.out.println("domain: " + (domain != null ? domain : ""));
        System.out.println("port: " + (port != null ? port : ""));
        System.out.println("uri: " + (uri != null ? uri : ""));
        System.out.println();
    }


    public static void main(String[] args) throws Exception {
        String url1 = "/how-to-format-xmlgregoriancalendar/";
        String url2 = "http://docs.oracle.com/javase/7/docs/api/java/util/Date.html";
        String url3 = "https://example.com:8080/test1/index.html";

      //  splitUsingRegex(url1);
        splitUsingRegex(url2);
        splitUsingRegex(url3);


    }
}