package views;

import controlls.ApplianceController;
import controlls.ClientController;
import controlls.EmployeeController;
import static controlls.EmployeeController.table;
import controlls.HomeController;
import controlls.ProductController;
import controlls.ProfileController;
import controlls.ReceiptController;
import daoImpl.HoaDonImple;
import daoImpl.HoaDonNhapImple;
import daoImpl.ThongKeImple;
import java.awt.CardLayout;
import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import utils.XAuth;
import utils.XMsgBox;
import utils.SubController;
import utils.XDate;
import utils.XImage;

public class MainPageFrame extends javax.swing.JFrame {

    private final Color normalBackground = new Color(135, 206, 235);
    private final Color hover = new Color(99, 216, 242);
    private final Color clicked = new Color(242, 171, 39);
    private final Map<JPanel, Boolean> btnMap = new HashMap();
    private Set<JPanel> btnSet;
    private CardLayout cards;
    ThongKeImple tkdao = new ThongKeImple();

    public MainPageFrame(boolean isFirstLogin) {
        if (isFirstLogin) {
            new ChaoJDialog(this, true).setVisible(true);
        }
        new LoginDialog1(this, true).setVisible(true);
        initComponents();
        init();
        setUser();
        this.setLocationRelativeTo(null);
    }

    private void init() {
        setInteract(btnHome, btnEmployee, btnClient, btnProduct, btnAppliance, btnReceipt, btnSummary, btnProfile, btnLogout, btnExit);
        btnSet = btnMap.keySet();
        cards = (CardLayout) container.getLayout();
        clock().start();
        btnClicked(btnHome, () -> {
            showCard("trangChu");
            runController(() -> {
                HomeController.init();
            });
        });

        this.setTitle("Quản Lý Bán Nước");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setUser() {
        File file = new File("EmpImages", XAuth.getUser().getHinh());
        if (!file.exists()) {
            XMsgBox.alert(this, "Chưa cập nhật ảnh đại diện!!!");
            btnProfile.requestFocus();
        }
        side_lblPic.setIcon(XImage.getResized(XImage.read("EmpImages", XAuth.getUser().getHinh()), side_lblPic.getWidth(), side_lblPic.getHeight()));
        side_lblUser.setText(XAuth.getUser().getHoVaTen());
        if (!XAuth.isAdmin()) {
            btnDisable(btnClient);
            btnDisable(btnEmployee);
            btnDisable(btnSummary);
            if (XAuth.isBanHang()) {
                btnDisable(btnAppliance);
            }
            if (XAuth.isKho()) {
                btnDisable(btnReceipt);
                btnDisable(btnProduct);
            }
        }
    }

    private void initController() {
        HomeController.initialize(this, homeEmpPic, homeEmpName, homeProdPic, homeProdName, homeLblSum);
        ClientController.initialize(this, tblClient, txtMaKH, txtTenClient, txtSDTClient, btnCreateClient, btnUpdateClient, btnDeleteClient);
        EmployeeController.initialize(this, tblNhanVien, txtManv, txtHoVaTenNV, txtmatkhauNV, rdoCvAdmin, rdoCvBan, rdoCvKho, rdoNvNam, rdoNvNu, buttonGroup2, buttonGroup3, txtnamsinh_NV, txtsodienthoai_NV, txtemail_NV, txtdiachi_NV, lblhinhNV, btncapnhatNV, btnThemNv, btndatlaiNv, btnXoaNV);
        ApplianceController.initialize(this, tblNL_SP, txtmasp_KH, txtTSP_KH, txtDonVI_SP, txtGT_SP, txtTKHo_SP, txtTThieu_SP, lblhinh_SP, btndatlai_SP, btnthem_SP, btnsua_SP, txtSLDatThem, btnThemNL, txtGhiChu);
        ReceiptController.initialize(this, tblHoaDon, tblCTHoaDon, txtTimHD, cboTime);
        ProfileController.initialize(this, txtMaNV, txtMatKhau, txtHoTen, Prof_txtMaCV, Prof_txtNgaySinh, Prof_rdoNam, Prof_rdoNu, Prof_txtSDT, Prof_txtEmail, Prof_txtDiaChi, Prof_lblHinh);
        ProductController.initialize(this, tblSanPham);
    }

    private void getController() {
        HomeController.getComponents(this, homeEmpPic, homeEmpName, homeProdPic, homeProdName, homeLblSum);
        ClientController.getComponents(this, tblClient, txtMaKH, txtTenClient, txtSDTClient, btnCreateClient, btnUpdateClient, btnDeleteClient);
        EmployeeController.getComponents(this, tblNhanVien, txtManv, txtHoVaTenNV, txtmatkhauNV, rdoCvAdmin, rdoCvBan, rdoCvKho, rdoNvNam, rdoNvNu, buttonGroup2, buttonGroup3, txtnamsinh_NV, txtsodienthoai_NV, txtemail_NV, txtdiachi_NV, lblhinhNV, btncapnhatNV, btnThemNv, btndatlaiNv, btnXoaNV);
        ApplianceController.getComponents(this, tblNL_SP, txtmasp_KH, txtTSP_KH, txtDonVI_SP, txtGT_SP, txtTKHo_SP, txtTThieu_SP, lblhinh_SP, btndatlai_SP, btnthem_SP, btnsua_SP, txtSLDatThem, btnThemNL, txtGhiChu);
        ReceiptController.getComponents(this, tblHoaDon, tblCTHoaDon, txtTimHD, cboTime);
        ProfileController.getComponents(this, txtMaNV, txtMatKhau, txtHoTen, Prof_txtMaCV, Prof_txtNgaySinh, Prof_rdoNam, Prof_rdoNu, Prof_txtSDT, Prof_txtEmail, Prof_txtDiaChi, Prof_lblHinh);
        ProductController.getComponents(this, tblSanPham);
    }

    private void runController(Runnable run) {
        initController();
        run.run();
        getController();
    }

    private Thread clock() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Date date = new Date();
                    lblDate.setText(XDate.toString(date, "E dd/MM/yyyy"));
                    lblTime.setText(XDate.toString(date, "hh:mm:ss"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainPageFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        thread.setDaemon(true);
        return thread;
    }

    private void setInteract(JPanel... others) {
        for (JPanel panel : others) {
            btnMap.put(panel, false);
        }
    }

    private void btnHover(JPanel panel) {
        if (btnMap.containsKey(panel) && btnMap.get(panel) == false) {
            panel.setBackground(hover);
        }
    }

    private void btnExited(JPanel panel) {
        if (btnMap.containsKey(panel) && btnMap.get(panel) == false) {
            panel.setBackground(normalBackground);
        }
    }

    private void btnClicked(JPanel clicked, Runnable run) {
        if (run != null) {
            if (btnMap.containsKey(clicked) && btnMap.get(clicked) == true) {
                return;
            }
            clicked.setBackground(this.clicked);
            run.run();

            btnMap.put(clicked, true);
            for (JPanel panel : btnSet) {
                if (panel.equals(clicked)) {
                    continue;
                }
                panel.setBackground(normalBackground);
                btnMap.put(panel, false);
            }
        }
    }

    private void btnReset(JPanel panel) {
        if (btnMap.containsKey(panel)) {
            btnMap.put(panel, false);
            panel.setBackground(normalBackground);
        }
    }

    private void btnDisable(JPanel panel) {
        sideNav.remove(panel);
    }

    private void showCard(String cardName) {
        cards.show(container, cardName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        side_lblUser = new javax.swing.JLabel();
        side_lblPic = new javax.swing.JLabel();
        sideNav = new javax.swing.JPanel();
        btnHome = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnEmployee = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnClient = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnProduct = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        btnAppliance = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btnReceipt = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnSummary = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnProfile = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnLogout = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnExit = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        container = new javax.swing.JPanel();
        pnlShowPanel = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        panelRound1 = new jpanelrounded.PanelRound();
        homeLblSum = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        panelRound6 = new jpanelrounded.PanelRound();
        homeEmpPic = new javax.swing.JLabel();
        homeEmpName = new javax.swing.JLabel();
        panelRound7 = new jpanelrounded.PanelRound();
        homeProdPic = new javax.swing.JLabel();
        homeProdName = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        pnlNhanVien = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtManv = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtHoVaTenNV = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtmatkhauNV = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        txtnamsinh_NV = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtsodienthoai_NV = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtemail_NV = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        rdoNvNam = new javax.swing.JRadioButton();
        rdoNvNu = new javax.swing.JRadioButton();
        jLabel36 = new javax.swing.JLabel();
        rdoCvAdmin = new javax.swing.JRadioButton();
        rdoCvBan = new javax.swing.JRadioButton();
        rdoCvKho = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtdiachi_NV = new javax.swing.JTextArea();
        jPanel20 = new javax.swing.JPanel();
        btnXoaNV = new javax.swing.JButton();
        btndatlaiNv = new javax.swing.JButton();
        btnThemNv = new javax.swing.JButton();
        btncapnhatNV = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel44 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        lblhinhNV = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtTenClient = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtSDTClient = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblClient = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnCreateClient = new javax.swing.JButton();
        btnUpdateClient = new javax.swing.JButton();
        btnDeleteClient = new javax.swing.JButton();
        btnRefreshClient = new javax.swing.JButton();
        pblQLSanPham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new customjtable.CustomJTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnTaoSP = new button.ButtonCustom();
        pnlKhoHang = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNL_SP = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        lblhinh_SP = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtmasp_KH = new javax.swing.JTextField();
        txtTThieu_SP = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtTSP_KH = new javax.swing.JTextField();
        txtTKHo_SP = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txtGT_SP = new javax.swing.JTextField();
        txtDonVI_SP = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        btnthem_SP = new javax.swing.JButton();
        btnsua_SP = new javax.swing.JButton();
        btndatlai_SP = new javax.swing.JButton();
        btnThemNL = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtSLDatThem = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel52 = new javax.swing.JLabel();
        btnInhoadon = new javax.swing.JButton();
        pblQLHoaDon = new javax.swing.JPanel();
        pnlTimHD = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTimHD = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cboTime = new javax.swing.JComboBox<>();
        btnTaoDH = new button.ButtonCustom();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblCTHoaDon = new customjtable.CustomJTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblHoaDon = new customjtable.CustomJTable();
        pnlThongKe = new javax.swing.JTabbedPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        cmbNam = new javax.swing.JComboBox<>();
        btnBieuDo = new button.ButtonCustom();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblDTC = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        thongKe_tblSP = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        thongKe_tblKH = new javax.swing.JTable();
        jPanel31 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        thongKe_tblNV = new javax.swing.JTable();
        pnlThietLap = new javax.swing.JPanel();
        Prof_lblHinh = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        Prof_txtMaCV = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        Prof_txtNgaySinh = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        Prof_rdoNam = new javax.swing.JRadioButton();
        Prof_rdoNu = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        Prof_txtSDT = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        Prof_txtEmail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Prof_txtDiaChi = new javax.swing.JTextArea();
        btnDoiMK = new javax.swing.JButton();
        btnUpdateProfile = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(54, 70, 78));
        jPanel2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 581));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(135, 206, 235));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel12.setOpaque(false);

        lblDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Day");

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("Time");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.setOpaque(false);
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        side_lblUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        side_lblUser.setForeground(new java.awt.Color(255, 255, 255));
        side_lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        side_lblUser.setText("User");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(side_lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(side_lblPic, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(side_lblPic, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(side_lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        sideNav.setBackground(new java.awt.Color(135, 206, 235));
        sideNav.setLayout(new java.awt.GridLayout(10, 1));

        btnHome.setBackground(new java.awt.Color(36, 103, 191));
        btnHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHomeMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        jLabel4.setText("Trang chủ");

        javax.swing.GroupLayout btnHomeLayout = new javax.swing.GroupLayout(btnHome);
        btnHome.setLayout(btnHomeLayout);
        btnHomeLayout.setHorizontalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnHomeLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnHomeLayout.setVerticalGroup(
            btnHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnHome);

        btnEmployee.setBackground(new java.awt.Color(36, 103, 191));
        btnEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmployeeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEmployeeMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/job.png"))); // NOI18N
        jLabel5.setText("Nhân viên");

        javax.swing.GroupLayout btnEmployeeLayout = new javax.swing.GroupLayout(btnEmployee);
        btnEmployee.setLayout(btnEmployeeLayout);
        btnEmployeeLayout.setHorizontalGroup(
            btnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnEmployeeLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnEmployeeLayout.setVerticalGroup(
            btnEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnEmployee);

        btnClient.setBackground(new java.awt.Color(36, 103, 191));
        btnClient.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClientMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/KhachHang.png"))); // NOI18N
        jLabel6.setText("Khách hàng");

        javax.swing.GroupLayout btnClientLayout = new javax.swing.GroupLayout(btnClient);
        btnClient.setLayout(btnClientLayout);
        btnClientLayout.setHorizontalGroup(
            btnClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnClientLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnClientLayout.setVerticalGroup(
            btnClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnClient);

        btnProduct.setBackground(new java.awt.Color(36, 103, 191));
        btnProduct.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProductMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProductMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProductMouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/SanPham.png"))); // NOI18N
        jLabel7.setText("Sản phẩm");

        javax.swing.GroupLayout btnProductLayout = new javax.swing.GroupLayout(btnProduct);
        btnProduct.setLayout(btnProductLayout);
        btnProductLayout.setHorizontalGroup(
            btnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProductLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnProductLayout.setVerticalGroup(
            btnProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnProduct);

        btnAppliance.setBackground(new java.awt.Color(36, 103, 191));
        btnAppliance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAppliance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnApplianceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnApplianceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnApplianceMouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/VatTu.png"))); // NOI18N
        jLabel8.setText("Kho hàng");

        javax.swing.GroupLayout btnApplianceLayout = new javax.swing.GroupLayout(btnAppliance);
        btnAppliance.setLayout(btnApplianceLayout);
        btnApplianceLayout.setHorizontalGroup(
            btnApplianceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnApplianceLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnApplianceLayout.setVerticalGroup(
            btnApplianceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnAppliance);

        btnReceipt.setBackground(new java.awt.Color(36, 103, 191));
        btnReceipt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReceiptMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReceiptMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReceiptMouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/invoice.png"))); // NOI18N
        jLabel9.setText("Hóa đơn");

        javax.swing.GroupLayout btnReceiptLayout = new javax.swing.GroupLayout(btnReceipt);
        btnReceipt.setLayout(btnReceiptLayout);
        btnReceiptLayout.setHorizontalGroup(
            btnReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnReceiptLayout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnReceiptLayout.setVerticalGroup(
            btnReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnReceipt);

        btnSummary.setBackground(new java.awt.Color(36, 103, 191));
        btnSummary.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSummary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSummaryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSummaryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSummaryMouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ThongKe.png"))); // NOI18N
        jLabel10.setText("Thống kê");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });

        javax.swing.GroupLayout btnSummaryLayout = new javax.swing.GroupLayout(btnSummary);
        btnSummary.setLayout(btnSummaryLayout);
        btnSummaryLayout.setHorizontalGroup(
            btnSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnSummaryLayout.createSequentialGroup()
                .addGap(0, 17, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnSummaryLayout.setVerticalGroup(
            btnSummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnSummary);

        btnProfile.setBackground(new java.awt.Color(36, 103, 191));
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnProfileMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user (1).png"))); // NOI18N
        jLabel11.setText("Thiết lập");

        javax.swing.GroupLayout btnProfileLayout = new javax.swing.GroupLayout(btnProfile);
        btnProfile.setLayout(btnProfileLayout);
        btnProfileLayout.setHorizontalGroup(
            btnProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnProfileLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnProfileLayout.setVerticalGroup(
            btnProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        sideNav.add(btnProfile);

        jPanel2.add(sideNav, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(36, 103, 191));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 80));
        jPanel5.setLayout(new java.awt.GridLayout(2, 1));

        btnLogout.setBackground(new java.awt.Color(36, 103, 191));
        btnLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout (1).png"))); // NOI18N
        jLabel12.setText("Đăng xuất");

        javax.swing.GroupLayout btnLogoutLayout = new javax.swing.GroupLayout(btnLogout);
        btnLogout.setLayout(btnLogoutLayout);
        btnLogoutLayout.setHorizontalGroup(
            btnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLogoutLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnLogoutLayout.setVerticalGroup(
            btnLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(btnLogout);

        btnExit.setBackground(new java.awt.Color(36, 103, 191));
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnExitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnExitMouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/turn-off.png"))); // NOI18N
        jLabel13.setText("Thoát");

        javax.swing.GroupLayout btnExitLayout = new javax.swing.GroupLayout(btnExit);
        btnExit.setLayout(btnExitLayout);
        btnExitLayout.setHorizontalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnExitLayout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnExitLayout.setVerticalGroup(
            btnExitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(btnExit);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_START);

        container.setLayout(new java.awt.CardLayout());

        pnlShowPanel.setBackground(new java.awt.Color(255, 255, 255));
        pnlShowPanel.setLayout(new javax.swing.BoxLayout(pnlShowPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        panelRound1.setBackground(new java.awt.Color(0, 255, 204));
        panelRound1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "TỔNG ĐƠN HÀNG TRONG NGÀY", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        panelRound1.setRoundButtomLeft(30);
        panelRound1.setRoundButtomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);

        homeLblSum.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        homeLblSum.setForeground(new java.awt.Color(102, 102, 255));
        homeLblSum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeLblSum.setText("999");

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/single-product-analysis-64.png"))); // NOI18N

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(homeLblSum, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel46)
                .addGap(0, 40, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(homeLblSum, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        panelRound6.setBackground(new java.awt.Color(0, 255, 204));
        panelRound6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "NHÂN VIÊN CỦA THÁNG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        panelRound6.setRoundButtomLeft(30);
        panelRound6.setRoundButtomRight(30);
        panelRound6.setRoundTopLeft(30);
        panelRound6.setRoundTopRight(30);

        homeEmpName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        homeEmpName.setForeground(new java.awt.Color(102, 102, 255));
        homeEmpName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeEmpName.setText("999");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(homeEmpPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(homeEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(homeEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeEmpPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        panelRound7.setBackground(new java.awt.Color(0, 255, 204));
        panelRound7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "BÁN CHẠY NHẤT THÁNG", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP, new java.awt.Font("Segoe UI", 1, 18), new java.awt.Color(102, 102, 102))); // NOI18N
        panelRound7.setRoundButtomLeft(30);
        panelRound7.setRoundButtomRight(30);
        panelRound7.setRoundTopLeft(30);
        panelRound7.setRoundTopRight(30);

        homeProdName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        homeProdName.setForeground(new java.awt.Color(102, 102, 255));
        homeProdName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeProdName.setText("999");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(homeProdPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(homeProdName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(homeProdName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeProdPic, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("BIỂU ĐỒ THỐNG KÊ TỔNG SẢN PHẦM BÁN RA TRONG 7 THÁNG GẦN NHẤT");
        jLabel51.setToolTipText("");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1382, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(90, 90, 90))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(1665, 1665, 1665))
        );

        pnlShowPanel.add(jPanel16);

        container.add(pnlShowPanel, "trangChu");

        jPanel18.setLayout(new java.awt.GridLayout(10, 1, 5, 5));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Mã NV:");
        jPanel18.add(jLabel27);

        txtManv.setEditable(false);
        txtManv.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtManv.setFocusable(false);
        jPanel18.add(txtManv);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Họ và tên:");
        jPanel18.add(jLabel28);

        txtHoVaTenNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel18.add(txtHoVaTenNV);

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel37.setText("Mật khẩu:");
        jPanel18.add(jLabel37);

        txtmatkhauNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel18.add(txtmatkhauNV);

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Năm sinh:");
        jPanel18.add(jLabel30);
        jPanel18.add(txtnamsinh_NV);

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Số ĐT:");
        jPanel18.add(jLabel32);
        jPanel18.add(txtsodienthoai_NV);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(51, 51, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Quản Lý Nhân Viên");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Email:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Giới tính:");

        buttonGroup3.add(rdoNvNam);
        rdoNvNam.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoNvNam.setText("Nam");

        buttonGroup3.add(rdoNvNu);
        rdoNvNu.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoNvNu.setText("Nữ");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel36.setText("Chức vụ:");

        buttonGroup2.add(rdoCvAdmin);
        rdoCvAdmin.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoCvAdmin.setText("Admin");

        buttonGroup2.add(rdoCvBan);
        rdoCvBan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoCvBan.setText("Bán hàng");

        buttonGroup2.add(rdoCvKho);
        rdoCvKho.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        rdoCvKho.setText("Quản kho");

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Địa chỉ:");

        txtdiachi_NV.setColumns(20);
        txtdiachi_NV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtdiachi_NV.setRows(5);
        jScrollPane5.setViewportView(txtdiachi_NV);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemail_NV)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(222, 222, 222))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(rdoNvNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(36, 36, 36)
                                .addComponent(rdoNvNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(166, 166, 166))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(224, 224, 224))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(rdoCvAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoCvBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoCvKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(34, 34, 34))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(236, 236, 236)))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(296, 296, 296))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtemail_NV, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNvNu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoNvNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCvAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoCvBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdoCvKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5)
                .addGap(20, 20, 20))
        );

        btnXoaNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });
        jPanel20.add(btnXoaNV);

        btndatlaiNv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btndatlaiNv.setText("Làm mới");
        btndatlaiNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlaiNvActionPerformed(evt);
            }
        });
        jPanel20.add(btndatlaiNv);

        btnThemNv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemNv.setText("Thêm ");
        btnThemNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNvActionPerformed(evt);
            }
        });
        jPanel20.add(btnThemNv);

        btncapnhatNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btncapnhatNV.setText("Cập nhật");
        btncapnhatNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapnhatNVActionPerformed(evt);
            }
        });
        jPanel20.add(btncapnhatNV);

        tblNhanVien.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "HÌNH", "MÃ NV", "HỌ TÊN", "MẬT KHẨU", "CHỨC VỤ", "GIỚI TÍNH", "NGÀY SINH", "SĐT", "EMAIL", "ĐỊA CHỈ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setRowHeight(25);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNhanVien);

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Ảnh nhân viên");

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102)))); // NOI18N
        jPanel26.setLayout(new java.awt.BorderLayout());

        lblhinhNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinhNVMouseClicked(evt);
            }
        });
        jPanel26.add(lblhinhNV, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                            .addComponent(jLabel34)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 385, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNhanVienLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1431, Short.MAX_VALUE)
                .addContainerGap())
        );

        container.add(pnlNhanVien, "nhanVien");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Họ và tên:");

        txtTenClient.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Số điện thoại:");

        txtSDTClient.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setText("Mã khách hàng:");

        txtMaKH.setEditable(false);
        txtMaKH.setBackground(new java.awt.Color(204, 204, 204));
        txtMaKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtMaKH.setFocusable(false);

        tblClient.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblClient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MaKH", "HỌ VÀ TÊN", "SĐT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClient.setGridColor(new java.awt.Color(255, 0, 0));
        tblClient.setRowHeight(25);
        tblClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClient);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 255));
        jLabel26.setText("Quản Lý Khách Hàng");

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnCreateClient.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnCreateClient.setText("Thêm ");
        btnCreateClient.setMaximumSize(new java.awt.Dimension(95, 32));
        btnCreateClient.setMinimumSize(new java.awt.Dimension(95, 32));
        btnCreateClient.setPreferredSize(new java.awt.Dimension(95, 32));
        btnCreateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClientActionPerformed(evt);
            }
        });
        jPanel17.add(btnCreateClient);

        btnUpdateClient.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnUpdateClient.setText("Cập nhật");
        btnUpdateClient.setMaximumSize(new java.awt.Dimension(95, 32));
        btnUpdateClient.setMinimumSize(new java.awt.Dimension(95, 32));
        btnUpdateClient.setPreferredSize(new java.awt.Dimension(95, 32));
        btnUpdateClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClientActionPerformed(evt);
            }
        });
        jPanel17.add(btnUpdateClient);

        btnDeleteClient.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnDeleteClient.setText("Xóa");
        btnDeleteClient.setMaximumSize(new java.awt.Dimension(95, 32));
        btnDeleteClient.setMinimumSize(new java.awt.Dimension(95, 32));
        btnDeleteClient.setPreferredSize(new java.awt.Dimension(95, 32));
        btnDeleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClientActionPerformed(evt);
            }
        });
        jPanel17.add(btnDeleteClient);

        btnRefreshClient.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnRefreshClient.setText("Làm mới");
        btnRefreshClient.setMaximumSize(new java.awt.Dimension(95, 32));
        btnRefreshClient.setMinimumSize(new java.awt.Dimension(95, 32));
        btnRefreshClient.setPreferredSize(new java.awt.Dimension(95, 32));
        btnRefreshClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshClientActionPerformed(evt);
            }
        });
        jPanel17.add(btnRefreshClient);

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1562, Short.MAX_VALUE)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSDTClient)
                    .addComponent(txtMaKH)
                    .addComponent(txtTenClient)
                    .addGroup(pnlKhachHangLayout.createSequentialGroup()
                        .addGroup(pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel23)
                            .addComponent(jLabel25)
                            .addComponent(jLabel24))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenClient, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSDTClient, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1639, Short.MAX_VALUE))
        );

        container.add(pnlKhachHang, "khachHang");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MÃ SP", "HÌNH", "LOẠI SP", "TÊN SẢN PHẨM", "GIÁ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setFont(new java.awt.Font("Times New Roman", 3, 20)); // NOI18N
        tblSanPham.setRowHeight(60);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Quản Lý Sản Phẩm");

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel48.setText("Tìm sản phẩm:");

        txtTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTimKiemMouseClicked(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTaoSP.setText("Tạo sản phẩm");
        btnTaoSP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnTaoSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pblQLSanPhamLayout = new javax.swing.GroupLayout(pblQLSanPham);
        pblQLSanPham.setLayout(pblQLSanPhamLayout);
        pblQLSanPhamLayout.setHorizontalGroup(
            pblQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pblQLSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pblQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1550, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pblQLSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pblQLSanPhamLayout.setVerticalGroup(
            pblQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pblQLSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pblQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pblQLSanPhamLayout.createSequentialGroup()
                        .addComponent(btnTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 82, Short.MAX_VALUE))
                    .addComponent(txtTimKiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1716, Short.MAX_VALUE)
                .addContainerGap())
        );

        container.add(pblQLSanPham, "sanPham");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Quản Lý Kho Hàng");

        tblNL_SP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblNL_SP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Hình", "Mã sản phẩm", "Tên sản phẩm", "Giá Tiền", "Tồn kho", "Tối Thiểu", "Đơn vị"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNL_SP.setRowHeight(25);
        tblNL_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNL_SPMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblNL_SP);

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel21.setLayout(new java.awt.BorderLayout());

        lblhinh_SP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhinh_SPMouseClicked(evt);
            }
        });
        jPanel21.add(lblhinh_SP, java.awt.BorderLayout.CENTER);

        jPanel24.setLayout(new java.awt.GridLayout(6, 2, 5, 5));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel38.setText("Mã nguyên liệu:");
        jPanel24.add(jLabel38);

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Tối thiểu");
        jPanel24.add(jLabel41);

        txtmasp_KH.setEditable(false);
        txtmasp_KH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtmasp_KH.setFocusable(false);
        jPanel24.add(txtmasp_KH);

        txtTThieu_SP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel24.add(txtTThieu_SP);

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel39.setText("Tên nguyên liệu");
        jPanel24.add(jLabel39);

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Tồn kho");
        jPanel24.add(jLabel42);

        txtTSP_KH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel24.add(txtTSP_KH);

        txtTKHo_SP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel24.add(txtTKHo_SP);

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Giá tiền");
        jPanel24.add(jLabel40);

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Đơn vị");
        jPanel24.add(jLabel43);

        txtGT_SP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel24.add(txtGT_SP);

        txtDonVI_SP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jPanel24.add(txtDonVI_SP);

        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnthem_SP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnthem_SP.setText("Đặt thêm");
        btnthem_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem_SPActionPerformed(evt);
            }
        });
        jPanel25.add(btnthem_SP);

        btnsua_SP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnsua_SP.setText("Sửa");
        btnsua_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsua_SPActionPerformed(evt);
            }
        });
        jPanel25.add(btnsua_SP);

        btndatlai_SP.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btndatlai_SP.setText("Làm mới");
        btndatlai_SP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndatlai_SPActionPerformed(evt);
            }
        });
        jPanel25.add(btndatlai_SP);

        btnThemNL.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnThemNL.setText("Thêm mới NL");
        btnThemNL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNLActionPerformed(evt);
            }
        });
        jPanel25.add(btnThemNL);

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 51, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Ảnh Nguyên Liệu");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setText("Số lượng đặt thêm:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane6.setViewportView(txtGhiChu);

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel52.setText("Ghi chú:");

        btnInhoadon.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        btnInhoadon.setText("In hóa đơn");
        btnInhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInhoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlKhoHangLayout = new javax.swing.GroupLayout(pnlKhoHang);
        pnlKhoHang.setLayout(pnlKhoHangLayout);
        pnlKhoHangLayout.setHorizontalGroup(
            pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhoHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 876, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKhoHangLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                                        .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtSLDatThem)
                                            .addComponent(jScrollPane6))
                                        .addGap(0, 141, Short.MAX_VALUE))
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(pnlKhoHangLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(btnInhoadon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93))))
        );
        pnlKhoHangLayout.setVerticalGroup(
            pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhoHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlKhoHangLayout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSLDatThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnlKhoHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlKhoHangLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1606, Short.MAX_VALUE)
                .addContainerGap())
        );

        container.add(pnlKhoHang, "khoHang");

        pblQLHoaDon.setBackground(new java.awt.Color(255, 255, 255));
        pblQLHoaDon.setLayout(new java.awt.BorderLayout());

        pnlTimHD.setBackground(new java.awt.Color(255, 255, 255));
        pnlTimHD.setPreferredSize(new java.awt.Dimension(1270, 80));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel3.setText("Tìm hóa đơn: ");

        txtTimHD.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtTimHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimHDKeyReleased(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("Theo:");

        cboTime.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        cboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Tháng trước", "Năm nay" }));
        cboTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTimeItemStateChanged(evt);
            }
        });

        btnTaoDH.setText("Tạo đơn hàng");
        btnTaoDH.setBorderColor(new java.awt.Color(102, 102, 102));
        btnTaoDH.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnTaoDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoDHActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 25)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 51, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Quản Lý Hóa Đơn");

        javax.swing.GroupLayout pnlTimHDLayout = new javax.swing.GroupLayout(pnlTimHD);
        pnlTimHD.setLayout(pnlTimHDLayout);
        pnlTimHDLayout.setHorizontalGroup(
            pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTimHDLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 622, Short.MAX_VALUE)
                .addComponent(btnTaoDH, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        pnlTimHDLayout.setVerticalGroup(
            pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTimHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoDH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTimHDLayout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlTimHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTimHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboTime))
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );

        pblQLHoaDon.add(pnlTimHD, java.awt.BorderLayout.PAGE_START);

        jScrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHI TIẾT HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N
        jScrollPane8.setPreferredSize(new java.awt.Dimension(450, 500));

        tblCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ SP", "SẢN PHẨM", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(tblCTHoaDon);

        pblQLHoaDon.add(jScrollPane8, java.awt.BorderLayout.LINE_END);

        jScrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DANH SÁCH HÓA ĐƠN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 18))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MÃ HD", "KHÁCH HÀNG", "NHÂN VIÊN", "TỔNG TIỀN", "GIẢM GIÁ", "NGÀY", "TRẠNG THÁI"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblHoaDon);

        pblQLHoaDon.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        container.add(pblQLHoaDon, "hoaDon");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel49.setText("Theo:");

        cmbNam.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tháng", "Năm" }));
        cmbNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNamItemStateChanged(evt);
            }
        });
        cmbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamActionPerformed(evt);
            }
        });

        btnBieuDo.setText("Biểu đồ");
        btnBieuDo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnBieuDo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBieuDoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1139, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblDTC.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tblDTC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "THỜI GIAN", "VỐN", "DOANH THU", "TIỀN LỜI", "TIỀN LỖ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDTC.setRowHeight(25);
        jScrollPane10.setViewportView(tblDTC);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1541, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 1841, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlThongKe.addTab("Doanh thu", jPanel27);

        jPanel29.setLayout(new java.awt.BorderLayout());

        jScrollPane11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu theo sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(0, 0, 255))); // NOI18N

        thongKe_tblSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        thongKe_tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SẢN PHẨM BÁN RA", "TỔNG DOANH THU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        thongKe_tblSP.setRowHeight(25);
        jScrollPane11.setViewportView(thongKe_tblSP);

        jPanel29.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        pnlThongKe.addTab("Sản phẩm", jPanel29);

        jPanel30.setLayout(new java.awt.BorderLayout());

        jScrollPane12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu theo khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(0, 0, 255))); // NOI18N

        thongKe_tblKH.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        thongKe_tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "TỔNG HÓA ĐƠN", "TỔNG DOANH THU "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        thongKe_tblKH.setRowHeight(25);
        jScrollPane12.setViewportView(thongKe_tblKH);

        jPanel30.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        pnlThongKe.addTab("Khách hàng", jPanel30);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thống kê doanh thu theo nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 20), new java.awt.Color(0, 0, 255))); // NOI18N
        jScrollPane13.setForeground(new java.awt.Color(0, 51, 255));

        thongKe_tblNV.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        thongKe_tblNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "MÃ NHÂN VIÊN", "TỔNG NHÂN VIÊN", "TỔNG HÓA ĐƠN", "TỔNG DOANH THU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        thongKe_tblNV.setRowHeight(25);
        jScrollPane13.setViewportView(thongKe_tblNV);

        jPanel31.add(jScrollPane13, java.awt.BorderLayout.CENTER);

        pnlThongKe.addTab("Nhân viên", jPanel31);

        container.add(pnlThongKe, "thongKe");

        Prof_lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Prof_lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Prof_lblHinhMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Mã nhân viên");

        txtMaNV.setEditable(false);
        txtMaNV.setBackground(new java.awt.Color(204, 204, 204));
        txtMaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMaNV.setText("Test");
        txtMaNV.setFocusable(false);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Mật khẩu");

        txtMatKhau.setEditable(false);
        txtMatKhau.setBackground(new java.awt.Color(204, 204, 204));
        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtMatKhau.setFocusable(false);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Họ và tên");

        txtHoTen.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Chức vụ");

        Prof_txtMaCV.setEditable(false);
        Prof_txtMaCV.setBackground(new java.awt.Color(204, 204, 204));
        Prof_txtMaCV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Prof_txtMaCV.setFocusable(false);

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Ngày sinh");

        Prof_txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 687, Short.MAX_VALUE))
                    .addComponent(Prof_txtNgaySinh))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prof_txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel6.add(jPanel7);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("Giới tính");

        buttonGroup1.add(Prof_rdoNam);
        Prof_rdoNam.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Prof_rdoNam.setText("Nam");

        buttonGroup1.add(Prof_rdoNu);
        Prof_rdoNu.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Prof_rdoNu.setText("Nữ");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Prof_rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(Prof_rdoNu)))
                .addContainerGap(647, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prof_rdoNam)
                    .addComponent(Prof_rdoNu))
                .addGap(16, 16, 16))
        );

        jPanel6.add(jPanel8);

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Điện thoại");

        Prof_txtSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(0, 685, Short.MAX_VALUE))
                    .addComponent(Prof_txtSDT))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prof_txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel9.add(jPanel10);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Email");

        Prof_txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Prof_txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prof_txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel9.add(jPanel11);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Địa chỉ");

        Prof_txtDiaChi.setColumns(20);
        Prof_txtDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Prof_txtDiaChi.setRows(3);
        jScrollPane1.setViewportView(Prof_txtDiaChi);

        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        btnUpdateProfile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateProfile.setText("Cập nhật");
        btnUpdateProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlThietLapLayout = new javax.swing.GroupLayout(pnlThietLap);
        pnlThietLap.setLayout(pnlThietLapLayout);
        pnlThietLapLayout.setHorizontalGroup(
            pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlThietLapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThietLapLayout.createSequentialGroup()
                        .addComponent(Prof_lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV)
                            .addComponent(txtMatKhau)
                            .addComponent(txtHoTen)
                            .addGroup(pnlThietLapLayout.createSequentialGroup()
                                .addGroup(pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Prof_txtMaCV)))
                    .addGroup(pnlThietLapLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThietLapLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnUpdateProfile)
                        .addGap(18, 18, 18)
                        .addComponent(btnDoiMK)))
                .addContainerGap())
        );
        pnlThietLapLayout.setVerticalGroup(
            pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThietLapLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlThietLapLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Prof_txtMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Prof_lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlThietLapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDoiMK)
                    .addComponent(btnUpdateProfile))
                .addContainerGap())
        );

        container.add(pnlThietLap, "thietLap");

        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseEntered
        btnHover(btnHome);
    }//GEN-LAST:event_btnHomeMouseEntered

    private void btnHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseExited
        btnExited(btnHome);
    }//GEN-LAST:event_btnHomeMouseExited

    private void btnHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHomeMouseClicked
        btnClicked(btnHome, () -> {
            showCard("trangChu");
            runController(() -> {
                HomeController.init();
            });
        });
    }//GEN-LAST:event_btnHomeMouseClicked

    private void btnEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmployeeMouseEntered
        btnHover(btnEmployee);
    }//GEN-LAST:event_btnEmployeeMouseEntered

    private void btnEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmployeeMouseExited
        btnExited(btnEmployee);
    }//GEN-LAST:event_btnEmployeeMouseExited

    private void btnEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmployeeMouseClicked
        btnClicked(btnEmployee, () -> {
            showCard("nhanVien");
            runController(() -> {
                EmployeeController.init();
            });
        });
    }//GEN-LAST:event_btnEmployeeMouseClicked

    private void btnClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseEntered
        btnHover(btnClient);
    }//GEN-LAST:event_btnClientMouseEntered

    private void btnClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseExited
        btnExited(btnClient);
    }//GEN-LAST:event_btnClientMouseExited

    private void btnClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientMouseClicked
        btnClicked(btnClient, () -> {
            showCard("khachHang");
            runController(() -> {
                ClientController.init();
            });
        });
    }//GEN-LAST:event_btnClientMouseClicked

    private void btnProductMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductMouseEntered
        btnHover(btnProduct);
    }//GEN-LAST:event_btnProductMouseEntered

    private void btnProductMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductMouseExited
        btnExited(btnProduct);
    }//GEN-LAST:event_btnProductMouseExited

    private void btnProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProductMouseClicked
        btnClicked(btnProduct, () -> {
            showCard("sanPham");
            runController(() -> {
                ProductController.fillTableSanPham("");
            });
        });
    }//GEN-LAST:event_btnProductMouseClicked

    private void btnApplianceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApplianceMouseEntered
        btnHover(btnAppliance);
    }//GEN-LAST:event_btnApplianceMouseEntered

    private void btnApplianceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApplianceMouseExited
        btnExited(btnAppliance);
    }//GEN-LAST:event_btnApplianceMouseExited

    private void btnApplianceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnApplianceMouseClicked
        btnClicked(btnAppliance, () -> {
            showCard("khoHang");
            runController(() -> {
                ApplianceController.init();
            });
        });
    }//GEN-LAST:event_btnApplianceMouseClicked

    private void btnReceiptMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReceiptMouseEntered
        btnHover(btnReceipt);
    }//GEN-LAST:event_btnReceiptMouseEntered

    private void btnReceiptMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReceiptMouseExited
        btnExited(btnReceipt);
    }//GEN-LAST:event_btnReceiptMouseExited

    private void btnReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReceiptMouseClicked
        btnClicked(btnReceipt, () -> {
            showCard("hoaDon");
            runController(() -> {
                ReceiptController.init();
            });
        });
    }//GEN-LAST:event_btnReceiptMouseClicked

    private void btnSummaryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSummaryMouseEntered
