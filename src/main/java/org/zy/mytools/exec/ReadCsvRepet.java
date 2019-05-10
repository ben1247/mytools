package org.zy.mytools.exec;

import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuezhang on 19/1/3.
 */
public class ReadCsvRepet {

    static String readUrl = "/Users/yuezhang/Downloads/duizhang/userName.csv";

    public static void main(String [] args){

        Map<String,String> map = new HashMap<>();

        BufferedReader br = null;
        try {
            br = CsvUtil.getBufferedReader(readUrl);
            String readLine;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                String key = readLine;
                String value = map.get(key);
                if (value != null && !value.equals("")){
                    System.out.println(readLine + " 有重复!!!!");
                }else{
                    value = readLine;
                    map.put(key,value);
                }
            }
            System.out.println("记录总数："+map.size());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
