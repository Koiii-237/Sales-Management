/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.QuanLySPDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SanPham;

/**
 *
 * @author ADMIN
 */
public class QLSP extends javax.swing.JPanel {

    DefaultTableModel tableModel;
    QuanLySPDAO spDao = new QuanLySPDAO();
    int index = -1;

    /**
     * Creates new form QLSP
     */
    public QLSP() {
        initComponents();
        initTable();
        fillTable();
    }

    public void initTable() {
        String[] cols = new String[]{"Mã SP", "Tên SP", "Đơn giá", "Ngày nhập", "Mã Menu"};
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(cols);
        tblQLSP.setModel(tableModel);
    }

    public void fillTable() {
        tableModel.setRowCount(0);
        for (SanPham sp : spDao.getALL()) {
            tableModel.addRow(spDao.getRow(sp));
        }
    }

    public void reset() {
        txtMasp.setText("");
        txtTensp.setText("");
        txtDongia.setText("");
        txtNgaynhap.setText("");
        txtMaMenu.setText("");
    }

    public void showDetails() {
        index = tblQLSP.getSelectedRow();
        if (index >= 0) {
            SanPham sp = spDao.getALL().get(index);
            txtMasp.setText(String.valueOf(sp.getMaSP()));
            txtTensp.setText(String.valueOf(sp.getTenSP()));
            txtDongia.setText(String.valueOf(sp.getDonGia()));
            txtNgaynhap.setText(String.valueOf(sp.getNgayNhap()));
            txtMaMenu.setText(String.valueOf(sp.getMaMENU()));

        } else {
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu Trong Hàng.");
        }
    }

    public void addSP() {
    String maSP = txtMasp.getText().trim();
    if (maSP.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã Sản Phẩm không được để trống.");
        return;
    }

    String tenSP = txtTensp.getText().trim();
    if (tenSP.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên Sản Phẩm không được để trống.");
        return;
    }

    String strDonGia = txtDongia.getText().trim();
    if (strDonGia.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Đơn Giá không được để trống.");
        return;
    }

    double donGia;
    try {
        donGia = Double.parseDouble(strDonGia);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Đơn Giá phải là số hợp lệ.");
        return;
    }

    if (donGia <= 0 || donGia > 1000000) {
        JOptionPane.showMessageDialog(this, "Đơn Giá phải lớn hơn 0 và không vượt quá 1 triệu.");
        return;
    }

    String ngayNhap = txtNgaynhap.getText().trim();
    if (ngayNhap.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ngày Nhập không được để trống.");
        return;
    }

    String maMENU = txtMaMenu.getText().trim();
    if (maMENU.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã Menu không được để trống.");
        return;
    }

    SanPham sp = new SanPham(maSP, tenSP, donGia, ngayNhap, maMENU);
    int result = spDao.insert(sp);
    if (result == 1) {
        fillTable();
        JOptionPane.showMessageDialog(this, "Thêm sản phẩm mới thành công!");
    } else {
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm sản phẩm.");
    }
}


    public void updateSP() {
    index = tblQLSP.getSelectedRow();
    if (index < 0) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa.");
        return;
    }

    String MaCu = spDao.getALL().get(index).getMaSP();

    String maSP = txtMasp.getText().trim();
    if (maSP.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã Sản Phẩm không được để trống.");
        return;
    }

    String tenSP = txtTensp.getText().trim();
    if (tenSP.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tên Sản Phẩm không được để trống.");
        return;
    }

    String strDonGia = txtDongia.getText().trim();
    if (strDonGia.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Đơn Giá không được để trống.");
        return;
    }

    double donGia;
    try {
        donGia = Double.parseDouble(strDonGia);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Đơn Giá phải là số hợp lệ.");
        return;
    }

    if (donGia <= 0 || donGia > 1000000) {
        JOptionPane.showMessageDialog(this, "Đơn Giá phải lớn hơn 0 và không vượt quá 1 triệu.");
        return;
    }

    String ngayNhap = txtNgaynhap.getText().trim();
    if (ngayNhap.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Ngày Nhập không được để trống.");
        return;
    }

    String maMENU = txtMaMenu.getText().trim();
    if (maMENU.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Mã Menu không được để trống.");
        return;
    }

    SanPham sp = new SanPham(maSP, tenSP, donGia, ngayNhap, maMENU);
    int result = spDao.update(sp, MaCu);
    if (result == 1) {
        fillTable();
        JOptionPane.showMessageDialog(this, "Sửa sản phẩm thành công!");
    } else {
        JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi sửa sản phẩm!");
    }
}

    public void deleteSP() {
        index = tblQLSP.getSelectedRow();
        if (index >= 0) {
            String TheoMa = spDao.getALL().get(index).getMaSP();
            int result = spDao.delete(TheoMa);
            if (result == 1) {
                fillTable();
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm mới thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra!");
            }
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

        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLSP = new javax.swing.JTable();
        btnSua = new javax.swing.JButton();
        btnLammoi = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbTensp = new javax.swing.JLabel();
        txtMasp = new javax.swing.JTextField();
        lbDongia = new javax.swing.JLabel();
        txtDongia = new javax.swing.JTextField();
        txtNgaynhap = new javax.swing.JTextField();
        txtMaMenu = new javax.swing.JTextField();
        lbNgaynhap = new javax.swing.JLabel();
        lbMaMenu = new javax.swing.JLabel();
        lbMasp = new javax.swing.JLabel();
        txtTensp = new javax.swing.JTextField();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Quản Lý Sản Phẩm");

        tblQLSP.setModel(new javax.swing.table.DefaultTableModel(
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
        tblQLSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLSP);

        btnSua.setText("SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnLammoi.setText("LÀM MỚI");
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nhập Thông Tin Sản Phẩm"));

        lbTensp.setText("Tên SP:");

        lbDongia.setText("Đơn giá:");

        txtDongia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDongiaActionPerformed(evt);
            }
        });

        lbNgaynhap.setText("Ngày nhập:");

        lbMaMenu.setText("Mã Menu:");

        lbMasp.setText("Mã SP:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbMaMenu)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNgaynhap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgaynhap))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDongia))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbMasp, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtMasp))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbTensp, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTensp)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMasp)
                    .addComponent(txtMasp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTensp)
                    .addComponent(txtTensp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDongia)
                    .addComponent(txtDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNgaynhap)
                    .addComponent(txtNgaynhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMaMenu))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteSP();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateSP();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addSP();
    }//GEN-LAST:event_btnThemActionPerformed

    private void tblQLSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLSPMouseClicked
        // TODO add your handling code here:
        showDetails();
    }//GEN-LAST:event_tblQLSPMouseClicked

    private void txtDongiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDongiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDongiaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLammoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDongia;
    private javax.swing.JLabel lbMaMenu;
    private javax.swing.JLabel lbMasp;
    private javax.swing.JLabel lbNgaynhap;
    private javax.swing.JLabel lbTensp;
    private javax.swing.JTable tblQLSP;
    private javax.swing.JTextField txtDongia;
    private javax.swing.JTextField txtMaMenu;
    private javax.swing.JTextField txtMasp;
    private javax.swing.JTextField txtNgaynhap;
    private javax.swing.JTextField txtTensp;
    // End of variables declaration//GEN-END:variables
}
