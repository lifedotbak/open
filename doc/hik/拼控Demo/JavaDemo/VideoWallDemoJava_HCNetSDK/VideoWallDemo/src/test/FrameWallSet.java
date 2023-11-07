package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import com.sun.jna.NativeLong;

import test.HCNetSDK.NET_DVR_USER_LOGIN_INFO;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameWallSet extends JFrame {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private static NativeLong m_lUserID;
	private JPanel contentPane;
	private JTextField textFieldWallName;
	private JTextField textFieldWallWidth;
	private JTextField textFieldWallHeight;
	private JTextField textField_3;
	private JTextField textField_4;
	private int iWallRow;
	private int iWallColum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWallSet frame = new FrameWallSet(m_lUserID);
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
	public FrameWallSet(NativeLong lUserID) {
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		m_lUserID = lUserID;
		setResizable(false);
		setTitle("Wall Configuration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLACK);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Wall Association", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnWallSure = new JButton("Ok");
		
		JButton btnWallQuit = new JButton("Exit");
		btnWallQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(281, Short.MAX_VALUE)
					.addComponent(btnWallSure, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnWallQuit, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(41))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnWallQuit)
						.addComponent(btnWallSure))
					.addGap(20))
		);
		
		JLabel lblNewLabel_7 = new JLabel("Device Wall");
		
		JLabel lblNewLabel_8 = new JLabel("Physical Wall");
		
		JButton btnNewButton_1 = new JButton("Get");
		
		JButton btnSet = new JButton("Set");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_7)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblNewLabel_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addGap(49))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_7)
								.addComponent(lblNewLabel_8)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addComponent(btnNewButton_1)
							.addGap(18)
							.addComponent(btnSet)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Wall No");
		
		JLabel lblNewLabel_1 = new JLabel("Wall Name");
		
		JLabel lblNewLabel_2 = new JLabel("Width");
		
		JLabel lblNewLabel_3 = new JLabel("Height");
		
		textFieldWallName = new JTextField();
		textFieldWallName.setColumns(10);
		
		textFieldWallWidth = new JTextField();
		textFieldWallWidth.setColumns(10);
		
		textFieldWallHeight = new JTextField();
		textFieldWallHeight.setColumns(10);
		
		JLabel lblgrid = new JLabel("(grid)");
		
		JLabel lblgrid_1 = new JLabel("(grid)");
		
		JLabel lblNewLabel_4 = new JLabel("Remarks:");
		
		JLabel lblNewLabel_5 = new JLabel("    BaseLine coordinates ,");
		
		JLabel lblNewLabel_6 = new JLabel("all devices are unified to");
		
		JButton btnGetWall = new JButton("Get");
		
		JButton btnSetWall = new JButton("Set");
		btnSetWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SetWall(arg0);
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}));
		
		JRadioButton rdbtnWallUse = new JRadioButton("Use");
		
		JLabel lblTo = new JLabel(" 1920*1920");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addGap(38)
									.addComponent(rdbtnWallUse))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_3))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldWallName, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldWallHeight, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
												.addComponent(textFieldWallWidth, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
											.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
												.addGroup(gl_panel.createSequentialGroup()
													.addGap(18)
													.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel.createSequentialGroup()
															.addComponent(lblgrid)
															.addGap(18)
															.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_4)
																.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
																.addComponent(lblNewLabel_5)))
														.addComponent(lblgrid_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
												.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addComponent(lblTo, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
													.addGap(25)))))))
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnGetWall, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSetWall, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(45))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(rdbtnWallUse))
					.addGap(39)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(textFieldWallName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textFieldWallWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblgrid))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(textFieldWallHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblgrid_1)
									.addGap(2)
									.addComponent(lblTo))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(14)
							.addComponent(lblNewLabel_6)))
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGetWall)
						.addComponent(btnSetWall))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
/**
 * Set Wall
 * @param arg0
 */
	private void SetWall(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		HCNetSDK.NET_DVR_VIDEOWALLDISPLAYMODE struDisplayMode = new HCNetSDK.NET_DVR_VIDEOWALLDISPLAYMODE();
		struDisplayMode.dwSize = struDisplayMode.size();
		struDisplayMode.byEnable = 0;
		 if (textFieldWallName.getText().length() == 0)
		    {
		        struDisplayMode.sName[0] = 0; 
		    }
		 else
		 {
			 byte[] byBuffer = new byte[HCNetSDK.NAME_LEN];
			 String strInput=textFieldWallName.getText();
			 byBuffer= strInput.getBytes();
			 struDisplayMode.sName=byBuffer;
		 }
		 iWallRow=Integer.parseInt(textFieldWallWidth.getText());
		 iWallColum=Integer.parseInt(textFieldWallHeight.getText());
		 struDisplayMode.struRect.dwWidth = iWallRow * HCNetSDK.VW_BASE_RESOLUTION_FIRST; 
		 struDisplayMode.struRect.dwHeight = iWallColum * HCNetSDK.VW_BASE_RESOLUTION_SECOND; 
		 JavaDemo.InitVideoWall(1,iWallRow,iWallColum);
	}
}
