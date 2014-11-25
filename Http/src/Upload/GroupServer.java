/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 *
 * @author Administrator
 */
public class GroupServer {

    private int port = 8080;
    private ServerSocket serverSocket;
    private ExecutorService executorService;  //线程池
    private final int POOL_SIZE = 12;  //单个CPU时线程池中工作线程的数目
    public static ArrayList<Socket> groupMembers = new ArrayList<Socket>(100);
    //建立一个在线组员链表
    public GroupServer() throws IOException {
        serverSocket = new ServerSocket(port);
        //创建线程池
        //Runtime的availableProcessors()方法返回当前系统的CPU的数目
        //系统的CPU越多，线程池中工作线程的数目也越多
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        System.out.println("服务器启动");
    }

    public void service() {

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept(); //监听客户请求, 处于阻塞状态.
                //接受一个客户请求,从线程池中拿出一个线程专门处理该客户.
                executorService.execute(new Handler1(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void sendToAllMembers(String msg) throws IOException {

        PrintWriter pw;
        Socket tempSocket;
        OutputStream socketOut;

        for (int i = 0; i < groupMembers.size(); i++) {
            tempSocket = groupMembers.get(i); //取出一个在线成员
            socketOut = tempSocket.getOutputStream();
            pw = new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);
            pw.println(msg);
        }
    }//群组发送结束。
    public static void removeMember(Socket socket) {
        groupMembers.remove(socket);//删除一个成员
    }
    public static void main(String args[]) throws IOException {
        new GroupServer().service();
    }
}
class Handler1 implements Runnable {

    private Socket socket;    

    public Handler1(Socket socket) {
        this.socket = socket;
    }



    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn, "GB2312"));
    }

    public String echo(String msg) {
        return "echo:" + msg;
    }

    public void run() {
        try {
            System.out.println("New connection accepted "
                    + socket.getInetAddress() + ":" + socket.getPort());            
            GroupServer.groupMembers.add(socket);
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);

            String msg = null;
            int i=0;
            String path="";
            BufferedWriter wr=null;
            while ((msg = br.readLine()) != null) {                
                //pw.println(echo(msg));//send to client.
                //GroupServer.sendToAllMembers(msg);
                if (msg.contains("bye".subSequence(0, 2))) {
                    System.out.println(socket.getInetAddress() + ":" + "Exit");
                    break;
                } 
                if(i==1){                    
                    wr.write(msg+"\r\n");
                }else{                    
                    path = "D:\\upload\\"+msg;
                    File file = new File(path);
                    if(!file.exists()){
                        file.createNewFile();
                    }
                    i=1;
                    wr = new BufferedWriter(new FileWriter("D:\\upload\\"+msg,true));
                    pw.println("ok");
                }                  
            }    
            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    GroupServer.removeMember(socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
