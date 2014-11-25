/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Naming;


/**
 *
 * @author Administrator
 */
public class RMIClient {
    
    public static void main(String[] args)throws Exception{
        //String url="rmi://222.201.101.15:1099/";
        String url="rmi://192.168.207.32:1099/";
        //String url="rmi://192.168.207.23:1099/";
        RMIServerService1 service1 = (RMIServerService1)Naming.lookup(url+"RMIService1");
        RMIServerService2 service2 = (RMIServerService2)Naming.lookup(url+"RMIService2");
        
        String msg1 = service1.echo("20111003413 张茂盛 计算机1101");
        System.out.println(msg1);
                
        String msg2 = service2.invertString("20111003413 张茂盛 计算机1101");
        System.out.println(msg2);
        
        msg2 = service2.invertString(msg2);
        System.out.println(msg2);
    }
}
