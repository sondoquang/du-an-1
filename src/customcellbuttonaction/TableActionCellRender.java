package customcellbuttonaction;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        PanelAction action = new PanelAction();
        if (isSelected) {
            com.setForeground(new Color(15, 89, 140));
            com.setBackground(new Color(204, 204, 204));
        } else {
            com.setForeground(Color.white);
            com.setBackground(new Color(204, 204, 204));
        }
        return action;
    }

}
