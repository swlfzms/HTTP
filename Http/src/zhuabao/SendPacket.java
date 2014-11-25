/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zhuabao;

import java.io.IOException;
import java.net.InetAddress;
import jpcap.JpcapCaptor;
import jpcap.JpcapSender;
import jpcap.NetworkInterface;
import jpcap.packet.EthernetPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.TCPPacket;

/**
 *
 * @author Administrator
 */
public class SendPacket {

    private JpcapSender sender;

    public SendPacket() throws IOException {
        NetworkInterface[] device = JpcapCaptor.getDeviceList();
        sender = JpcapSender.openDevice(device[0]);
    }

    public static void main(String[] args) throws Exception {
        new SendPacket().sendTCPPacket();
    }

    public void sendTCPPacket() throws IOException {
        TCPPacket tcp = new TCPPacket(8000, 80, 56, 78, false, false, false, false, true, false, true, true, 50, 10);
        tcp.setIPv4Parameter(0, false, false, false, 0, false, false, false, 0, 1010101, 100, IPPacket.IPPROTO_TCP,
                //InetAddress.getByName("192.168.207.31"), InetAddress.getByName("192.168.207.32"));
                InetAddress.getByName("192.168.204.211"), InetAddress.getByName("192.168.204.211"));
        tcp.data = ("20111003413 张茂盛").getBytes("GBK");
        EthernetPacket ether = new EthernetPacket();
        ether.frametype = EthernetPacket.ETHERTYPE_IP;
        ether.src_mac = new byte[]{(byte)00,(byte)38,(byte)130,(byte)234,(byte)223,(byte)149};
        ether.dst_mac = new byte[]{(byte)00,(byte)38,(byte)130,(byte)234,(byte)223,(byte)149};
        tcp.datalink = ether;
        for(int i=0;i<2;i++){
            sender.sendPacket(tcp);
        }
    }
}
