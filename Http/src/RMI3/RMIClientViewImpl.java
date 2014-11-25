/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Administrator
 */
public class RMIClientViewImpl extends UnicastRemoteObject
        implements RMIClientView {

    RMIClientJFrame frame;

    public RMIClientViewImpl(RMIClientJFrame frame) throws Exception {
        this.frame = frame;
    }

    public void showMessageToClient(String msg) throws RemoteException {
        frame.appendMessageToArea(msg + "\n");
    }
}