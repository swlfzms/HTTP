/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

/**
 *
 * @author Administrator
 */
import java.net.*;
import java.io.*;

public class EchoUDPClient {

    //private String remoteHost = "222.201.101.15";// or localhost"
    //private int remotePort = 5005;//对方（服务器）端口号
    private String remoteHost = "222.201.101.15";// or localhost"
    private int remotePort = 8880;//对方（服务器）端口号
    private DatagramSocket socket;
    private InetAddress remoteIP;

    public EchoUDPClient() throws IOException {
        socket = new DatagramSocket(); //与本地任意一个未使用的UDP端口绑定
        //   socket=new DatagramSocket(9900); //与本地一个固定的UDP端口绑定
        remoteIP = InetAddress.getByName(remoteHost);//网络地址转换
    }

    public void send(String msg) throws IOException {
        byte[] outputData = msg.getBytes("GBK");
        //先准备一个待发送的数据报
        DatagramPacket outputPacket = new DatagramPacket(outputData,
                outputData.length, remoteIP, remotePort);
        socket.send(outputPacket);  //给EchoUDPServer发送数据报
    }

    public String receive() throws IOException {
        String msg = null;
        //先准备一个空数据报
        DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);
        socket.receive(inputPacket);//接收EchoUDPServer的回送数据报
        msg = new String(inputPacket.getData(), 0, inputPacket.getLength(), "GBK");
        return msg;
    }

    public void close() {
        socket.close();//释放端口.
    }
}
