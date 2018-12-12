package org.zy.mytools.exec;

import org.zy.mytools.domain.OrderExportInfo;
import org.zy.mytools.util.CsvUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuezhang on 18/12/7.
 */
public class ReadOldDiffNewCsv {

    static String oldUrl = "/Users/yuezhang/Downloads/duizhang/1203.csv";
    static String newUrl = "/Users/yuezhang/Downloads/duizhang/1207.csv";
    static String diffUrl = "/Users/yuezhang/Downloads/duizhang/diff.csv";

    static String header = "订单号,支付流水号,下单用户名,支付方式,支付方式解释,支付金额,应付金额,支付状态,平台类型,产品线,下单时间,更新时间,支付时间,渠道来源,外部订单号,外部交易流水号,渠道订单号,位置入口id,来源内容ID,活动ID,活动明细ID,活动原始ID,商品编号,商品名称,商品价格,购买数量,权益编号,权益类型,品牌名称";


    public static void main(String [] args){

        // 读旧数据
        BufferedReader br = null;
        Map<String,OrderExportInfo> orderExportMap = new HashMap<>();
        try {

            br = CsvUtil.getBufferedReader(oldUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br.readLine()) != null) {
                OrderExportInfo order = new OrderExportInfo(readLine);
                if (order.getOrderNo() != null){
                    orderExportMap.put(order.getOrderNo(),order);
                }
                csvLength++;
            }

            System.out.println("旧的所有行数："+csvLength);

        }catch (Exception e) {
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

        // 读新数据
        BufferedReader br2 = null;
        List<OrderExportInfo> orderList = new ArrayList<>();

        try {
            br2 = CsvUtil.getBufferedReader(newUrl);
            String readLine;
            int csvLength = 0;
            while ((readLine = br2.readLine()) != null)  //读取到的内容给line变量
            {
                OrderExportInfo order = new OrderExportInfo(readLine);
                if (order.getOrderNo() != null){
                    orderList.add(order);
                }
                csvLength++;
            }
            System.out.println("新的所有行数："+csvLength);

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

        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(diffUrl);
            CsvUtil.writeLine(bw,header);

            int diffLength = 0;
            for (OrderExportInfo order : orderList){
                OrderExportInfo oldOrder = orderExportMap.get(order.getOrderNo());
                if (oldOrder == null){
                    String writeLine = OrderExportInfo.getWriteCsv(order);
                    CsvUtil.writeLine(bw,writeLine);
                    diffLength++;
                }
            }

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
