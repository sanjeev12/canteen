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

