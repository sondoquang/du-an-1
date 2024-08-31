/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlls;

import daoImpl.NhanVienDAO;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import utils.XAuth;
import utils.XMsgBox;
import utils.XValidate;
import utils.XDate;
import utils.XImage;

/**
 *
 * @author ndhlt
 */
public class ProfileController {

    private static JFrame frame;
    private static JTextField txtMaNv;
    private static JPasswordField txtPass;
    private static JTextField Name;
    private static JTextField ChucVu;
    private static JTextField NgaySinh;
    private static JRadioButton rdoNam;
    private static JRadioButton rdoNu;
    private static JTextField DienThoai;
    private static JTextField email;
    private static JTextArea DiaChi;
    private static JLabel Hinh;
    private static File img;

    public static void initialize(JFrame frame, JTextField txtMaNv, JPasswordField txtPass, JTextField Name, JTextField ChucVu, JTextField NgaySinh,
            JRadioButton rdoNam, JRadioButton rdoNu, JTextField DienThoai, JTextField email, JTextArea DiaChi, JLabel Hinh) {
        ProfileController.frame = frame;
        ProfileController.txtMaNv = txtMaNv;
        ProfileController.txtPass = txtPass;
        ProfileController.Name = Name;
        ProfileController.ChucVu = ChucVu;
        ProfileController.NgaySinh = NgaySinh;
        ProfileController.rdoNam = rdoNam;
        ProfileController.rdoNu = rdoNu;
        ProfileController.DienThoai = DienThoai;
        ProfileController.email = email;
        ProfileController.DiaChi = DiaChi;
        ProfileController.Hinh = Hinh;
    }

    public static void getComponents(JFrame frame, JTextField txtMaNv, JPasswordField txtPass, JTextField Name, JTextField ChucVu, JTextField NgaySinh,
            JRadioButton rdoNam, JRadioButton rdoNu, JTextField DienThoai, JTextField email, JTextArea DiaChi, JLabel Hinh) {
        frame = ProfileController.frame;
        txtMaNv = ProfileController.txtMaNv;
        txtPass = ProfileController.txtPass;
        Name = ProfileController.Name;
        ChucVu = ProfileController.ChucVu;
        NgaySinh = ProfileController.NgaySinh;
        rdoNam = ProfileController.rdoNam;
        rdoNu = ProfileController.rdoNu;
        DienThoai = ProfileController.DienThoai;
        email = ProfileController.email;
        DiaChi = ProfileController.DiaChi;
        Hinh = ProfileController.Hinh;
    }

    public static void setForm() {
        txtMaNv.setText(XAuth.getUser().getMaNV());
        txtPass.setText(XAuth.getUser().getMatKhau());
        Name.setText(XAuth.getUser().getHoVaTen());
        ChucVu.setText(XAuth.getUser().getCv().getTenCV());
        NgaySinh.setText(XDate.toString(XAuth.getUser().getNgaySinh(), "dd/MM/yyyy"));
        DienThoai.setText(XAuth.getUser().getSDT());
        email.setText(XAuth.getUser().getEmail());
        if (XAuth.getUser().isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        DiaChi.setText(XAuth.getUser().getDiaChi());
        if (XAuth.getUser().getHinh() != null && !XAuth.getUser().getHinh().isBlank()) {
            Hinh.setIcon(XImage.getResized(XImage.read("EmpImages", XAuth.getUser().getHinh()), Hinh.getWidth(), Hinh.getHeight()));
            Hinh.setToolTipText(XAuth.getUser().getHinh());
        }
    }

    public static void updateProfile() {
        if (checkInput()) {
            if (!XAuth.getUser().getHinh().equals(Hinh.getToolTipText())) {
                File ogFile = new File("EmpImages", XAuth.getUser().getHinh());
                ogFile.delete();
                XImage.save("EmpImages", img);
            }
            XAuth.getUser().setHoVaTen(Name.getText());
            XAuth.getUser().setNgaySinh(XDate.toDate(NgaySinh.getText(), "dd/MM/yyyy"));
            XAuth.getUser().setSDT(DienThoai.getText());
            XAuth.getUser().setEmail(email.getText());
            XAuth.getUser().setDiaChi(DiaChi.getText());
            XAuth.getUser().setGioiTinh(rdoNam.isSelected());
            XAuth.getUser().setHinh(Hinh.getToolTipText());
            new NhanVienDAO().update(XAuth.getUser());
            XMsgBox.inform(frame, "Cập nhật thành công");
        }
    }

    public static void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Thiết lập bộ lọc để chỉ chọn các tệp ảnh
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                String ext = getFileExtension(f);
                return ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif");
            }

            @Override
            public String getDescription() {
                return "Image files (*.jpg, *.jpeg, *.png, *.gif)";
            }

            private String getFileExtension(File f) {
                String name = f.getName();
                int lastIndex = name.lastIndexOf('.');
                if (lastIndex == -1) {
                    return "";
                }
                return name.substring(lastIndex + 1).toLowerCase();
            }
        });
        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ImageIcon icon = XImage.getResized(new ImageIcon(file.getAbsolutePath()), Hinh.getWidth(), Hinh.getHeight());
            Hinh.setIcon(icon);
            Hinh.setToolTipText(file.getName());
            ProfileController.img = file;
        }
    }

    private static boolean checkInput() {
        if (Name.getText().isBlank()) {
            XMsgBox.alert(frame, "Tên không được để trống");
            Name.requestFocus();
            return false;
        }
        if(!XValidate.name(Name.getText())){
            XMsgBox.alert(frame, "Tên không hợp lệ");
            Name.requestFocus();
            return false;
        }
        if (NgaySinh.getText().isBlank()) {
            XMsgBox.alert(frame, "Ngày sinh không được để trống");
            NgaySinh.requestFocus();
            return false;
        }
        if(!XDate.validate(NgaySinh.getText(), "dd/MM/yyyy")){
            XMsgBox.alert(frame, "Hãy nhập ngày sinh theo định dạng dd/mm/yyyy");
            NgaySinh.requestFocus();
            return false;
        }
        if (DienThoai.getText().isBlank()) {
            XMsgBox.alert(frame, "SDT không được để trống");
            DienThoai.requestFocus();
            return false;
        }
        if(!XValidate.phoneNumber(DienThoai.getText())){
            XMsgBox.alert(frame, "SDT không hợp lệ");
            DienThoai.requestFocus();
            return false;
        }
        if (email.getText().isBlank()) {
            XMsgBox.alert(frame, "Email không được để trống");
            email.requestFocus();
            return false;
        }
        if(XValidate.email(email.getText())){
            XMsgBox.alert(frame, "Email không hợp lệ");
            email.requestFocus();
            return false;
        }
        if (DiaChi.getText().isBlank()) {
            XMsgBox.alert(frame, "Địa chỉ không được để trống");
            DiaChi.requestFocus();
            return false;
        }
        if (Hinh.getIcon() == null) {
            XMsgBox.alert(frame, "Hình không được để trống");
            Hinh.requestFocus();
            return false;
        }
        return true;
    }
}