//        btnHover(btnSummary);
    }//GEN-LAST:event_btnSummaryMouseEntered

    private void btnSummaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSummaryMouseExited
//        btnExited(btnSummary);
    }//GEN-LAST:event_btnSummaryMouseExited

    private void btnSummaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSummaryMouseClicked

//        btnClicked(btnSummary, () -> {
//            showCard("thongKe");
//            runController(()->{
//                SummarryController.init();
//            });
//        });
    }//GEN-LAST:event_btnSummaryMouseClicked

    private void btnProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseEntered
        btnHover(btnProfile);
    }//GEN-LAST:event_btnProfileMouseEntered

    private void btnProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseExited
        btnExited(btnProfile);
    }//GEN-LAST:event_btnProfileMouseExited

    private void btnProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProfileMouseClicked
        btnClicked(btnProfile, () -> {
            showCard("thietLap");
            runController(() -> {
                ProfileController.setForm();
            });
        });
    }//GEN-LAST:event_btnProfileMouseClicked

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        btnHover(btnLogout);
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        btnExited(btnLogout);
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseClicked
        btnClicked(btnLogout, this::logOut);
        this.dispose();
    }//GEN-LAST:event_btnLogoutMouseClicked

    private void btnExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseEntered
        btnHover(btnExit);
    }//GEN-LAST:event_btnExitMouseEntered

    private void btnExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseExited
        btnExited(btnExit);
    }//GEN-LAST:event_btnExitMouseExited

    private void btnExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseClicked
        btnClicked(btnExit, this::exit);
        btnReset(btnExit);
    }//GEN-LAST:event_btnExitMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        PasswordChangeWindow wdw = new PasswordChangeWindow();
        wdw.setReturnWindow(this);
        wdw.setVisible(true);
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnDeleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClientActionPerformed
        HoaDonImple hddao = new HoaDonImple();
        if (hddao.selectByID(txtMaKH.getText()) == null) {
            runController(() -> {
                ClientController.delete();
            });
        } else {
            XMsgBox.inform(null, "Xóa khách hàng thất bại");
            return;
        }
    }//GEN-LAST:event_btnDeleteClientActionPerformed

    private void btnUpdateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClientActionPerformed
        runController(() -> {
            ClientController.update();
        });
    }//GEN-LAST:event_btnUpdateClientActionPerformed

    private void btnCreateClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClientActionPerformed
        runController(() -> {
            ClientController.insert();
        });
    }//GEN-LAST:event_btnCreateClientActionPerformed

    private void tblClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientMouseClicked
        runController(() -> {
            ClientController.tableClick();
        });
    }//GEN-LAST:event_tblClientMouseClicked

    private void btndatlaiNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlaiNvActionPerformed
        runController(() -> {
            EmployeeController.clearForm();
            rdoCvBan.isSelected();
        });
    }//GEN-LAST:event_btndatlaiNvActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
        runController(() -> {
            EmployeeController.delete();
        });
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btncapnhatNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapnhatNVActionPerformed
        runController(() -> {
            EmployeeController.update();
        });
    }//GEN-LAST:event_btncapnhatNVActionPerformed

    private void btnThemNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNvActionPerformed
        runController(() -> {
            EmployeeController.insert();
        });
    }//GEN-LAST:event_btnThemNvActionPerformed

    private void btnthem_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem_SPActionPerformed
        HoaDonNhapImple hdndao = new HoaDonNhapImple();
        String maHDN = hdndao.createIdHDNhap();
        runController(() -> {
            ApplianceController.insertAdd(maHDN);

        });
    }//GEN-LAST:event_btnthem_SPActionPerformed

    private void btndatlai_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndatlai_SPActionPerformed
        runController(() -> {
            ApplianceController.clearForm();
        });
    }//GEN-LAST:event_btndatlai_SPActionPerformed

    private void txtTimHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimHDKeyReleased

        runController(() -> {
            ReceiptController.search();
        });
    }//GEN-LAST:event_txtTimHDKeyReleased

    private void cboTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTimeItemStateChanged

        runController(() -> {
            ReceiptController.fillTableBills();
        });
    }//GEN-LAST:event_cboTimeItemStateChanged

    private void btnTaoDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoDHActionPerformed
        SubController subFrame = new CreateOrdersJFrame(null);
        subFrame.setReturnWindow(this);
        subFrame.setVisible(true);
        this.setVisible(false);
        ReceiptController.fillTableBills();
    }//GEN-LAST:event_btnTaoDHActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        if (evt.getClickCount() == 1) {
            runController(() -> {
                ReceiptController.fillDetailBills();
            });
        } else {
            DefaultTableModel model = (DefaultTableModel) tblHoaDon.getModel();
            String mahd = model.getValueAt(tblHoaDon.getSelectedRow(), 0) + "";
            SubController subFrame = new CreateOrdersJFrame(mahd);
            subFrame.setReturnWindow(this);
            subFrame.setVisible(true);
            this.setVisible(false);
            ReceiptController.fillTableBills();
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnUpdateProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProfileActionPerformed
        runController(() -> {
            ProfileController.updateProfile();
        });
        setUser();
    }//GEN-LAST:event_btnUpdateProfileActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        btnClicked(btnProfile, () -> {
            showCard("thietLap");
            runController(() -> {
                ProfileController.setForm();
            });
        });
    }//GEN-LAST:event_jPanel1MouseClicked

    private void Prof_lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Prof_lblHinhMouseClicked
        if (evt.getClickCount() == 2) {
            runController(() -> {
                ProfileController.chonAnh();
            });
        }
    }//GEN-LAST:event_Prof_lblHinhMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        if (evt.getClickCount() == 2) {
            runController(() -> {
                ProductController.chuyenTrang();
            });
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        runController(() -> {
            EmployeeController.curr = table.getSelectedRow();
            EmployeeController.edit();
        });
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblNL_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNL_SPMouseClicked
        runController(() -> {
            ApplianceController.tableClick();
        });
    }//GEN-LAST:event_tblNL_SPMouseClicked

    private void lblhinhNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinhNVMouseClicked
        runController(() -> {
            EmployeeController.setImageProduct();
        });
    }//GEN-LAST:event_lblhinhNVMouseClicked

    private void txtTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimKiemMouseClicked

    }//GEN-LAST:event_txtTimKiemMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        runController(() -> {
            ProductController.fillTableSanPham(txtTimKiem.getText());
        });
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void btnTaoSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoSPActionPerformed
        if (!XAuth.isAdmin()) {
            return;
        }
        runController(() -> {
            ProductController.taoSanPhamMoi();
        });
    }//GEN-LAST:event_btnTaoSPActionPerformed

    private void lblhinh_SPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhinh_SPMouseClicked
        runController(() -> {
            ApplianceController.chonAnh();
        });
    }//GEN-LAST:event_lblhinh_SPMouseClicked

    private void cmbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamActionPerformed

    }//GEN-LAST:event_cmbNamActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        btnClicked(btnSummary, () -> {
            showCard("thongKe");
            runController(() -> {
                this.fillTableSanPham();
                this.fillTableNhanVien();
                this.fillTableKhachHang();
                this.fillDoanhThuDay();
            });
        });
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        btnHover(btnSummary);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        btnExited(btnSummary);
    }//GEN-LAST:event_jLabel10MouseExited

    private void cmbNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNamItemStateChanged
        if ((cmbNam.getSelectedItem() + "").equals("Ngày")) {
            this.fillDoanhThuDay();
        } else if ((cmbNam.getSelectedItem() + "").equals("Tháng")) {
            this.fillDoanhThuMonth();
        } else {
            this.fillDoanhThuYear();
        }
    }//GEN-LAST:event_cmbNamItemStateChanged

    private void btnRefreshClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshClientActionPerformed
        runController(() -> {
            ClientController.clearForm();
        });
    }//GEN-LAST:event_btnRefreshClientActionPerformed

    private void btnThemNLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNLActionPerformed
        HoaDonNhapImple hdndao = new HoaDonNhapImple();
        String maHDN = hdndao.createIdHDNhap();
        runController(() -> {
            ApplianceController.insert(maHDN);
        });
    }//GEN-LAST:event_btnThemNLActionPerformed

    private void btnBieuDoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBieuDoActionPerformed
        new ChartJDialog(this, true).setVisible(true);
    }//GEN-LAST:event_btnBieuDoActionPerformed

    private void btnInhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInhoadonActionPerformed
        runController(() -> {
            ApplianceController.inHoaDon();
        });
    }//GEN-LAST:event_btnInhoadonActionPerformed

    private void btnsua_SPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsua_SPActionPerformed
        runController(() -> {
            ApplianceController.update();
        });
    }//GEN-LAST:event_btnsua_SPActionPerformed

    private void exit() {
        boolean res = XMsgBox.confirm(this, "Chắc chắn muốn thoát ?");
        if (res) {
            System.exit(0);
        } else {
            btnReset(btnExit);
        }
    }

    private void logOut() {
        XAuth.clear();
        new MainPageFrame(false).setVisible(true);
    }

    private void fillTableSanPham() {
        DefaultTableModel model = (DefaultTableModel) thongKe_tblSP.getModel();
        List<Object[]> list = tkdao.getDTSP();
        NumberFormat formatter = new DecimalFormat("####,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], row[1], row[2], formatter.format(row[3])});
        }
    }

    public void fillTableKhachHang() {
        DefaultTableModel model = (DefaultTableModel) thongKe_tblKH.getModel();
        List<Object[]> list = tkdao.getDTKH();
        NumberFormat formatter = new DecimalFormat("###,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], row[1], row[2], formatter.format(row[3])});
        }
    }

    public void fillTableNhanVien() {
        DefaultTableModel model = (DefaultTableModel) thongKe_tblNV.getModel();
        List<Object[]> list = tkdao.getDTNV();
        NumberFormat formatter = new DecimalFormat("####,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], row[1], row[2], formatter.format(row[3])});
        }
    }

    public void fillDoanhThuDay() {
        DefaultTableModel model = (DefaultTableModel) tblDTC.getModel();
        List<Object[]> list = tkdao.getDTTDAY();
        NumberFormat formatter = new DecimalFormat("####,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], formatter.format(row[1]), formatter.format(row[2]), formatter.format(row[3]), formatter.format(row[4])});
        }
    }

    public void fillDoanhThuMonth() {
        DefaultTableModel model = (DefaultTableModel) tblDTC.getModel();
        List<Object[]> list = tkdao.getDTTMonth();
        NumberFormat formatter = new DecimalFormat("####,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], formatter.format(row[1]), formatter.format(row[2]), formatter.format(row[3]), formatter.format(row[4])});
        }
    }

    public void fillDoanhThuYear() {
        DefaultTableModel model = (DefaultTableModel) tblDTC.getModel();
        List<Object[]> list = tkdao.getDTTYEAR();
        NumberFormat formatter = new DecimalFormat("####,###.#");
        model.setRowCount(0);
        for (Object[] row : list) {
            model.addRow(new Object[]{row[0], formatter.format(row[1]), formatter.format(row[2]), formatter.format(row[3]), formatter.format(row[4])});
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPageFrame(true).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Prof_lblHinh;
    private javax.swing.JRadioButton Prof_rdoNam;
    private javax.swing.JRadioButton Prof_rdoNu;
    private javax.swing.JTextArea Prof_txtDiaChi;
    private javax.swing.JTextField Prof_txtEmail;
    private javax.swing.JTextField Prof_txtMaCV;
    private javax.swing.JTextField Prof_txtNgaySinh;
    private javax.swing.JTextField Prof_txtSDT;
    private javax.swing.JPanel btnAppliance;
    private button.ButtonCustom btnBieuDo;
    private javax.swing.JPanel btnClient;
    private javax.swing.JButton btnCreateClient;
    private javax.swing.JButton btnDeleteClient;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JPanel btnEmployee;
    private javax.swing.JPanel btnExit;
    private javax.swing.JPanel btnHome;
    private javax.swing.JButton btnInhoadon;
    private javax.swing.JPanel btnLogout;
    private javax.swing.JPanel btnProduct;
    private javax.swing.JPanel btnProfile;
    private javax.swing.JPanel btnReceipt;
    private javax.swing.JButton btnRefreshClient;
    private javax.swing.JPanel btnSummary;
    private button.ButtonCustom btnTaoDH;
    private button.ButtonCustom btnTaoSP;
    private javax.swing.JButton btnThemNL;
    private javax.swing.JButton btnThemNv;
    private javax.swing.JButton btnUpdateClient;
    private javax.swing.JButton btnUpdateProfile;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JButton btncapnhatNV;
    private javax.swing.JButton btndatlaiNv;
    private javax.swing.JButton btndatlai_SP;
    private javax.swing.JButton btnsua_SP;
    private javax.swing.JButton btnthem_SP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cboTime;
    private javax.swing.JComboBox<String> cmbNam;
    private javax.swing.JPanel container;
    private javax.swing.JLabel homeEmpName;
    private javax.swing.JLabel homeEmpPic;
    private javax.swing.JLabel homeLblSum;
    private javax.swing.JLabel homeProdName;
    private javax.swing.JLabel homeProdPic;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblhinhNV;
    private javax.swing.JLabel lblhinh_SP;
    private jpanelrounded.PanelRound panelRound1;
    private jpanelrounded.PanelRound panelRound6;
    private jpanelrounded.PanelRound panelRound7;
    private javax.swing.JPanel pblQLHoaDon;
    private javax.swing.JPanel pblQLSanPham;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlKhoHang;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlShowPanel;
    private javax.swing.JPanel pnlThietLap;
    private javax.swing.JTabbedPane pnlThongKe;
    private javax.swing.JPanel pnlTimHD;
    private javax.swing.JRadioButton rdoCvAdmin;
    private javax.swing.JRadioButton rdoCvBan;
    private javax.swing.JRadioButton rdoCvKho;
    private javax.swing.JRadioButton rdoNvNam;
    private javax.swing.JRadioButton rdoNvNu;
    private javax.swing.JPanel sideNav;
    private javax.swing.JLabel side_lblPic;
    private javax.swing.JLabel side_lblUser;
    private customjtable.CustomJTable tblCTHoaDon;
    private javax.swing.JTable tblClient;
    private javax.swing.JTable tblDTC;
    private customjtable.CustomJTable tblHoaDon;
    private javax.swing.JTable tblNL_SP;
    private javax.swing.JTable tblNhanVien;
    private customjtable.CustomJTable tblSanPham;
    private javax.swing.JTable thongKe_tblKH;
    private javax.swing.JTable thongKe_tblNV;
    private javax.swing.JTable thongKe_tblSP;
    private javax.swing.JTextField txtDonVI_SP;
    private javax.swing.JTextField txtGT_SP;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoVaTenNV;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtManv;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtSDTClient;
    private javax.swing.JTextField txtSLDatThem;
    private javax.swing.JTextField txtTKHo_SP;
    private javax.swing.JTextField txtTSP_KH;
    private javax.swing.JTextField txtTThieu_SP;
    private javax.swing.JTextField txtTenClient;
    private javax.swing.JTextField txtTimHD;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextArea txtdiachi_NV;
    private javax.swing.JTextField txtemail_NV;
    private javax.swing.JTextField txtmasp_KH;
    private javax.swing.JPasswordField txtmatkhauNV;
    private javax.swing.JTextField txtnamsinh_NV;
    private javax.swing.JTextField txtsodienthoai_NV;
    // End of variables declaration//GEN-END:variables
    
}
