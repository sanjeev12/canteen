package canteen1;

public class table {

    private String tblName, tblCode;
    private int tblId,tblSize;

    public table() {

    }

    public table(String tblName, String tblCode) {
        this.tblName = tblName;
        this.tblCode = tblCode;
    }

    public void setTable(String tblName, String tblCode,int tblSize) {
        this.tblName = tblName;
        this.tblCode = tblCode;
        this.tblSize=tblSize;
    }

    public String getTableName() {
        return tblName;
    }

    public String getTableCode() {
        return this.tblCode;
    }

    public void setTblSize(int tblSize) {
        this.tblSize = tblSize;
    } 

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public void setTblId(int tblId) {
        this.tblId = tblId;
    }

    public void setTblCode(String tblCode) {
        this.tblCode = tblCode;
    }

    public String getTblCode() {
        return tblCode;
    }

    public int getTblId() {
        return tblId;
    }

    public String getTblName() {
        return tblName;
    }

    public int getTblSize() {
        return tblSize;
    }
   
}

