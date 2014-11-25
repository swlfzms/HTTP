/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class RMIClientServiceImpl extends UnicastRemoteObject
        implements RMIServerService {

    public RMIClientServiceImpl() throws Exception{
        
    }
    @Override
    public String echo(String msg) throws RemoteException {
        return "来自老师：" + msg;
    }

    @Override
    public String echo(String yourNo, byte[] yourName) throws RemoteException {
        return yourNo;
    }

    @Override
    public Date getTime() throws Exception {
        return new Date();
    }

    @Override
    public byte[] getMessage(String msg) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String putMessage(String msg1, byte[] msg2) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
