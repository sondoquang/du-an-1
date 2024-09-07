package utils;

import java.awt.Color;
import java.sql.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public final class XTable {

    public static DefaultTableModel creatTableModel(Object[][] rows, String[] headers, boolean isEditable) {
        DefaultTableModel model = new DefaultTableModel(rows, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        return model;
    }

    public static DefaultTableModel creatTableModel(String[] headers, int rowCount, boolean isEditable) {
        DefaultTableModel model = new DefaultTableModel(headers, rowCount) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        return model;
    }

    public static DefaultTableModel creatTableModel(String[] headers, ResultSet rs, boolean isEditable) throws SQLException {
        DefaultTableModel model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        ResultSetMetaData data = rs.getMetaData();
        while (rs.next()) {
            Object[] row = new Object[data.getColumnCount()];
            for (int i = 0; i < data.getColumnCount(); i++) {
                row[i] = rs.getObject(i);
            }
            model.addRow(row);
        }
        return model;
    }

    public static <Thing> DefaultTableModel creatTableModel(String[] headers, List<Thing> list, boolean isEditable) {
        DefaultTableModel model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return isEditable;
            }
        };
        return model;
    }

    public static void fillTable(JTable table, ArrayList<Object[]> rows) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        rows.forEach(row -> {
            model.addRow(row);
        });
        table.setModel(model);
    }

    public static void insertImage(JTable table, int column, int height, int width, String folder) {
        for (int i = 0; i < table.getModel().getRowCount(); i++) {
            table.setRowHeight(i, height);
        }
        table.getColumnModel().getColumn(column).setWidth(width);
        table.getColumnModel().getColumn(column).setCellRenderer(new ImageRenderer(folder));
    }

    public class ImageTableModel extends DefaultTableModel {

        private static final long serialVersionUID = 1L;
        private final int ImageCol;

        public ImageTableModel(Object[][] data, Object[] columnNames, int ImageCol) {
            super(data, columnNames);
            this.ImageCol = ImageCol;
        }

        @Override
        public Class<?> getColumnClass(int column) {
            return column == ImageCol ? ImageIcon.class : Object.class;
        }
    }

    public static class ImageRenderer extends DefaultTableCellRenderer {

        public String folder;

        public ImageRenderer(String folder) {
            this.folder = folder;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Gọi phương thức gốc để lấy thành phần hiển thị mặc định
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Kiểm tra nếu giá trị của ô là tên tệp hình ảnh
            if (value instanceof String) {
                String imagePath = (String) value;

                try {
                    int h = table.getRowHeight(row);
                    int w = table.getColumnModel().getColumn(column).getWidth();

                    // Thiết lập hình ảnh cho JLabel trong thành phần hiển thị
                    JLabel label = new JLabel(XImage.getResized(XImage.read(folder, imagePath), w, h));
                    label.setHorizontalAlignment(JLabel.CENTER);

                    // Thiết lập thành phần hiển thị là JLabel chứa hình ảnh
                    return label;
                } catch (Exception e) {
                    return new JLabel(imagePath);
                }
            }

            return component;
        }
    }

    public static class ColoredCellRenderer extends DefaultTableCellRenderer {

        private Color color;

        public ColoredCellRenderer(Color color) {
            this.color = color;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Đổi màu chữ của ô dữ liệu dựa trên giá trị
            int val = Integer.parseInt(value + "");
            if (val < Integer.parseInt(table.getValueAt(row, column + 1) + "")) {
                label.setForeground(color);
                label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
            } else {
                label.setForeground(Color.BLACK);
            }
            return label;
        }
    }

    // Custom ImageRenderer cho table //
    public static class setImageRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            if (value == null) {
                JLabel label = new JLabel("No Image");
                label.setHorizontalAlignment(CENTER);
                label.setOpaque(isSelected);
                label.setBackground(com.getBackground());
            } else {
                JLabel label = null;
                if (value instanceof Icon) {
                    Icon image = (ImageIcon) value;
                    XImage.getResized((ImageIcon) image, label.getWidth(), label.getHeight());
                    label = new JLabel(image);

                } else {
                    label = new JLabel("No Image");
                }
                label.setHorizontalAlignment(CENTER);
                label.setOpaque(isSelected);
                label.setBackground(com.getBackground());
                com = label;
            }
            return com;
        }
    }

    // Set checkBox cho cột(column) trong JTable //
    public static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

        public CheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Cập nhật trạng thái của JCheckBox
            setSelected((Boolean) value);
            // Tùy chỉnh màu sắc nền nếu ô được chọn
            if (isSelected) {
                setForeground(new Color(15, 89, 140));
                setBackground(new Color(204, 204, 204));
            } else {
                setForeground(Color.decode("#000000"));
            }
            return this;
        }
    }

    public static class checkBoxEditor extends AbstractCellEditor implements TableCellEditor {

        private JCheckBox checkBox;

        public checkBoxEditor() {
            checkBox = new JCheckBox();
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            checkBox.setHorizontalTextPosition(JCheckBox.CENTER);
            checkBox.setVerticalAlignment(JCheckBox.CENTER);
            checkBox.setVerticalTextPosition(JCheckBox.CENTER);
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected((boolean) value);
            return checkBox;
        }

    }
}
