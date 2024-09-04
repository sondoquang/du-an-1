
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
        
        cmdEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onEdit(row);
            }
        });
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdDelete = new customcellbuttonaction.ActionButton();
        cmdEdit = new customcellbuttonaction.ActionButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(1, 0));

        cmdDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customcellbuttonaction/el-hg-icon-delete-16.png"))); // NOI18N
        add(cmdDelete);

        cmdEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customcellbuttonaction/pen.png"))); // NOI18N
        add(cmdEdit);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private customcellbuttonaction.ActionButton cmdDelete;
    private customcellbuttonaction.ActionButton cmdEdit;
    // End of variables declaration//GEN-END:variables
}
