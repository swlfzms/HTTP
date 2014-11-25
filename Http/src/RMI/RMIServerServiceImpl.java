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
public class RMIServerServiceImpl extends UnicastRemoteObject
        implements RMIServerService {

    byte[] username;
    public RMIServerServiceImpl() throws Exception{
        
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

    @Override
    public byte[] getMessage(String msg) throws RemoteException {
        return username;
    }

    @Override
    public String putMessage(String msg1, byte[] msg2) throws RemoteException {
        this.username = msg2;
        System.out.println("写入信息:"+msg1+","+msg2.toString());
        return "success";
    }
}
