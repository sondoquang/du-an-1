package views;

import entities.NguyenLieu;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import utils.XImage;
import utils.XMsgBox;
import controller.NguyenLieuController;
import controlls.ProductController;
import daoImpl.ChiTietSanPhamDAO;
import daoImpl.NguyenLieuDAO;
import daoimpl.SanPhamDAO;
import entities.ChiTietSanPham;
import entities.SanPham;
import utils.XAuth;
import utils.XTable;
import utils.XValidate;

public class CheBienJDialog extends javax.swing.JDialog implements NguyenLieuController {
    NguyenLieuDAO nldao = new NguyenLieuDAO();
    ChiTietSanPhamDAO ctspdao = new ChiTietSanPhamDAO();
    String maSPUD;
    public CheBienJDialog(java.awt.Frame parent, boolean modal, String maSPUD) {
        super(parent, modal);
        initComponents();
        this.maSPUD = maSPUD;
        this.initialize();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panelRound2 = new jpanelrounded.PanelRound();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMaSP = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaVon = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThanhPhan = new customjtable.CustomJTable();
        jPanel5 = new javax.swing.JPanel();
        btnreset = new button.ButtonCustom();
        btnUpdate = new button.ButtonCustom();
        btnThemSP = new button.ButtonCustom();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNguyenLieu = new customjtable.CustomJTable();
        jLabel9 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jSplitPane1.setDividerLocation(750);
        jSplitPane1.setDividerSize(15);
        jSplitPane1.setOneTouchExpandable(true);

        panelRound2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel4.setText("Mã sản phẩm:");
        jLabel4.setToolTipText("");

        txtMaSP.setEditable(false);
        txtMaSP.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMaSP.setText("Mã sản phẩm tự động");
        jScrollPane2.setViewportView(txtMaSP);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel5.setText("Tên sản phẩm:");
        jLabel5.setToolTipText("");

        txtTenSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel6.setText("Loại sản phẩm:");
        jLabel6.setToolTipText("");

        cboLoaiSP.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        cboLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trà Sữa", "Cà Phê", "Trà", " " }));

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel4.setForeground(new java.awt.Color(204, 204, 204));

        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel7.setText("Giá bán:");
        jLabel7.setToolTipText("");

        txtGiaBan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel10.setText("Giá vốn:");
        jLabel10.setToolTipText("");

        txtGiaVon.setEditable(false);
        txtGiaVon.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaVon, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(74, 74, 74)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaVon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thành phần", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel8.setText("Hàng hóa thành phần:");
        jLabel8.setToolTipText("");

        jScrollPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane3MouseClicked(evt);
            }
        });

        tblThanhPhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NGUYÊN LIỆU", "TÊN NGUYÊN LIỆU", "SỐ LƯỢNG", "ĐƠN VỊ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThanhPhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThanhPhanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThanhPhan);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnreset.setText("Làm mới form");
        btnreset.setBorderColor(new java.awt.Color(153, 255, 255));
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });
        jPanel5.add(btnreset);

        btnUpdate.setText("Cập nhật sản phẩm");
        btnUpdate.setBorderColor(new java.awt.Color(153, 255, 255));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel5.add(btnUpdate);

        btnThemSP.setText("Thêm sản phẩm");
        btnThemSP.setBorderColor(new java.awt.Color(153, 255, 255));
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemSP);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelRound2);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        tblNguyenLieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MÃ NL", "TÊN NL", "HÌNH"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNguyenLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguyenLieuMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNguyenLieu);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("TÌM KIẾM:");

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        this.setImageProduct();
    }//GEN-LAST:event_lblImageMouseClicked

    private void tblNguyenLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguyenLieuMouseClicked
        if(XAuth.isAdmin())
        this.chonSoLuong();
    }//GEN-LAST:event_tblNguyenLieuMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased

    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblThanhPhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThanhPhanMouseClicked
        if(XAuth.isAdmin()) 
            this.checkSLThanhPhan();
    }//GEN-LAST:event_tblThanhPhanMouseClicked

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        this.searchNL();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
        this.resetForm();
    }//GEN-LAST:event_btnresetActionPerformed

    private void jScrollPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane3MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(checkEmpty()){
            this.update();
        }else{
            XMsgBox.inform(this, "Vui lòng không được để trống thông tin !!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        if(checkEmpty()){
            this.insertSanPham();
        }else{
            XMsgBox.inform(this, "Vui lòng không được để trống thông tin !!");
        }
    }//GEN-LAST:event_btnThemSPActionPerformed

