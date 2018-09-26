package org.zy.mytools.util;

import java.io.*;

/**
 * Created by yuezhang on 18/9/24.
 */
public class CsvUtil {

    public static void writeLine(BufferedWriter bw , String line){
        try {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedReader getBufferedReader(String fileUrl){
        try {
            return new BufferedReader(new FileReader(new File(fileUrl)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedWriter getBufferedWriter(String fileUrl){
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileUrl)), "GBK"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
