package org.zy.mytools.exec;

import org.zy.mytools.domain.OrderExportInfo;
import org.zy.mytools.util.CsvUtil;
import org.zy.mytools.util.StringUtil;

import java.io.BufferedReader;
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
//            "/Users/yuezhang/Downloads/orderStat/DDPOS-53-59.csv",
//            "/Users/yuezhang/Downloads/orderStat/DDPOS-JJC-53-59.csv"
        "/Users/yuezhang/Downloads/orderStat/DDPOS-426-502.csv",
        "/Users/yuezhang/Downloads/orderStat/DDPOS-JJC-426-502.csv"
    };


    static Map<String,Integer> apppltStat = new HashMap<String,Integer>(){
        {
            put("iph",0);
            put("aph",0);
            put("ipd",0);
            put("clt",0);
            put("web",0);
            put("wap",0);
            put("atv",0);
//            put("atvos","PPOS");
            put("other",0);
        }
    };

    static Map<String,Integer> apppltPayStat = new HashMap<String,Integer>(){
        {
            put("iph",0);
            put("aph",0);
            put("ipd",0);
            put("clt",0);
            put("web",0);
            put("wap",0);
            put("atv",0);
//            put("atvos","PPOS");
            put("other",0);
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
//            put("sn.tec.ppos.launcher","atvos");
        }
    };

    static Map<String,Integer> goodsStat = new HashMap<String,Integer>(){
        {
            put("VIP1",0);
            put("VIP3",0);
            put("VIP6",0);
            put("VIP12",0);
            put("VIP24",0);
            put("VIPMonthly",0);
            put("SVIP1",0);
            put("SVIP3",0);
            put("SVIP6",0);
            put("SVIP12",0);
            put("SVIP24",0);
            put("SVIPMonthly",0);
            put("DP",0);
            put("SPORTS",0);
            put("other",0);

            put("VIP",0);
            put("SVIP",0);
        }
    };

    static Map<String,Integer> goodsPayStat = new HashMap<String,Integer>(){
        {
            put("VIP1",0);
            put("VIP3",0);
            put("VIP6",0);
            put("VIP12",0);
            put("VIP24",0);
            put("VIPMonthly",0);
            put("SVIP1",0);
            put("SVIP3",0);
            put("SVIP6",0);
            put("SVIP12",0);
            put("SVIP24",0);
            put("SVIPMonthly",0);
            put("DP",0);
            put("SPORTS",0);
            put("other",0);

            put("VIP",0);
            put("SVIP",0);
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

//            put("DB2638601673284","SVIP7day");// biuos大会员7天
//            put("DB8057187120653","SVIP1"); // biuos大会员
//            put("DB1287180352741","SVIP3"); // biuos大会员
//            put("DB7367107564392","SVIP12");// biuos大会员
//            put("DB7618602867439","SVIP13");// biuos大会员

        }
    };

    static Map<String,String> categoryMap = new HashMap<String,String>(){
        {
            put("CATEA","DP");
            put("CATEB","SPORTS");
            put("CATEC","SPORTS");
        }
    };

    static Map<String,Integer> payWayStat = new HashMap<String,Integer>(){
        {
            put("ALI", 0);
            put("WX", 0);
            put("SN", 0);
            put("APPLE", 0);
            put("CMCC", 0);
            put("UNICOM", 0);
            put("TELECOM", 0);
            put("JT", 0);
            put("BD",0);
            put("other",0);
        }
    };

    static Map<String,Integer> payWayPayStat = new HashMap<String,Integer>(){
        {
            put("ALI", 0);
            put("WX", 0);
            put("SN", 0);
            put("APPLE", 0);
            put("CMCC", 0);
            put("UNICOM", 0);
            put("TELECOM", 0);
            put("JT", 0);
            put("BD",0);
            put("other",0);
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

                    String withOutAppid = withOutAppidMap.get(orderInfo.getAppid());
                    if (StringUtil.isNotEmpty(withOutAppid)){
                        // 排除掉
                    }else {
                        orderList.add(orderInfo);
                    }

                }
                System.out.println(readChannelUrl[i]+"流水表格中所有行数："+csvLength);
                System.out.println();

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


        // =======================端统计=======================

        for(OrderExportInfo order : orderList){
            String appplt = appidMap.get(order.getAppid());
            if (StringUtil.isNotEmpty(appplt)){
                // 下单量
                int count = apppltStat.get(appplt);
                apppltStat.put(appplt,count+1);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = apppltPayStat.get(appplt);
                    apppltPayStat.put(appplt,payCount+1);
                }

            }else{
//                System.out.println("其他端有：" + order.getAppid());
                // 下单量
                int count = apppltStat.get("other");
                apppltStat.put("other",count+1);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = apppltPayStat.get("other");
                    apppltPayStat.put("other",payCount+1);
                }
            }
        }
        int apppltTotalCount = 0;
        int apppltPayTotalCount = 0;
        for (String key : apppltStat.keySet()) {
            int count = apppltStat.get(key);
            int payCount = apppltPayStat.get(key);
            apppltTotalCount += count;
            apppltPayTotalCount += payCount;
            System.out.println("端： " + key + "  下单量：" + count + "  支付量：" + payCount);
        }
        System.out.println("端总计： 下单量：" + apppltTotalCount + "   支付量：" + apppltPayTotalCount);
        System.out.println();
        System.out.println();



        //=======================商品统计=======================

        for(OrderExportInfo order : orderList){
            String goods = goodsMap.get(order.getGoodsNo());
            if (StringUtil.isNotEmpty(goods)){
                // 下单量
                int count = goodsStat.get(goods);
                goodsStat.put(goods,count+1);

                // VIP和SVIP的下单总量
                if (goods.startsWith("VIP")){
                    int c = goodsStat.get("VIP");
                    goodsStat.put("VIP",c+1);
                }else if (goods.startsWith("SVIP")){
                    int c = goodsStat.get("SVIP");
                    goodsStat.put("SVIP",c+1);
                }

                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = goodsPayStat.get(goods);
                    goodsPayStat.put(goods,payCount+1);

                    // VIP和SVIP的支付总量
                    if (goods.startsWith("VIP")){
                        int c = goodsPayStat.get("VIP");
                        goodsPayStat.put("VIP",c+1);
                    }else if (goods.startsWith("SVIP")){
                        int c = goodsPayStat.get("SVIP");
                        goodsPayStat.put("SVIP",c+1);
                    }
                }

            }else{
                // 商品大类
                String category = categoryMap.get(order.getRightsCategory());
                if (StringUtil.isNotEmpty(category)){
                    // 下单量
                    int count = goodsStat.get(category);
                    goodsStat.put(category,count+1);
                    // 支付量
                    if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                        int payCount = goodsPayStat.get(category);
                        goodsPayStat.put(category,payCount+1);
                    }

                }else {
                    // 下单量
                    int count = goodsStat.get("other");
                    goodsStat.put("other",count+1);
                    // 支付量
                    if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                        int payCount = goodsPayStat.get("other");
                        goodsPayStat.put("other",payCount+1);
                    }
                }
            }
        }
        int gooodsTotalCount = 0;
        int goodsPayTotalCount = 0;
        for (String key : goodsStat.keySet()) {
            int count = goodsStat.get(key);
            int payCount = goodsPayStat.get(key);
            if (!"VIP".equals(key) && !"SVIP".equals(key)){
                gooodsTotalCount += count;
                goodsPayTotalCount += payCount;
            }

            System.out.println("商品： " + key + "  下单量：" + count + "  支付量：" + payCount);
        }
        System.out.println("商品总计： 下单量：" + gooodsTotalCount + "   支付量：" + goodsPayTotalCount);
        System.out.println();
        System.out.println();

        //=======================支付方式统计=======================

        for(OrderExportInfo order : orderList){
            String payChannel = payWayMap.get(order.getPayWay());
            if (StringUtil.isNotEmpty(payChannel)){
                // 下单量
                int count = payWayStat.get(payChannel);
                payWayStat.put(payChannel,count+1);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = payWayPayStat.get(payChannel);
                    payWayPayStat.put(payChannel,payCount+1);
                }

            }else{
                // 下单量
                int count = payWayStat.get("other");
                payWayStat.put("other",count+1);
                // 支付量
                if ("已支付".equals(order.getStatus()) || "已发放".equals(order.getStatus()) || "已支付未加权益".equals(order.getStatus())){
                    int payCount = payWayPayStat.get("other");
                    payWayPayStat.put("other",payCount+1);
                }
            }
        }
        int payWayTotalCount = 0;
        int payWayPayTotalCount = 0;
        for (String key : payWayStat.keySet()) {
            int count = payWayStat.get(key);
            int payCount = payWayPayStat.get(key);
            payWayTotalCount += count;
            payWayPayTotalCount += payCount;
            System.out.println("支付方式： " + key + "  下单量：" + count + "  支付量：" + payCount);
        }
        System.out.println("支付方式总计： 下单量：" + payWayTotalCount + "   支付量：" + payWayPayTotalCount);
        System.out.println();
    }

}
