/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multicastqq;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Administrator
 */
public class MulticastQQ {

    /**
     * @param args the command line arguments
     */
    InetAddress groupIP=InetAddress.getByName("224.0.1.8");
    int port =8900;
    MulticastSocket ms =null;
    byte[] inBuff;
    byte[] outBuff;
    public MulticastQQ()throws IOException{
        ms = new MulticastSocket(port);
        ms.joinGroup(groupIP);
        inBuff = new byte[1024];
        outBuff = new byte[1024];
    }
    public void send(String msg)
    {
        try{
            outBuff=("20111003412 何杰: "+msg).getBytes("GBK");
            DatagramPacket outdp = new DatagramPacket(outBuff,outBuff.length,groupIP,port);
            ms.send(outdp);
        }catch(Exception e){}
    }
    public String receive(){
        try{
            DatagramPacket indp = new DatagramPacket(inBuff,inBuff.length);
            ms.receive(indp);
            String msg = new String(indp.getData(),0,indp.getLength(),"GBK");
            return "["+indp.getAddress()+"]"+msg;
            
        }catch(Exception e){return null;}
    }
    public void close(){
        if(ms!=null){
            try{
                ms.leaveGroup(groupIP);
                ms.close();
            }catch(IOException e){}
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
