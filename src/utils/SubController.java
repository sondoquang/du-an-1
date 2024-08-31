
package utils;

import javax.swing.JFrame;

public abstract class SubController extends JFrame {
    protected JFrame returnWindow;
    public void setReturnWindow(JFrame mainWindow) {
        if(mainWindow!=null){
            this.returnWindow = mainWindow;
        }
    }
}
