package PortScan;


import java.net.InetSocketAddress;
import java.net.Socket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class ThreadScaner extends Thread{
    String host;
    int start,end;
    
    public ThreadScaner(){
        
    }            
    public ThreadScaner(String host,int start,int end){
        this.host = host;
        this.start= start;
        this.end = end;
    }
    
    @Override
    public void run(){
        Socket socket = null;
        for(int port = start;port<end;port++){
            try{
                socket = new Socket();
                socket.connect(new InetSocketAddress(host,port),150);
                System.out.println("Open the port"+port+socket);
            }catch(Exception e){
                System.out.println("Can't connect to the port"+port);
            }finally{
                try{
                    if(socket!=null){
                        socket.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args){
        ThreadScaner app1 = new ThreadScaner("192.168.207.31",100,250);
        app1.start();
        ThreadScaner app2 = new ThreadScaner("192.168.207.31",251,500);        
        app2.start();
    }
}
