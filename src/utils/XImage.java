
package utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/com/edusys/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }
    
    public static void save(File src){
        File dst = new File("src/resources",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        XFile.copyPaste(src, dst.getAbsolutePath());
    }
    
    public static void save(String folder, File src){
        File dst = new File(folder,src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        XFile.copyPaste(src, dst.getAbsolutePath());
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("C:\\Users\\acer\\Documents\\DuAn1\\", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon read(String folder, String fileName){
        File path = new File(folder, fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
    
    public static ImageIcon getResized(ImageIcon img, int WIDTH, int HEIGHT){
        Image resize = img.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        return new ImageIcon(resize);
    }
    
    public static String getPath(String fileName){
        File Path = new File("src/resources",fileName);
        return Path.getAbsolutePath();
    }
    public static void saveImg(File src){
        File dst = new File("C:\\Users\\acer\\Documents\\DuAn1\\EmpImages",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdir();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
