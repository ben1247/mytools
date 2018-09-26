package org.zy.mytools.exec;

import org.zy.mytools.domain.Order;
import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuezhang on 18/9/26.
 */
public class ReadBizCsvToChCsv {

    // 易付宝201807
    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp.csv";
    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp-diff.csv";

    public static void main(String [] args){

        // 读第三方流水
        BufferedReader br = null;
        Map<String,PayStatement> statementMap = new HashMap<>();

        try {
            br = CsvUtil.getBufferedReader(readChannelUrl);
            String readLine;
            int csvLength = 0;
            double totalAmount = 0;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                PayStatement statement = new PayStatement(readLine);
                statementMap.put(statement.getStatementId(),statement);
                totalAmount += Double.parseDouble(statement.getAmount());
                csvLength++;
            }
            System.out.println("流水表格中所有行数："+csvLength + "    总金额：" + totalAmount);

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

        // 读业务订单
        BufferedReader br2 = null;
        List<Order> orderList = new ArrayList<>();
        try {
            br2 = CsvUtil.getBufferedReader(readBizUrl);
            String readLine;
            int csvLength = 0;
            double totalAmount = 0;
            while ((readLine = br2.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                orderList.add(order);
                totalAmount += Double.parseDouble(order.getAmount());
                csvLength++;
            }
            System.out.println("业务表格中所有行数："+csvLength + "   总金额：" + totalAmount);

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

        // 写差异数据
        String headLine = "支付流水号,用户名,商品名称,价格,系统";
        String footLine = "xxx,总金额,%s,xxx";
        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);

            int diffLength = 0;
            double totalAmount = 0;

            for (Order order : orderList){
                PayStatement statement = statementMap.get(order.getStatementId());
                if (statement == null){
                    String writeLine = Order.getWriteCsv(order);
                    CsvUtil.writeLine(bw,writeLine);
                    diffLength++;
                    totalAmount += Double.parseDouble(order.getAmount());
                }
            }

            CsvUtil.writeLine(bw,String.format(footLine,totalAmount));

            System.out.println("未匹配的数据数量："+diffLength);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