//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CheBienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CheBienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CheBienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CheBienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                CheBienJDialog dialog = new CheBienJDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.ButtonCustom btnThemSP;
    private button.ButtonCustom btnUpdate;
    private button.ButtonCustom btnreset;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblImage;
    private jpanelrounded.PanelRound panelRound2;
    private customjtable.CustomJTable tblNguyenLieu;
    private customjtable.CustomJTable tblThanhPhan;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaVon;
    private javax.swing.JTextPane txtMaSP;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables

    @Override
    public void initialize() {
        this.setLocationRelativeTo(this);
        this.setTitle("Chế Biến");
        this.fillTableNguyenLieu();
        txtMaSP.setText(spdao.createMaSP());
        if(maSPUD != null){
            this.setForm();
        }
        this.updateStatus();
    }

    @Override
    public void fillTableNguyenLieu() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        try {
            List<NguyenLieu> list = nldao.selectAll();
            model.setRowCount(0);
            for (NguyenLieu nl : list) {
                Object[] row = {
                    nl.getMaNL(),
                    nl.getTenNL(),
                    nl.getHinh()
                };
                model.addRow(row);
            }
            XTable.insertImage(tblNguyenLieu, 2, 100, 100, "IngriImages");
        } catch (Exception e) {
            XMsgBox.alert(this, "Lỗi truy vấn dữ liệu !!");
        }
    }

    @Override
    public void setImageProduct() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            XImage.save("ProdImages", file);
            this.setImage(file.getName());
        }
    }

    public void setImage(String nameFile) {
        try {
            ImageIcon Icon = XImage.read("ProdImages", nameFile);
            lblImage.setIcon(XImage.getResized(XImage.read("ProdImages", nameFile), lblImage.getWidth(), lblImage.getHeight()));
            lblImage.setToolTipText(nameFile);
        } catch (Exception e) {
            XMsgBox.alert(this, "Ảnh không có sẵn .");
        }

    }

    @Override
    public void fillTableThanhPhan(String maNL, Integer soLuong) {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        NguyenLieu nl = nldao.selectByID(maNL);
        Object[] row = {
            nl.getMaNL(),
            nl.getTenNL(),
            soLuong,
            nl.getDonVi()
        };
        model.addRow(row);
    }

    @Override
    public void searchNL() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        try {
            List<NguyenLieu> list = nldao.selectByName(txtSearch.getText());
            model.setRowCount(0);
            for (NguyenLieu nl : list) {
                Object[] row = {
                    nl.getMaNL(),
                    nl.getTenNL(),
                    nl.getHinh()};
                model.addRow(row);
            }
            XTable.insertImage(tblNguyenLieu, 2, 100, 100, "IngriImages");
        } catch (Exception e) {
            XMsgBox.alert(this, "Lỗi kết nối database !!");
        }
    }

    @Override
    public void deleteNL() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        int row = tblThanhPhan.getSelectedRow();
        if (model.getRowCount() >= 0) {
            model.removeRow(row);
        }
    }
    SanPhamDAO spdao = new SanPhamDAO();
    String maSP;

    @Override
    public void insertSanPham() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        maSP = spdao.createMaSP();
        if (XValidate.number(txtGiaBan.getText())) {
            if (this.checkGiaban()) {
                if (txtTenSP.getText() != null && lblImage.getToolTipText() != null) {
                    if (model.getRowCount() > 0) {
                        SanPham sp = new SanPham();
                        sp.setLoaiSP(cboLoaiSP.getSelectedItem() + "");
                        sp.setTenSP(txtTenSP.getText());
                        sp.setGiaTien(Double.valueOf(txtGiaBan.getText()));
                        sp.setHinh(lblImage.getToolTipText());
                        spdao.insert(sp);
                        this.insertCTSanPham(maSP);
                        XMsgBox.alert(this, "Thêm sản phẩm thành công");
                    } else {
                        XMsgBox.alert(this, "Thành phần không được để trống !!");
                    }
                } else {
                    XMsgBox.alert(this, "Tên và hình không được để trống !!");
                }
            } else {
                XMsgBox.alert(this, "Giá bán phải lớn hơn giá vốn !!");
            }
        } else {
            XMsgBox.alert(this, "Sai định dạng giá bán !!");
        }
    }

    @Override
    public void insertCTSanPham(String masp) {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        ChiTietSanPham ctsp = new ChiTietSanPham();
        for (int i = 0; i < tblThanhPhan.getRowCount(); i++) {
            NguyenLieu nl = nldao.selectByID(model.getValueAt(i, 0)+"");
            int soLuong = Integer.parseInt(model.getValueAt(i, 2) + "");
            ctsp.setMaSP(masp);
            ctsp.setMaNL(model.getValueAt(i, 0) + "");
            ctsp.setSoLuong(soLuong);
            ctsp.setGiaVon(nl.getMinGia()*soLuong);
            ctspdao.insert(ctsp);
        }
        this.resetForm();
    }

    @Override
    public void resetForm() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        txtTenSP.setText("");
        txtGiaBan.setText("");
        lblImage.setIcon(null);
        lblImage.setToolTipText("");
        txtGiaVon.setText("");
        txtMaSP.setText(spdao.createMaSP());
        btnThemSP.setEnabled(true);
        updateStatus();
        model.setRowCount(0);
    }

    public void chonSoLuong() {
        DefaultTableModel model = (DefaultTableModel) tblNguyenLieu.getModel();
        Integer soLuong;
        String sl = XMsgBox.prompt(this, "Nhập số lượng");
        if (XValidate.positiveInt(sl) == true && !sl.equals("0")) {
            soLuong = Integer.valueOf(sl);
            String manl  = model.getValueAt(tblNguyenLieu.getSelectedRow(), 0)+"";
            if(this.checkIndexProduct(manl) == -1){
                 this.fillTableThanhPhan(model.getValueAt(tblNguyenLieu.getSelectedRow(), 0) + "", soLuong);
            }else{
                DefaultTableModel modeltp = (DefaultTableModel) tblThanhPhan.getModel();
                modeltp.setValueAt(soLuong, this.checkIndexProduct(manl), 2);
            }
        } else {
            XMsgBox.alert(this, "Số lượng phải là nguyên dương !!");
        }
        txtGiaVon.setText(this.giaVon()+"");
    }

    public void checkSLThanhPhan() {
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        Integer soLuong;
        String sl = XMsgBox.prompt(this, "Nhập số lượng");
        if (XValidate.positiveInt(sl)) {
            soLuong = Integer.valueOf(sl);
            model.setValueAt(soLuong + "", tblThanhPhan.getSelectedRow(), 2);
            if (soLuong == 0) {
                model.removeRow(tblThanhPhan.getSelectedRow());
            }
        } else {
            XMsgBox.alert(this, "Số lượng phải là nguyên dương !!");
        }
        txtGiaVon.setText(this.giaVon()+"");
    }

    private Double giaVon() {
        Double giaVon = 0.0;
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        for (int i = 0; i < tblThanhPhan.getRowCount(); i++) {
            NguyenLieu nl = nldao.selectByID(model.getValueAt(i, 0) + "");
            Double minGia = nl.getMinGia();
            giaVon += minGia * (Integer.valueOf(model.getValueAt(i, 2) + ""));
        }
        return giaVon;
    }

    private boolean checkGiaban() {
        Double giaBan = Double.valueOf(txtGiaBan.getText());
        Double giaVon = Double.valueOf(txtGiaVon.getText());
        return (giaBan - giaVon) > 0;
    }
    @Override
    public void update(){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        try {
            SanPham sp = new SanPham();
            sp.setMaSP(txtMaSP.getText());
            sp.setTenSP(txtTenSP.getText());
            sp.setLoaiSP(cboLoaiSP.getSelectedItem()+"");
            sp.setGiaTien(Double.parseDouble(txtGiaBan.getText()));
            sp.setHinh(lblImage.getToolTipText());
            spdao.update(sp);
            ctspdao.delete(maSPUD);
            this.insertCTSanPham(maSPUD);
            XMsgBox.inform(this, "Cập nhật thành công.");
        } catch (Exception e) {
            XMsgBox.inform(this, "Cập nhật thất bại.");
        }
    }
    
    @Override
    public void setForm(){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        try {
            SanPham sp = (SanPham) spdao.selectByID(maSPUD);
            txtMaSP.setText(sp.getMaSP());
            txtGiaBan.setText(sp.getGiaTien()+"");
            txtTenSP.setText(sp.getTenSP());
            if(sp.getLoaiSP().equals("Trà Sữa")){
                cboLoaiSP.setSelectedIndex(0);
            }else if(sp.getLoaiSP().equals("Cà Phê")){
                cboLoaiSP.setSelectedIndex(1);
            }else{
                cboLoaiSP.setSelectedIndex(2);
            }
            this.setImage(sp.getHinh());
            List <ChiTietSanPham> list = ctspdao.selectAllApplianceForPro(maSPUD);
            for(ChiTietSanPham ctsp: list){
                NguyenLieu nl = nldao.selectByID(ctsp.getMaNL());
                Object [] row ={
                    ctsp.getMaNL(),
                    nl.getTenNL(),
                    ctsp.getSoLuong(),
                    nl.getDonVi()
                };
                model.addRow(row);
            }
            txtGiaVon.setText(this.giaVon()+"");
        } catch (Exception e) {
        }
    }
    
    @Override
    public boolean checkEmpty(){
        if(txtGiaBan.getText().equals(""))
            return false;
        if(txtTenSP.getText().equals(""))
            return false;
        if((cboLoaiSP.getSelectedItem()+"").equals(""))
            return false;
        if(lblImage.getToolTipText() == null)
            return false;
        return true;
    }
    
    
    @Override
    public int checkIndexProduct(String manl){
        DefaultTableModel model = (DefaultTableModel) tblThanhPhan.getModel();
        for(int i=0 ; i<model.getRowCount() ; i++){
            String maNL = model.getValueAt(i, 0)+"";
            if(manl.equals(maNL))
                return i;
        }
        return -1;
    }
    
    @Override
    public void updateStatus(){
        if(!txtMaSP.getText().equals(spdao.createMaSP())){
            btnThemSP.setEnabled(false);
        }else{
            btnUpdate.setEnabled(false);
        }
        if(!XAuth.isAdmin()){
            btnThemSP.setEnabled(false);
            btnreset.setEnabled(false);
            btnUpdate.setEnabled(false);
        }
    }
}
