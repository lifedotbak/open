package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogDynamicDecode extends JDialog {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private static NativeLong m_lUserID;
	private final JPanel contentPanel = new JPanel();
	private JTextField dwWallNo;
	private JTextField dwWinNo;
	private JTextField dwSubWinNo;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDynamicDecode dialog = new DialogDynamicDecode();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDynamicDecode() {
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		setTitle("Dynamic Decode");
		setBounds(100, 100, 1013, 526);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		//panel.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dynamic Decode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Decode Channel Switch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblWallNo = new JLabel("Wall No");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblWinNo_1 = new JLabel("Win No");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblSubwinNo_1 = new JLabel("SubWin No");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		JButton btnGet = new JButton("Get");
		
		JButton btnSet = new JButton("Set");
		
		JLabel lblNewLabel_1 = new JLabel("Switch");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Open", "Close"}));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addComponent(btnGet, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(btnSet, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(26))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblWallNo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(lblWinNo_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
							.addComponent(lblSubwinNo_1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addGap(4)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(21)
							.addComponent(lblWallNo))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(20)
							.addComponent(lblWinNo_1))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(18)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSubwinNo_1))))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(btnGet)
						.addComponent(btnSet))
					.addGap(17))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Batch Dynamic Decode", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblWallNo_1 = new JLabel("Wall No");
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JLabel lblWinNo_2 = new JLabel("Win No");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblSubwinNo_2 = new JLabel("SubWin No");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		
		JButton btnClear = new JButton("Clear");
		
		JButton btnTakeStreamSetting_1 = new JButton("Take Stream Setting");
		
		JButton btnStartDecoding_1 = new JButton("Start Decoding");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(23)
							.addComponent(lblWallNo_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(24)
							.addComponent(btnTakeStreamSetting_1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(9)
							.addComponent(btnSave)
							.addGap(14)
							.addComponent(btnClear)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnStartDecoding_1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(6)
							.addComponent(lblWinNo_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(lblSubwinNo_2, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addGap(79))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(21)
							.addComponent(lblWallNo_1))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWinNo_2)
								.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSubwinNo_2))))
					.addGap(20)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnSave)
							.addComponent(btnStartDecoding_1)
							.addComponent(btnClear))
						.addComponent(btnTakeStreamSetting_1))
					.addGap(42))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Batch Decoder Switch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		{
			cancelButton = new JButton("Exit");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ExitActionPerformed(arg0);
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 481, Short.MAX_VALUE)))
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(cancelButton)
							.addGap(37))
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(7)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_8 = new JLabel("Wall No");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel label_9 = new JLabel("Win No");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		
		JLabel label_10 = new JLabel("SubWin No");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Wall No");
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Index No", "Window No", "Switch"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblWinNo_3 = new JLabel("Win No");
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		JLabel lblSubwinNo_3 = new JLabel("SubWin No");
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		
		JLabel lblSwitch = new JLabel("Switch");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Open", "Close"}));
		
		JButton btnDelete = new JButton("Delete");
		
		JButton btnGet_1 = new JButton("Get");
		
		JButton btnSet_1 = new JButton("Set");
		
		JButton btnModify = new JButton("Modify");
		
		JButton btnAdd = new JButton("Add");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(10)
									.addComponent(label_8)
									.addGap(4)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(16)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblSwitch, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(10)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel_3.createSequentialGroup()
													.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
													.addGap(32)
													.addComponent(lblWinNo_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
													.addGap(18))
												.addGroup(gl_panel_3.createSequentialGroup()
													.addComponent(btnModify)
													.addGap(30)))
											.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
												.addComponent(btnDelete)
												.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(15)
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)))
							.addGap(14)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_3.createSequentialGroup()
											.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
											.addGap(2)
											.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(31)
											.addComponent(label_10, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
											.addGap(2)
											.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_3.createSequentialGroup()
											.addGap(8)
											.addComponent(lblSubwinNo_3, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(15)
									.addComponent(btnGet_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnSet_1))))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(16)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(3)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblWinNo_3)
							.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_14, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblSubwinNo_3)))
					.addGap(31)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSwitch)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnDelete)
						.addComponent(btnModify)
						.addComponent(btnGet_1)
						.addComponent(btnSet_1))
					.addGap(129)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(3)
							.addComponent(label_8))
						.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(2)
							.addComponent(label_9))
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(3)
							.addComponent(label_10))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(1)
							.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
		);
		panel_3.setLayout(gl_panel_3);
		JLabel lblNewLabel = new JLabel("Wall No");
		dwWallNo = new JTextField();
		dwWallNo.setColumns(10);
		
		JLabel lblWinNo = new JLabel("Win No");
		
		dwWinNo = new JTextField();
		dwWinNo.setColumns(10);
		
		dwSubWinNo = new JTextField();
		dwSubWinNo.setColumns(10);
		
		JLabel lblSubwinNo = new JLabel("SubWin No");
		
		JButton btnTakeStreamSetting = new JButton("Take Stream Setting");
		btnTakeStreamSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpStreamCfg(arg0);
			}
		});
		
		JButton btnStartDecoding = new JButton("Start Decoding");
		btnStartDecoding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onBnStartDynamicDecode(arg0);
			}
		});
		
		JButton btnStopDecoding = new JButton("Stop Decoding");
		btnStopDecoding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onBnStopDynamicDecode();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(23)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dwWallNo, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(btnTakeStreamSetting)))
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnStartDecoding)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(btnStopDecoding))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblWinNo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dwWinNo, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
							.addComponent(lblSubwinNo, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dwSubWinNo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addGap(16))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(21)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWinNo)
								.addComponent(dwWinNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(dwSubWinNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSubwinNo)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(dwWallNo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnTakeStreamSetting)
							.addComponent(btnStartDecoding))
						.addComponent(btnStopDecoding))
					.addGap(18))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		this.setLocationRelativeTo(null);
	}
	/**
	 * Stop dynamic decoding
	 */
	private void onBnStopDynamicDecode() {
		// TODO Auto-generated method stub
		String ip = JavaDemo.GetTreeIp();
		if (ip == "") {
			return;
		}
		DeviceInfo deviceInfo = JavaDemo.m_hashDeviceInfo.get(ip);
		NativeLong lUserID = deviceInfo.GetNUserID();
		if (lUserID.intValue() == -1) {
			DialogMessage dlg = new DialogMessage("log first");
			dlg.setBounds(this.getX() + this.getWidth() / 3, this.getY() + this.getHeight() / 3, 370, 200);
			dlg.setVisible(true);
			return;
		}
		int dwChannelEx= 1 + ((0&0xff)<<16) + (1 << 24); 
	    System.out.println("Test"+dwChannelEx);
		VideoWallDataOp.StopDynamic(dwChannelEx,ip);
	}

