
package utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class XFile {

    public static void writeObject(Object o, String filePath) {
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(XFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object readObject(String filePath) {
        FileInputStream fileIn;
        Object o = null;
        try {
            fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            o = (Object) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(XFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }

    public static void saveFile(Object o) {
        JFileChooser save = new JFileChooser();
        save.setCurrentDirectory(new File("."));
        int rep = save.showSaveDialog(null);
        if (rep == JFileChooser.APPROVE_OPTION) {
            writeObject(o, save.getSelectedFile().getAbsolutePath());
        }
    }

    public static Object openFile() {
        Object o = null;
        JFileChooser open = new JFileChooser();

        open.setCurrentDirectory(new File("."));
        int rep = open.showOpenDialog(null);
        if (rep == JFileChooser.APPROVE_OPTION) {
            o = readObject(open.getSelectedFile().getAbsolutePath());
        }
        return o;
    }
    
    public static void copyPaste(File src, String destination){
        try{
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(destination);
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
}
