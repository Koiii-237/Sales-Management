/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ChuongTrinhKMDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuongTrinhKhuyenMai;
/**
 *
 * @author ADMIN
 */
public class CTKM extends javax.swing.JPanel {

        DefaultTableModel tableModel;
    ChuongTrinhKMDAO kmDao = new ChuongTrinhKMDAO();
    /**
     * Creates new form CTKM
     */
    public CTKM() {
        initComponents();
        initTable();
        fillTable();
    }
    public void initTable() {
        String[] cols = new String[]{"Mã KM", "Phần trăm KM", "Thời gian bắt đầu", "Thời gian kết thúc", "Mã SP"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        tblCTKM.setModel(tableModel);
    }
     public void fillTable() {
        tableModel.setRowCount(0);
        for (ChuongTrinhKhuyenMai km : kmDao.getALL()) {
            tableModel.addRow(kmDao.getRow(km));
        }
    }
     private boolean validateForm() {
        if (txtMaKM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khuyến mãi.");
            return false;
        }
        if (txtPhantramKM.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm khuyến mãi.");
            return false;
        }
        if (txtThoigianbatdau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian bắt đầu.");
            return false;
        }
        if (txtThoigianketthuc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thời gian kết thúc.");
            return false;
        }
        if (txtMaSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
            return false;
        }
        try {
            double gt = Double.parseDouble(txtPhantramKM.getText().trim());
            if (gt <= 0 || gt >= 100) {
                JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi phải > 0 và < 100 (%).");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phần trăm khuyến mãi phải là số.");
            return false;
        }

        return true;
     }
     public boolean showDetails() {
        int i = tblCTKM.getSelectedRow();
        if (i >= 0) {
            ChuongTrinhKhuyenMai km = kmDao.getALL().get(i);
            txtMaKM.setText(String.valueOf(km.getMaKM()));
            txtPhantramKM.setText(String.valueOf(km.getPhanTramKM()));
            txtThoigianbatdau.setText(String.valueOf(km.getThoiGianBatDau()));
            txtThoigianketthuc.setText(String.valueOf(km.getThoiGianKetThuc()));
            txtMaSP.setText(String.valueOf(km.getMaSP()));
            return true;
        } else {
            return false;
        }
    }
     public void addKM() {
        String maKM = txtMaKM.getText();
        double phanTramKM = Double.parseDouble(txtPhantramKM.getText());
        String thoiGianBatDau = txtThoigianbatdau.getText();
        String thoiGianKetThuc = txtThoigianketthuc.getText();
        String maSP = txtMaSP.getText();
        ChuongTrinhKhuyenMai km = new ChuongTrinhKhuyenMai(maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP);
        int result = kmDao.insert(km);
        if (result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm khuyến mãi mới thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
        }
    }
     public void updateKM() {
       int i = tblCTKM.getSelectedRow();
       if (i >= 0) {
        ChuongTrinhKhuyenMai kmcu = kmDao.getALL().get(i);
        String MaCu = kmcu.getMaKM();

        String maKM = txtMaKM.getText();
        double phanTramKM = Double.parseDouble(txtPhantramKM.getText());
        String thoiGianBatDau = txtThoigianbatdau.getText();
        String thoiGianKetThuc = txtThoigianketthuc.getText();
        String maSP = txtMaSP.getText();
        ChuongTrinhKhuyenMai kmm = new ChuongTrinhKhuyenMai(maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP);
        int result = kmDao.insert(kmm);
            if (result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Sửa khuyến mãi thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi sửa khuyến mãi!");
        }
    }
}
     public void deleteKM() {
        String maKM = txtMaKM.getText();
        double phanTramKM = Double.parseDouble(txtPhantramKM.getText());
        String thoiGianBatDau = txtThoigianbatdau.getText();
        String thoiGianKetThuc = txtThoigianketthuc.getText();
        String maSP = txtMaSP.getText();
        ChuongTrinhKhuyenMai km = new ChuongTrinhKhuyenMai(maKM, phanTramKM, thoiGianBatDau, thoiGianKetThuc, maSP);

        int result = kmDao.insert(km);
            if (result == 1) {
            fillTable();
            JOptionPane.showMessageDialog(this, "Xóa khuyến mãi mới thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
        }
    }
     public void reset(){
         txtMaKM.setText("");
         txtPhantramKM.setText("");
         txtThoigianbatdau.setText("");
         txtThoigianketthuc.setText("");
         txtMaSP.setText("");
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbThoigianbatdau = new javax.swing.JLabel();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTKM = new javax.swing.JTable();
        lbMaSP = new javax.swing.JLabel();
        txtThoigianketthuc = new javax.swing.JTextField();
        btnLammoi = new javax.swing.JButton();
        lbThoigianketthuc = new javax.swing.JLabel();
        lbMaKM = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        btnSua2 = new javax.swing.JButton();
        lbPhantramKM = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        txtPhantramKM = new javax.swing.JTextField();
        txtMaSP = new javax.swing.JTextField();
        txtThoigianbatdau = new javax.swing.JTextField();
        lbCTHD = new javax.swing.JLabel();

        lbThoigianbatdau.setText("Thời gian bắt đầu:");

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        tblCTKM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCTKM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTKMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTKM);

        lbMaSP.setText("Mã SP:");

        txtThoigianketthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThoigianketthucActionPerformed(evt);
            }
        });

        btnLammoi.setText("LÀM MỚI");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        lbThoigianketthuc.setText("Thời gian kết thúc:");

        lbMaKM.setText("Mã KM:");

        txtMaKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKMActionPerformed(evt);
            }
        });

        btnSua2.setText("SỬA");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        lbPhantramKM.setText("Phần trăm KM:");

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        txtPhantramKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhantramKMActionPerformed(evt);
            }
        });

        lbCTHD.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbCTHD.setForeground(new java.awt.Color(0, 0, 255));
        lbCTHD.setText("CHƯƠNG TRÌNH KHUYẾN MÃI");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbMaKM)
                                    .addComponent(lbThoigianbatdau)
                                    .addComponent(lbPhantramKM)
                                    .addComponent(lbThoigianketthuc)
                                    .addComponent(lbMaSP))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhantramKM, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThoigianbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCTHD)
                                    .addComponent(txtThoigianketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbCTHD)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbMaKM)
                            .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPhantramKM)
                            .addComponent(txtPhantramKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThoigianbatdau)
                            .addComponent(txtThoigianbatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbThoigianketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtThoigianketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnLammoi)
                        .addGap(42, 42, 42)
                        .addComponent(btnThem)
                        .addGap(38, 38, 38)
                        .addComponent(btnSua2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMaSP)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteKM();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtThoigianketthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThoigianketthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThoigianketthucActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        updateKM();
    }//GEN-LAST:event_btnSua2ActionPerformed

    private void txtPhantramKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhantramKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhantramKMActionPerformed

    private void txtMaKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKMActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addKM();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblCTKMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTKMMouseClicked
        // TODO add your handling code here:
        showDetails();
    }//GEN-LAST:event_tblCTKMMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCTHD;
    private javax.swing.JLabel lbMaKM;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbPhantramKM;
    private javax.swing.JLabel lbThoigianbatdau;
    private javax.swing.JLabel lbThoigianketthuc;
    private javax.swing.JTable tblCTKM;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtPhantramKM;
    private javax.swing.JTextField txtThoigianbatdau;
    private javax.swing.JTextField txtThoigianketthuc;
    // End of variables declaration//GEN-END:variables
}
