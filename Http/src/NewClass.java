
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

/**
 *
 * @author Administrator
 */
public class NewClass extends JFrame {

    
    private TransPanel canvas;
    private final Container contentPane;
    public NewClass() {        
        contentPane = getContentPane();
        setTitle("TransformTest");
        setSize(400, 400);       
        
        canvas = new TransPanel();       
        canvas.setLocation(136, 260);
        canvas.setSize(128, 128);        
        contentPane.add(canvas);                
                
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseMoved(MouseEvent e) {                      
                double x,y;
                x = e.getX();
                y = e.getY();
                double rate;
                double tx = Math.abs(y-324);
                double ty = Math.abs(x-200);
                rate = Math.atan(ty/tx)*180/Math.PI;                
                if(x<200) canvas.roteAngle=-rate;
                else canvas.roteAngle=rate;
                canvas.setRotate();                
            }
        });
    }
    
    public static void main(String[] args) {
        JFrame frame = new NewClass();                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
class TransPanel extends JPanel {
    double roteAngle = 0;    
    public TransPanel() {        
        img = new ImageIcon("image//1.jpg").getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);        
        drawTransImage(g, img.getWidth(this), img.getHeight(this), 0);        
    }

    public void drawTransImage(Graphics g, int drawx, int drawy, int zoom) {        
        int w = img.getWidth(this);
        int h = img.getHeight(this);                                   
        Graphics2D g2 = (Graphics2D) g;
        if (roteAngle != 0) {
            g2.rotate(Math.toRadians(roteAngle), w >> 1, h >> 1);            
        }        
        g2.drawImage(img, 0, 0, w, h, null);
    }

    public void setRotate() {                
        repaint();
    }     
    private Image img;
}