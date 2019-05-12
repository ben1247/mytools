package org.zy.mytools.exec;

import org.zy.mytools.domain.BaseStat;
import org.zy.mytools.domain.OrderExportInfo;
import org.zy.mytools.domain.OrderStatInfo;
import org.zy.mytools.util.BaseStatUtil;
import org.zy.mytools.util.CsvUtil;
import org.zy.mytools.util.StringUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单统计
 * Created by yuezhang on 2019/5/9.
 */
public class OrderStat {

    static String [] readChannelUrl = {
            "/Users/yuezhang/Downloads/orderStat/DDPOS-53-59.csv",
            "/Users/yuezhang/Downloads/orderStat/DDPOS-JJC-53-59.csv"
//        "/Users/yuezhang/Downloads/orderStat/DDPOS-426-502.csv",
//        "/Users/yuezhang/Downloads/orderStat/DDPOS-JJC-426-502.csv"
    };

    static String writeUrl = "/Users/yuezhang/Downloads/orderStat/orderStatTemplate.csv";

    public static void main(String [] args){

        List<OrderExportInfo> orderList = new ArrayList<>();

        for (int i = 0; i<readChannelUrl.length; i++){
            // 读文件
            BufferedReader br = null;
            try {
                br = CsvUtil.getBufferedReader(readChannelUrl[i]);
                String readLine;
                int csvLength = 0;
                while ((readLine = br.readLine()) != null)  //读取到的内容给line变量
                {
                    csvLength++;
                    if (csvLength==1){continue;}
                    OrderExportInfo orderInfo = new OrderExportInfo(readLine);

                    // 过滤不需要的订单
                    if (StringUtil.isEmpty(withOutAppidMap.get(orderInfo.getAppid()))
                        && StringUtil.isEmpty(withOutPayWayMap.get(orderInfo.getPayWay()))){
                        orderList.add(orderInfo);
                    }
                }
//                System.out.println(readChannelUrl[i]+"流水表格中所有行数："+csvLength);
//                System.out.println();

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


        // 端统计
        apppltStat(orderList);

        // 商品统计
        goodsStat(orderList);

        // 支付方式统计
        payWayStat(orderList);

        // 写文件
        writeOrderToTemplate(orderList);
    }


    /**
     * 端统计
     * @param orderList
     */
    public static void apppltStat(List<OrderExportInfo> orderList){
        System.out.println("=========端统计============");
        for(OrderExportInfo order : orderList){
            String appplt = appidMap.get(order.getAppid());
            if (StringUtil.isNotEmpty(appplt)){
                // 下单量
                int count = BaseStatUtil.get(appplt,apppltStat);
                BaseStatUtil.put(appplt,count+1,apppltStat);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = BaseStatUtil.get(appplt,apppltPayStat);
                    BaseStatUtil.put(appplt,payCount+1,apppltPayStat);
                }

            }else{
//                System.out.println("其他端有：" + order.getAppid());
                // 下单量
                int count = BaseStatUtil.get(OTHER,apppltStat);
                BaseStatUtil.put(OTHER,count+1,apppltStat);

                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = BaseStatUtil.get(OTHER,apppltPayStat);
                    BaseStatUtil.put(OTHER,payCount+1,apppltPayStat);
                }
            }
        }
        int apppltTotalCount = 0;
        int apppltPayTotalCount = 0;
        for (BaseStat stat: apppltStat){
            String key = stat.getKey();
            int count = stat.getCount();
            int payCount = BaseStatUtil.get(key,apppltPayStat);
            apppltTotalCount += count;
            apppltPayTotalCount += payCount;
            System.out.println(key + ":" + count + "," + payCount);
        }

        System.out.println("总计:" + apppltTotalCount + "," + apppltPayTotalCount);
        System.out.println();
        System.out.println();

    }

