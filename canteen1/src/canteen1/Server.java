/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerInterface {

    List<table> tblList;
    table tbl;
    Item itm;
    List<Item> itmList;
    OrderList odr;
    List<OrderList> odrList;
    
    public Server()throws RemoteException
    {
        super();
    }

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
        tbl = null;
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from tableinfo where TableName =?");
            pstmt.setString(1, tableName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                tbl.setTable(rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(2)));
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
        itm = null;
        itmList.clear();
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderitemlist");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                itm.setItem(rs.getString(1), rs.getString(2), rs.getInt(3));
                itm.setId(rs.getInt(1));
                itmList.add(itm);
            }
            return itmList;

        } catch (SQLException ex) {
            System.out.println("error on loadorderlist");
            return null;
        }
    }

    @Override
    public int addItem(Item itm) {
        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Insert into orderitemlist values (null,?,?,?)");
            pstmt.setString(1, itm.getItemCode());
            pstmt.setString(2, itm.getItemName());
            pstmt.setInt(3, itm.getItemRate());

            int rs = pstmt.executeUpdate();
            System.out.println("Adding if item Successful");
            return 1;

        } catch (SQLException ex) {
            System.out.println("error on add" + ex);
            return 0;
        }
    }

    @Override
    public int editItem(Item itm) {
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Update orderitemlist set Rate=?, Name =?,Code=? where Id=? ");
            pstmt.setInt(1, itm.getItemRate());
            pstmt.setString(2, itm.getItemName());
            pstmt.setString(3, itm.getItemCode());
            pstmt.setInt(4, itm.getId());
            int rs = pstmt.executeUpdate();
            System.out.println("successfully updated item" + rs);
            return 1;

        } catch (SQLException ex) {
            System.out.println("error on loadorderlist" + ex);
            return 0;
        }
    }

    @Override
    public int delItem(String itmName) {
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from orderitemlist where Name =?");
            pstmt.setString(1, itmName);
            int i = pstmt.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("error on del" + e);
            return 0;
        }
    }

    @Override
    public Item getItem(String itmName) {
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderitemlist where Name =?");
            pstmt.setString(1, itmName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                itm.setItem(rs.getString(2), rs.getString(3), rs.getInt(4));
                itm.setId(1);
            }
            return itm;

        } catch (SQLException ex) {
            System.out.println("error on loadorderlist");
            return null;
        }
    }

    @Override
    public List<OrderList> getAllOrder() {
        odr = null;
        odrList.clear();
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderlist");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                odr.setOrderList(rs.getString(6), rs.getString(2), null, rs.getInt(3), rs.getInt(4));
                odr.setId(rs.getInt(1));
                odrList.add(odr);
            }
            return odrList;
        } catch (SQLException ex) {
            System.out.println("error on loadorderlist");
            return null;
        }
    }

    @Override
    public int addOrder(OrderList odr) {
        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Insert into orderlist values (null,?,?,?,?,?)");
            pstmt.setString(1, odr.getItemName());
            pstmt.setInt(2, odr.getItemRate());
            pstmt.setInt(3, odr.getOderQuantity());
            pstmt.setInt(4, odr.getItemRate() * odr.getOderQuantity());
            pstmt.setString(5, odr.getTableName());

            int rs = pstmt.executeUpdate();
            System.out.println("Adding if order Successful");
            return 1;

        } catch (SQLException ex) {
            System.out.println("error on add" + ex);
            return 0;
        }
    }

    @Override
    public int editOrder(OrderList odr) {
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Update orderlist set Rate=?, Name =?, QTY=?, amount=?, TableName=?   where SN=? ");
            pstmt.setInt(1, odr.getItemRate());
            pstmt.setString(2, odr.getItemName());
            pstmt.setInt(3, odr.getOderQuantity());
            pstmt.setInt(4, odr.getItemRate() * odr.getOderQuantity());
            pstmt.setString(5, odr.getTableName());
            int rs = pstmt.executeUpdate();
            System.out.println("successfully updated order" + rs);
            return 1;

        } catch (SQLException ex) {
            System.out.println("error on loadorderlist" + ex);
            return 0;
        }
    }

    @Override
    public List<OrderList> getOrderList(String tableName) {
        odrList.clear();
        odr = null;
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderlist where TableName =?");
            pstmt.setString(1, tableName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                odr.setOrderList(rs.getString(6), rs.getString(2), null, rs.getInt(3), rs.getInt(4));
                odr.setId(rs.getInt(1));
                odrList.add(odr);
            }
            return odrList;

        } catch (NumberFormatException | SQLException ex) {
            System.out.println("error on loadorderlist");
            return null;
        }
    }

    @Override
    public int delOrder(String tableName) {
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from orderlist where TableName =?");
            pstmt.setString(1, tableName);
            int i = pstmt.executeUpdate();
            return i;
        } catch (SQLException e) {
            System.out.println("error on del" + e);
            return 0;
        }
    }

    @Override
    public boolean login(String username, String password) {
        try {
            System.out.println(username+ " "+password);

            DBConnection db = new DBConnection();
            PreparedStatement pstmt = db.conn.prepareStatement("select * from logindb where Username=? AND Password=?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;//success login
            } else {
                //unsuccess Login
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("error "+ex);
            return false;
        }
    }

    
    public static void main(String[] args) throws RemoteException {
        try {
            Registry r = LocateRegistry.createRegistry(1099);
            r.rebind("canteenserv", new Server());
            System.out.println("server registered");
        } catch (RemoteException e) {
            System.out.println("error " + e);
        }
    }
}
