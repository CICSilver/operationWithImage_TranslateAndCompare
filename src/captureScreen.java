import org.xvolks.jnative.misc.POINT;
import org.xvolks.jnative.misc.basicStructures.HWND;
import org.xvolks.jnative.util.User32;
import org.xvolks.jnative.util.WinMM;
import org.xvolks.jnative.util.constants.winuser.WM;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class captureScreen  {

    public void screenShot(String filePath, String fileName,Point pix1,Point pix2) throws Exception {

        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenReceangle=new Rectangle(screensize);
        Robot robot=new Robot();
        BufferedImage image=robot.createScreenCapture(screenReceangle);
        File screenFile=new File(filePath+fileName);
        if(!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        BufferedImage subimage=image.getSubimage(pix1.x,pix1.y,pix2.x,pix2.y);
        ImageIO.write(subimage,"png",screenFile);
    }
    
    public String fileName() {
        Date now=new Date();
        SimpleDateFormat sdfname=new SimpleDateFormat("yyyyMMdd");
        return sdfname.format(now);
    }
    
    public String filePath() {
        Date now=new Date();
        SimpleDateFormat sdfPath=new SimpleDateFormat("yyyyMMddHHss");
        return sdfPath.format(now);
    }
    
    public static void main(String[] args) throws Exception {
        captureScreen cap=new captureScreen();
        String path=cap.filePath();
        String name=cap.fileName();
        Point pix1=new Point(900,900);
        Point pix2=new Point(1800,1800);
        cap.screenShot("D:"+File.separator+path+File.separator,name+".png",pix1,pix2);
    }

}
