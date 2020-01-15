package ru.potapov.Threads;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class provides Arrays os Strings(to precious - method sort) of clear arguments for using as URL
 * without repetition by Regex and Interface Set
 */
public class Sorting {

    private boolean b1;

    public static void setB1(boolean b1) {
        b1 = b1;
    }
    private static final String regex = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    private Sorting() { }

    private static String[] sort_arg(String[] args,String str,boolean b1) {
        Predicate<String> pred = ((String s)-> s.substring(s.lastIndexOf(".")+1).matches(str));
        //System.out.println(args[0].substring(args[0].lastIndexOf(".")));
        Predicate<String> pred2 = i -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher m = pattern.matcher(i);
            return (b1&&m.matches());

        };

        return Arrays.stream(args).filter(pred2.and(pred)).
                collect(Collectors.toCollection(HashSet::new)).stream().toArray(String[]::new);

    }

    /**
     * Method set array as static in DownloadFile class for executors
     */
    public static String [] GetArr(String[] arr,String str, boolean b1) {
        if (arr.length<1){
            try {
                throw new ArgsException("Please stop trolling me", arr.length);
            } catch (ArgsException e) {
                System.err.println(e.getMessage());
                System.err.println("The length args = "+e.getNumber());

            }
        }
        return (Sorting.sort_arg(arr,str,b1));

    }


}
