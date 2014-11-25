/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.*;

/**
 *
 * @author Administrator
 */
public class EchoUDPServer {

    private DatagramSocket socket;
    private int port=8800;

    public EchoUDPServer() throws SocketException {
        socket = new DatagramSocket(port);
        System.out.println("服务器启动");
    }

    public String echo(String msg) {
        //return "20111003413 张茂盛";
        return "20111003511 余东熹";
    }

    public void service() throws IOException {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(new byte[512], 512);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength(), "GBK");
                System.out.println(packet.getAddress() + ":" + packet.getPort() + ">" + msg);
                packet.setData(echo(msg).getBytes("GBK"));
                socket.send(packet);
            }
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) throws SocketException, IOException{
        EchoUDPServer app = new EchoUDPServer();
        app.service();
    }
}
