package org.zy.mytools.exec;

import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.dao.PayStatementDao;
import org.zy.mytools.util.CsvUtil;

import java.io.*;

/**
 * Created by yuezhang on 18/9/22.
 */
public class ReadCsvToDB {

    // 微信201806 601
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/wx201806-601.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/wx201806-601-diff.csv";

    // 微信201806 801
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/wx201806-801.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/wx201806-801-diff.csv";

    // 易付宝201806
    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806.csv";
    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806-diff.csv";

    // 支付宝201806-pay
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/ali201806-pay.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/ali201806-pay-diff.csv";

    // 支付宝201806-panyue
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/ali201806-panyue.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/ali201806-panyue-diff.csv";

    // 微信201807-601
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/wx201807-601.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/wx201807-601-diff.csv";

    // 微信201807-801
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/wx201807-801.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/wx201807-801-diff.csv";

    // 易付宝201807
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807-diff.csv";

    // 支付宝201807-pay
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/ali201807-pay.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/ali201807-pay-diff.csv";

    // 支付宝201807-panyue
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/ali201807-panyue.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/ali201807-panyue-diff.csv";

    // 微信201808 601
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-601.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-601-diff.csv";

    // 微信201808 801
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-801.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-801-diff.csv";

    // 易付宝201808
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/yifubao201808.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/yifubao201808-diff.csv";

    // 支付宝201808-pay
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/ali201808-pay.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/ali201808-pay-diff.csv";

    // 支付宝201808-panyue
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/ali201808-panyue.csv";
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/ali201808-panyue-diff.csv";

    public static void main(String [] args){

        String headLine = "支付流水号,渠道流水号,价格,商品名称";

        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = CsvUtil.getBufferedReader(readUrl);
            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);

            String readLine;
            PayStatementDao payStatementDao = new PayStatementDao();
            int csvLength = 0;
            int dbLength = 0;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {

                System.out.println(readLine);
                PayStatement csvPs = new PayStatement(readLine);
                PayStatement dbPs = payStatementDao.getPayStatement(csvPs.getStatementId(),csvPs.getTransactionId());
                // 输出查询结果
                if(dbPs==null){
                    String writeLine = PayStatement.getWriteCsv(csvPs);
                    CsvUtil.writeLine(bw,writeLine);
                    dbLength++;
                }
                csvLength++;
            }
            System.out.println("csv表格中所有行数："+csvLength);
            System.out.println("未匹配的数据数量："+dbLength);

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
