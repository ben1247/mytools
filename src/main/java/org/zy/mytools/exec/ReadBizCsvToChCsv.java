package org.zy.mytools.exec;

import org.zy.mytools.dao.PayStatementDao;
import org.zy.mytools.domain.Order;
import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by yuezhang on 18/9/26.
 */
public class ReadBizCsvToChCsv {

    // 易付宝201807
//    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
//    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-ddpos-ddp-diff.csv";

    // 支付宝201808
    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808.csv";
    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808-ddpos-ddp.csv";
    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/ali/ali201808-biz-ch-diff.csv";

    // 微信201808
//    static String readChannelUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808.csv";
//    static String readBizUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808-ddpos-ddp.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808V2/wx/wx201808-biz-ch-diff.csv";

    public static void main(String [] args){

        PayStatementDao payStatementDao = new PayStatementDao();

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
                if (statementMap.get(statement.getStatementId()) != null){
                    System.out.println("流水重复了："+statement.getStatementId() + "   " + statement.getSubject() + "  " + statement.getAmount());
                }
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

        // 读第三方流水,第三方流水交易号作为key
        BufferedReader br3 = null;
        Map<String,PayStatement> statementMap2 = new HashMap<>();
        try {
            br3 = CsvUtil.getBufferedReader(readChannelUrl);
            String readLine;
            while ((readLine = br3.readLine()) != null)  //读取到的内容给line变量
            {
                PayStatement statement = new PayStatement(readLine);
                statementMap2.put(statement.getTransactionId(),statement);
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
        String headLine = "支付流水号,用户名,商品名称,价格,系统,订单号";
//        String headLine = "支付流水号,用户名,商品名称,价格,系统,流水里的商品名称,流水金额";
        String footLine = ",,总金额,%s,";
        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);

            int diffLength = 0;
            double totalAmount = 0;

            double csvAmount = 0;
            double bizAmount = 0;

            for (Order order : orderList){
                PayStatement statement = statementMap.get(order.getStatementId());
                if (statement == null){
                    statement = statementMap2.get(order.getStatementId());
                    if (statement != null){
                        System.out.println("第三方流水号："+statement.getTransactionId() + "   " + statement.getSubject());
                    }
                }
                // 查支付中心，拿到交易流水信息
                if (statement == null){
                    PayStatement tempStatement = payStatementDao.getPayStatementByOutOrderNo(order.getOrderNo());
                    if (tempStatement != null){
                        statement = statementMap2.get(tempStatement.getTransactionId());
                    }
                }

                if (statement == null){
                    String writeLine = Order.getWriteCsv(order);
                    CsvUtil.writeLine(bw,writeLine);
                    diffLength++;
                    totalAmount += Double.parseDouble(order.getAmount());
                }else{
//                    Double orderAmount = Double.parseDouble(order.getAmount());
//                    Double statementAmount = Double.parseDouble(statement.getAmount());
//                    if (!Objects.equals(orderAmount, statementAmount)){
//                        StringBuilder writeLine = new StringBuilder();
//                        writeLine.append(order.getStatementId()).append("\t").append(",")
//                                .append(order.getUserName()).append(",")
//                                .append(order.getGoodsName()).append(",")
//                                .append(order.getAmount()).append(",")
//                                .append(order.getSystem()).append(",")
//                                .append(statement.getSubject()).append(",")
//                                .append(statement.getAmount());
//                        CsvUtil.writeLine(bw,writeLine.toString());
//                    }
//                    csvAmount += statementAmount;
//                    bizAmount += orderAmount;
                }
            }

            CsvUtil.writeLine(bw,String.format(footLine,totalAmount));

            System.out.println("未匹配的数据数量："+diffLength);
//            System.out.println("流水匹配金额："+csvAmount);
//            System.out.println("业务匹配总金额："+bizAmount);
            System.out.println("业务未匹配总金额："+totalAmount);

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
