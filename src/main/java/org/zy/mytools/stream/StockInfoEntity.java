package org.zy.mytools.stream;

/**
 * @ClassName StockInfoEntity
 * @Description TODO
 * @Author ZhangYue
 * @Date 2021/3/4 10:33
 **/
public class StockInfoEntity {

    private Integer id;

    private String tenantId;

    private String shopId;

    private String storeDate;

    private String godsId;

    private String barcode;

    private String smallBarcode;

    private String logCode;

    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }

    public String getGodsId() {
        return godsId;
    }

    public void setGodsId(String godsId) {
        this.godsId = godsId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSmallBarcode() {
        return smallBarcode;
    }

    public void setSmallBarcode(String smallBarcode) {
        this.smallBarcode = smallBarcode;
    }

    public String getLogCode() {
        return logCode;
    }

    public void setLogCode(String logCode) {
        this.logCode = logCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
