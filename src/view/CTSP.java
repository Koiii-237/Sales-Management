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
    int index = -1;
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
                 index = tblCTSP.getSelectedRow();
                if (index >= 0) {
                    String TheoMa = ctspDao.getALL().get(index).getMaCTSP();
                    String maCTSP = txtMaCTSP.getText();
                    String maSP = txtMasp.getText();
                    String tenSP = txtTensp.getText();
                    double donGia = Double.parseDouble(txtDongia.getText());
                    String ngayNhap = txtNgaynhap.getText();
                    int soLuongSanPham = Integer.parseInt(txtSoluongSP.getText());
                    int choice = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "NOTIFICATION!!!", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        ChiTietSanPham ctspp = new ChiTietSanPham(maCTSP, maSP, tenSP, donGia, ngayNhap, soLuongSanPham);
                        int result = ctspDao.update(ctspp, TheoMa);
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
    index = tblCTSP.getSelectedRow();
      if (index >= 0) {
          String TheoMa = ctspDao.getALL().get(index).getMaCTSP();
          int Result = ctspDao.delete(TheoMa);
          if (Result == 1) {
              JOptionPane.showMessageDialog(this, "Xóa Thành Công.");
          } else {
              JOptionPane.showMessageDialog(this, "Xóa Thất Bại.");
          }
      } else {
              JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Thông Tin Trên Bảng Để Xóa,");
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

        btnLammoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbNgaynhap = new javax.swing.JLabel();
        lbTensp = new javax.swing.JLabel();
        txtNgaynhap = new javax.swing.JTextField();
        lbDongia = new javax.swing.JLabel();
        lbMasp = new javax.swing.JLabel();
        txtDongia = new javax.swing.JTextField();
        txtTensp = new javax.swing.JTextField();
        txtMasp = new javax.swing.JTextField();
        lbSoluongSP = new javax.swing.JLabel();
        txtSoluongSP = new javax.swing.JTextField();
        lbMaCTSP = new javax.swing.JLabel();
        txtMaCTSP = new javax.swing.JTextField();
        lbChitietsanpham = new javax.swing.JLabel();

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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Chi Tiết Sản Phẩm"));

        lbNgaynhap.setText("Ngày nhập:");

        lbTensp.setText("Tên SP:");

        lbDongia.setText("Đơn giá:");

        lbMasp.setText("Mã SP:");

        lbSoluongSP.setText("Số lượng SP:");

        lbMaCTSP.setText("Mã CTSP:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(lbMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSoluongSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbSoluongSP)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNgaynhap)
                                    .addComponent(lbTensp)
                                    .addComponent(lbDongia)
                                    .addComponent(lbMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMasp, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                                    .addComponent(txtTensp)
                                    .addComponent(txtDongia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNgaynhap))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaCTSP)
                    .addComponent(txtMaCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTensp)
                    .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDongia)
                    .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgaynhap)
                    .addComponent(txtNgaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMasp)
                    .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoluongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSoluongSP))
                .addContainerGap())
        );

        lbChitietsanpham.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbChitietsanpham.setForeground(new java.awt.Color(0, 0, 255));
        lbChitietsanpham.setText("Chi Tiết Sản Phẩm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(lbChitietsanpham))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbChitietsanpham)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
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
    private javax.swing.JPanel jPanel1;
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
