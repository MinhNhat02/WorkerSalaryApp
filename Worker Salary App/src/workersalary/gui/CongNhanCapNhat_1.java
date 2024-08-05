package workersalary.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class CongNhanCapNhat_1 extends JPanel {
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public CongNhanCapNhat_1() {
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 NV", "T\u00EAn NV", "CMND", "Ng\u00E0y Sinh", "Gi\u1EDBi T\u00EDnh", "\u0110ia Ch\u1EC9", "S\u0110T", "L\u01B0\u01A1ng CB", "Ph\u1EE5 C\u1EA5p", "M\u00E3 HSL", "M\u00E3 PB", "HSL", "Ph\u00F2ng Ban"
			}
		));

		JLabel lblNewLabel = new JLabel("Mã NV");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblMHsl = new JLabel("Lương Cơ Bản");
		lblMHsl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblMHsl_1 = new JLabel("Mã HSL");
		lblMHsl_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblMPb = new JLabel("Mã PB");
		lblMPb.setFont(new Font("Tahoma", Font.PLAIN, 13));

		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ Tên");
		lblHTn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JComboBox comboBox = new JComboBox();
		
		JComboBox comboBox_1 = new JComboBox();
		
		JComboBox comboBox_2 = new JComboBox();
		
		JComboBox comboBox_3 = new JComboBox();
		
		JComboBox comboBox_4 = new JComboBox();
		
		JLabel lblGiiTnh = new JLabel("Giới Tính");
		lblGiiTnh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblPhCp = new JLabel("Phụ Cấp");
		lblPhCp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblHSLng = new JLabel("Hệ Số Lượng");
		lblHSLng.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblPhngBan = new JLabel("Phòng Ban");
		lblPhngBan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(table, GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 662, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCmnd, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMHsl, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMPb, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblMHsl_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNgySinh)
										.addComponent(lblHTn))
									.addGap(38))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblHSLng, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPhngBan, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPhCp, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lblCmnd, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblMHsl, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(lblMPb, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblMHsl_1, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblHTn, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
									.addGap(11)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblHSLng, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblPhngBan, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSinThoi, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblaCh, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
										.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPhCp, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblGiiTnh, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
					.addGap(34)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
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

	}
}