/**
 * Start dynamic decoding
 * @param arg0
 */
private void onBnStartDynamicDecode(ActionEvent arg0) {
		// TODO Auto-generated method stub
	//DeviceInfo deviceInfo = JavaDemo.m_hashDeviceInfo.get(sIP);
	//NativeLong lUserID = deviceInfo.GetNUserID();
	String ip = JavaDemo.GetTreeIp();
	if (ip == "")
	{
		return;
	}
	DeviceInfo deviceInfo = JavaDemo.m_hashDeviceInfo.get(ip);
	NativeLong lUserID = deviceInfo.GetNUserID();
	if (lUserID.intValue() == -1)
	{
		DialogMessage dlg = new DialogMessage("log first");
		dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
		dlg.setVisible(true);
		return;
	}
	HWND hwnd = new HWND(Native.getComponentPointer(JavaDemo.panelRealPlay)); // get preview handle
	StartDynamic(ip,hwnd);
 }
/**
 * Turn on dynamic decoding
 */
private void StartDynamic(String sDeviceIP,HWND hWnd) {
	//HWND hWnd = new HWND(Native.getComponentPointer(JavaDemo.panelPlay));
	// TODO Auto-generated method stub
	/*HCNetSDK.NET_DVR_DEV_CHAN_INFO_EX struChanInfo = new HCNetSDK.NET_DVR_DEV_CHAN_INFO_EX();
	struChanInfo.write();
	
	HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX struStreamDev = new HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX();
	struStreamDev.write();
	
	
	HCNetSDK.NET_DVR_DEC_STREAM_MODE struStreamMode = new HCNetSDK.NET_DVR_DEC_STREAM_MODE();
	struStreamMode.setType(HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX.class);*/
	
	HCNetSDK.NET_DVR_PU_STREAM_CFG_V41 struStreamCfg = new HCNetSDK.NET_DVR_PU_STREAM_CFG_V41();
	struStreamCfg.dwSize=struStreamCfg.size();
	struStreamCfg.byStreamMode = 1;
	switch (struStreamCfg.byStreamMode)
    {
        case 1:		/*IP or Domain Take Stream*/
        	
        	HCNetSDK.NET_DVR_DEV_CHAN_INFO_EX struChanInfo = new HCNetSDK.NET_DVR_DEV_CHAN_INFO_EX();
        	
        	
        	byte [] byAddress = "10.41.14.160".getBytes();	
        	//byte [] byAddress = DialogUpStreamCfg.deviceIP.getBytes();
    		for(int i=0;i<byAddress.length;i++)
    		{
    			struChanInfo.byAddress[i] = byAddress[i];
    		}
    		    struChanInfo.wDVRPort = 8000;
    		byte [] byUserName = "admin".getBytes();
    		//byte [] byUserName = DialogUpStreamCfg.loginUserName.getBytes();
    		for(int i=0;i<byUserName.length;i++)
    		{
    			struChanInfo.sUserName[i]=byUserName[i];
    		}
    		
    		byte [] byPwd = "abcd12345".getBytes();
    		//byte [] byPwd = DialogUpStreamCfg.loginPwd.getBytes();
    		for(int i=0;i<byPwd.length;i++)
    		{
    			struChanInfo.sPassword[i]=byPwd[i];
    		}
    		struChanInfo.byTransProtocol = 0;
    		struChanInfo.byTransMode = 0;
    		struChanInfo.byResolution = 3;
    		struChanInfo.byDispChan = 1; 
    		struChanInfo.byFactoryType = 0; 
    		struChanInfo.byChanType = 3;    //Channel type 0 (normal) or 3 (local)
    		struChanInfo.dwChannel = 1;    //Specific channel number
             
    		struChanInfo.write();

        	HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX struStreamDev = new HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX();
        	struStreamDev.struDevChanInfo=struChanInfo;
        	struStreamDev.write();
        	
        	
        	HCNetSDK.NET_DVR_DEC_STREAM_MODE struStreamMode = new HCNetSDK.NET_DVR_DEC_STREAM_MODE();
        	struStreamMode.setType(HCNetSDK.NET_DVR_DEC_STREAM_DEV_EX.class);
        	struStreamMode.struDecStreamDev=struStreamDev;
        	struStreamMode.write();
        	
        	struStreamCfg.uDecStreamMode=struStreamMode;
        	struStreamCfg.write();
        	
       default:
        	break;
    }
      //int dwChannelEx= 1 + ((0&0xff)<<16) + (1 << 24); 
      //System.out.println("Test"+dwChannelEx);
     
		int dwChannelEx = Integer.parseInt(dwWinNo.getText()) + (Integer.parseInt(dwSubWinNo.getText()) << 16)
				+ (Integer.parseInt(dwWallNo.getText()) << 24);

		if (false == hCNetSDK.NET_DVR_MatrixStartDynamic_V41(m_lUserID, dwChannelEx, struStreamCfg.getPointer())) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "faild to Start DynamcDecode", null, errInfo);
			DialogMessage dlg = new DialogMessage("Dynamic decoding failed");
			dlg.setBounds(780, 500, 400, 200);
			dlg.setVisible(true);
			return;
		}

		else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "success to Start DynamicDecode", null, errInfo);
			DialogMessage dlg = new DialogMessage("Dynamic decoding succeeded");
			dlg.setBounds(780, 500, 400, 200);
			dlg.setVisible(true);
	}
      //VideoWallDataOp.StartDynamic(struStreamCfg, dwChannelEx,sDeviceIP, hWnd); 
      System.out.println("Test"+dwChannelEx);
      preview(sDeviceIP);

}

/**
 * 预览
 */
	private void preview(String ip) {
		// TODO Auto-generated method stub
		int iChannelNum = 1;
		System.out.println(iChannelNum);
		HCNetSDK.NET_DVR_PREVIEWINFO strPreviewInfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
		strPreviewInfo.lChannel = new NativeLong(iChannelNum);

		boolean bPlay = false;
		for (int j = 0; j < 4; j++) {
			if (JavaDemo.arrShow[j].GetFocus()) {
				bPlay = true;
				JavaDemo.arrShow[j].StartPlay(m_lUserID, strPreviewInfo);
			}
		}
		if (bPlay == false) {
			JavaDemo.arrShow[0].StartPlay(m_lUserID, strPreviewInfo);
		}

	}

/**
 * up Stream
 * @param arg0
 */
private void UpStreamCfg(ActionEvent arg0) {
		// TODO Auto-generated method stub
	    DialogUpStreamCfg dialogUpStreamCfg=new DialogUpStreamCfg();
	    dialogUpStreamCfg.setVisible(true);
	}

/**
 * quit
 * @param arg0
 */
	private void ExitActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		DialogDynamicDecode.this.dispose();
		
	}
}
