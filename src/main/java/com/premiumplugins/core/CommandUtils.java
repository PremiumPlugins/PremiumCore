package com.premiumplugins.core;

public class CommandUtils {

    public static String subArray(String[] args, int start) {
        String s = "";
        int i = 0;
        for (String str : args) {
            if (i >= start) s = s.concat(str + " ");
            i++;
        }
        return s.substring(0, s.length()-1);
    }

}
