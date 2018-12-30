import javafx.scene.control.Alert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class MousePix   {
    private static Point pt1,pt2;
    private static boolean count=true;
    public static void main(String[] args) {

        JFrame jf=new JFrame();
        jf.setTitle("截图软件");
        jf.setLayout(new FlowLayout());
        jf.setUndecorated(true);
        jf.setBackground(new Color(251, 245, 255, 35));
        jf.setSize(1920,1080);
        JTextArea jT=new JTextArea();
        jf.add(jT);
        JButton captureScreen=new JButton("截屏");
        captureScreen.setSize(300,300);
        jf.add(captureScreen);
        jf.setVisible(true);

        captureScreen.addActionListener(e -> {


            captureScreen cap=new captureScreen();
            try {
                cap.screenShot("D:" + File.separator + cap.filePath() + File.separator, cap.fileName()+".png", pt1, pt2);
            }catch (Exception ex) {
                System.out.println("未知错误！");
            }
        });

        jf.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point=e.getLocationOnScreen();
                jT.setText(point.x+","+point.y);
                if(count) {
                    pt1=point;
                    count=false;
                }
                else {
                    pt2=point;
                    count=true;
                }

            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

    }

}
