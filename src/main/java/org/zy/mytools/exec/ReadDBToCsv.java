package org.zy.mytools.exec;

import org.zy.mytools.dao.PayStatementDao;
import org.zy.mytools.domain.PayStatement;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuezhang on 18/9/25.
 */
public class ReadDBToCsv {

    static int pageSize = 10000;

    // ==========================支付宝201806-pay ============================================================
//    static String startTime = "2018-06-01 00:00:00";
//    static String endTime = "2018-07-01 00:00:00";
//    static String payWays = "11,12,13,14,103,104"; // 2088201535082534
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/ali201806-pay.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/ali201806-pay-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/ali201806-pay-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/ali201806-pay-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";

//    static String payWays = "15,16,17,18"; // 2017090608582548


    // ==========================支付宝201807-pay ============================================================
//    static String startTime = "2018-07-01 00:00:00";
//    static String endTime = "2018-08-01 00:00:00";
//    static String payWays = "11,12,13,14,103,104"; // 2088201535082534
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/ali201807-pay.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/diff/ali201807-pay-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/diff/ali201807-pay-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/diff/ali201807-pay-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";

    // ==========================支付宝201808-pay ============================================================
//    static String startTime = "2018-08-01 00:00:00";
//    static String endTime = "2018-09-01 00:00:00";
//    static String payWays = "11,12,13,14,103,104"; // 2088201535082534
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/ali201808-pay.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/ali201808-pay-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/ali201808-pay-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/ali201808-pay-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";

    // ==========================微信wx201808-601 ============================================================
//    static String startTime = "2018-08-01 00:00:00";
//    static String endTime = "2018-09-01 00:00:00";
//    static String payWays = "21,22,24,25,26"; // 1218588601
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-601.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/wx201808-601-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/wx201808-601-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

    // ==========================微信wx201808-801 ============================================================
//    static String startTime = "2018-08-01 00:00:00";
//    static String endTime = "2018-09-01 00:00:00";
//    static String payWays = "23,27"; // 1231381801
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/wx201808-801.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/wx201808-801-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/wx201808-801-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

    // ==========================易付宝yifubao201806 ============================================================
//    static String startTime = "2018-06-01 00:00:00";
//    static String endTime = "2018-07-01 00:00:00";
//    static String payWays = "7031,7032,7036,7034,7035,7033";
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201806/yifubao201806.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/yifubao201806-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/yifubao201806-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/yifubao201806-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";

    // ==========================易付宝yifubao201807 ============================================================
//    static String startTime = "2018-07-01 00:00:00";
//    static String endTime = "2018-08-01 00:00:00";
//    static String payWays = "7031,7032,7036,7034,7035,7033";
//    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201807/yifubao201807.csv";
//
//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/diff/yifubao201807-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201807/diff/yifubao201807-diff-DDPOS.csv";
//    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/yifubao201806-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";

    // ==========================易付宝yifubao201808 ============================================================
    static String startTime = "2018-08-01 00:00:00";
    static String endTime = "2018-09-01 00:00:00";
    static String payWays = "7031,7032,7036,7034,7035,7033";
    static String readUrl = "/Users/yuezhang/Downloads/duizhang/201808/yifubao201808.csv";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/yifubao201808-diff-DIP-API-SPORT.csv";
//    static String merchantId = "DIP-API-SPORT";

    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201808/diff/yifubao201808-diff-DDPOS.csv";
    static String merchantId = "DDPOS";

//    static String writeUrl = "/Users/yuezhang/Downloads/duizhang/201806/diff/yifubao201806-diff-DDPOS_SPORT.csv";
//    static String merchantId = "DDPOS_SPORT";


    public static void main(String [] args){

        // 读数据
        String headLine = "支付流水号,渠道流水号,价格,商品名称";
        String footLine = "xxx,总金额,%s,xxx";
        BufferedReader br = null;

        Map<String,PayStatement> csvMap = new HashMap<>();
        try {
            br = CsvUtil.getBufferedReader(readUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
            {
                PayStatement csvPs = new PayStatement(readLine);
                csvMap.put(csvPs.getStatementId(),csvPs);
                csvLength++;
            }
            System.out.println("csv表格中所有行数："+csvLength);

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


        // 写数据
        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);
            PayStatementDao payStatementDao = new PayStatementDao();

            int csvLength = 0;
            int pageNo = 1;
            int dbLength = 0;
            double totalAmount = 0;
            boolean hasNextPage = true;
            while (hasNextPage){
                List<PayStatement> statementList = payStatementDao.getPayStatementList(merchantId,payWays,startTime,endTime,pageNo,pageSize);
                if (statementList == null || statementList.size() < pageSize){
                    hasNextPage = false;
                }else {
                    pageNo ++;
                }
                if (statementList != null && statementList.size() > 0){
                    dbLength += statementList.size();
                    for (PayStatement dbPs : statementList){
                        System.out.println("db statement: " + dbPs);
                        PayStatement csvPs = csvMap.get(dbPs.getStatementId());
                        if (csvPs == null){
                            String writeLine = PayStatement.getWriteCsv(dbPs);
                            CsvUtil.writeLine(bw,writeLine);
                            csvLength++;
                            totalAmount += Double.parseDouble(dbPs.getAmount());
                        }
                    }

                    // 清空list
                    statementList.clear();
                }

            }

            CsvUtil.writeLine(bw,String.format(footLine,totalAmount));

            System.out.println("数据库所有行数："+dbLength);
            System.out.println("未匹配的数据数量："+csvLength);

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
