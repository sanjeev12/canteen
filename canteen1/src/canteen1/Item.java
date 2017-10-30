package canteen1;

import java.io.Serializable;

public class Item implements Serializable{

    private String itmName, itmCode;
    private int itmRate, id;

    Item() {
    }

    public Item(String itmCode, String itmName, int itmRate) {
        this.itmCode = itmCode;
        this.itmName = itmName;
        this.itmRate = itmRate;
    }

    public void setItem(String itmCode, String itmName, int itmRate) {
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

    public String getItemCode() {
        return itmCode;
    }

    public String getItemName() {
        return itmName;
    }

    public int getItemRate() {
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
