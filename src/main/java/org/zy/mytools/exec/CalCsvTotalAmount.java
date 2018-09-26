package org.zy.mytools.exec;

import org.zy.mytools.domain.Order;
import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * 计算csv总金额
 * Created by yuezhang on 18/9/26.
 */
public class CalCsvTotalAmount {

    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
    static String readDDPUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddp.csv";
    static String readDDPOSUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos.csv";
    static String readDDPOSandDDPUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp.csv";

    public static void main(String [] args){

        BufferedReader br = null;
        double channelTotalAmount = 0;
        try {
            br = CsvUtil.getBufferedReader(readUrl);
            String readLine;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                PayStatement statement = new PayStatement(readLine);
                channelTotalAmount += Double.parseDouble(statement.getAmount());

            }
            System.out.println("流水表格总金额："+channelTotalAmount);

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

        // 读DDP订单
        BufferedReader br2 = null;
        double ddpTotalAmount = 0;
        try {
            br2 = CsvUtil.getBufferedReader(readDDPUrl);
            String readLine;
            while ((readLine = br2.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                ddpTotalAmount += Double.parseDouble(order.getAmount());

            }
            System.out.println("ddp总金额："+ddpTotalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br2 != null){
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 读DDPOS订单
        BufferedReader br3 = null;
        double ddposTotalAmount = 0;
        try {
            br3 = CsvUtil.getBufferedReader(readDDPOSUrl);
            String readLine;
            while ((readLine = br3.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                ddposTotalAmount += Double.parseDouble(order.getAmount());

            }
            System.out.println("ddpos总金额："+ddposTotalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br3 != null){
                try {
                    br3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // ddpos＋ddp
        BufferedReader br4 = null;
        double ddposAndDdpTotalAmount = 0;
        try {
            br4 = CsvUtil.getBufferedReader(readDDPOSandDDPUrl);
            String readLine;
            while ((readLine = br4.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                ddposAndDdpTotalAmount += Double.parseDouble(order.getAmount());

            }
            System.out.println("ddpos和ddp总金额："+ddposAndDdpTotalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br4 != null){
                try {
                    br4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
