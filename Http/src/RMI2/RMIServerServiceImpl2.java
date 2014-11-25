/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Administrator
 */
public class RMIServerServiceImpl2 extends UnicastRemoteObject
        implements RMIServerService2 {

    public RMIServerServiceImpl2() throws Exception {
    }

    public String invertString(String msg) throws RemoteException {
        int len = msg.length();
        String result="";
        for(int i=len-1;i>=0;i--){
            result+=msg.charAt(i);
        }
        return result;
    }
}
