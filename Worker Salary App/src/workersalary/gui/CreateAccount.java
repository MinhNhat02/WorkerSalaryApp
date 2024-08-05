package workersalary.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtOldPass;
	private JTextField txtNewPass;
	private JTextField txtRepeat;
	private JFrame parent;

	public CreateAccount(JFrame parent) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setVisible(true);
			}
		});
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JLabel lblTitle = new JLabel("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 128, 255));
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 27));
		
		JButton btnSave = new JButton("L\u01B0u");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnExit = new JButton("Tho\u00E1t");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				parent.setVisible(true);
			}
		});
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_1_1 = new JPanel();
		
		JPanel panel_1_1_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
						.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(125)
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(124, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(104, Short.MAX_VALUE)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(83))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(32))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(112)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(13))
		);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("T\u00E0i Kho\u1EA3n");
		
		JLabel lblUsername_1 = new JLabel("M\u1EADt Kh\u1EA9u");
		
		txtOldPass = new JTextField();
		txtOldPass.setColumns(10);
		
		JLabel lblUsername_2 = new JLabel("M\u1EADt Kh\u1EA9u M\u1EDBi");
		
		txtNewPass = new JTextField();
		txtNewPass.setColumns(10);
		
		JLabel lblUsername_2_1 = new JLabel("Nh\u1EADp L\u1EA1i");
		
		txtRepeat = new JTextField();
		txtRepeat.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblUsername_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(txtNewPass, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblUsername_2_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(txtRepeat, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblUsername_1, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtOldPass, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))))
					.addGap(10))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOldPass, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername_1))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername_2)
						.addComponent(txtNewPass, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRepeat, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername_2_1))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}
