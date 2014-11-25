/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class RMIServerServiceImpl extends UnicastRemoteObject
        implements RMIServerService {

    ArrayList<RMIClientView> onLine;
    
    
    public RMIServerServiceImpl() throws Exception {
        onLine = new ArrayList<RMIClientView>(50);        
    }

    @Override
    public String echo(String msg) throws RemoteException {
        
        return "来自张茂盛的echo："+msg;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String addClientToOnLine(RMIClientView client, String name) throws RemoteException {
        onLine.add(client);
        System.out.println("成功添加了"+client.toString());
        return "成功添加了";
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String delClientFromOnLine(RMIClientView client) throws RemoteException {
        onLine.remove(client);        
        System.out.println("成功删除了"+client.toString());
        return "成功删除";
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sendMessageToServer(String msg) throws RemoteException {
        if(msg.length()<50){
            for(int i=0;i<onLine.size();i++){
                RMIClientView client = onLine.get(i);
                try{
                    client.showMessageToClient(msg);
                }catch(Exception e){
                    System.out.println("成功删除了"+client.toString());
                    onLine.remove(client);
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
