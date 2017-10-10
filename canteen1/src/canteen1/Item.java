package canteen1;
public class Item {

    private String itmName, itmCode;
    private int itmRate, itmQuantity;

    Item() {
    }

    Item(String itmCode, String itmName, int itmRate, int itmQuantity) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.itmRate = itmRate;
        this.itmQuantity = itmQuantity;
    }

    void setItem(String itmCode, String itmName, int itmRate, int itmQuantity) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.itmRate = itmRate;
        this.itmQuantity = itmQuantity;
    }

    String getItemCode() {
        return itmCode;
    }

    String getItemName() {
        return itmName;
    }

    int getItemRate() {
        return itmRate;
    }

    int getItemQuantity() {
        return itmQuantity;
    }
}


//class order list 
 class OrderList extends Item {
    private String tableName;
 OrderList(){};

    public OrderList(String tableName,String itemName,String itemCode,int rate,int itemQuantity){
        this.tableName=tableName;
        super.setItem(itemCode, itemName, rate, itemQuantity);
    }

    public String getTableName() {
        return tableName;
    }
    
            
 
}