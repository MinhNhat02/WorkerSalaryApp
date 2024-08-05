package workersalary.gui;

import java.sql.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Employee;
import workersalary.entity.implement.Worker;
import workersalary.repository.AssignmentRepository;
import workersalary.repository.EmployeeRepository;
import workersalary.repository.TimeKeepingRepository;
import workersalary.util.Utils;

public class CongNhanLuong extends javax.swing.JPanel {

    public CongNhanLuong() {
        initComponents();
        loadComboboxStaff();
        loadTables();
    }

    private void loadComboboxStaff() {
        DefaultComboBoxModel modelId = (DefaultComboBoxModel) cbWorkerId.getModel();
        DefaultComboBoxModel modelName = (DefaultComboBoxModel) cbName.getModel();
        modelId.removeAllElements();
        modelName.removeAllElements();
        List<Employee> employees = EmployeeRepository.getInstance().getList();
        if (employees != null && employees.size() > 0) {
            for (Employee employee : employees) {
                if (employee instanceof Worker) {
                    modelId.addElement(employee.getId());
                    modelName.addElement(employee.getFullName());
                }
            }
            if(cbWorkerId.getItemCount() > 0) cbWorkerId.setSelectedIndex(0);
            if(cbName.getItemCount() > 0) cbName.setSelectedIndex(0);
        }
    }

    public void loadTables() {
        DefaultTableModel model1 = (DefaultTableModel) tableBSalary.getModel();
        DefaultTableModel model2 = (DefaultTableModel) tableSalary.getModel();
        List<Employee> list = EmployeeRepository.getInstance().getList();
        model2.setRowCount(0);
        model1.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (Employee employee : list) {
                if (employee instanceof Worker) {
                    if (employee.getSalary() == 0) {
                        Object[] row = {
                            employee.getId(),
                            employee.getFullName(),
                            employee.getIdentifyCard(),
                            Utils.dateToString(employee.getDateOfBirth()),
                            employee.getGender(),
                            employee.getAddress(),
                            employee.getPhoneNumber(),
                            employee.getDepartment().getName()};
                        model1.addRow(row);
                    } else {
                        Object[] row = {
                            employee.getId(),
                            employee.getFullName(),
                            employee.getIdentifyCard(),
                            Utils.doubleToString(employee.getAllowance()),
                            Utils.doubleToString(employee.getSalary())};
                        model2.addRow(row);
                    }
                }
            }
        }
    }

    private void clear() {
        cbName.setSelectedIndex(0);
        cbWorkerId.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSalary = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBSalary = new javax.swing.JTable();
        cbName = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        txtSalary = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        cbWorkerId = new javax.swing.JComboBox<>();
        btnDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jLabel3.setText("Ho và Tên:");

        tableSalary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CN", "Tên CN", "CMND", "Phụ Cấp", "Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSalary.getTableHeader().setReorderingAllowed(false);
        tableSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSalaryMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSalary);
        if (tableSalary.getColumnModel().getColumnCount() > 0) {
            tableSalary.getColumnModel().getColumn(0).setResizable(false);
            tableSalary.getColumnModel().getColumn(1).setResizable(false);
            tableSalary.getColumnModel().getColumn(2).setResizable(false);
            tableSalary.getColumnModel().getColumn(3).setResizable(false);
            tableSalary.getColumnModel().getColumn(4).setResizable(false);
        }

        tableBSalary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "CMND", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Phòng Ban"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBSalaryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableBSalary);

        cbName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNameItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lương Công Nhân");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnAdd.setText("Tính Lương");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        txtSalary.setEditable(false);

        jLabel4.setText("Mã NV:");

        btnClear.setText("Xóa Rỗng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        cbWorkerId.setEnabled(false);
        cbWorkerId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbWorkerIdItemStateChanged(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("Lương:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 936, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbWorkerId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 684, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(36, 36, 36)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbWorkerId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(94, 94, 94)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnDelete)
                                .addComponent(btnClear)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(53, 53, 53)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSalaryMouseClicked
        int row = tableSalary.getSelectedRow();
        if (row < 0) {
            return;
        }
        String staffId = tableSalary.getValueAt(row, 0).toString();
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        if (employee != null) {
            cbName.setSelectedItem(employee.getFullName());
        }
    }//GEN-LAST:event_tableSalaryMouseClicked

    private void cbWorkerIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbWorkerIdItemStateChanged

    }//GEN-LAST:event_cbWorkerIdItemStateChanged

    private void cbNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNameItemStateChanged
        if(cbName.getItemCount() == 0 || cbWorkerId.getItemCount() == 0) return;
        int cbNameIndex = cbName.getSelectedIndex();
        if (cbNameIndex < 0) {
            return;
        }
        cbWorkerId.setSelectedIndex(cbNameIndex);
        String workerId = cbWorkerId.getSelectedItem().toString();
        if (workerId == null || workerId.isEmpty()) {
            return;
        }
        Employee employee = EmployeeRepository.getInstance().getById(workerId);
        txtSalary.setText(Utils.doubleToString(employee.getSalary()));
    }//GEN-LAST:event_cbNameItemStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String workerIdString = cbWorkerId.getSelectedItem().toString();
        if (workerIdString == null || workerIdString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã CN không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date curDate = Utils.getCurDate();
        Employee employee = EmployeeRepository.getInstance().getById(workerIdString);
        int produceNum = AssignmentRepository.getInstance().workerProduceCount((Worker)employee);
        if (produceNum == -1) {
            JOptionPane.showMessageDialog(null, "Công Nhân Chưa Được được phân công 1 sản phẩm nào!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double dayWork = TimeKeepingRepository.getInstance().countDayWork(employee.getId(), curDate);
        if (dayWork == -1) {
            JOptionPane.showMessageDialog(null, "Công Nhân Chưa Được Chấm Công 1 Ca Nào!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TimeKeepingRepository.getInstance().calculateSalary((Worker)employee, produceNum, curDate);
        if (employee.getSalary() > 0) {
            if (EmployeeRepository.getInstance().update(employee)) {
                JOptionPane.showMessageDialog(null, "Tính Lương Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
                loadTables();
                clear();
            } else {
                JOptionPane.showMessageDialog(null, "Không Tìm Thấy Nhân Viên!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int row = tableSalary.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 Công Nhân Đã Tính Lương Để Xóa!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String workerId = tableSalary.getValueAt(row, 0).toString();
        if (workerId == null || workerId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã CN không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Employee employee = EmployeeRepository.getInstance().getById(workerId);
        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Công Nhân!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        employee.setSalary(0);
        if (EmployeeRepository.getInstance().update(employee)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTables();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Công Nhân!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tableBSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBSalaryMouseClicked
        int row = tableBSalary.getSelectedRow();
        if(row < 0) {
            return;
        }
        String workerId = tableBSalary.getValueAt(row, 0).toString();
        Employee employee = EmployeeRepository.getInstance().getById(workerId);
        if(employee != null) {
            cbName.setSelectedItem(employee.getFullName());
        }
    }//GEN-LAST:event_tableBSalaryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox<String> cbName;
    private javax.swing.JComboBox<String> cbWorkerId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableBSalary;
    private javax.swing.JTable tableSalary;
    private javax.swing.JTextField txtSalary;
    // End of variables declaration//GEN-END:variables
}
