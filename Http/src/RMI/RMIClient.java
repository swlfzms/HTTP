/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Naming;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class RMIClient {
    
    public static void main(String[] args) throws Exception{
        //String url="rmi://222.201.101.15:1099/";
        //String url="rmi://192.168.207.23:1099/";
        String url="rmi://192.168.204.211:1099/";
        RMIServerService service = (RMIServerService)Naming.lookup(url+"RMIService");
        //String msg = service.echo("20111003413","张茂盛".getBytes("GB2312"));
        String msg = service.putMessage("20111003413","张茂盛".getBytes("GB2312"));
        System.out.println(msg);
        //Date time = service.getTime();
        msg="";
        byte[] s = service.getMessage("20111003413");
        msg = new String(s,"gb2312");
        System.out.println(msg.toString());
    }
}
