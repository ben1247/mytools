package org.zy.mytools.exec;

import org.zy.mytools.dao.PayStatementDao;
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
 * 比较流水csv和业务csv
 * Created by yuezhang on 18/9/26.
 */
public class ReadChCsvToBizCsv {

    // 易付宝201806
//    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806-new.csv";
//    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806-ddpos-ddp.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806-ddpos-ddp-diff.csv";

    // 易付宝201807
//    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
//    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp-diff.csv";

    // 微信201808
//    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808.csv";
//    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808-ddpos-ddp.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808-ch-biz-diff.csv";

    // 支付宝201808
    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808.csv";
    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808-ddpos-ddp.csv";
    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808-ch-biz-diff.csv";

    public static void main(String [] args){

        PayStatementDao statementDao = new PayStatementDao();

        // 读第三方流水
        BufferedReader br = null;
        List<PayStatement> statementList = new ArrayList<>();
        try {
            br = CsvUtil.getBufferedReader(readChannelUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                PayStatement statement = new PayStatement(readLine);
                statementList.add(statement);
                csvLength++;
            }
            System.out.println("流水表格中所有行数："+csvLength);

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

        // 读业务订单－根据statementId作为key
        BufferedReader br2 = null;
        Map<String,Order> orderMap = new HashMap<>();
        try {
            br2 = CsvUtil.getBufferedReader(readBizUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br2.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                orderMap.put(order.getStatementId(),order);
                csvLength++;
            }
            System.out.println("业务表格中所有行数："+csvLength);

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

        // 读业务订单-根据orderNo作为key
        BufferedReader br3 = null;
        Map<String,Order> orderMap2 = new HashMap<>();
        try {
            br3 = CsvUtil.getBufferedReader(readBizUrl);
            String readLine;
            while ((readLine = br3.readLine()) != null)  //读取到的内容给line变量
            {
                Order order = new Order(readLine);
                orderMap2.put(order.getOrderNo(),order);
            }
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

        // 写差异数据
        String headLine = "支付流水号,渠道流水号,价格,商品名称";
        String footLine = ",,总金额,%s,";
        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);

            int diffLength = 0;
            double totalAmount = 0;

            for (PayStatement statement : statementList){
                Order order = orderMap.get(statement.getStatementId());

                // 查支付中心
                if (order == null){
                    PayStatement dbStatement = statementDao.getPayStatement(statement.getStatementId(),statement.getTransactionId());
                    if (dbStatement != null){
                        order = orderMap2.get(dbStatement.getOutTradeNo());
                    }
                }
                if (order == null){
                    String writeLine = PayStatement.getWriteCsv(statement);
                    CsvUtil.writeLine(bw,writeLine);
                    diffLength++;
                    totalAmount += Double.parseDouble(statement.getAmount());
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
