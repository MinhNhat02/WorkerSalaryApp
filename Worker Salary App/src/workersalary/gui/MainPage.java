package workersalary.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.AbstractButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Component;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JLabel;

public class MainPage extends JFrame {

    private JPanel contentPane;
    private final ButtonGroup buttonGroup_0 = new ButtonGroup();
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();
    private final ButtonGroup buttonGroup_2 = new ButtonGroup();
    private JLabel imageLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainPage frame = new MainPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainPage() {
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                parent.setVisible(true);
//            }
//        });
        setBackground(new Color(0, 0, 0));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        contentPane = new JPanel();
        contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPane.setBorder(null);

        setContentPane(contentPane);

        JPanel functionPanel = new JPanel();
        functionPanel.setBounds(0, 0, 197, 750);
        functionPanel.setBorder(UIManager.getBorder("MenuBar.border"));
        functionPanel.setAutoscrolls(true);
        functionPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        functionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        FlowLayout flowLayout = (FlowLayout) functionPanel.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        functionPanel.setBackground(new Color(255, 255, 255));

        JPanel viewPanel = new JPanel();
        viewPanel.setBounds(198, 0, 985, 750);
        viewPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        viewPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        viewPanel.setBackground(new Color(255, 255, 255));

        JButton btnTChu = new JButton("Trang Chủ");
        btnTChu.setHorizontalAlignment(SwingConstants.LEFT);
        btnTChu.setBackground(new Color(51, 102, 204));
        btnTChu.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnTChu.setForeground(Color.WHITE);
        btnTChu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnTChu.setPreferredSize(new Dimension(190, 40));
        functionPanel.add(btnTChu);
        
        JButton btnNv = new JButton("Nhân Viên");
        btnNv.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisibleGroupButton(buttonGroup_0);
                clearViewPanel(viewPanel);
            }
        });
        btnNv.setHorizontalAlignment(SwingConstants.LEFT);
        btnNv.setBackground(new Color(51, 102, 204));
        btnNv.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNv.setForeground(Color.WHITE);
        btnNv.setPreferredSize(new Dimension(190, 40));
        functionPanel.add(btnNv);
        
        JButton btnNvCN = new JButton("Cập Nhật");
        btnNvCN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new NhanVienCapNhat());
            }
        });
        buttonGroup_0.add(btnNvCN);
        btnNvCN.setHorizontalAlignment(SwingConstants.LEFT);
        btnNvCN.setPreferredSize(new Dimension(190, 40));
        btnNvCN.setForeground(Color.WHITE);
        btnNvCN.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNvCN.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnNvCN);

        JButton btnNvCC = new JButton("Chấm Công");
        btnNvCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new NhanVienChamCong());
            }
        });
        buttonGroup_0.add(btnNvCC);
        btnNvCC.setHorizontalAlignment(SwingConstants.LEFT);
        btnNvCC.setBackground(new Color(51, 153, 255));
        btnNvCC.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNvCC.setForeground(Color.WHITE);
        btnNvCC.setPreferredSize(new Dimension(190, 40));
        functionPanel.add(btnNvCC);

        JButton btnNvL = new JButton("Lương");
        btnNvL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new NhanVienLuong());
            }
        });
        buttonGroup_0.add(btnNvL);
        btnNvL.setHorizontalAlignment(SwingConstants.LEFT);
        btnNvL.setPreferredSize(new Dimension(190, 40));
        btnNvL.setForeground(Color.WHITE);
        btnNvL.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNvL.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnNvL);


        JButton btnCn = new JButton("Công Nhân");
        btnCn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisibleGroupButton(buttonGroup_1);
                clearViewPanel(viewPanel);
            }
        });
        btnCn.setHorizontalAlignment(SwingConstants.LEFT);
        btnCn.setBackground(new Color(51, 102, 204));
        btnCn.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCn.setForeground(Color.WHITE);
        btnCn.setPreferredSize(new Dimension(190, 40));
        functionPanel.add(btnCn);

        JButton btnCnCN = new JButton("Cập Nhật");
        btnCnCN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new CongNhanCapNhat());
            }
        });
        buttonGroup_1.add(btnCnCN);
        btnCnCN.setHorizontalAlignment(SwingConstants.LEFT);
        btnCnCN.setPreferredSize(new Dimension(190, 40));
        btnCnCN.setForeground(Color.WHITE);
        btnCnCN.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCnCN.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnCnCN);

        JButton btnCnPC = new JButton("Phân Công");
        btnCnPC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new CongNhanPhanCong());
            }
        });
        buttonGroup_1.add(btnCnPC);
        btnCnPC.setHorizontalAlignment(SwingConstants.LEFT);
        btnCnPC.setPreferredSize(new Dimension(190, 40));
        btnCnPC.setForeground(Color.WHITE);
        btnCnPC.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCnPC.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnCnPC);

        JButton btnCnCC = new JButton("Chấm Công");
        btnCnCC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new CongNhanChamCong());
            }
        });
        buttonGroup_1.add(btnCnCC);
        btnCnCC.setHorizontalAlignment(SwingConstants.LEFT);
        btnCnCC.setBackground(new Color(51, 153, 255));
        btnCnCC.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCnCC.setForeground(Color.WHITE);
        btnCnCC.setPreferredSize(new Dimension(190, 40));
        functionPanel.add(btnCnCC);

        JButton btnCnL = new JButton("Lương");
        btnCnL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new CongNhanLuong());
            }
        });
        buttonGroup_1.add(btnCnL);
        btnCnL.setHorizontalAlignment(SwingConstants.LEFT);
        btnCnL.setPreferredSize(new Dimension(190, 40));
        btnCnL.setForeground(Color.WHITE);
        btnCnL.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnCnL.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnCnL);

        JButton btnSP = new JButton("Sản Phẩm");
        btnSP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisibleGroupButton(buttonGroup_2);
                clearViewPanel(viewPanel);
            }
        });
        btnSP.setHorizontalAlignment(SwingConstants.LEFT);
        btnSP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSP.setPreferredSize(new Dimension(190, 40));
        btnSP.setForeground(Color.WHITE);
        btnSP.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSP.setBackground(new Color(51, 102, 204));
        functionPanel.add(btnSP);

        JButton btnSPCN = new JButton("Cập Nhật");
        btnSPCN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new SanPhamCapNhat());
            }
        });
        buttonGroup_2.add(btnSPCN);
        btnSPCN.setHorizontalAlignment(SwingConstants.LEFT);
        btnSPCN.setPreferredSize(new Dimension(190, 40));
        btnSPCN.setForeground(Color.WHITE);
        btnSPCN.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSPCN.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnSPCN);

        JButton btnSPCD = new JButton("Công Đoạn");
        btnSPCD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(viewPanel, new CongDoanSanPham());
            }
        });
        buttonGroup_2.add(btnSPCD);
        btnSPCD.setHorizontalAlignment(SwingConstants.LEFT);
        btnSPCD.setPreferredSize(new Dimension(190, 40));
        btnSPCD.setForeground(Color.WHITE);
        btnSPCD.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnSPCD.setBackground(new Color(51, 153, 255));
        functionPanel.add(btnSPCD);

        contentPane.setLayout(null);
        contentPane.add(functionPanel);
        contentPane.add(viewPanel);
        viewPanel.setLayout(new CardLayout(0, 0));

        imageLabel = new JLabel("");
        viewPanel.add(imageLabel, "name_99092075910500");
        init(viewPanel.getSize());
    }

    void init(Dimension size) {
        setVisibleGroupButton(buttonGroup_1);
        setVisibleGroupButton(buttonGroup_2);
        imageLabel.setSize(size);
        ImageIcon icon = new ImageIcon("res/Salary.jpg");
        icon.setImage(icon.getImage().getScaledInstance((int) size.getWidth(), (int) size.getHeight(), 0));
        imageLabel.setIcon(icon);
    }

    void setVisibleGroupButton(ButtonGroup btg) {
        if (btg == null) {
            return;
        }
        if (btg.getButtonCount() > 0) {
            AbstractButton abstractButton = null;
            int count = 0;
            Enumeration<AbstractButton> list = btg.getElements();
            while (btg.getElements().hasMoreElements()) {
                if (btg.getButtonCount() == count) {
                    break;
                }
                abstractButton = list.nextElement();
                if (abstractButton != null) {
                    abstractButton.setVisible(!abstractButton.isVisible());
                    count++;
                }
            }

        }
    }

    void clearViewPanel(JPanel viewPanel) {
        if (viewPanel.getComponentCount() > 0) {
            viewPanel.getComponent(0).setVisible(false);
            viewPanel.removeAll();
            viewPanel.add(imageLabel);
            viewPanel.validate();
        }
    }

    void changePanel(JPanel viewPanel, JPanel panel) {
        viewPanel.removeAll();
        viewPanel.add(panel);
        viewPanel.validate();
    }
}