    /**
     * 商品统计
     * @param orderList
     */
    public static void goodsStat(List<OrderExportInfo> orderList){
        System.out.println("=========商品统计============");
        for(OrderExportInfo order : orderList){
            String goods = goodsMap.get(order.getGoodsNo());
            if (StringUtil.isNotEmpty(goods)){
                // 下单量
                int count = BaseStatUtil.get(goods,goodsStat);
                BaseStatUtil.put(goods,count+1,goodsStat);
                // VIP和SVIP的下单总量
                if (goods.startsWith("VIP")){
                    int c = BaseStatUtil.get("VIP",goodsStat);
                    BaseStatUtil.put("VIP",c+1,goodsStat);
                }else if (goods.startsWith("SVIP")){
                    int c = BaseStatUtil.get("SVIP",goodsStat);
                    BaseStatUtil.put("SVIP",c+1,goodsStat);
                }

                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = BaseStatUtil.get(goods,goodsPayStat);
                    BaseStatUtil.put(goods,payCount+1,goodsPayStat);

                    // VIP和SVIP的支付总量
                    if (goods.startsWith("VIP")){
                        int c = BaseStatUtil.get("VIP",goodsPayStat);
                        BaseStatUtil.put("VIP",c+1,goodsPayStat);
                    }else if (goods.startsWith("SVIP")){
                        int c = BaseStatUtil.get("SVIP",goodsPayStat);
                        BaseStatUtil.put("SVIP",c+1,goodsPayStat);
                    }
                }

            }else{
                // 商品大类
                String category = categoryMap.get(order.getRightsCategory());
                if (StringUtil.isNotEmpty(category)){
                    // 下单量
                    int count = BaseStatUtil.get(category,goodsStat);
                    BaseStatUtil.put(category,count+1,goodsStat);
                    // 支付量
                    if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                        int payCount = BaseStatUtil.get(category,goodsPayStat);
                        BaseStatUtil.put(category,payCount+1,goodsPayStat);
                    }

                }else {
                    // 下单量
                    int count = BaseStatUtil.get(OTHER,goodsStat);
                    BaseStatUtil.put(OTHER,count+1,goodsStat);
                    // 支付量
                    if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                        int payCount = BaseStatUtil.get(OTHER,goodsPayStat);
                        BaseStatUtil.put(OTHER,payCount+1,goodsPayStat);
                    }
                }
            }
        }
        int gooodsTotalCount = 0;
        int goodsPayTotalCount = 0;
        for (BaseStat stat: goodsStat){
            String key = stat.getKey();
            int count = stat.getCount();
            int payCount = BaseStatUtil.get(key,goodsPayStat);
            if (!"VIP".equals(key) && !"SVIP".equals(key)){
                gooodsTotalCount += count;
                goodsPayTotalCount += payCount;
            }
            System.out.println(key + ":" + count + "," + payCount);
        }

        System.out.println("总计:" + gooodsTotalCount + "," + goodsPayTotalCount);
        System.out.println();
        System.out.println();
    }

    /**
     * 支付方式统计
     * @param orderList
     */
    public static void payWayStat(List<OrderExportInfo> orderList){
        System.out.println("=========支付方式统计============");
        for(OrderExportInfo order : orderList){
            String payChannel = payWayMap.get(order.getPayWay());
            if (StringUtil.isNotEmpty(payChannel)){
                // 下单量
                int count = BaseStatUtil.get(payChannel,payWayStat);
                BaseStatUtil.put(payChannel,count+1,payWayStat);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = BaseStatUtil.get(payChannel,payWayPayStat);
                    BaseStatUtil.put(payChannel,payCount+1,payWayPayStat);
                }

            }else{
                // 下单量
//                System.out.println("其他支付：" + order.getPayWay() + "   " + order.getPayWayName());
                int count = BaseStatUtil.get(OTHER,payWayStat);
                BaseStatUtil.put(OTHER,count+1,payWayStat);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = BaseStatUtil.get(OTHER,payWayPayStat);
                    BaseStatUtil.put(OTHER,payCount+1,payWayPayStat);
                }
            }
        }
        int payWayTotalCount = 0;
        int payWayPayTotalCount = 0;
        for (BaseStat stat: payWayStat){
            String key = stat.getKey();
            int count = stat.getCount();
            int payCount = BaseStatUtil.get(key,payWayPayStat);
            payWayTotalCount += count;
            payWayPayTotalCount += payCount;
            System.out.println(key + ":" + count + "," + payCount);
        }
        System.out.println("总计:" + payWayTotalCount + "," + payWayPayTotalCount);
        System.out.println();
    }

    /**
     * 写订单到模版中
     * @param orderList
     */
    public static void writeOrderToTemplate(List<OrderExportInfo> orderList){
        if (orderList == null || orderList.size() == 0){
            return;
        }

        System.out.println("开始写入订单模版里");

        BufferedWriter bw = null;
        try {

            bw = CsvUtil.getBufferedWriter(writeUrl);
            CsvUtil.writeLine(bw,headLine);

            for (OrderExportInfo order : orderList){

                OrderStatInfo orderStat = new OrderStatInfo();

                // 端============================
                String appplt = appidMap.get(order.getAppid());
                if (StringUtil.isEmpty(appplt)){
                    appplt = OTHER;
                }
                orderStat.setAppplt(appplt);

                // 商品============================
                String goods = goodsMap.get(order.getGoodsNo());
                if (StringUtil.isEmpty(goods)){
                    goods = OTHER;
                }
                orderStat.setGoods(goods);

                // 商品组============================
                String goodsGroup;
                if (goods.startsWith("VIP")){
                    goodsGroup = "VIP";
                }else if (goods.startsWith("SVIP")){
                    goodsGroup = "SVIP";
                }else{
                    goodsGroup = categoryMap.get(order.getRightsCategory());
                    if (StringUtil.isEmpty(goodsGroup)){
                        goodsGroup = OTHER;
                    }
                }
                orderStat.setGoodsGroup(goodsGroup);

                // 支付渠道============================
                String payChannel = payWayMap.get(order.getPayWay());
                if (StringUtil.isEmpty(payChannel)){
                    payChannel = OTHER;
                }
                orderStat.setPayChannel(payChannel);

                // 是否下单（只要有订单，肯定就是1）============================
                orderStat.setIsCreate(1);

                // 是否支付============================
                orderStat.setIsPay(0);
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    orderStat.setIsPay(1);
                }

                // 写文件
                String writeLine = OrderStatInfo.getWriteCsv(orderStat);
                CsvUtil.writeLine(bw,writeLine);
            }

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
            System.out.println("结束写入订单模版里");
        }

    }



    // =================================================================================================================================== //

    static List<BaseStat> apppltStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("iph",0));
            add(new BaseStat("aph",0));
            add(new BaseStat("ipd",0));
            add(new BaseStat("clt",0));
            add(new BaseStat("web",0));
            add(new BaseStat("wap",0));
            add(new BaseStat("atv",0));
            add(new BaseStat(OTHER,0));
        }
    };

    static List<BaseStat> apppltPayStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("iph",0));
            add(new BaseStat("aph",0));
            add(new BaseStat("ipd",0));
            add(new BaseStat("clt",0));
            add(new BaseStat("web",0));
            add(new BaseStat("wap",0));
            add(new BaseStat("atv",0));
            add(new BaseStat(OTHER,0));
        }
    };


    static List<BaseStat> goodsStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("VIP1",0));
            add(new BaseStat("VIP3",0));
            add(new BaseStat("VIP6",0));
            add(new BaseStat("VIP12",0));
            add(new BaseStat("VIP24",0));
            add(new BaseStat("VIPMonthly",0));
            add(new BaseStat("VIP",0));
            add(new BaseStat("SVIP1",0));
            add(new BaseStat("SVIP3",0));
            add(new BaseStat("SVIP6",0));
            add(new BaseStat("SVIP12",0));
            add(new BaseStat("SVIP24",0));
            add(new BaseStat("SVIPMonthly",0));
            add(new BaseStat("SVIP",0));
            add(new BaseStat("DP",0));
            add(new BaseStat("SPORTS",0));
            add(new BaseStat(OTHER,0));
        }
    };

    static List<BaseStat> goodsPayStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("VIP1",0));
            add(new BaseStat("VIP3",0));
            add(new BaseStat("VIP6",0));
            add(new BaseStat("VIP12",0));
            add(new BaseStat("VIP24",0));
            add(new BaseStat("VIPMonthly",0));
            add(new BaseStat("VIP",0));
            add(new BaseStat("SVIP1",0));
            add(new BaseStat("SVIP3",0));
            add(new BaseStat("SVIP6",0));
            add(new BaseStat("SVIP12",0));
            add(new BaseStat("SVIP24",0));
            add(new BaseStat("SVIPMonthly",0));
            add(new BaseStat("SVIP",0));
            add(new BaseStat("DP",0));
            add(new BaseStat("SPORTS",0));
            add(new BaseStat(OTHER,0));
        }
    };

    static List<BaseStat> payWayStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("ALI", 0));
            add(new BaseStat("WX", 0));
            add(new BaseStat("SN", 0));
            add(new BaseStat("APPLE", 0));
            add(new BaseStat("CMCC", 0));
            add(new BaseStat("UNICOM", 0));
            add(new BaseStat("TELECOM", 0));
            add(new BaseStat("JT", 0));
            add(new BaseStat("BD",0));
            add(new BaseStat(OTHER,0));
        }
    };

    static List<BaseStat> payWayPayStat = new ArrayList<BaseStat>(){
        {
            add(new BaseStat("ALI", 0));
            add(new BaseStat("WX", 0));
            add(new BaseStat("SN", 0));
            add(new BaseStat("APPLE", 0));
            add(new BaseStat("CMCC", 0));
            add(new BaseStat("UNICOM", 0));
            add(new BaseStat("TELECOM", 0));
            add(new BaseStat("JT", 0));
            add(new BaseStat("BD",0));
            add(new BaseStat(OTHER,0));
        }
    };


    static Map<String,String> appidMap = new HashMap<String,String>(){
        {
            put("com.pptv.iphoneapp","iph");
            put("PPTVSPORTSNO1","aph");
            put("com.pplive.androidphone","aph");
            put("com.pptv.ipadwebkit","ipd");
            put("pptv.client","clt");
            put("pptv.web.h5","web");
            put("pptv.web","web"); //主站
            put("pptv.wap","wap");//M站
            put("pptv.atv.launcher","atv");
            put("pptv.atv.live","atv");
        }
    };

    static Map<String,String> goodsMap = new HashMap<String,String>(){
        {
            put("DA6598948650792","VIP1");
            put("DA6590873659482","VIP3");
            put("DA8598941926380","VIP6");
            put("DA2198917598236","VIP12");
            put("DA3611979241368","VIP12");
            put("DA3899062874039","VIP24");
            put("DA4795496174523","VIPMonthly");
            put("DA9193436725109","VIPMonthly");
            put("DB0796835217408","SVIP1");
            put("DB5876871584390","SVIP3");
            put("DB6008901358942","SVIP6");
            put("DB6886803849512","SVIP12");
            put("DB7276817865940","SVIP24");
            put("DB7043954986732","SVIPMonthly");
        }
    };

    static Map<String,String> categoryMap = new HashMap<String,String>(){
        {
            put("CATEA","DP");
            put("CATEB","SPORTS");
            put("CATEC","SPORTS");
        }
    };

    static Map<String,String> payWayMap = new HashMap<String,String>(){
        {
            put("11", "ALI");
            put("12", "ALI");
            put("103", "ALI");
            put("104", "ALI");
            put("15", "ALI");
            put("16", "ALI");
            put("17", "ALI");
            put("21", "WX");
            put("22", "WX");
            put("23", "WX");
            put("24", "WX");
            put("25", "WX");
            put("28", "WX");
            put("205", "WX");
            put("206", "WX");
            put("207", "WX");
            put("208", "WX");
            put("26", "WX");
            put("7031", "SN");
            put("7032", "SN");
            put("7033", "SN");
            put("7036", "SN");
            put("7034", "SN");
            put("7035", "SN");
            put("41", "APPLE");
            put("42", "APPLE");
            put("43", "APPLE");
            put("61", "CMCC");
            put("62", "CMCC");
            put("51", "UNICOM");
            put("52", "UNICOM");
            put("53", "UNICOM");
            put("54", "UNICOM");
            put("301", "TELECOM");
            put("302", "TELECOM");
            put("7051", "JT");
            put("7052", "JT");
            put("7053", "JT");
            put("7054", "JT");
            put("91","BD");
        }
    };

    static Map<String,String> withOutAppidMap = new HashMap<String,String>(){
        {
            put("360.aphone","360.aphone");
            put("sn.ec.app","sn.ec.app");
            put("sn.ec.pc","sn.ec.pc");
            put("sn.ec.wap","sn.ec.wap");
            put("sn.tec.ppos.launcher","sn.tec.ppos.launcher");
        }
    };

    static Map<String,String> withOutPayWayMap = new HashMap<String,String>(){
        {
            put("-1","-1");
            put("-2","-2");
            put("full_diamond","full_diamond");
        }
    };

    final static String headLine = "端,商品组,商品,支付渠道,是否下单,是否支付";

    final static String OTHER = "other";
}
