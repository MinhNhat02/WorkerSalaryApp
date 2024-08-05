package workersalary.gui;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import workersalary.entity.Product;
import workersalary.repository.ProductRepository;

public class SanPhamCapNhat_1 extends JPanel {

    private JTable table;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_8;

    public SanPhamCapNhat_1() {
        
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "Mã SP", "Tên SP", "Kiểu Dáng", "Chất Liệu", "Số Lượng"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
        }
        JLabel lblNewLabel = new JLabel("Mã SP");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JLabel lblCmnd = new JLabel("Kiểu Dáng");
        lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 13));

        textField = new JTextField();
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setColumns(10);

        JLabel lblHTn = new JLabel("Tên Sản Phẩm");
        lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JLabel lblNgySinh = new JLabel("Chất Liệu");
        lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JLabel lblGiiTnh = new JLabel("Số Lượng");
        lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 13));

        JPanel panel = new JPanel();

        JSpinner spinner = new JSpinner();
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(86)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblNewLabel)
                                                        .addComponent(lblCmnd, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE))
                                                .addGap(37)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                                                .addGap(31)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(lblHTn)
                                                        .addComponent(lblNgySinh))
                                                .addGap(38)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24)
                                                                .addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18)
                                                                .addComponent(spinner, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(164, Short.MAX_VALUE))
                        .addComponent(table, GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblCmnd, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                                                .addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                                .addGap(47)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                                .addGap(33)
                                .addComponent(table, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
        );

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton.setPreferredSize(new Dimension(50, 30));

        JButton btnSa = new JButton("Sửa");
        btnSa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSa.setPreferredSize(new Dimension(50, 30));

        JButton btnXa = new JButton("Xóa");
        btnXa.setPreferredSize(new Dimension(50, 30));

        JButton btnXaTrng = new JButton("Xóa Trống");
        btnXaTrng.setPreferredSize(new Dimension(50, 30));

        JButton btnTmKim = new JButton("Tìm Kiếm");
        btnTmKim.setPreferredSize(new Dimension(50, 30));

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(117)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnSa, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(btnXa, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18)
                                                .addComponent(btnXaTrng, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(47, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSa, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXa, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXaTrng, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(btnTmKim, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        setLayout(groupLayout);
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Product> list = ProductRepository.getInstance().getList();
        System.out.println(list.size());
        model.setRowCount(0);
        if (list != null && list.size() > 0) {
            for (Product product : list) {
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
}
