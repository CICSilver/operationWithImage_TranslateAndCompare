import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class captureScreen {

    public static void getScreen(String filePath,String fileName) throws Exception {
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenReceangle=new Rectangle(screensize);
        Robot robot=new Robot();
        BufferedImage image=robot.createScreenCapture(screenReceangle);
        File screenFile=new File(fileName+filePath);
        if(!screenFile.getParentFile().exists()) {
            screenFile.getParentFile().mkdirs();
        }
        BufferedImage subimage=image.getSubimage(100,100,500,500);
        ImageIO.write(subimage,"png",screenFile);
    }
    public static void main(String[] args) throws Exception {
        Date now=new Date();
        SimpleDateFormat sdfname=new SimpleDateFormat("yyyymmdd");
        SimpleDateFormat sdfPath=new SimpleDateFormat("yyyymmddhhss");
        String path=sdfPath.toString();
        String name=sdfname.toString();
        captureScreen.getScreen("D:"+File.separator+path+File.separator,name+".png");
    }

}
