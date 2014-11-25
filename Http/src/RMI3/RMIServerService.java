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
public interface RMIServerService extends Remote {

    public String echo(String msg) throws RemoteException;

    public String addClientToOnLine(RMIClientView client, String name)
            throws RemoteException;

    public String delClientFromOnLine(RMIClientView client) throws RemoteException;

    public void sendMessageToServer(String msg) throws RemoteException;
}
