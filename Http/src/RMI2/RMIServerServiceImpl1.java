/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class RMIServerServiceImpl1 extends UnicastRemoteObject
        implements RMIServerService1 {

    public RMIServerServiceImpl1() throws Exception{
        
    }
    @Override
    public String echo(String msg) throws RemoteException {
        return "来自张茂盛：" + msg;
    }

    @Override
    public String echo(String yourNo, byte[] yourName) throws RemoteException {
        return yourNo;
    }

    @Override
    public Date getTime() throws Exception {
        return new Date();
    }
}
