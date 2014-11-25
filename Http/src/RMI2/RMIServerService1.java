/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public interface RMIServerService1 extends Remote{
    public String echo(String msg) throws RemoteException;
    public String echo(String yourNo,byte[] yourName) throws RemoteException;
    public Date getTime() throws Exception;       
}
