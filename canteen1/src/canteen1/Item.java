package canteen1;

public class Item {

    private String itmName, itmCode;
    private int itmRate,id;

    Item() {
    }

    Item(String itmCode, String itmName, int itmRate) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.itmRate = itmRate;
    }
    
    void setItem(String itmCode, String itmName, int itmRate) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.itmRate = itmRate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setItemCode(String itmCode) {
        this.itmCode = itmCode;
    }

    public void setItemName(String itmName) {
        this.itmName = itmName;
    }

    public void setItemRate(int itmRate) {
        this.itmRate = itmRate;
    }
    

}
