package workersalary.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Employee;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.repository.EmployeeRepository;
import workersalary.repository.TimeKeepingRepository;
import workersalary.util.Utils;

public class NhanVienLuong extends javax.swing.JPanel {

    public NhanVienLuong() {
        initComponents();
        loadComboboxStaff();
        loadTables();
    }

    private void loadComboboxStaff() {
        DefaultComboBoxModel modelId = (DefaultComboBoxModel) cbStaffId.getModel();
        DefaultComboBoxModel modelName = (DefaultComboBoxModel) cbName.getModel();
        modelId.removeAllElements();
        modelName.removeAllElements();
        List<Employee> employees = EmployeeRepository.getInstance().getList();
        if (employees != null && employees.size() > 0) {
            for (Employee employee : employees) {
                if (employee instanceof AdministrativeStaff) {
                    modelId.addElement(employee.getId());
                    modelName.addElement(employee.getFullName());
                }
            }
            cbStaffId.setSelectedIndex(0);
            cbName.setSelectedIndex(0);
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
                if (employee instanceof AdministrativeStaff) {
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
                            Utils.doubleToString(((AdministrativeStaff) employee).getBasicSalary()),
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
        cbStaffId.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableSalary = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBSalary = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbStaffId = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbName = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        tableSalary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên NV", "CMND", "Lương CB", "Phụ Cấp", "Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            tableSalary.getColumnModel().getColumn(5).setResizable(false);
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
        if (tableBSalary.getColumnModel().getColumnCount() > 0) {
            tableBSalary.getColumnModel().getColumn(0).setResizable(false);
            tableBSalary.getColumnModel().getColumn(1).setResizable(false);
            tableBSalary.getColumnModel().getColumn(2).setResizable(false);
            tableBSalary.getColumnModel().getColumn(3).setResizable(false);
            tableBSalary.getColumnModel().getColumn(4).setResizable(false);
            tableBSalary.getColumnModel().getColumn(5).setResizable(false);
            tableBSalary.getColumnModel().getColumn(6).setResizable(false);
            tableBSalary.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lương Nhân Viên");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Lương:");

        cbStaffId.setEnabled(false);
        cbStaffId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbStaffIdItemStateChanged(evt);
            }
        });

        jLabel3.setText("Ho và Tên:");

        cbName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbNameItemStateChanged(evt);
            }
        });

        jLabel4.setText("Mã NV:");

        txtSalary.setEditable(false);

        btnAdd.setText("Tính Lương");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Xóa Rỗng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbStaffId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSalary)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStaffId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String staffId = cbStaffId.getSelectedItem().toString();
        if (staffId == null || staffId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã NV không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        Date curDate = Utils.getCurDate();
        double dayWork = TimeKeepingRepository.getInstance().countDayWork(employee.getId(), curDate);
        if (dayWork == -1) {
            JOptionPane.showMessageDialog(null, "Nhân Viên Chưa Được Chấm Công 1 Ca Nào!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        TimeKeepingRepository.getInstance().calculateSalary((AdministrativeStaff)employee, dayWork);
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
            JOptionPane.showMessageDialog(null, "Chọn 1 Nhân Viên Đã Tính Lương Để Xóa!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String staffId = tableSalary.getValueAt(row, 0).toString();
        if (staffId == null || staffId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã NV không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        if(employee == null) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Nhân Viên!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        employee.setSalary(0);
        if (EmployeeRepository.getInstance().update(employee)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTables();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Nhân Viên!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void cbStaffIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbStaffIdItemStateChanged

    }//GEN-LAST:event_cbStaffIdItemStateChanged


    private void cbNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbNameItemStateChanged
        if(cbName.getItemCount() == 0 || cbStaffId.getItemCount() == 0) return;
        int cbNameIndex = cbName.getSelectedIndex();
        if (cbNameIndex < 0) {
            return;
        }
        cbStaffId.setSelectedIndex(cbNameIndex);
        String staffId = cbStaffId.getSelectedItem().toString();
        if (staffId == null || staffId.isEmpty()) {
            return;
        }
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        txtSalary.setText(Utils.doubleToString(employee.getSalary()));
    }//GEN-LAST:event_cbNameItemStateChanged

    private void tableSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSalaryMouseClicked
        int row = tableSalary.getSelectedRow();
        if(row < 0) {
            return;
        }
        String staffId = tableSalary.getValueAt(row, 0).toString();
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        if(employee != null) {
            cbName.setSelectedItem(employee.getFullName());
        }
    }//GEN-LAST:event_tableSalaryMouseClicked

    private void tableBSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBSalaryMouseClicked
        int row = tableBSalary.getSelectedRow();
        if(row < 0) {
            return;
        }
        String staffId = tableBSalary.getValueAt(row, 0).toString();
        Employee employee = EmployeeRepository.getInstance().getById(staffId);
        if(employee != null) {
            cbName.setSelectedItem(employee.getFullName());
        }
    }//GEN-LAST:event_tableBSalaryMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JComboBox<String> cbName;
    private javax.swing.JComboBox<String> cbStaffId;
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
