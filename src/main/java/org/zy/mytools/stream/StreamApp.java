package org.zy.mytools.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName StreamApp
 * @Description TODO
 * @Author ZhangYue
 * @Date 2021/3/4 10:34
 **/
public class StreamApp {

    public static void main(String [] args){
        List<StockInfoEntity> stockList = getStockList();

        List<StockInfoEntity> list = stockList.stream().collect(Collectors.groupingBy(stockInfo -> stockInfo.getShopId() + stockInfo.getLogCode()))
                .values().stream().map(stockInfoArray -> {
                    StockInfoEntity stockInfo = stockInfoArray.get(0);
                    stockInfo.setAmount(stockInfoArray.stream().mapToDouble(StockInfoEntity::getAmount).sum());
                    return stockInfo;
        }).collect(Collectors.toList());

        list.forEach(stock -> System.out.println("shopId: " + stock.getShopId() + "  logCode: " + stock.getLogCode() + " amount: " + stock.getAmount()));
    }

    private static List<StockInfoEntity> getStockList(){
        List<StockInfoEntity> stockList = new ArrayList<>();
        StockInfoEntity entity1 = new StockInfoEntity();
        entity1.setShopId("shopId001");
        entity1.setLogCode("logCode001");
        entity1.setAmount(10D);
        stockList.add(entity1);

        StockInfoEntity entity2 = new StockInfoEntity();
        entity2.setShopId("shopId002");
        entity2.setLogCode("logCode002");
        entity2.setAmount(20D);
        stockList.add(entity2);

        StockInfoEntity entity3 = new StockInfoEntity();
        entity3.setShopId("shopId001");
        entity3.setLogCode("logCode001");
        entity3.setAmount(30D);
        stockList.add(entity3);

        StockInfoEntity entity4 = new StockInfoEntity();
        entity4.setShopId("shopId001");
        entity4.setLogCode("logCode004");
        entity4.setAmount(40D);
        stockList.add(entity4);

        StockInfoEntity entity5 = new StockInfoEntity();
        entity5.setShopId("shopId005");
        entity5.setLogCode("logCode005");
        entity5.setAmount(10D);
        stockList.add(entity5);

        return stockList;
    }
}
