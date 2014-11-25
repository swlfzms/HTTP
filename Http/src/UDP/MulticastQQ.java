/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author Administrator
 */
public class MulticastQQ {

    private MulticastSocket mss = null;
    private int port = 8900;
    private InetAddress group = null;

    public MulticastQQ() {
        try {
            group = InetAddress.getByName("224.0.1.8");
            mss = new MulticastSocket(port);
        } catch (Exception e) {
        }
    }

    public void send(String msg) throws Exception {
        byte[] buffer = msg.getBytes();
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length, group, port);
        mss.send(dp);
    }

    public void close() throws IOException {
        if (mss != null) {
            mss.leaveGroup(group);
            mss.close();
        }
    }
}
