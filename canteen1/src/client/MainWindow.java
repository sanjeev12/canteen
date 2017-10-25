package client;

import canteen1.*;

import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class MainWindow extends JFrame implements ActionListener, MouseListener {

    JMenu menu, itmmenu;
    JMenuBar menubar;
    JMenuItem tblAdd, tblEdit, tblDel, itmAdd, itmEdit, itmDel;
    JPanel panel1, panel2, panel3, panel4;
    JTable table1, table2, table3;
    DefaultTableModel model1, model2, model3;
    JScrollPane pane1, pane2, pane3;
    JLabel lblOrder, lblTable, lblOrderlist, lblItemlist, lblDiscount, lblDamount, lblTotal;
    JTextField txtDiscount, txtDamount, txtTotal;
    JPopupMenu popmenu1, popmenu2;
    JButton btnReserve, btnAdditm, btnPaid;
    final String ServerAddress = "//localhost/canteenserv";

    List<table> tblList = new ArrayList<>();
    table tbl;
    Item itm;
    java.util.List<Item> itmList;
    OrderList odr;
    java.util.List<OrderList> odrList;

    void databaseinit() {
        try {
            System.out.println("asdfasdf");

            ServerInterface SR = (ServerInterface) Naming.lookup("//localhost/canteenserv");
            System.out.println("asdfasdf1");

            tblList.addAll(SR.getAllTable());
            System.out.println("Table database fetch complete");
//            model1.setRowCount(0);
//            model3.setRowCount(0);
//            //add table name on table
//            for (table temp : tblList) {
//                model1.addRow(new Object[]{temp.getTblId(), temp.getTblName(), "Free"});
//            }
//            itmList = SR.getAllItem();
//            //fetch all item list
//            for (Item itm : itmList) {
//                model3.addRow(new Object[]{itm.getId(), itm.getItemName(), itm.getItemRate()});
//            }
//            table1.changeSelection(0, 0, false, false);
//            table3.changeSelection(0, 0, false, false);
        } catch (Exception ex) {
            System.out.println("error database init: " + ex);
        }
    }

    MainWindow() {
        menu = new JMenu("Table");
        itmmenu = new JMenu("Item");
        menubar = new JMenuBar();
        tblAdd = new JMenuItem("Add");
        tblEdit = new JMenuItem("Edit");
        tblDel = new JMenuItem("Del");

        itmAdd = new JMenuItem("Add");
        itmEdit = new JMenuItem("Edit");
        itmDel = new JMenuItem("Del");

        panel1 = new JPanel(new BorderLayout());
        panel2 = new JPanel(new BorderLayout());
        panel3 = new JPanel(new BorderLayout());
        panel4 = new JPanel(new GridLayout(4, 2));
        popmenu1 = new JPopupMenu();
        popmenu2 = new JPopupMenu();
        btnAdditm = new JButton("Add");
        btnReserve = new JButton("Reserve");
        btnPaid = new JButton("Paid");

        lblTable = new JLabel("Table List");
        lblOrderlist = new JLabel("Order");
        lblItemlist = new JLabel("Item List");

        lblDiscount = new JLabel("Discount %");
        lblDamount = new JLabel("Discount Amount");
        lblTotal = new JLabel("Total Amount");
        txtDiscount = new JTextField();
        txtDamount = new JTextField();
        txtTotal = new JTextField();

        //action listener
        btnAdditm.addActionListener(this);
        btnReserve.addActionListener(this);
        btnPaid.addActionListener(this);
        tblAdd.addActionListener(this);
        tblEdit.addActionListener(this);
        tblDel.addActionListener(this);

        itmAdd.addActionListener(this);
        itmEdit.addActionListener(this);
        itmDel.addActionListener(this);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        menubar.add(menu);
        menu.add(tblAdd);
        menu.add(tblEdit);
        menu.add(tblDel);

        menubar.add(itmmenu);
        itmmenu.add(itmAdd);
        itmmenu.add(itmEdit);
        itmmenu.add(itmDel);

//        popmenu1.add(tblAdd);
//        popmenu1.add(tblEdit);
//        popmenu1.add(tblDel);
//
//        popmenu2.add(itmAdd);
//        popmenu2.add(itmEdit);
//        popmenu2.add(itmDel);
        setJMenuBar(menubar);

        setLayout(null);
        add(panel1);
        panel1.setBackground(Color.red);
        panel1.setBounds(10, 10, screen.width / 3 - 20, 500);

        add(panel2);
        panel2.setBackground(Color.BLUE);
        panel2.setBounds(20 + screen.width / 3, 10, screen.width / 3 - 20, 500);

        add(panel3);
        panel3.setBackground(Color.GREEN);
        panel3.setBounds(30 + 2 * (screen.width / 3), 10, screen.width / 3 - 40, 500);

        //panel 1
        model1 = new DefaultTableModel();
        model1.addColumn("Table No.");
        model1.addColumn("Table Name");
        model1.addColumn("Status");
        model1.addColumn("Time");
        table1 = new JTable(model1);
        table1.setSize(panel1.getSize());
        table1.setFocusable(false);
        table1.setRowSelectionAllowed(true);
        table1.addMouseListener(this);
        pane1 = new JScrollPane(table1);
        pane1.setSize(panel1.getSize());
        panel1.add(lblTable, BorderLayout.NORTH);
        panel1.add(pane1, BorderLayout.CENTER);
        panel1.add(btnReserve, BorderLayout.SOUTH);
        panel1.setBorder(BorderFactory.createLineBorder(Color.black));

        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = table1.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String temp = model1.getValueAt(table1.getSelectedRow(), 2).toString();
                if (temp.equals("Reserve")) {
                    btnReserve.setText("Free");
                    lblOrderlist.setText("Order of " + model1.getValueAt(table1.getSelectedRow(), 1).toString());

                } else {
                    btnReserve.setText("Reserve");
                    lblOrderlist.setText("Order");
                }
                loadorderlist();
            }
        });

        //panel 2
        model2 = new DefaultTableModel();
        model2.addColumn("SN.");
        model2.addColumn("Particular");
        model2.addColumn("Rate");
        model2.addColumn("QTY");
        model2.addColumn("Amount");
        table2 = new JTable(model2);
        table2.setSize(panel2.getSize());
        pane2 = new JScrollPane(table2);
        pane2.setSize(panel2.getSize());
        panel2.add(pane2, BorderLayout.CENTER);
        panel2.add(lblOrderlist, BorderLayout.NORTH);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel4.add(lblDiscount);
        panel4.add(txtDiscount);
        panel4.add(lblDamount);
        txtTotal.setEditable(false);
        panel4.add(txtDamount);
        panel4.add(lblTotal);
        panel4.add(txtTotal);
        panel4.add(btnPaid);
        panel2.add(panel4, BorderLayout.SOUTH);

        //panel 3
        model3 = new DefaultTableModel();
        model3.addColumn("SN.");
        model3.addColumn("Item Name");
        model3.addColumn("Rate");
        table3 = new JTable(model3);
        table3.addMouseListener(this);

        pane3 = new JScrollPane(table3);
        panel3.add(btnAdditm, BorderLayout.SOUTH);
        panel3.add(pane3, BorderLayout.CENTER);
        panel3.add(lblItemlist, BorderLayout.NORTH);
        table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //database
        databaseinit();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(screen.width, 600);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    public static void main(String[] args) {
        new MainWindow();
    }

    void loadorderlist() {
        model2.setRowCount(0);
        try {

            DBConnection dbc = new DBConnection();
            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderlist where TableName =?");
            pstmt.setString(1, model1.getValueAt(table1.getSelectedRow(), 1).toString());
            System.out.println(model1.getValueAt(table1.getSelectedRow(), 1).toString());
            ResultSet rs = pstmt.executeQuery();
            int i = 1, sum = 0;
            while (rs.next()) {
                model2.addRow(new Object[]{i++, rs.getString(2), rs.getString(3), rs.getString(4), Integer.parseInt(rs.getString(3)) * Integer.parseInt(rs.getString(4))});
                sum = sum + Integer.parseInt(rs.getString(3)) * Integer.parseInt(rs.getString(4));
            }
            txtTotal.setText("" + sum);
        } catch (Exception ex) {
            System.out.println("error on loadorderlist");
        }

    }

    void deleteorderlist() {
        String tableName = model1.getValueAt(table1.getSelectedRow(), 1).toString();
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("delete from orderlist where TableName =?");
            pstmt.setString(1, tableName);
            int i = pstmt.executeUpdate();
        } catch (Exception e) {

        }

    }

    void addreport() {
        String tableName = model1.getValueAt(table1.getSelectedRow(), 1).toString();
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("insert into report values (null,?,?,CURRENT_TIMESTAMP)");
            pstmt.setString(1, tableName);
            pstmt.setString(2, txtTotal.getText());
            int i = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("error on addreport: " + e);
        }

    }

    void addorderlist() {
        String name = model3.getValueAt(table3.getSelectedRow(), 1).toString();
        int rate = Integer.parseInt(model3.getValueAt(table3.getSelectedRow(), 2).toString());
        int quantity = 1;
        int amount = rate * quantity;
        String tableName = model1.getValueAt(table1.getSelectedRow(), 1).toString();
        System.out.println(name + rate + quantity + amount + tableName);
        try {
            DBConnection dbc = new DBConnection();

            PreparedStatement pstmt = dbc.conn.prepareStatement("select * from orderlist where TableName =? and Name=?");
            pstmt.setString(1, tableName);
            pstmt.setString(2, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.first()) {

                pstmt = dbc.conn.prepareStatement("UPDATE orderlist SET QTY=? where Name=?");
                quantity = Integer.parseInt(rs.getString(4)) + 1;
                System.out.println("here" + quantity);
                pstmt.setString(1, Integer.toString(quantity));
                pstmt.setString(2, name);
                int a = pstmt.executeUpdate();
            } else {
                System.out.println("there");
                pstmt = dbc.conn.prepareStatement("insert into orderlist values (null,?,?,?,?,?)");
                pstmt.setString(1, name);
                pstmt.setString(2, Integer.toString(rate));
                pstmt.setString(3, Integer.toString(quantity));
                pstmt.setString(4, Integer.toString(amount));
                pstmt.setString(5, tableName);
                int i = pstmt.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("error on addorderlist: " + e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPaid) {
            int dialogue = JOptionPane.showConfirmDialog(null, "Bill Paid?", "Bill conformation", JOptionPane.YES_NO_OPTION);
            if (dialogue == 0) {
                addreport();
                model1.setValueAt("Free", table1.getSelectedRow(), 2);
                deleteorderlist();
                loadorderlist();

            }

        }
        if (e.getSource() == btnReserve) {
            String temp = btnReserve.getText();
            model1.setValueAt(temp, table1.getSelectedRow(), 2);
            if (temp.equals("Reserve")) {
                temp = "Free";
                loadorderlist();
                lblOrderlist.setText("Order of " + model1.getValueAt(table1.getSelectedRow(), 1).toString());

            } else {
                int dialogue = JOptionPane.showConfirmDialog(null, "Do you want to free Table", "Free Table Conformation", JOptionPane.YES_NO_OPTION);
                if (dialogue == 0) {
                    temp = "Reserve";
                    lblOrderlist.setText("Order");
                    deleteorderlist();
                    loadorderlist();
                }
            }
            btnReserve.setText(temp);

        }

        if (e.getSource() == btnAdditm) {

            model1.setValueAt("Reserve", table1.getSelectedRow(), 2);
            addorderlist();
            loadorderlist();

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == table1) {
            if (e.getButton() == 3) {
                table1.setRowSelectionInterval(table1.rowAtPoint(e.getPoint()), table1.rowAtPoint(e.getPoint()));
                popmenu1.show(table1, e.getX(), e.getY());
            }
        }
        if (e.getSource() == table3) {
            if (e.getButton() == 3) {
                table3.setRowSelectionInterval(table3.rowAtPoint(e.getPoint()), table3.rowAtPoint(e.getPoint()));
                popmenu2.show(table3, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
