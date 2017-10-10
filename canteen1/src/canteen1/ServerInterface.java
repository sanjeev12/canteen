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
     String getprin();
     List<table> getAllTable();
     int addTable(table tbl);
     int editTable(table tbl);
     int delTable(table tbl);
     table getTable(String tableName);
     
     
     
     
    
}
