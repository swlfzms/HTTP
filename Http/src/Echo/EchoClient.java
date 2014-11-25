package Echo;



/**
 *
 * @author Administrator
 */
import java.net.*;
import java.io.*;

public class EchoClient {
  //private String host="222.201.101.15";
  //private int port=9009;//查成绩
  //private String host="192.168.207.39";
  private String host="192.168.204.204";
  private int port=8008;

  public Socket socket;
  private PrintWriter pw;//�����ֽں��ַ�֮��ת����.
  private BufferedReader br;

  public EchoClient()throws IOException{
     socket=new Socket(host,port);//����Է�����l��,TCP��������ֵ�l�ӽ׶�.
    
     pw=getWriter(socket);
     br=getReader(socket);
  }

  private PrintWriter getWriter(Socket socket)throws IOException{
    OutputStream socketOut = socket.getOutputStream();
   // return new PrintWriter(socketOut,true);
    return new PrintWriter(new OutputStreamWriter(socketOut,"GB2312"),true);
   }

  private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    //return new BufferedReader(new InputStreamReader(socketIn));
    return new BufferedReader(new InputStreamReader(socketIn,"GB2312"));
  }

  public void send(String msg)throws IOException{
        pw.println(msg);//д�����������ϵͳ���õײ㺯�����͡�
   }
  public void send(byte[] b)throws IOException{
        socket.getOutputStream().write(b);
        //д�����������ϵͳ���õײ㺯�����͡�
   }

  public String receive()throws IOException{
    String msg=br.readLine();//ֻ�ܽ���һ����Ϣ.
    return msg;
  }

  public void close() throws IOException{
  socket.close();//�Ĵ����ֶϿ�.
  }
  
}
