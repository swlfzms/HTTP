/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI2;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class RMIServer {
    public RMIServer(){
        
    }
    public static void main(String args[]) {
        try {
            RMIServerService1 service1 = new RMIServerServiceImpl1();//实例化远程类
            RMIServerService2 service2 = new RMIServerServiceImpl2();

            LocateRegistry.createRegistry(1099);//监听1099端口
            Naming.rebind("RMIService1", service1);//以名字“RMIService”来发布远程类
            Naming.rebind("RMIService2", service2);

            System.out.println("服务器注册了2个RMIService对象");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
