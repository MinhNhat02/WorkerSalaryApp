package workersalary.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Employee;
import workersalary.entity.ShiftWork;
import workersalary.entity.TimeKeeping;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.entity.implement.TimeKeepingStaff;
import workersalary.repository.EmployeeRepository;
import workersalary.repository.ShiftWorkRepository;
import workersalary.repository.TimeKeepingRepository;
import workersalary.util.Utils;

public class NhanVienChamCong extends javax.swing.JPanel {

    private TimeKeepingRepository repo = TimeKeepingRepository.getInstance();
    private boolean isClickedGetStaffTable = false;

    public NhanVienChamCong() {
        initComponents();
        loadComboboxShift();
        loadTable();
    }

    public void loadTable() {
        loadTable(repo.getList());
    }

    public void loadTable(List<TimeKeeping> timeKeepings) {
        DefaultTableModel model = (DefaultTableModel) timeKeepingTable.getModel();
        List<TimeKeeping> list = timeKeepings;
        model.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (TimeKeeping timeKeeping : list) {
                if (timeKeeping instanceof TimeKeepingStaff) {
                    String strLeavePermission = "";
                    if (timeKeeping.isLeavePermission()) {
                        strLeavePermission = "có";
                    } else {
                        strLeavePermission = "không";
                    }
                    Object[] row = {
                        timeKeeping.getId(),
                        Utils.dateToString(timeKeeping.getCreatedDate()),
                        timeKeeping.getShiftWork().getName(),
                        String.format("%d:%02d-%d:%02d", timeKeeping.getShiftWork().getStartTime().getHours(), timeKeeping.getShiftWork().getStartTime().getMinutes(),
                        timeKeeping.getShiftWork().getEndTime().getHours(), timeKeeping.getShiftWork().getEndTime().getMinutes()),
                        timeKeeping.getEmployee().getId(),
                        timeKeeping.getEmployee().getFullName(),
                        timeKeeping.getStatus(),
                        strLeavePermission};
                    model.addRow(row);
                }
            }
        }
    }

    public void loadComboboxShift() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbShiftWork.getModel();
        model.removeAllElements();
        List<ShiftWork> shiftWorks = ShiftWorkRepository.getInstance().getList();
        if (shiftWorks != null && shiftWorks.size() > 0) {
            for (ShiftWork shiftWork : shiftWorks) {
                model.addElement(shiftWork.getName());
            }
            if (model.getSize() > 0) {
                String shiftWorkName = model.getElementAt(0).toString();
                model.setSelectedItem(shiftWorkName);
                ShiftWork shift = ShiftWorkRepository.getInstance().getByName(shiftWorkName);
                txtTimeWork.setText(String.format("%d:%02d-%d:%02d", shift.getStartTime().getHours(), shift.getStartTime().getMinutes(),
                        shift.getEndTime().getHours(), shift.getEndTime().getMinutes()));
            }
        }
    }

    public TimeKeeping getTimeKeepingFromTimeKeepingTable() {
        String id = txtId.getText().trim();
        ShiftWork shiftWork = getShiftWorkWithField();
        int row = timeKeepingTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 chấm công để xóa!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String employeeId = timeKeepingTable.getValueAt(row, 4).toString();
        Employee employee = EmployeeRepository.getInstance().getById(employeeId);
        boolean exist = Boolean.parseBoolean(timeKeepingTable.getValueAt(row, 5).toString());
        String status = "";
        if (exist) {
            status = "có mặt";
        } else {
            status = "nghỉ";
        }
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã Công không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        boolean leavePermission = Boolean.parseBoolean(staffTable.getValueAt(row, 6).toString());
        return new TimeKeepingStaff(id, employee, Utils.getCurDate(), shiftWork, status, leavePermission);
    }

    public void getTimeKeepingStaffs() {
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        List<Employee> list = EmployeeRepository.getInstance().getAll();
        model.setRowCount(0);
        String shiftWorkName = cbShiftWork.getSelectedItem().toString();
        ShiftWork shiftWork = ShiftWorkRepository.getInstance().getByName(shiftWorkName);
        if (shiftWork == null) {
            JOptionPane.showMessageDialog(null, "Không Có Ca làm việc để lấy danh sách chấm công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());
        if (list != null && list.size() > 0) {
            for (Employee employee : list) {
                if (employee instanceof AdministrativeStaff) {
                    if (repo.checkTimeKeeping(employee.getId(), curDate, shiftWork.getId()) == null) {
                        AdministrativeStaff staff = (AdministrativeStaff) employee;
                        Object[] row = {
                            staff.getId(),
                            staff.getFullName(),
                            staff.getIdentifyCard(),
                            staff.getDepartment().getName(),
                            false,
                            false
                        };
                        model.addRow(row);
                    }
                }
            }
            isClickedGetStaffTable = true;
        } else {
            isClickedGetStaffTable = false;
            JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Để lấy danh sách!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private ShiftWork getShiftWorkWithField() {
        String shiftWorkName = cbShiftWork.getSelectedItem().toString();
        if (shiftWorkName == null || shiftWorkName.isEmpty()) {
            return null;
        }
        return ShiftWorkRepository.getInstance().getByName(shiftWorkName);
    }

    private void clear() {
        txtId.setText("");
        if (cbShiftWork.getItemCount() > 0) {
            cbShiftWork.setSelectedIndex(0);
        }
    }

    private TimeKeeping getTimeKeepingFromStaffTable() {
        String id = txtId.getText().trim();
        ShiftWork shiftWork = getShiftWorkWithField();
        int row = staffTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 nhân viên để chấm công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String employeeId = staffTable.getValueAt(row, 0).toString();
        Employee employee = EmployeeRepository.getInstance().getById(employeeId);
        boolean exist = Boolean.parseBoolean(staffTable.getValueAt(row, 4).toString());
        String status = "";
        if (exist) {
            status = "có mặt";
        } else {
            status = "nghỉ";
        }
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã Công không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        boolean leavePermission = Boolean.parseBoolean(staffTable.getValueAt(row, 5).toString());
        return new TimeKeepingStaff(id, employee, Utils.getCurDate(), shiftWork, status, leavePermission);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        timeKeepingTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        staffTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbShiftWork = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        btnGetStaffs = new javax.swing.JButton();
        btnCheckAll = new javax.swing.JButton();
        btnResetTimeKeeping = new javax.swing.JButton();
        btnTimeKeeping = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTimeWork = new javax.swing.JTextField();

        timeKeepingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Công", "Ngày Chấm", "Ca", "Giờ Làm", "Mã NV", "Tên NV", "Trạng Thái", "Nghỉ Phép"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        timeKeepingTable.getTableHeader().setReorderingAllowed(false);
        timeKeepingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                timeKeepingTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(timeKeepingTable);
        if (timeKeepingTable.getColumnModel().getColumnCount() > 0) {
            timeKeepingTable.getColumnModel().getColumn(0).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(1).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(2).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(3).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(4).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(5).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(6).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(7).setResizable(false);
        }

        staffTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "CMND", "Phòng Ban", "Có Mặt", "Có Phép"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        staffTable.setColumnSelectionAllowed(true);
        staffTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(staffTable);
        staffTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (staffTable.getColumnModel().getColumnCount() > 0) {
            staffTable.getColumnModel().getColumn(0).setResizable(false);
            staffTable.getColumnModel().getColumn(1).setResizable(false);
            staffTable.getColumnModel().getColumn(2).setResizable(false);
            staffTable.getColumnModel().getColumn(3).setResizable(false);
            staffTable.getColumnModel().getColumn(4).setResizable(false);
            staffTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel2.setText("Giờ Làm:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chấm Công Nhân Viên");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cbShiftWork.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbShiftWorkItemStateChanged(evt);
            }
        });

        jLabel3.setText("Ca Làm:");

        btnGetStaffs.setText("Lấy Danh Sách Chấm Công");
        btnGetStaffs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetStaffsActionPerformed(evt);
            }
        });

        btnCheckAll.setText("Có Mặt Tất Cả");
        btnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAllActionPerformed(evt);
            }
        });

        btnResetTimeKeeping.setText("Đặt Lại Chấm Công");
        btnResetTimeKeeping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTimeKeepingActionPerformed(evt);
            }
        });

        btnTimeKeeping.setText("Chấm Công");
        btnTimeKeeping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeKeepingActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã Công");

        txtTimeWork.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnResetTimeKeeping, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGetStaffs, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnTimeKeeping, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(txtTimeWork, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbShiftWork, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(74, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbShiftWork, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimeWork, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGetStaffs)
                            .addComponent(btnCheckAll))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetTimeKeeping)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTimeKeeping)
                            .addComponent(btnDelete))
                        .addGap(52, 52, 52))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAllActionPerformed
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        if (model == null || model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Nào Để Chấm Công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true, i, 4);
        }
    }//GEN-LAST:event_btnCheckAllActionPerformed

    private void btnResetTimeKeepingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTimeKeepingActionPerformed
        DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
        if (model == null || model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Nào Để Chấm Công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 4);
            model.setValueAt(false, i, 5);
        }
    }//GEN-LAST:event_btnResetTimeKeepingActionPerformed

    private void btnTimeKeepingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeKeepingActionPerformed
        TimeKeeping timeKeeping = getTimeKeepingFromStaffTable();
        if (timeKeeping == null) {
            return;
        }
        if (repo.add(timeKeeping)) {
            JOptionPane.showMessageDialog(null, "Chấm công thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            getTimeKeepingStaffs();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã Công đã tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnTimeKeepingActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtId.getText().trim();
        int row = timeKeepingTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 chấm công để xóa!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã Công không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (repo.delete(id)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã Công không tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbShiftWorkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbShiftWorkItemStateChanged
        String curItem = cbShiftWork.getSelectedItem().toString();
        if (curItem == null || curItem.isEmpty()) {
            return;
        }
        ShiftWork shift = ShiftWorkRepository.getInstance().getByName(curItem);
        txtTimeWork.setText(String.format("%d:%02d-%d:%02d", shift.getStartTime().getHours(), shift.getStartTime().getMinutes(),
                shift.getEndTime().getHours(), shift.getEndTime().getMinutes()));
        if (isClickedGetStaffTable)
            getTimeKeepingStaffs();
    }//GEN-LAST:event_cbShiftWorkItemStateChanged

    private void btnGetStaffsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetStaffsActionPerformed
        getTimeKeepingStaffs();
    }//GEN-LAST:event_btnGetStaffsActionPerformed

    private void timeKeepingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeKeepingTableMouseClicked
        if (timeKeepingTable.getRowCount() <= 0) {
            return;
        }
        int row = timeKeepingTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        String id = timeKeepingTable.getValueAt(row, 0).toString();
        String shiftWorkName = timeKeepingTable.getValueAt(row, 2).toString();
        String dateStr = timeKeepingTable.getValueAt(row, 1).toString();
        int year = -1;
        int month = -1;
        int day = -1;
        try {
            day = Integer.parseInt(dateStr.split("/")[0]);
            month = Integer.parseInt(dateStr.split("/")[1]);
            year = Integer.parseInt(dateStr.split("/")[2]);
        } catch (Exception e) {

        }
        txtId.setText(id);
        cbShiftWork.setSelectedItem(shiftWorkName);
    }//GEN-LAST:event_timeKeepingTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckAll;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGetStaffs;
    private javax.swing.JButton btnResetTimeKeeping;
    private javax.swing.JButton btnTimeKeeping;
    private javax.swing.JComboBox<String> cbShiftWork;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable staffTable;
    private javax.swing.JTable timeKeepingTable;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTimeWork;
    // End of variables declaration//GEN-END:variables
}
