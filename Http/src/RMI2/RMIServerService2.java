/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Administrator
 */
  public interface RMIServerService2 extends Remote{
       public String invertString(String msg) throws RemoteException;    
}
