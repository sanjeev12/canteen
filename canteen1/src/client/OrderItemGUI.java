/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import canteen1.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;

/**
 *
 * @author san
 */
public class OrderItemGUI  extends JFrame implements ActionListener {

    JLabel itemname, lblTno, lblcode;
    JTextField txttable, txtTno, txtcode;
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
    OrderItemGUI(String bt) {

        setLayout(null);
        itemname = new JLabel("Item Name");
        itemname.setBounds(10, 40, 100, 20);
        txttable = new JTextField();
        txttable.setBounds(110, 40, 100, 20);
        lblTno = new JLabel("Item Rate");
        lblTno.setBounds(10, 70, 100, 20);
        txtTno = new JTextField();
        txtTno.setBounds(110, 70, 100, 20);
        lblcode = new JLabel("Item Code");
        lblcode.setBounds(10, 100, 100, 20);
        txtcode = new JTextField();
        txtcode.setBounds(110, 100, 100, 20);
        btnadd = new JButton(bt);
        btnadd.setBounds(10, 130, 100, 20);
        btndel = new JButton("Delete");
        btndel.setBounds(110, 130, 100, 20);

        add(itemname);
        add(txttable);

        add(lblcode);
        add(txtcode);
        if (bt.equals("Del")) {
            add(btndel);
        }
        add(lblTno);
        add(txtTno);
        if (bt.equals("Add") || bt.equals("Update")) {
            add(btnadd);
        }
        btnadd.addActionListener(this);
        btndel.addActionListener(this);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(false);
        setSize(300, 200);
        setVisible(true);

    }

    public static void main(String[] args) {
        new OrderItemGUI("add");
    }

    void add() {
        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("Insert into orderitemlist values (null,?,?,?)");
            pstmt.setString(2, txtTno.getText());
            pstmt.setString(1, txttable.getText());
            pstmt.setString(3, txtcode.getText());
            
            itm.setItem(txtcode.getText(), txttable.getText(), ABORT);
            ServerInterface SR = (ServerInterface) Naming.lookup(ServerAddress);
            SR.addItem(itm);
            int rs = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Add Successful");
            
        } catch (Exception ex) {
            System.out.println("error on add" + ex);
        }
    }

    void update() {
        String temp1, temp2, temp3;
        temp1 = txtTno.getText();
        temp2 = txttable.getText();
        temp3 = txtcode.getText();
        if (fetch(txttable.getText()) > 0) {
            txtTno.setText(temp1);
            txttable.setText(temp2);
            txtcode.setText(temp3);
            JOptionPane.showMessageDialog(null, "Name already Exist");
        } else {
            txtTno.setText(temp1);
            txttable.setText(temp2);
            txtcode.setText(temp3);
            try {
                DBConnection dbc = new DBConnection();
                PreparedStatement pstmt = dbc.conn.prepareStatement("Update orderitemlist set Rate=?, Name =?,Code=? where Id=? ");
                pstmt.setString(1, temp1);
                pstmt.setString(2, temp2);
                pstmt.setString(3, temp3);
                pstmt.setString(4, code);
                int rs = pstmt.executeUpdate();
                System.out.println("success" + rs);
                JOptionPane.showMessageDialog(null, "Update Successful");

            } catch (Exception ex) {
                System.out.println("error on loadorderlist" + ex);
            }
        }
    }

    int fetch(String name) {
        int i = 0;
        try {
            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderitemlist where Name =?");
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                i++;
                txtTno.setText(rs.getString(3));
                txttable.setText(rs.getString(2));
                txtcode.setText(rs.getString(4));
                code = rs.getString(1);
            }

        } catch (Exception ex) {
            System.out.println("error on loadorderlist");
        }

        return i;
    }

    void del() {
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from orderitemlist where Name =?");
            pstmt.setString(1, txttable.getText());
            int i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error on del" + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnadd) {
            if (btnadd.getText().equals("Add")) {
                add();
            } else if (btnadd.getText().equals("Update")) {
                update();
            }
            setVisible(false);
        }
        if (e.getSource() == btndel) {
            del();
            JOptionPane.showMessageDialog(null, "Delete Successful");
            setVisible(false);
        }
    }
}
