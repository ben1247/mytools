package org.zy.mytools.exec;

import org.zy.mytools.dao.PayStatementDao;
import org.zy.mytools.domain.NotificationToMerchantResponse;
import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuezhang on 18/10/16.
 */
public class CreatePayCenterSign {

    static String readUrl = "/Users/yuezhang/Downloads/paycenter/ddpos.csv";

    static String writeUrl = "/Users/yuezhang/Downloads/paycenter/ddposwithsign.csv";

    public static void main(String [] args){

        // 读第三方流水
        BufferedReader br = null;

        try {
            br = CsvUtil.getBufferedReader(readUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                NotificationToMerchantResponse notification = new NotificationToMerchantResponse(readLine);



                csvLength++;
            }
            System.out.println("订单总所有行数："+csvLength);

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
