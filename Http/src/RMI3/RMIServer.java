/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI3;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Administrator
 */
public class RMIServer {

    public RMIServer() {
    }

    public static void main(String args[]) {
        try {
            RMIServerService service = new RMIServerServiceImpl();//实例化远程类            
            LocateRegistry.createRegistry(1099);//监听1099端口
            Naming.rebind("RMIService3", service);//以名字“RMIService”来发布远程类            

            System.out.println("服务器注册了RMIService对象");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
