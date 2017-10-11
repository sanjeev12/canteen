/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen1;

import java.rmi.*;
import java.util.List;

/**
 *
 * @author san
 */
public interface ServerInterface extends Remote{
     List<table> getAllTable();
     int addTable(table tbl);
     int editTable(table tbl);
     int delTable(String tableName);
     table getTable(String tableName);
     
     List<Item> getAllItem();
     int addItem(Item itm);
     int editItem(Item itm);
     int delItem(String itmName);
     Item getItem(String itmName);
     
     List<OrderList> getAllOrder();
     int addOrder(OrderList odr);
     int editOrder(OrderList odr);
     int delOrder(String tableName);
     List<OrderList> getOrderList(String tableName);
     
     boolean login(String username,String password);
    
}
