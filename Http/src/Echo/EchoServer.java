package Echo;

import NetWorkDB.ConnectionProvider;
import java.io.*;
import java.net.*;
import model.User;

public class EchoServer {

    private int port = 8008;
    private ServerSocket serverSocket;

    public EchoServer() throws IOException {
        serverSocket = new ServerSocket(port);//����8008�ż���˿ڡ�
        System.out.println("��������");
    }

    private PrintWriter putWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();//��������������ĵ�ַ��
        return new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);

    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();//�����������뻺����ĵ�ַ
        return new BufferedReader(new InputStreamReader(socketIn, "GB2312"));
    }

    public String echo(String msg) {
        // return "echo"+msg;
        return msg;
    }

    public void service() {//���ͻ��汾,��ÿ��ֻ��ͬʱ��һ��ͻ���bͨ��l�ӡ�
        System.out.println("服务器启动........");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();  //�ȴ�ͻ�l�ӣ���l�Ӿ����һ���׽��֡��������.
                System.out.println("New connection accepted "
                        + socket.getInetAddress() + ":" + socket.getPort());
                BufferedReader br = getReader(socket);//���ַ�u�����������
                PrintWriter pw = putWriter(socket);//���ַ�д�����������

                String msg = null;
                while ((msg = br.readLine()) != null) //�����������ж���һ���ַ��������.
                {                    
                    if(msg.startsWith("<login>")){
                        int i = msg.indexOf(">");
                        msg = msg.substring(i+1, msg.length());
                        
                        User user = new User();
                        int j = msg.indexOf("^");
                        user.setUsername(msg.substring(0,j));
                        user.setPassword(msg.substring(j+1, msg.length()));                        
                        try{
                            ConnectionProvider cp = new ConnectionProvider();
                            msg = cp.login(user);
                        }catch(Exception e){
                            System.out.println("数据库连接创建失败");
                        }         
                        System.out.println("发给"+socket.getInetAddress()+": "+msg);
                        pw.println(msg);
                    }else{
                        System.out.println("发给"+socket.getInetAddress()+": "+msg);
                        pw.println(msg);
                    }
                    if (msg.equals("bye")) { //���ͻ����͵���ϢΪ��bye�����ͽ���ͨ��                        ������������һ���ַ�
                        break;
                        //System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();  //�Ͽ�l��
                        System.out.println("close the socket"+socket.getInetAddress() + ":" + socket.getPort());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        new EchoServer().service();
    }
}