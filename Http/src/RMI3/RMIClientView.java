/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI3;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Administrator
 */
public interface RMIClientView extends Remote{      
   public void showMessageToClient(String msg)throws RemoteException;
}

