import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

/**
 * 图片转为base64编码
 * base64解码为图片
 * 获取图片每个像素的rgb码
 * 按每个像素的rgb码比较两个图片的相似度
 * @author Silver__2018/12/30
 */
public class ImageTranslate {
    private Base64.Encoder encoder=Base64.getEncoder();
    private Base64.Decoder decoder=Base64.getDecoder();
    private int[] rgb=new int[3];


    BufferedImage bi;

    /**
     * 图片转换为字符串数据
     * @return  返回编码后的字符串
     */
    public String imageToBase64String(String fileName) {

        BufferedImage bi;
        File file=new File(fileName);
        try {
            bi = ImageIO.read(file);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ImageIO.write(bi,"png",bos);
            byte[] binary=bos.toByteArray();
            return encoder.encodeToString(binary);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 译码
     * @param base64String 图片的base64码字符串
     */
    public void base64StringToPic(String base64String) {

        byte[] bytes=decoder.decode(base64String);
        File file=new File("D://TEST.png");
        ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
        try {
            BufferedImage bi1=ImageIO.read(bais);
            ImageIO.write(bi1,"png",file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param fileName 文件位置
     * @return  每个像素的RGB码
     */
    public String[][] getPixelRGB(String fileName) {
        try {
            File file=new File(fileName);
            BufferedImage bi=ImageIO.read(file);
            int width=bi.getWidth();
            int height=bi.getHeight();
            int minx=bi.getMinX();
            int miny=bi.getMinY();
            String[][] pixelRgbArray=new String[width][height];
            for(int i = minx; i<width; i++) {
                for(int j=miny;j<height;j++) {
                    int pixel = bi.getRGB(i, j);
                    // 下面三行代码将一个数字转换为RGB数字
                    rgb[0] = (pixel & 0xff0000) >> 16;
                    rgb[1] = (pixel & 0xff00) >> 8;
                    rgb[2] = (pixel & 0xff);
                    pixelRgbArray[i][j]=rgb[0]+","+rgb[1]+","+rgb[2];
                    System.out.println("i:"+i+"j:"+j+"["+rgb[0]+","+rgb[1]+","+rgb[2]+"]");
                }
            }
            return pixelRgbArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean compareByPix(String[][] str1,String[][] str2) {
        //判断两图是否为按比例拉伸图，不是则判断两图不同
        if((str1.length/str1[1].length)!=(str2.length/str2[1].length)) {
            return false;
        }
        int width,height;
        width=str1.length<str2.length?str1.length:str2.length;
        height=str1[1].length<str2[1].length?str1[1].length:str2[1].length;
        double allPix=width*height;
        double count=0;
        for(int i=0;i<width-1;i++) {
            for(int j=0;j<height-1;j++) {
                if(str1[i][j].equals(str2[i][j])) {
                    count++;
                }
            }
        }
        if((count/allPix)*100>90) {
            return true;
        }

        return false;
    }
    public static void main(String[] args) {
        ImageTranslate iC=new ImageTranslate();
        String[][] str1,str2;
        String str=iC.imageToBase64String("D://201812301613/123.png");
        iC.base64StringToPic(str);
        str1=iC.getPixelRGB("D://TEST.png");
        str2=iC.getPixelRGB("D://201812301613/123.png");
        boolean f=iC.compareByPix(str1,str2);
        System.out.println(f);

    }
}
