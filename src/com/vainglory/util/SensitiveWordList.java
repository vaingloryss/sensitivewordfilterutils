package com.vainglory.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SensitiveWordList {

    public static List<String> getSensWordList(){
        List<String> sensWordList = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(new File("testSensitiveWord.txt")));
            String str;
            while ((str = bf.readLine())!=null){
                sensWordList.add(str);
            }
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensWordList;
    }
}
