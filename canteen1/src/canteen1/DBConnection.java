package canteen1;

import java.rmi.registry.*;
import java.sql.*;
public class DBConnection 
{
    public Connection conn;
    public DBConnection()
    {
        try 
        {
            
            //Loading MYSQL Database Driver
            Class.forName("com.mysql.jdbc.Driver"); 
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","root","");
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }  
}
