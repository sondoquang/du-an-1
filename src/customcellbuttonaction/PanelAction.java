
package customcellbuttonaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAction extends javax.swing.JPanel {

  
    public PanelAction() {
        initComponents();
    }
    
    public void initEvent(TableActionEvent event , int row){
        cmdDelete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ad) {
                event.onDetete(row);
            }
        });
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdDelete = new customcellbuttonaction.ActionButton();

        setLayout(new java.awt.BorderLayout());

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customcellbuttonaction/el-hg-icon-delete-16.png"))); // NOI18N
        add(cmdDelete, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customcellbuttonaction.ActionButton cmdDelete;
    // End of variables declaration//GEN-END:variables
}
