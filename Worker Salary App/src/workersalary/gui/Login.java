package workersalary.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Login() {
		JFrame current = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		JButton btnLogin = new JButton("\u0110\u0103ng Nh\u1EADp");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				new MainPage(current).show();
//				setVisible(false);
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblngNhp = new JLabel("\u0110\u0103ng Nh\u1EADp");
		lblngNhp.setForeground(new Color(0, 128, 255));
		lblngNhp.setHorizontalAlignment(SwingConstants.CENTER);
		lblngNhp.setFont(new Font("Times New Roman", Font.BOLD, 27));
		
		JButton btnExit = new JButton("Tho\u00E1t");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		JLabel lbliMtKhu = new JLabel("Đổi Mật Khẩu");
		lbliMtKhu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new CreateAccount(current).show();
				setVisible(false);
			}
		});
		lbliMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lbliMtKhu.setForeground(new Color(0, 128, 255));
		lbliMtKhu.setFont(new Font("Times New Roman", Font.BOLD, 16));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lblngNhp, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addGap(118))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGap(41)
								.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addGap(77)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lbliMtKhu, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
							.addGap(128))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(lblngNhp)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbliMtKhu, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		txtPassword = new JPasswordField();
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("T\u00E0i Kho\u1EA3n");
		
		JLabel lblPassword = new JLabel("M\u1EADt Kh\u1EA9u");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(txtUsername, Alignment.LEADING)
						.addComponent(txtPassword, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
