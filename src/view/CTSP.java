/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ChiTietSPDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietSanPham;

/**
 *
 * @author ADMIN
 */
public class CTSP extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    ChiTietSPDAO ctspDao = new ChiTietSPDAO();

    /**
     * Creates new form CTSP
     */
    public CTSP() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã CTSP", "Mã SP", "Tên SP", "Đơn Giá", "Ngày Nhập", "Số Lượng Sản Phẩm"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        tblCTSP.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (ChiTietSanPham km : ctspDao.getALL()) {
            tableModel.addRow(ctspDao.getRow(km));
        }
    }

    public void reset() {
        txtMaCTSP.setText("");
        txtMasp.setText("");
        txtTensp.setText("");
        txtDongia.setText("");
        txtNgaynhap.setText("");
        txtSoluongSP.setText("");
    }

    private boolean validateForm() {
        if (txtMaCTSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chi tiết sản phẩm.");
            return false;
        }
        if (txtMasp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm.");
            return false;
        }
        if (txtTensp.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm.");
            return false;
        }
        if (txtDongia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá.");
            return false;
        }
        if (txtNgaynhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày.");
            return false;
        }
        if (txtSoluongSP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng sản phẩm.");
            return false;
        }
        return true;
    }

    public void showDetails() {
        int i = tblCTSP.getSelectedRow();
        if (i >= 0) {
            ChiTietSanPham ctsp = ctspDao.getALL().get(i);
            txtMaCTSP.setText(String.valueOf(ctsp.getMaCTSP()));
            txtMasp.setText(String.valueOf(ctsp.getMaSP()));
            txtTensp.setText(String.valueOf(ctsp.getTenSP()));
            txtDongia.setText(String.valueOf(ctsp.getDonGia()));
            txtNgaynhap.setText(String.valueOf(ctsp.getNgayNhap()));
            txtSoluongSP.setText(String.valueOf(ctsp.getSoLuongSanPham()));
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin ở trong bảng!!!", "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addCTSP() {
        try {
            if (validateForm()) {
                String maCTSP = txtMaCTSP.getText();
                String maSP = txtMasp.getText();
                String tenSP = txtTensp.getText();
                double donGia = Double.parseDouble(txtDongia.getText());
                String ngayNhap = txtNgaynhap.getText();
                int soLuongSanPham = Integer.parseInt(txtSoluongSP.getText());
                ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham);
                int result = ctspDao.insert(ctsp);
                if (result == 1) {
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm mới thành công!", "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
                    reset();
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!", "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
                    reset();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateCTSP() {
        try {
            if (validateForm()) {
                int index = tblCTSP.getSelectedRow();
                if (index >= 0) {
                    String maCTSP = txtMaCTSP.getText();
                    String maSP = txtMasp.getText();
                    String tenSP = txtTensp.getText();
                    double donGia = Double.parseDouble(txtDongia.getText());
                    String ngayNhap = txtNgaynhap.getText();
                    int soLuongSanPham = Integer.parseInt(txtSoluongSP.getText());
                    int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "NOTIFICATION!!!", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        ChiTietSanPham ctspp = new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham);
                        int result = ctspDao.update(ctspp);
                        if (result == 1) {
                            fillTable();
                            JOptionPane.showMessageDialog(this, "Sửa chi tiết sản phẩm thành công!");
                            reset();
                        } else {
                            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi sửa chi tiết sản phẩm!");
                            reset();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Hủy bỏ chỉnh sửa!!", "NOTIFICATION!!!", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin muốn sửa!!!", "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void deleteCTSP() {
        try {
            if (validateForm()) {
                int index = tblCTSP.getSelectedRow();
                if (index >= 0) {
                    int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "NOTIFICATION!!!", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        String maCTSP = txtMaCTSP.getText();
                        String maSP = txtMasp.getText();
                        String tenSP = txtTensp.getText();
                        double donGia = Double.parseDouble(txtDongia.getText());
                        String ngayNhap = txtNgaynhap.getText();
                        int soLuongSanPham = Integer.parseInt(txtSoluongSP.getText());
                        ChiTietSanPham ctsp = new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham);
                        String id = ctsp.getMaSP();

                        int result = ctspDao.delete(id);
                        if (result == 1) {
                            fillTable();
                            JOptionPane.showMessageDialog(this, "Xóa chi tiết sản phẩm mới thành công!");
                            reset();
                        } else {
                            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
                            reset();
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Hủy bỏ xóa dữ liệu!!!", "NOTIFICATION!!!", JOptionPane.INFORMATION_MESSAGE);
                        reset();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn thông tin muốn sửa!!!", "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage(), "NOTIFICATION!!!", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbChitietsanpham = new javax.swing.JLabel();
        btnLammoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        lbMasp = new javax.swing.JLabel();
        lbDongia = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbTensp = new javax.swing.JLabel();
        lbNgaynhap = new javax.swing.JLabel();
        lbMaCTSP = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        txtMaCTSP = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        lbSoluongSP = new javax.swing.JLabel();
        txtDongia = new javax.swing.JTextField();
        txtSoluongSP = new javax.swing.JTextField();
        txtNgaynhap = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();

        lbChitietsanpham.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbChitietsanpham.setForeground(new java.awt.Color(0, 0, 255));
        lbChitietsanpham.setText("Chi Tiết Sản Phẩm");

        btnLammoi.setText("LÀM MỚI");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        lbMasp.setText("Mã SP:");

        lbDongia.setText("Đơn giá:");

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbTensp.setText("Tên SP:");

        lbNgaynhap.setText("Ngày nhập:");

        lbMaCTSP.setText("Mã CTSP:");

        lbSoluongSP.setText("Số lượng SP:");

        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(158, 158, 158)
                                .addComponent(lbChitietsanpham))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbSoluongSP)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSoluongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbNgaynhap)
                                        .addComponent(lbDongia)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbTensp)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(lbMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMasp)
                                    .addComponent(txtTensp)
                                    .addComponent(txtDongia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNgaynhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaCTSP))))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbChitietsanpham)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMasp)
                    .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLammoi))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTensp)
                    .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDongia)
                    .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgaynhap)
                    .addComponent(txtNgaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaCTSP)
                    .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoluongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSoluongSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateCTSP();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteCTSP();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addCTSP();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked
        // TODO add your handling code here:\
        showDetails();
    }//GEN-LAST:event_tblCTSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbChitietsanpham;
    private javax.swing.JLabel lbDongia;
    private javax.swing.JLabel lbMaCTSP;
    private javax.swing.JLabel lbMasp;
    private javax.swing.JLabel lbNgaynhap;
    private javax.swing.JLabel lbSoluongSP;
    private javax.swing.JLabel lbTensp;
    private javax.swing.JTable tblCTSP;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMaCTSP;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtNgaynhap;
    private javax.swing.JTextField txtSoluongSP;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
