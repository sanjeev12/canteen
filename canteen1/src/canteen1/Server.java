/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author san
 */
public class Server implements ServerInterface {

    List<table> tblList;
    table tbl;

    @Override
    public List<table> getAllTable() {
        tblList.clear();
        tbl = null;

        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from tableinfo");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tbl.setTable(rs.getString(3), rs.getString(4), Integer.parseUnsignedInt(rs.getString(2)));
                tbl.setTblId(Integer.parseInt(rs.getString(1)));
                tblList.add(tbl);
            }
        } catch (NumberFormatException | SQLException ex) {
            System.out.println("error on table info fetch all" + ex);
        }
        return tblList;
    }

    @Override
    public int addTable(table tbl) {
        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Insert into tableinfo values (null,?,?,?)");
            pstmt.setString(1, String.valueOf(tbl.getTblSize()));
            pstmt.setString(2, tbl.getTableName());
            pstmt.setString(3, tbl.getTblCode());
            int rs = pstmt.executeUpdate();
            System.out.println("successfully added table info");
            return 1;

        } catch (SQLException ex) {
            System.out.println("error on adding table" + ex);
            return 0;
        }
    }

    @Override
    public int editTable(table tbl) {
        if (getTable(tbl.getTableName()).equals(tbl)) {
            return 2;
        } else {
            try {
                DBConnection dbc = new DBConnection();
                PreparedStatement pstmt = dbc.conn.prepareStatement("Update tableinfo set Size=?, TableName =? where Id=? ");
                pstmt.setString(1, String.valueOf(tbl.getTblSize()));
                pstmt.setString(2, tbl.getTableName());
                pstmt.setString(3, String.valueOf(tbl.getTblId()));
                int rs = pstmt.executeUpdate();
                System.out.println("success" + rs);
                return 1;

            } catch (SQLException ex) {
                System.out.println("error on editing table" + ex);
                return 0;
            }
        }
    }

    @Override
    public int delTable(String tableName) {
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from tableinfo where TableName =?");
            pstmt.setString(1, tableName);
            int i = pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("error on del Table" + e);
            return 0;
        }
    }

    @Override
    public table getTable(String tableName) {
        tbl=null;
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from tableinfo where TableName =?");
            pstmt.setString(1, tableName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tbl.setTable(rs.getString(3),rs.getString(4),Integer.parseInt(rs.getString(2)));
                tbl.setTblId(Integer.parseInt(rs.getString(1)));
                }
            return tbl;

        } catch (NumberFormatException | SQLException ex) {
            System.out.println("error on loadorderlist");
            return null;
        }
    }

    @Override
    public List<Item> getAllItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addItem(Item itm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editItem(Item itm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delItem(Item itm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrderList> getAllOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int addOrder(OrderList odr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int editOrder(OrderList odr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delOrder(OrderList odr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
