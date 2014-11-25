package PortScan;


import java.io.IOException;
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
public class PortScanner {

    public static void main(String[] args) {
        PortScanner app = new PortScanner();
        String address="192.168.207.32";
        //app.scan(address, 100, 150);
        app.quickscan(address, 100, 500);
    }

    public void scan(String host, int start, int end) {
        Socket socket = null;
        for (int port = start; port < end; port++) {
            try {
                socket = new Socket(host, port);//TCP的三次握手过程
                System.out.println("Open port " + port + socket);
            } catch (IOException e) {
                System.out.println("Can't connect to port " + port);
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void quickscan(String host,int start,int end){
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
}
