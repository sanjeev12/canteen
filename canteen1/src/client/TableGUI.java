package client;

import canteen1.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class TableGUI extends JFrame implements ActionListener {

    JLabel tablename, lblTno;
    JTextField txttable, txtTno;
    JButton btnadd, btndel;
    JComboBox cmb;
    String code = "1";
    final String ServerAddress = "//localhost/canteenserv";

    List<table> tblList = new ArrayList<>();
    table tbl;
    Item itm;
    java.util.List<Item> itmList;
    OrderList odr;
    java.util.List<OrderList> odrList;

    TableGUI() {
        cmb = new JComboBox();
        tablename = new JLabel("Table Name");
        tablename.setBounds(10, 40, 100, 20);
        txttable = new JTextField();
        txttable.setBounds(110, 40, 100, 20);
        lblTno = new JLabel("Table Capacity");
        lblTno.setBounds(10, 70, 100, 20);
        txtTno = new JTextField();
        txtTno.setBounds(110, 70, 100, 20);
        btnadd = new JButton("Update");
        btnadd.setBounds(10, 100, 100, 20);
        btndel = new JButton("Delete");
        btndel.setBounds(110, 100, 100, 20);
        add(tablename);
        add(txttable);
        add(btnadd);
        add(btndel);
        add(lblTno);
        add(txtTno);
        init();
        add(cmb);

        cmb.setBounds(110, 10, 130, 20);
        cmb.addActionListener(this);
        btnadd.addActionListener(this);
        btndel.addActionListener(this);

        setLayout(null);
        setVisible(true);
        setSize(300, 200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new TableGUI();
    }

    void init() {
        //cmb.removeAllItems();
        try {
//            DBConnection dbc = new DBConnection();
//            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from tableinfo");
//            ResultSet rs = pstmt.executeQuery();

            ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
            tblList = SR.getAllTable();
            for (table temp : tblList) {
                cmb.addItem(temp.getTblName());
                //System.out.println(rs.getString(3));
            }
            cmb.addItem("New*");
            if (cmb.getItemCount() > 1) {
                fetch(cmb.getSelectedItem().toString());
            }
            if (cmb.getSelectedItem().toString().equals("New*")) {
                btnadd.setText("Add");
            } else {
                btnadd.setText("Update");
            }

        } catch (Exception ex) {
            System.out.println("error on init" + ex);
        }

    }

    void add() {
        try {

//            DBConnection dbc = new DBConnection();
//            PreparedStatement pstmt = dbc.conn.prepareStatement("Insert into tableinfo values (null,?,?,0)");
//            pstmt.setString(1, txtTno.getText());
//            pstmt.setString(2, txttable.getText());
//            int rs = pstmt.executeUpdate();
            tbl = new table();
            tbl.setTable(txttable.getText(), "0", Integer.parseInt(txtTno.getText()));
            ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
            SR.addTable(tbl);
            JOptionPane.showMessageDialog(null, "Add Successful");

        } catch (Exception ex) {
            System.out.println("error on add" + ex);
        }
    }

    void update() {
        String temp1, temp2;
        temp1 = txtTno.getText();
        temp2 = txttable.getText();
        if (fetch(txttable.getText()) > 0) {
            txtTno.setText(temp1);
            txttable.setText(temp2);
            JOptionPane.showMessageDialog(null, "Name already Exist");
        } else {
            txtTno.setText(temp1);
            txttable.setText(temp2);
            try {
                table temp = new table(temp2, "");
                temp.setTblId(Integer.parseInt(temp2));

                ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
                SR.editTable(tbl);

//                DBConnection dbc = new DBConnection();
//                PreparedStatement pstmt = dbc.conn.prepareStatement("Update tableinfo set Size=?, TableName =? where Id=? ");
//                pstmt.setString(1, temp1);
//                pstmt.setString(2, temp2);
//                pstmt.setString(3, code);
//                int rs = pstmt.executeUpdate();
//                System.out.println("success" + rs);
                JOptionPane.showMessageDialog(null, "Update Successful");

            } catch (Exception ex) {
                System.out.println("error on loadorderlist" + ex);
            }
        }
    }

    int fetch(String name) {
        int i = 0;
        if (cmb.getSelectedItem().toString().equals("New*")) {
        } else {
            try {
//                DBConnection dbc = new DBConnection();
//                PreparedStatement pstmt = dbc.conn.prepareStatement("select * from tableinfo where TableName =?");
//                pstmt.setString(1, name);
//                ResultSet rs = pstmt.executeQuery();

                ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
                tbl = SR.getTable(name);
                txtTno.setText(String.valueOf(tbl.getTblSize()));
                txttable.setText(tbl.getTableName());
                code = tbl.getTblCode();

            } catch (Exception ex) {
                System.out.println("error on loadorderlist");
            }
        }
        return i;
    }

    void del() {
        try {
//            DBConnection dbc = new DBConnection();
//
//            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from tableinfo where TableName =?");
//            pstmt.setString(1, cmb.getSelectedItem().toString());
//            int i = pstmt.executeUpdate();

            ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
            SR.delTable(cmb.getSelectedItem().toString());
        } catch (Exception e) {
            System.out.println("error on del" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmb) {
            if (cmb.getItemCount() > 1) {
                if (cmb.getSelectedItem().toString().equals("New*")) {
                    txtTno.setText("");
                    btnadd.setText("Add");
                    txttable.setText("");
                } else {
                    btnadd.setText("Update");
                    fetch(cmb.getSelectedItem().toString());
                    System.out.println("success");
                }
            }

        }
        if (e.getSource() == btnadd) {
            if (btnadd.getText() == "Add") {
                add();
            } else if (btnadd.getText() == "Update") {
                update();
            }
            for (int i = 0; i < cmb.getItemCount(); i++) {
                System.out.println("removed " + i);
                cmb.removeItemAt(i);

            }

            init();
            // cmb.addItem("New*");
            //cmb.removeItemAt(0);

        }
        if (e.getSource() == btndel) {
            if (cmb.getSelectedItem().toString().equals("New*")) {
                //   JOptionPane.showMessageDialog(null, "cannot delete new");
            } else {
                del();
                System.out.println("fghjkl");
                //cmb.removeAllItems();
                for (int i = 0; i < cmb.getItemCount(); i++) {
                    cmb.removeItemAt(i);
                }
                System.out.println("fghjkl");
                init();
                // cmb.addItem("New*");
                // cmb.removeItemAt(0);
            }
        }
    }

}
