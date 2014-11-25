package Upload;

/**
 *
 * @author Administrator
 */
import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileClient {

    private String host = "192.168.204.211";
    //private String host="192.168.207.39";
    private int port = 8080;
    public Socket socket;
    private PrintWriter pw;//�����ֽں��ַ�֮��ת����.
    private BufferedReader br;

    public FileClient() throws IOException {
        socket = new Socket(host, port);//����Է�����l��,TCP��������ֵ�l�ӽ׶�.

        pw = getWriter(socket);
        br = getReader(socket);
    }

    public String fileGet(String fName) throws IOException {
        Socket socket2 = new Socket(host, port + 1);//连接数据文件服务器8081.

        if (socket2 != null) {
            byte[] buff = new byte[1024 * 2];

            //文件保存对话框.
            JFileChooser chooser = new JFileChooser(fName);
            chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();

            if (file != null) {
                FileOutputStream fileOut = new FileOutputStream(file); //新建本地空文件.

                InputStream socketIn = socket2.getInputStream();//准备网络输入流。
                OutputStream socketOut = socket2.getOutputStream();

                PrintWriter pw2 = new PrintWriter(new OutputStreamWriter(socketOut, "GBK"), true);
                pw2.println(fName);//告诉服务器要下载的文件名.

                //接下来,接收文件.
                int len = socketIn.read(buff);//读一块到缓冲区.
                while (len != -1) {
                    fileOut.write(buff, 0, len);//写一块到文件.
                    len = socketIn.read(buff);
                }
                fileOut.close();
                JOptionPane.showMessageDialog(null, "文件接收完毕.");
                socket2.close();
                return "文件下载成功.";
            } else {
                socket2.close();
                return "文件名错误.";
            }
        } else {
            return "服务器连接失败.";
        }
    }

    public String fileput(String filePath,String fileName) throws IOException {
        Socket socket2 = new Socket(host, port);//连接数据文件服务器8082.                    
            //FileOutputStream fileOut = new FileOutputStream(file); //新建本地空文件.
            InputStream socketIn = socket2.getInputStream();//准备网络输入流。
            OutputStream socketOut = socket2.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(socketIn));
            PrintWriter pw2 = new PrintWriter(new OutputStreamWriter(socketOut, "GBK"), true);
            pw2.println(fileName);//告诉服务器要下载的文件名.

            String msg = null;
            msg = br.readLine();
            BufferedReader br1 = new BufferedReader(new FileReader(filePath));
            if (msg.equals("ok")) {
                while ((msg = br1.readLine()) != null) {
                    pw2.println(msg);
                }                
                pw2.close();
            }
            //JOptionPane.showMessageDialog(null, "文件上传完毕.");
            br.close();
            br1.close();
            socket2.close();
            return "文件上传成功.";
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        // return new PrintWriter(socketOut,true);
        return new PrintWriter(new OutputStreamWriter(socketOut, "GB2312"), true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        //return new BufferedReader(new InputStreamReader(socketIn));
        return new BufferedReader(new InputStreamReader(socketIn, "GB2312"));
    }

    public void send(String msg) throws IOException {
        pw.println(msg);//д�����������ϵͳ���õײ㺯�����͡�
    }

    public void send(byte[] b) throws IOException {
        socket.getOutputStream().write(b);
        //д�����������ϵͳ���õײ㺯�����͡�
    }

    public String receive() throws IOException {
        String msg = br.readLine();//ֻ�ܽ���һ����Ϣ.
        return msg;
    }

    public void close() throws IOException {
        socket.close();//�Ĵ����ֶϿ�.
    }
}
