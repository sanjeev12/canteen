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
     List<table> getAllTable() throws RemoteException;
     int addTable(table tbl)throws RemoteException;
     int editTable(table tbl) throws RemoteException;
     int delTable(String tableName) throws RemoteException;
     table getTable(String tableName) throws RemoteException;
     
     List<Item> getAllItem() throws RemoteException;
     int addItem(Item itm) throws RemoteException;
     int editItem(Item itm) throws RemoteException;
     int delItem(String itmName) throws RemoteException;
     Item getItem(String itmName) throws RemoteException;
     
     List<OrderList> getAllOrder() throws RemoteException;
     int addOrder(OrderList odr)throws RemoteException;
     int editOrder(OrderList odr)throws RemoteException;
     int delOrder(String tableName)throws RemoteException;
     List<OrderList> getOrderList(String tableName)throws RemoteException;
     
     boolean login(String username,String password)throws RemoteException;
    
}
