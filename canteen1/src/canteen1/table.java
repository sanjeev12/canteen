package canteen1;

public class table {

    private String tblName, tblCode;

    table() {

    }

    table(String tblName, String tblCode) {
        this.tblName = tblName;
        this.tblCode = tblCode;
    }

    void setTable(String tblName, String tblCode) {
        this.tblName = tblName;
        this.tblCode = tblCode;
    }

    String getTableName() {
        return tblName;
    }

    String getTableCode() {
        return this.tblCode;
    }

}

class Item {

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
