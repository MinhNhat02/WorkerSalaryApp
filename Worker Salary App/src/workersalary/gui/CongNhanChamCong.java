package workersalary.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Assignment;
import workersalary.entity.Employee;
import workersalary.entity.ProductionStage;
import workersalary.entity.ShiftWork;
import workersalary.entity.TimeKeeping;
import workersalary.entity.implement.TimeKeepingStaff;
import workersalary.entity.implement.TimeKeepingWorker;
import workersalary.repository.AssignmentRepository;
import workersalary.repository.EmployeeRepository;
import workersalary.repository.ProductRepository;
import workersalary.repository.ProductionStageRepository;
import workersalary.repository.ShiftWorkRepository;
import workersalary.repository.TimeKeepingRepository;
import workersalary.entity.Product;
import workersalary.util.Utils;

/**
 *
 * @author Admin
 */
public class CongNhanChamCong extends javax.swing.JPanel {

    private TimeKeepingRepository repo = TimeKeepingRepository.getInstance();
    private boolean isClickedGetWorkerTable = false;

    public CongNhanChamCong() {
        initComponents();
        loadComboboxProduct();
        loadComboboxShift();
        loadComboboxStage();
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
                if (timeKeeping instanceof TimeKeepingWorker) {
                    String strLeavePermission = "";
                    if (timeKeeping.isLeavePermission()) {
                        strLeavePermission = "có";
                    } else {
                        strLeavePermission = "không";
                    }
                    Object[] row = {
                        timeKeeping.getId(),
                        timeKeeping.getEmployee().getId(),
                        timeKeeping.getEmployee().getFullName(),
                        Utils.dateToString(timeKeeping.getCreatedDate()),
                        timeKeeping.getShiftWork().getName(),
                        Utils.timeToTimeToString(timeKeeping.getShiftWork().getStartTime(), timeKeeping.getShiftWork().getEndTime()),
                        ((TimeKeepingWorker) timeKeeping).getAssignment().getId(),
                        ((TimeKeepingWorker) timeKeeping).getStage().getName(),
                        ((TimeKeepingWorker) timeKeeping).getAssignment().getFinishAmount(),
                        timeKeeping.getStatus(),
                        strLeavePermission};
                    model.addRow(row);
                }
            }
        }
    }

    public void loadComboboxProduct() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbProduct.getModel();
        model.removeAllElements();
        List<Product> list = ProductRepository.getInstance().getList();
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                model.addElement(product.getName());
            }
            if (cbProduct.getItemCount() > 0) {
                cbProduct.setSelectedIndex(0);
            }
        }
    }

    public void loadComboboxStage() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbStage.getModel();
        model.removeAllElements();
        List<ProductionStage> list = ProductionStageRepository.getInstance().getList();
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                model.addElement(stage.getName());
            }
            if (cbStage.getItemCount() > 0) {
                cbStage.setSelectedIndex(0);
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
                txtShiftSalary.setText(shift.getSalary() + "");
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
        boolean leavePermission = Boolean.parseBoolean(assignmentTable.getValueAt(row, 6).toString());
        return new TimeKeepingStaff(id, employee, Utils.getCurDate(), shiftWork, status, leavePermission);
    }

    public void getTimeKeepingWorkers() {
        DefaultTableModel model = (DefaultTableModel) assignmentTable.getModel();
        List<Assignment> list = AssignmentRepository.getInstance().getList();
        model.setRowCount(0);
        String shiftWorkName = cbShiftWork.getSelectedItem().toString();
        ShiftWork shiftWork = ShiftWorkRepository.getInstance().getByName(shiftWorkName);
        if (shiftWork == null) {
            JOptionPane.showMessageDialog(null, "Không Có Ca làm việc để lấy danh sách chấm công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        java.sql.Date curDate = new java.sql.Date(System.currentTimeMillis());
        if (list != null && list.size() > 0) {
            for (Assignment assignment : list) {
                if (repo.checkTimeKeeping(assignment.getWorker().getId(), curDate, shiftWork.getId()) == null) {
                    Object[] row = {
                        assignment.getId(),
                        assignment.getWorker().getId(),
                        assignment.getWorker().getFullName(),
                        false,
                        false,
                        0
                    };
                    model.addRow(row);
                }
            }
            isClickedGetWorkerTable = true;
        } else {
            isClickedGetWorkerTable = false;
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

    private TimeKeeping getTimeKeepingFromWorkerTable() {
        String id = txtId.getText().trim();
        ShiftWork shiftWork = getShiftWorkWithField();
        int row = assignmentTable.getSelectedRow();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã Công không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (cbStage.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Công Đoạn không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (cbProduct.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Sản Phẩm không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        ProductionStage stage = ProductionStageRepository.getInstance().getByName(cbStage.getSelectedItem().toString());
        Product product = ProductRepository.getInstance().getByName(cbProduct.getSelectedItem().toString());
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn 1 phân công để chấm công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        String assignmentId = assignmentTable.getValueAt(row, 0).toString();
        if (assignmentId == null || assignmentId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tồn tại mã phân công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        Assignment assignment = AssignmentRepository.getInstance().getById(assignmentId);
        String employeeId = assignmentTable.getValueAt(row, 1).toString();
        Employee employee = EmployeeRepository.getInstance().getById(employeeId);
        boolean exist = Boolean.parseBoolean(assignmentTable.getValueAt(row, 3).toString());
        String status = "";
        if (exist) {
            status = "có mặt";
        } else {
            status = "nghỉ";
        }
        boolean leavePermission = Boolean.parseBoolean(assignmentTable.getValueAt(row, 4).toString());
        return new TimeKeepingWorker(id, employee, Utils.getCurDate(), shiftWork, status, leavePermission, product, stage, assignment);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        cbShiftWork = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        btnTimeKeeping = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        timeKeepingTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        assignmentTable = new javax.swing.JTable();
        btnGetStaffs = new javax.swing.JButton();
        btnResetTimeKeeping = new javax.swing.JButton();
        txtShiftSalary = new javax.swing.JTextField();
        btnCheckAll = new javax.swing.JButton();
        txtTimeWork = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        cbProduct = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbStage = new javax.swing.JComboBox<>();

        jLabel6.setText("Mã Công");

        cbShiftWork.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbShiftWorkItemStateChanged(evt);
            }
        });

        jLabel5.setText("Lương Theo Ca:");

        jLabel2.setText("Giờ Làm:");

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnTimeKeeping.setText("Chấm Công");
        btnTimeKeeping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeKeepingActionPerformed(evt);
            }
        });

        timeKeepingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Công", "Mã CN", "Tên CN", "Ngày Chấm", "Ca", "Giờ Làm", "Mã PC", "Công Đoạn", "Số Sản Phẩm", "Trạng Thái", "Nghỉ Phép"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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
            timeKeepingTable.getColumnModel().getColumn(8).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(9).setResizable(false);
            timeKeepingTable.getColumnModel().getColumn(10).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chấm Công Công Nhân");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setText("Ca Làm:");

        assignmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã PC", "Mã CN", "Tên CN", "Có Mặt", "Có Phép"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        assignmentTable.setColumnSelectionAllowed(true);
        assignmentTable.getTableHeader().setReorderingAllowed(false);
        assignmentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                assignmentTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(assignmentTable);
        assignmentTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (assignmentTable.getColumnModel().getColumnCount() > 0) {
            assignmentTable.getColumnModel().getColumn(0).setResizable(false);
            assignmentTable.getColumnModel().getColumn(1).setResizable(false);
            assignmentTable.getColumnModel().getColumn(2).setResizable(false);
            assignmentTable.getColumnModel().getColumn(3).setResizable(false);
            assignmentTable.getColumnModel().getColumn(4).setResizable(false);
        }

        btnGetStaffs.setText("Lấy Danh Sách Chấm Công");
        btnGetStaffs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetStaffsActionPerformed(evt);
            }
        });

        btnResetTimeKeeping.setText("Đặt Lại Chấm Công");
        btnResetTimeKeeping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTimeKeepingActionPerformed(evt);
            }
        });

        txtShiftSalary.setEditable(false);

        btnCheckAll.setText("Có Mặt Tất Cả");
        btnCheckAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckAllActionPerformed(evt);
            }
        });

        txtTimeWork.setEditable(false);

        cbProduct.setEnabled(false);
        cbProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProductItemStateChanged(evt);
            }
        });
        cbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProductActionPerformed(evt);
            }
        });

        jLabel7.setText("Sản Phẩm:");

        jLabel8.setText("Công Đoạn:");

        cbStage.setEnabled(false);
        cbStage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbStageItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(592, 592, 592)
                        .addComponent(btnTimeKeeping, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(807, 807, 807)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbStage, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(547, 547, 547)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetTimeKeeping, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGetStaffs, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCheckAll, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTimeWork, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbShiftWork, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtShiftSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(284, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(15, 15, 15))))
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbStage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGetStaffs)
                    .addComponent(btnCheckAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetTimeKeeping)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimeKeeping)
                    .addComponent(btnDelete))
                .addGap(404, 404, 404))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
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
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtShiftSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(252, 252, 252))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timeKeepingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_timeKeepingTableMouseClicked
        if (timeKeepingTable.getRowCount() <= 0) {
            return;
        }
        int row = timeKeepingTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        String id = timeKeepingTable.getValueAt(row, 0).toString();
        String shiftWorkName = timeKeepingTable.getValueAt(row, 4).toString();
        String dateStr = timeKeepingTable.getValueAt(row, 3).toString();
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

    private void cbShiftWorkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbShiftWorkItemStateChanged
        String curItem = cbShiftWork.getSelectedItem().toString();
        if (curItem == null || curItem.isEmpty()) {
            return;
        }
        ShiftWork shift = ShiftWorkRepository.getInstance().getByName(curItem);
        txtShiftSalary.setText(shift.getSalary() + "");
        txtTimeWork.setText(String.format("%d:%02d-%d:%02d", shift.getStartTime().getHours(), shift.getStartTime().getMinutes(),
                shift.getEndTime().getHours(), shift.getEndTime().getMinutes()));
        if (isClickedGetWorkerTable)
            getTimeKeepingWorkers();
    }//GEN-LAST:event_cbShiftWorkItemStateChanged

    private void btnGetStaffsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetStaffsActionPerformed
        getTimeKeepingWorkers();
    }//GEN-LAST:event_btnGetStaffsActionPerformed

    private void btnCheckAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckAllActionPerformed
        DefaultTableModel model = (DefaultTableModel) assignmentTable.getModel();
        if (model == null || model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Nào Để Chấm Công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(true, i, 3);
        }
    }//GEN-LAST:event_btnCheckAllActionPerformed

    private void btnResetTimeKeepingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTimeKeepingActionPerformed
        DefaultTableModel model = (DefaultTableModel) assignmentTable.getModel();
        if (model == null || model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không Có Nhân Viên Nào Để Chấm Công!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(false, i, 3);
            model.setValueAt(false, i, 4);
        }
    }//GEN-LAST:event_btnResetTimeKeepingActionPerformed

    private void btnTimeKeepingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeKeepingActionPerformed
        TimeKeeping timeKeeping = getTimeKeepingFromWorkerTable();
        if (timeKeeping == null) {
            return;
        }
        int row = assignmentTable.getSelectedRow();
        if(row < 0) {
            return;
        }
        if (repo.add(timeKeeping)) {
            JOptionPane.showMessageDialog(null, "Chấm công thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            getTimeKeepingWorkers();
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
            getTimeKeepingWorkers();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã Công không tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void cbProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProductItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProductItemStateChanged

    private void cbStageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbStageItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbStageItemStateChanged

    private void cbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbProductActionPerformed

    private void assignmentTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_assignmentTableMouseClicked
        int row = assignmentTable.getSelectedRow();
        if(row < 0) {
            return;
        }
        String assignmentId = assignmentTable.getValueAt(row, 0).toString();
        Assignment assignment = AssignmentRepository.getInstance().getById(assignmentId);
        if(assignment == null) return;
        cbProduct.setSelectedItem(assignment.getStage().getProduct().getName());
        cbStage.setSelectedItem(assignment.getStage().getName());
    }//GEN-LAST:event_assignmentTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable assignmentTable;
    private javax.swing.JButton btnCheckAll;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnGetStaffs;
    private javax.swing.JButton btnResetTimeKeeping;
    private javax.swing.JButton btnTimeKeeping;
    private javax.swing.JComboBox<String> cbProduct;
    private javax.swing.JComboBox<String> cbShiftWork;
    private javax.swing.JComboBox<String> cbStage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable timeKeepingTable;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtShiftSalary;
    private javax.swing.JTextField txtTimeWork;
    // End of variables declaration//GEN-END:variables
}
