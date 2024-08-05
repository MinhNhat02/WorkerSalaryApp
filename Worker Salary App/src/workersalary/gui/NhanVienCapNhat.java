package workersalary.gui;

import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Department;
import workersalary.entity.Employee;
import workersalary.entity.implement.AdministrativeStaff;
import workersalary.repository.DepartmentRepository;
import workersalary.repository.EmployeeRepository;
import workersalary.util.Utils;

public class NhanVienCapNhat extends javax.swing.JPanel {

    private EmployeeRepository repo = EmployeeRepository.getInstance();
    
    public NhanVienCapNhat() {
        initComponents();
        loadComboboxGender();
        loadComboboxDepartment();
        loadTable();
    }

    private void loadComboboxGender() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbGender.getModel();
        model.removeAllElements();
        model.addElement("Nam");
        model.addElement("Nữ");
        model.addElement("Khác");
    }

    private void loadComboboxDepartment() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbDepartment.getModel();
        model.removeAllElements();
        List<Department> list = DepartmentRepository.getInstance().getList();
        if (list != null && list.size() > 0) {
            for (Department department : list) {
                model.addElement(department.getName());
            }
            if(cbDepartment.getItemCount() > 0) cbDepartment.setSelectedIndex(0);
        }
    }

    public void loadTable() {
        loadTable(repo.getList());
    }

    public void loadTable(List<Employee> employees) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Employee> list = employees;
        model.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (Employee employee : list) {
                if (employee instanceof AdministrativeStaff) {
                    AdministrativeStaff staff = (AdministrativeStaff) employee;
                    Object[] row = {
                        staff.getId(),
                        staff.getFullName(),
                        staff.getIdentifyCard(),
                        Utils.dateToString(employee.getDateOfBirth()),
                        staff.getGender(),
                        staff.getAddress(),
                        staff.getPhoneNumber(),
                        String.format("%.2f", staff.getBasicSalary()),
                        String.format("%.2f", staff.getAllowance()),
                        staff.getDepartment().getName()
                    };
                    model.addRow(row);
                }
            }
        }
    }

    public void clear() {
        txtStaffId.setText("");
        txtAllowance.setText("");
        txtFullname.setText("");
        txtIdentifyCard.setText("");
        txtPhoneNumber.setText("");
        txtAllowance.setText("");
        dateChooser.setDate(null);
        if(cbDepartment.getItemCount() > 0) cbDepartment.setSelectedIndex(0);
        cbGender.setSelectedIndex(0);
        txtSearch.setText("");
        txtAddress.setText("");
        txtBasicSalary1.setText("");
    }

    public Employee getEmployeeInTable() {
        java.util.Date currDate = new java.util.Date();
        String id = txtStaffId.getText().trim();
        String fullName = txtFullname.getText().trim();
        String identifyCard = txtIdentifyCard.getText().trim();
        String gender = cbGender.getSelectedItem().toString();
        String address = txtAddress.getText().trim();
        String basicSalaryStr = txtBasicSalary1.getText().trim();
        String allowanceStr = txtAllowance.getText().trim();
        String departmentName = cbDepartment.getSelectedItem().toString();
        String phoneNumber = txtPhoneNumber.getText().trim();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã NV không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (fullName == null || fullName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Họ và Tên không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (identifyCard == null || identifyCard.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô CMND không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (dateChooser.getDate() == null || dateChooser.toString().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Ngày Sinh không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        java.util.Date dateChoose = dateChooser.getDate();
        Date dateOfBirth = new Date(dateChoose.getYear(), dateChoose.getMonth(), dateChoose.getDate());
        if (dateOfBirth.getYear() > currDate.getYear() + 100 || dateOfBirth.getYear() < currDate.getYear() - 100) {
            JOptionPane.showMessageDialog(null, "Ngày Sinh phải nằm trong khoảng " + (currDate.getYear() - 100)
                    + " đến " + (currDate.getYear() + 100) + "!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (basicSalaryStr == null || basicSalaryStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Lương Cơ Bản không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (allowanceStr == null || allowanceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Phụ Cấp không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (address == null || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Địa Chỉ không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Số Điến Thoại không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (phoneNumber.length() != 10) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có 10 số!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        double basicSalary = Double.parseDouble(basicSalaryStr);
        double allowance = Double.parseDouble(allowanceStr);
        Department department = DepartmentRepository.getInstance().getByName(departmentName);
        return new AdministrativeStaff(basicSalary, id, fullName, identifyCard, dateOfBirth, gender, allowance, phoneNumber, address, department);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        txtIdentifyCard = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        txtStaffId = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        txtFullname = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbGender = new javax.swing.JComboBox<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        txtAllowance = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cbDepartment = new javax.swing.JComboBox<>();
        txtAddress = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBasicSalary1 = new javax.swing.JTextField();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhân Viên Hành Chính");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Ngày Sinh:");

        jLabel6.setText("Mã NV:");

        jLabel5.setText("Phụ Cấp");

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtIdentifyCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentifyCardActionPerformed(evt);
            }
        });

        btnSearch.setText("Tim Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã NV: ");

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel3.setText("CMND:");

        btnClear.setText("Xóa Rỗng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtStaffId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStaffIdActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        jLabel7.setText("Họ Tên:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        table.setBackground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "CMND", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Lương CB", "Phụ Cấp", "Phòng Ban"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(8).setResizable(false);
            table.getColumnModel().getColumn(9).setResizable(false);
        }

        jLabel8.setText("Giới Tính:");

        cbGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGenderActionPerformed(evt);
            }
        });

        dateChooser.setDateFormatString("MMMM dd, yyyy");

        jLabel9.setText("Lương Cơ Bản:");

        txtAllowance.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAllowanceFocusLost(evt);
            }
        });
        txtAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAllowanceActionPerformed(evt);
            }
        });

        jLabel10.setText("Số ĐT: ");

        txtPhoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhoneNumberActionPerformed(evt);
            }
        });

        jLabel11.setText("Phong Ban:");

        txtAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddressActionPerformed(evt);
            }
        });

        jLabel12.setText("Địa Chỉ:");

        txtBasicSalary1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBasicSalary1FocusLost(evt);
            }
        });
        txtBasicSalary1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBasicSalary1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(70, 70, 70)
                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(16, 16, 16)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtIdentifyCard, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtStaffId, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBasicSalary1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtAllowance, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                .addGap(27, 27, 27))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStaffId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdentifyCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBasicSalary1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        loadTable();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtIdentifyCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentifyCardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentifyCardActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String search = txtSearch.getText().trim();
        if (search == null || search.isEmpty())
            loadTable();
        else {
            loadTable(repo.filterById(search));
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtStaffId.getText().trim();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã NV không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (repo.delete(id)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Không tồn tại NV!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void txtStaffIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStaffIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStaffIdActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        Employee employee = getEmployeeInTable();
        if (employee == null) {
            return;
        }
        if (repo.update(employee)) {
            JOptionPane.showMessageDialog(null, "Sửa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Không tồn tại NV!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        Employee employee = getEmployeeInTable();
        if (employee == null) {
            return;
        }
        if (repo.add(employee)) {
            JOptionPane.showMessageDialog(null, "Thêm Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã NV đã tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void cbGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGenderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbGenderActionPerformed

    private void txtAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAllowanceActionPerformed

    private void txtPhoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPhoneNumberActionPerformed

    private void txtAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddressActionPerformed

    private void txtBasicSalary1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBasicSalary1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBasicSalary1ActionPerformed

    private void txtAllowanceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAllowanceFocusLost
        String text = checkNum(txtAllowance.getText().trim(), "Phụ Cấp");
        txtAllowance.setText(text);
    }//GEN-LAST:event_txtAllowanceFocusLost

    public String checkNum(String text, String title) {
        String result = "";
        if (text == null || text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return result;
        }
        double allowance = -1;
        try {
            allowance = Double.parseDouble(text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " chỉ có thể nhập số!", "Warning", JOptionPane.WARNING_MESSAGE);
            return result;
        }
        if (allowance <= 10000) {
            result = "10000";
        } else {
            result = text;
        }
        return result;
    }

    private void txtBasicSalary1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBasicSalary1FocusLost
        String text = checkNum(txtBasicSalary1.getText().trim(), "Lương Cơ Bản");
        txtBasicSalary1.setText(text);
    }//GEN-LAST:event_txtBasicSalary1FocusLost

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        if (table.getRowCount() <= 0) {
            return;
        }
        int row = table.getSelectedRow();
        if (row < 0) {
            return;
        }
        String id = table.getValueAt(row, 0).toString();
        String fullName = table.getValueAt(row, 1).toString();
        String cmnd = table.getValueAt(row, 2).toString();
        String dateOfBirth = table.getValueAt(row, 3).toString();
        String gender = table.getValueAt(row, 4).toString();
        String address = table.getValueAt(row, 5).toString();
        String phoneNumber = table.getValueAt(row, 6).toString();
        String basicSalary = table.getValueAt(row, 7).toString();
        String allowance = table.getValueAt(row, 8).toString();
        String departmentName = table.getValueAt(row, 9).toString();
        int year = -1;
        int month = -1;
        int day = -1;
        try {
            day = Integer.parseInt(dateOfBirth.split("/")[0]);
            month = Integer.parseInt(dateOfBirth.split("/")[1]);
            year = Integer.parseInt(dateOfBirth.split("/")[2]);
        } catch (Exception e) {

        }
        txtStaffId.setText(id);
        txtFullname.setText(fullName);
        txtIdentifyCard.setText(cmnd);
        dateChooser.setDate(new Date(year - 1900, month - 1, day));
        txtBasicSalary1.setText(basicSalary);
        txtAllowance.setText(allowance);
        txtAddress.setText(address);
        txtPhoneNumber.setText(phoneNumber);
        cbGender.setSelectedItem(gender);
        cbDepartment.setSelectedItem(departmentName);
    }//GEN-LAST:event_tableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbDepartment;
    private javax.swing.JComboBox<String> cbGender;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAllowance;
    private javax.swing.JTextField txtBasicSalary1;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtIdentifyCard;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStaffId;
    // End of variables declaration//GEN-END:variables
}
