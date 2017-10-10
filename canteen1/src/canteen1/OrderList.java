package canteen1;
//class order list 

public class OrderList extends Item {

    private String tableName;
    private int odrQuantity,id;

    OrderList() {
    }

    ;

  

    public OrderList(String tableName, String itemName, String itemCode, int rate, int itemQuantity) {
        this.tableName = tableName;
        this.odrQuantity = itemQuantity;
        super.setItem(itemCode, itemName, rate);
    }

    public void setOderQuantity(int odrQuantity) {
        this.odrQuantity = odrQuantity;
    }

    public int getOderQuantity() {
        return odrQuantity;
    }
    
    public void setItemQuantity(int itemQuantity) {
        this.odrQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return odrQuantity;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
