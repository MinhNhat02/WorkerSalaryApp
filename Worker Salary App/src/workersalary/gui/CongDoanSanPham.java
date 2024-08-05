package workersalary.gui;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Product;
import workersalary.entity.ProductionStage;
import workersalary.repository.ProductRepository;
import workersalary.repository.ProductionStageRepository;
import workersalary.util.Utils;

public class CongDoanSanPham extends javax.swing.JPanel {

    private ProductionStageRepository repo = ProductionStageRepository.getInstance();

    public CongDoanSanPham() {
        initComponents();
        loadComboboxProduct();
        loadComboboxStage();
        loadProductTable();
        loadProductionStageTable();
        spinnerAmount.setValue(1);
    }

    public void loadComboboxStage() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbNeededStage.getModel();
        model.removeAllElements();
        model.addElement("");
        List<ProductionStage> list = ProductionStageRepository.getInstance().getList();
        if (list != null && list.size() > 0) {
            for (ProductionStage stage : list) {
                model.addElement(stage.getName());
            }
            if (cbNeededStage.getItemCount() > 0) {
                cbNeededStage.setSelectedIndex(0);
            }
        }
    }

    public void loadComboboxProduct() {
        DefaultComboBoxModel model1 = (DefaultComboBoxModel) cbProductId.getModel();
        DefaultComboBoxModel model2 = (DefaultComboBoxModel) cbProduct.getModel();
        model1.removeAllElements();
        model2.removeAllElements();
        List<Product> list = ProductRepository.getInstance().getList();
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                if (product.getAmount() > 0) {
                    model1.addElement(product.getId());
                    model2.addElement(product.getName());
                }
            }
            if (cbProductId.getItemCount() > 0) {
                cbProductId.setSelectedIndex(0);
            }
            if (cbProduct.getItemCount() > 0) {
                cbProduct.setSelectedIndex(0);
            }
        }
    }

    public void loadProductTable() {
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        model.setRowCount(0);
        List<Product> products = ProductRepository.getInstance().getList();
        if (products != null && products.size() > 0) {
            for (Product product : products) {
                if (product.getAmount() > 0) {
                    Object[] row = {
                        product.getId(),
                        product.getName(),
                        product.getStyle(),
                        product.getMaterial(),
                        product.getAmount(),};
                    model.addRow(row);
                }
            }
        }
        loadComboboxProduct();
    }

    public void loadProductionStageTable() {
        DefaultTableModel model = (DefaultTableModel) stageTable.getModel();
        model.setRowCount(0);
        List<ProductionStage> stages = repo.getList();
        if (stages != null && stages.size() > 0) {
            for (ProductionStage stage : stages) {
                String needStageStr = "";
                if (stage.getNeededStage() == null) {
                    needStageStr = "không có";
                } else {
                    needStageStr = stage.getNeededStage().getName();
                }
                Object[] row = {
                    stage.getId(),
                    stage.getName(),
                    Utils.doubleToString(stage.getPrice()),
                    stage.getProduct().getId(),
                    stage.getProduct().getName(),
                    stage.getAmount(),
                    needStageStr,};
                model.addRow(row);
            }
        }
        loadComboboxStage();
    }

    public void clear() {
        txtStageId.setText("");
        txtStageName.setText("");
        txtStagePrice.setText("10000");
        spinnerAmount.setValue(1);
        cbNeededStage.setSelectedIndex(0);
        if (cbProduct.getItemCount() > 0) {
            cbProduct.setSelectedIndex(0);
        }
        if (cbProductId.getItemCount() > 0) {
            cbProductId.setSelectedIndex(0);
        }
    }

    public ProductionStage getProductionStageFromField() {
        String id = txtStageId.getText().trim();
        String name = txtStageName.getText().trim();
        String priceStr = txtStagePrice.getText().trim();
        int amount = (int) spinnerAmount.getValue();
        String productId = cbProductId.getSelectedItem().toString();
        String needStageName = cbNeededStage.getSelectedItem().toString().trim();
        ProductionStage needStage = null;
        if (needStageName != null && needStageName.length() > 0) {
            needStage = repo.getByName(needStageName);
        }
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã CĐ không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Tên CĐ không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (priceStr == null || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Giá CĐ không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (productId == null || productId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã SP không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        double price = Double.parseDouble(priceStr);
        Product product = ProductRepository.getInstance().getById(productId);

        return new ProductionStage(id, name, price, product, amount, needStage);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        stageTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtStageId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtStageName = new javax.swing.JTextField();
        txtStagePrice = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbProductId = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbProduct = new javax.swing.JComboBox<>();
        spinnerAmount = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        cbNeededStage = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        stageTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CĐ", "Tên CĐ", "Giá CĐ", "Mã SP", "Tên SP", "Số Lượng", "Công Đoạn Yêu Câu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(stageTable);
        if (stageTable.getColumnModel().getColumnCount() > 0) {
            stageTable.getColumnModel().getColumn(0).setResizable(false);
            stageTable.getColumnModel().getColumn(1).setResizable(false);
            stageTable.getColumnModel().getColumn(2).setResizable(false);
            stageTable.getColumnModel().getColumn(3).setResizable(false);
            stageTable.getColumnModel().getColumn(4).setResizable(false);
            stageTable.getColumnModel().getColumn(5).setResizable(false);
            stageTable.getColumnModel().getColumn(6).setResizable(false);
        }

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Kiểu Dáng", "Chất Liệu", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(0).setResizable(false);
            productTable.getColumnModel().getColumn(1).setResizable(false);
            productTable.getColumnModel().getColumn(2).setResizable(false);
            productTable.getColumnModel().getColumn(3).setResizable(false);
            productTable.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setText("Mã CĐ:");

        jLabel2.setText("Tên CĐ:");

        txtStagePrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStagePriceFocusLost(evt);
            }
        });

        jLabel3.setText("Giá CĐ:");

        jLabel4.setText("Mã SP:");

        cbProductId.setEnabled(false);

        jLabel5.setText("Tên SP:");

        cbProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProductItemStateChanged(evt);
            }
        });

        spinnerAmount.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerAmountStateChanged(evt);
            }
        });
        spinnerAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spinnerAmountFocusLost(evt);
            }
        });

        jLabel6.setText("Số Lượng:");

        jLabel7.setText("Công đoạn cần làm trước");

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

        btnClear.setText("Xóa Rỗng");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Công Đoạn Sản Phẩm");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtStageId, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtStageName, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cbProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(spinnerAmount)
                                        .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtStagePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cbNeededStage, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(88, 88, 88)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStageId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProductId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStageName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStagePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinnerAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNeededStage))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnAdd)
                                .addComponent(btnUpdate)
                                .addComponent(btnClear)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProductItemStateChanged
        if (cbProduct.getItemCount() == 0 || cbProductId.getItemCount() == 0) {
            return;
        }
        int cbNameIndex = cbProduct.getSelectedIndex();
        if (cbNameIndex < 0) {
            return;
        }
        cbProductId.setSelectedIndex(cbNameIndex);
    }//GEN-LAST:event_cbProductItemStateChanged

    private void spinnerAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spinnerAmountFocusLost

    }//GEN-LAST:event_spinnerAmountFocusLost

    private void txtStagePriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStagePriceFocusLost
        String priceStr = txtStagePrice.getText().toString().trim();
        if (priceStr == null || priceStr.isEmpty()) {
            return;
        }
        txtStagePrice.setText(String.format("%.2f", Utils.checkNumDouble(priceStr, "Giá CĐ", 10000)));
    }//GEN-LAST:event_txtStagePriceFocusLost

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        ProductionStage stage = getProductionStageFromField();
        if (stage == null) {
            return;
        }
        int productLeft = stage.getProduct().getAmount() - stage.getAmount();
        if (productLeft >= 0) {
            stage.getProduct().setAmount(productLeft);
            if (repo.checkExistStageNeeded(stage)) {
                JOptionPane.showMessageDialog(null, "Công đoạn hiện đang được yêu cầu bởi công đoạn khác!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            ProductRepository.getInstance().update(stage.getProduct());
            loadProductTable();
        }
        if (repo.add(stage)) {
            JOptionPane.showMessageDialog(null, "Thêm Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadProductionStageTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã CĐ đã tồn tại!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String id = txtStageId.getText().trim();
        if (id == null || id.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô Mã CĐ không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (repo.delete(id)) {
            JOptionPane.showMessageDialog(null, "Xóa Thành Công.", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadProductionStageTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, "Mã CĐ không tồn tại hoặc tồn tại dữ liệu phân công nên không thể xóa!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        ProductionStage stage = getProductionStageFromField();
        if (stage == null) {
            return;
        }
        String text = repo.update(stage);
        if (text.equals("Sửa Thành Công")) {
            JOptionPane.showMessageDialog(null, text + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
            loadProductionStageTable();
            clear();
        } else {
            JOptionPane.showMessageDialog(null, text + "!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void productTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productTableMouseClicked
        if (productTable.getRowCount() <= 0) {
            return;
        }
        int row = productTable.getSelectedRow();
        if (row < 0) {
            return;
        }
        cbProduct.setSelectedItem(productTable.getValueAt(row, 1).toString());
    }//GEN-LAST:event_productTableMouseClicked

    private void stageTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stageTableMouseClicked
        if (stageTable.getRowCount() <= 0) {
            return;
        }
        int row = stageTable.getSelectedRow();
        if (row < 0) {
            return;
        }

        txtStageId.setText(stageTable.getValueAt(row, 0).toString());
        txtStageName.setText(stageTable.getValueAt(row, 1).toString());
        txtStagePrice.setText(stageTable.getValueAt(row, 2).toString());
        cbProductId.setSelectedItem(stageTable.getValueAt(row, 3).toString());
        cbProduct.setSelectedItem(stageTable.getValueAt(row, 4).toString());
        spinnerAmount.setValue(Integer.parseInt(stageTable.getValueAt(row, 5).toString()));
        String needStageStr = stageTable.getValueAt(row, 6).toString();
        if (needStageStr.equalsIgnoreCase("không có")) {
            cbNeededStage.setSelectedIndex(0);
        } else {
            cbNeededStage.setSelectedItem(needStageStr);
        }
    }//GEN-LAST:event_stageTableMouseClicked

    private void spinnerAmountStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerAmountStateChanged
        String productId = cbProductId.getSelectedItem().toString();
        if (productId == null || productId.isEmpty()) {
            return;
        }
        Product product = ProductRepository.getInstance().getById(productId);
        if (product == null) {
            return;
        }
        spinnerAmount.setValue(Utils.checkNumInt(spinnerAmount.getValue().toString(), "Số Lượng", 1, product.getAmount()));
    }//GEN-LAST:event_spinnerAmountStateChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbNeededStage;
    private javax.swing.JComboBox<String> cbProduct;
    private javax.swing.JComboBox<String> cbProductId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable productTable;
    private javax.swing.JSpinner spinnerAmount;
    private javax.swing.JTable stageTable;
    private javax.swing.JTextField txtStageId;
    private javax.swing.JTextField txtStageName;
    private javax.swing.JTextField txtStagePrice;
    // End of variables declaration//GEN-END:variables
}
