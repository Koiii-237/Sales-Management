/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ChiTietHoaDDAO;
import dao.HoaDonDAO;
import dao.QuanLySPDAO;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.SanPham;

/**
 *
 * @author ADMIN
 */
public class HDCT extends javax.swing.JPanel {
    HoaDonDAO hdDAO = new HoaDonDAO();
    ChiTietHoaDDAO chiTietHDDAO = new ChiTietHoaDDAO();
    QuanLySPDAO spDAO = new QuanLySPDAO();
    
    DefaultTableModel modelHoaDon = new DefaultTableModel();
    DefaultTableModel modelHoaDonChiTiet = new DefaultTableModel();
    DefaultTableModel modelSanPham = new DefaultTableModel();
    /**
     * Creates new form HDCT
     */
    public HDCT() {
        initComponents();
        initTableHoaDon();
        fillToTableHD();
        initTableHoaDonChiTiet();
        initTableSanPham();
        fillToTableSanPham();
        
        jSpinner1.setModel(new SpinnerNumberModel(1, 1, 10, 1));
    }
    
    public void initTableHoaDon(){
        String column[] = {"Mã Hóa Đơn", "Mã Sản Phẩm", "Mã NV", "Tên SP", "Trạng Thái", "Đơn Giá", "Thời Gian Thanh Toán", "Số Lượng"};
        modelHoaDon.setColumnIdentifiers(column);
        tblHoaDon.setModel(modelHoaDon);
    }
    
    public void fillToTableHD(){
        modelHoaDon.setRowCount(0);
        for(HoaDon hd : hdDAO.getALL()){
            modelHoaDon.addRow(hdDAO.getRow(hd));
        }
    }
    
    public void initTableHoaDonChiTiet(){
        String column[] = {"Mã Chi Tiết Hóa Đơn", "Mã Hóa Đơn", "Mã Khuyến Mãi", "Tên SP", "Đơn Giá", "Đơn Giá", "Trạng Thái"};
        modelHoaDon.setColumnIdentifiers(column);
        tblHoaDonChiTiet.setModel(modelHoaDon);
    }
    
    public void fillToTableHDChiTiet(){
        modelHoaDonChiTiet.setRowCount(0);
        for(ChiTietHoaDon hd : chiTietHDDAO.read()){
            modelHoaDonChiTiet.addRow(chiTietHDDAO.getRow(hd));
        }
    }
     
    public void initTableSanPham(){
        String column[] = {"Mã Sản Phẩm", "Tên Sản Phẩm", "Đơn Giá", "Ngày Nhập", "Mã Menu"};
        modelSanPham.setColumnIdentifiers(column);
        tblSanPham.setModel(modelSanPham);
    }
    
    public void fillToTableSanPham(){
        modelSanPham.setRowCount(0);
        for(SanPham sp : spDAO.getALL()){
            modelSanPham.addRow(spDAO.getRow(sp));
        }
    }
    
    public boolean checkForm() {
        if (txtMaHD.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Mã Hóa Đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtMaNV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập Mã Nhân Viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public void ChooseHD() {
        int selectedRow = tblHoaDon.getSelectedRow();
        if (selectedRow >= 0) {
            String maHD = (String) tblHoaDon.getValueAt(selectedRow, 0); // Lấy mã HĐ từ cột 0
            modelHoaDonChiTiet.setRowCount(0); // Xóa dữ liệu cũ trong Chi Tiết Hóa Đơn

            // Lọc và hiển thị HDCT tương ứng
            for (ChiTietHoaDon hd : chiTietHDDAO.read()) {
                if (hd.getMaHD().equals(maHD)) {
                    modelHoaDonChiTiet.addRow(chiTietHDDAO.getRow(hd));
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Dữ Liệu!!!", "NOTIFICATION!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public boolean addHDCT() {
        int index = tblHoaDon.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra trạng thái hóa đơn
        String trangThai = (String) tblHoaDon.getValueAt(index, 3);
        if ("Đã thanh toán".equals(trangThai)) {
            JOptionPane.showMessageDialog(this, "Không thể thêm SP vào hóa đơn đã thanh toán", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        int indexSP = tblSanPham.getSelectedRow();
        if (indexSP < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String maCTHD = chiTietHDDAO.read().get(index).getMaCTHD();
        String maKM = chiTietHDDAO.read().get(index).getMaKM();
        String maChiTietSanPham = chiTietHDDAO.read().get(index).getMaCTSP();
        String maHD = hdDAO.getALL().get(index).getMaHD();
        String tenSP = spDAO.getALL().get(index).getTenSP();
        double giaSP = spDAO.getALL().get(index).getDonGia();

        ChiTietHoaDon hdct = new ChiTietHoaDon(maCTHD, maHD, maKM, maChiTietSanPham, tenSP, giaSP, trangThai);
        

        if (chiTietHDDAO.create(hdct) == 1) {
            ChooseHD(); // Làm mới bảng chi tiết
            return true;
        }
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnTaoHoaDonMoi = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        lbGT1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        txtMaHD = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Hóa đơn:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Hóa đơn chi tiết:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Sản phẩm:");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDonChiTiet);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Quản lý Hóa Đơn Chi Tiết");

        btnTaoHoaDonMoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnTaoHoaDonMoi.setText("Tạo hóa đơn mới");
        btnTaoHoaDonMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonMoiActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lbGT1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbGT1.setText("Số lượng:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã HD:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mã NV:");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(264, 264, 264)
                                    .addComponent(lbGT1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnThem)
                                    .addGap(5, 5, 5))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(btnTaoHoaDonMoi)
                                .addGap(62, 62, 62)
                                .addComponent(btnThanhToan))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel3)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDonMoi)
                    .addComponent(btnThanhToan))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbGT1)
                    .addComponent(btnThem))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1344, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 365, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 365, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 99, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 99, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        ChooseHD();
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChiTietMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonChiTietMouseClicked

    private void btnTaoHoaDonMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMoiActionPerformed

    }//GEN-LAST:event_btnTaoHoaDonMoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(addHDCT()){
            JOptionPane.showMessageDialog(this, "Thêm Thành Công Vào Hóa Đơn Chi Tiết!!!", "NOTIFICATION!!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDonMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lbGT1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
