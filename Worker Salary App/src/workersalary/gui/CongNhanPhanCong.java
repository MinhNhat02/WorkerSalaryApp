package workersalary.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Assignment;
import workersalary.entity.Employee;
import workersalary.entity.ProductionStage;
import workersalary.entity.implement.Worker;
import workersalary.repository.AssignmentRepository;
import workersalary.repository.EmployeeRepository;
import workersalary.repository.ProductionStageRepository;
import workersalary.util.Utils;

public class CongNhanPhanCong extends javax.swing.JPanel {

    private AssignmentRepository repo = AssignmentRepository.getInstance();

    public CongNhanPhanCong() {
        initComponents();
        loadComboboxStaff();
        loadAssignmentTable();
        loadStageTable();
        spinnerFinishAmount.setValue(1);
    }

    private void loadComboboxStaff() {
        DefaultComboBoxModel modelId = (DefaultComboBoxModel) cbWorkerId.getModel();
        DefaultComboBoxModel modelName = (DefaultComboBoxModel) cbWorker.getModel();
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
            if (cbWorkerId.getItemCount() > 0) {
                cbWorkerId.setSelectedIndex(0);
            }
            if (cbWorker.getItemCount() > 0) {
                cbWorker.setSelectedIndex(0);
            }
        }
    }

    public void loadAssignmentTable() {
        DefaultTableModel model = (DefaultTableModel) assignmentTable.getModel();
        List<Assignment> list = AssignmentRepository.getInstance().getList();
        model.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (Assignment assignment : list) {
                Object[] row = {
                    assignment.getId(),
                    assignment.getWorker().getId(),
                    assignment.getWorker().getFullName(),
                    assignment.getStage().getId(),
                    assignment.getStage().getName(),
                    assignment.getFinishAmount()
                };
                model.addRow(row);
            }
        }
    }

    public void loadStageTable() {
        DefaultTableModel model = (DefaultTableModel) stageTable.getModel();
        List<ProductionStage> list = ProductionStageRepository.getInstance().getList();
        model.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                if (stage.getAmount() > 0) {
                    String needStageId = "không có";
                    String needStageName = "không có";
                    if (stage.getNeededStage() != null) {
                        needStageId = stage.getNeededStage().getId();
                        needStageName = stage.getNeededStage().getName();
                    }
                    Object[] row = {
                        stage.getId(),
                        stage.getName(),
                        needStageId,
                        needStageName,
                        stage.getProduct().getName(),
                        stage.getAmount()
                    };
                    model.addRow(row);
                }
            }
        }
    }

    public void clear() {
        txtAssignmentId.setText("");
        if (cbWorkerId.getItemCount() > 0) {
            cbWorkerId.setSelectedIndex(0);
        }
        if (cbWorker.getItemCount() > 0) {
            cbWorker.setSelectedIndex(0);
        }
        spinnerFinishAmount.setValue(1);
        labelNeededStage.setText("");
        labelNeedStageStatus.setText("");
    }

    public Assignment getAssignmentFromField() {
        String id = txtAssignmentId.getText().trim();
        String workerId = cbWorkerId.getSelectedItem().toString();
        int amount = (int) spinnerFinishAmount.getValue();
        int row = stageTable.getSelectedRow();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã CĐ không được bỏ trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 Công Đoạn Để Phân Công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (workerId == null || workerId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Mã CN!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String stageId = stageTable.getValueAt(row, 0).toString();
        if (stageId == null || stageId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Mã CĐ!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Employee employee = EmployeeRepository.getInstance().getById(workerId);
        if (employee == null) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Công Nhân!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        ProductionStage stage = ProductionStageRepository.getInstance().getById(stageId);
        if (stage == null) {
            JOptionPane.showMessageDialog(null, "Không Tìm Thấy Công Đoạn!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (amount > stage.getAmount()) {
            JOptionPane.showMessageDialog(null, "Không thể phân công số lượng sản phẩm lớn hơn số lượng sản phẩm trong công đoạn!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new Assignment(id, (Worker) employee, stage, amount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        assignmentTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        stageTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtAssignmentId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbWorker = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        spinnerFinishAmount = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        labelNeededStage = new javax.swing.JLabel();
        labelNeedStageStatus = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbWorkerId = new javax.swing.JComboBox<>();
        btnClear = new javax.swing.JButton();

        assignmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã PC", "Mã CN", "Tên CN", "Mã CĐ", "Tên CĐ", "SL Phân Công"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        assignmentTable.getTableHeader().setReorderingAllowed(false);
        assignmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assignmentTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(assignmentTable);
        if (assignmentTable.getColumnModel().getColumnCount() > 0) {
            assignmentTable.getColumnModel().getColumn(0).setResizable(false);
            assignmentTable.getColumnModel().getColumn(1).setResizable(false);
            assignmentTable.getColumnModel().getColumn(2).setResizable(false);
            assignmentTable.getColumnModel().getColumn(3).setResizable(false);
            assignmentTable.getColumnModel().getColumn(4).setResizable(false);
            assignmentTable.getColumnModel().getColumn(5).setResizable(false);
        }

        stageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CĐ", "Tên CĐ", "Mã CĐ YC", "CĐ Yêu Cầu", "Tên SP", "SL Cần Làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stageTable.getTableHeader().setReorderingAllowed(false);
        stageTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stageTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(stageTable);
        if (stageTable.getColumnModel().getColumnCount() > 0) {
            stageTable.getColumnModel().getColumn(0).setResizable(false);
            stageTable.getColumnModel().getColumn(1).setResizable(false);
            stageTable.getColumnModel().getColumn(2).setResizable(false);
            stageTable.getColumnModel().getColumn(3).setResizable(false);
            stageTable.getColumnModel().getColumn(4).setResizable(false);
            stageTable.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setText("Công nhân:");

        jLabel2.setText("Mã PC:");

        cbWorker.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbWorkerItemStateChanged(evt);
            }
        });

        jLabel3.setText("Số lương phân công: ");

        spinnerFinishAmount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerFinishAmountStateChanged(evt);
            }
        });

        jLabel4.setText("Công Đoạn Yêu Cầu: ");

        jLabel7.setText("CĐ Yêu Cầu Trạng Thái:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Phân Công Công Nhân");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setText("Mã CN:");

        cbWorkerId.setEnabled(false);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(labelNeedStageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtAssignmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbWorker, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(spinnerFinishAmount)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(29, 29, 29)
                                .addComponent(labelNeededStage, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbWorkerId, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAssignmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbWorkerId))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbWorker))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerFinishAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNeededStage, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(labelNeedStageStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete)
                            .addComponent(btnClear)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void stageTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stageTableMouseClicked
        int row = stageTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        String needStageId = stageTable.getValueAt(row, 2).toString();
        ProductionStage needStage = null;
        if (needStageId.equals("không có")) {
        } else {
            needStage = ProductionStageRepository.getInstance().getById(needStageId);
        }

        if (needStage == null) {
            labelNeededStage.setText("");
            labelNeedStageStatus.setText("");
        } else {
            labelNeededStage.setText(needStage.getName());
            labelNeedStageStatus.setText("Còn " + needStage.getAmount() + " sản phẩm");
        }

    }//GEN-LAST:event_stageTableMouseClicked

    private void cbWorkerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbWorkerItemStateChanged
        if (cbWorker.getItemCount() == 0 || cbWorkerId.getItemCount() == 0) {
            return;
        }
        int cbNameIndex = cbWorker.getSelectedIndex();
        if (cbNameIndex < 0) {
            return;
        }
        cbWorkerId.setSelectedIndex(cbNameIndex);
    }//GEN-LAST:event_cbWorkerItemStateChanged

    private void spinnerFinishAmountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerFinishAmountStateChanged
        spinnerFinishAmount.setValue(Utils.checkNumInt(spinnerFinishAmount.getValue().toString(), "Số Lượng", 1));
    }//GEN-LAST:event_spinnerFinishAmountStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Assignment assignment = getAssignmentFromField();
        if (assignment == null) {
            return;
        }
        if (assignment.getStage().getNeededStage() != null
                && assignment.getStage().getNeededStage().getAmount() > 0) {
            JOptionPane.showMessageDialog(null, "Không thể phân công đoạn kế tiếp, hãy hoàn thành công đoạn yêu cầu!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (repo.checkExistWorkerAssignmentSameStage(assignment)) {
            JOptionPane.showMessageDialog(null, "Không thể phân công 1 công nhân vào cùng 1 giai đoạn!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int nStageAmount = assignment.getStage().getAmount() - assignment.getFinishAmount();
        if (nStageAmount >= 0) {
            assignment.getStage().setAmount(nStageAmount);
            ProductionStageRepository.getInstance().update(assignment.getStage());
        }
        if (repo.add(assignment)) {
            JOptionPane.showMessageDialog(null, "Thêm Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadAssignmentTable();
            loadStageTable();
            clear();
            repo.getAll();
        } else {
            JOptionPane.showMessageDialog(null, "Mã PC đã tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String assignmentId = txtAssignmentId.getText().trim();
        if (assignmentId == null || assignmentId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã PC không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Assignment assignment = repo.getById(assignmentId);
        if (assignment == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy phân công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int amount = (int) spinnerFinishAmount.getValue();
        if (amount > assignment.getStage().getAmount()) {
            JOptionPane.showMessageDialog(null, "Không thể phân công số lượng sản phẩm lớn hơn số lượng sản phẩm trong công đoạn!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int nStageAmount = assignment.getStage().getAmount() - amount;
        if (nStageAmount >= 0) {
            assignment.getStage().setAmount(nStageAmount);
            ProductionStageRepository.getInstance().update(assignment.getStage());
            assignment.setFinishAmount(amount);
        }
        if (repo.update(assignment)) {
            JOptionPane.showMessageDialog(null, "Sửa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadAssignmentTable();
            loadStageTable();
            clear();
            repo.getAll();
        } else {
            JOptionPane.showMessageDialog(null, "Mã PC không tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String assignmentId = txtAssignmentId.getText().trim();
        if (assignmentId == null || assignmentId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã PC không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Assignment assignment = repo.getById(assignmentId);
        if(assignment == null) {
            JOptionPane.showMessageDialog(null, "Mã PC không tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int nStageAmount = assignment.getStage().getAmount() + assignment.getFinishAmount();
        if (nStageAmount >= 0) {
            assignment.getStage().setAmount(nStageAmount);
            ProductionStageRepository.getInstance().update(assignment.getStage());
        }
        if (repo.delete(assignmentId)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadAssignmentTable();
            loadStageTable();
            clear();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void assignmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assignmentTableMouseClicked
        int row = assignmentTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        txtAssignmentId.setText(assignmentTable.getValueAt(row, 0).toString());
        cbWorker.setSelectedItem(assignmentTable.getValueAt(row, 2).toString());
        spinnerFinishAmount.setValue(Integer.parseInt(assignmentTable.getValueAt(row, 5).toString()));
    }//GEN-LAST:event_assignmentTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable assignmentTable;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbWorker;
    private javax.swing.JComboBox<String> cbWorkerId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNeedStageStatus;
    private javax.swing.JLabel labelNeededStage;
    private javax.swing.JSpinner spinnerFinishAmount;
    private javax.swing.JTable stageTable;
    private javax.swing.JTextField txtAssignmentId;
    // End of variables declaration//GEN-END:variables
}
