/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen1;

import java.rmi.RemoteException;
import java.rmi.registry.*;

/**
 *
 * @author sanjeev
 */
public class CanteenServer {

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
