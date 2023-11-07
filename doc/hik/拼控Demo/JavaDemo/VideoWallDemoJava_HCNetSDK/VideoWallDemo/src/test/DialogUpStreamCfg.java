package test;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DialogUpStreamCfg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	public static  JTextField chDeviceIP;
	private JTextField textField_3;
	public static JTextField lDevicePort;
	public static JTextField chLoginUserName;
	public static JTextField chLoginPwd;
	public static String deviceIP;
	public static String devicePort;
	public static String loginUserName;
	public static String loginPwd;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogUpStreamCfg dialog = new DialogUpStreamCfg();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogUpStreamCfg() {
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		setTitle("Take Stream Information");
		setBounds(100, 100, 1289, 624);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblTakeStreamMode = new JLabel("Take Stream Mode");
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "IP or Domain Name Take Stream", "URL Take Stream", "Dynamic DNS Take Stream"}));
		JLabel lblDecodingDelayTime = new JLabel("Decoding Delay Time");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblNewLabel = new JLabel("ms");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Code Stream Encryption Switch");
		
		JLabel lblCodeStreamEncryption = new JLabel("Code Stream Encryption Paaword");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "IP or Domain Name Take Stream", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "URL Take Stream", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dynamic DNS Take Stream", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		{
			buttonPane = new JPanel();
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						UpStreamCfg();
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Exit");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						DialogUpStreamCfg.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(38)
							.addComponent(lblTakeStreamMode)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addComponent(lblDecodingDelayTime)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addGap(64)
							.addComponent(chckbxNewCheckBox)
							.addGap(70)
							.addComponent(lblCodeStreamEncryption)
							.addGap(10)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1077, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 1077, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 1077, Short.MAX_VALUE))
						.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTakeStreamMode)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDecodingDelayTime)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(chckbxNewCheckBox))
							.addGap(18))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCodeStreamEncryption)
							.addGap(22))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(19)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(54))
		);
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(862)
					.addComponent(okButton)
					.addGap(64)
					.addComponent(cancelButton)
					.addGap(115))
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cancelButton)
						.addComponent(okButton)))
		);
		buttonPane.setLayout(gl_buttonPane);
		
		JLabel lblDomainName = new JLabel("Domain Name");
		
		JLabel lblDdns = new JLabel("DDNS Server Port ");
		
		JLabel lblRemoteHostPaaword = new JLabel("Remote Host Paaword");
		
		JLabel lblManufacturerType = new JLabel("Manufacturer Type");
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		
		JLabel lblRemoteHostPort = new JLabel("Remote Host Port Transmission");
		
		JLabel lblNewLabel_6 = new JLabel("Stream Protocol");
		
		JLabel lblNewLabel_7 = new JLabel("Channel Type");
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Use Stream Server");
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		
		JComboBox comboBox_8 = new JComboBox();
		comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"", "TCP", "UDP", "MCAST"}));
		
		JComboBox comboBox_9 = new JComboBox();
		comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"", "Ordinary channel", "Zero channel ", " Stream ID"}));
		
		JLabel lblDomainNameServer = new JLabel("Domain Name Server Type");
		
		JLabel lblTransmissionStreamMode = new JLabel("Transmission Stream Mode");
		
		JLabel lblRemoteHostChannel = new JLabel("Remote Host Channel No");
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"", "IPServer", "Dyndns", "PeanutHull", "NO-IP", "hiDDNS"}));
		
		JLabel lblDdns_1 = new JLabel("DDNS Server Address");
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"Main stream", "主码流"}));
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		
		JLabel lblRemoteHostUser_1 = new JLabel("Remote Host User Name");
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		
		JLabel lblid_1 = new JLabel("Stream ID");
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("IP");
		
		textField_20 = new JTextField();
		textField_20.setColumns(10);
		
		JLabel lblPort_1 = new JLabel("Port");
		
		textField_21 = new JTextField();
		textField_21.setColumns(10);
		
		JLabel lblProtocol_1 = new JLabel("Protocol");
		
		JComboBox comboBox_12 = new JComboBox();
		comboBox_12.setModel(new DefaultComboBoxModel(new String[] {"", "TCP", "UDP"}));
		
		JComboBox comboBox_13 = new JComboBox();
		comboBox_13.setModel(new DefaultComboBoxModel(new String[] {"", "HIKVISION", "DAHUA", "SUNELL", "YAAN", "LAUNCH"}));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(29)
							.addComponent(lblDomainName))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDdns)
								.addComponent(lblRemoteHostPaaword)
								.addComponent(lblManufacturerType))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField_13)
								.addComponent(textField_12)
								.addComponent(textField_11)
								.addComponent(comboBox_13, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(35)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblRemoteHostPort)
							.addGap(18)
							.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_6)
							.addGap(18)
							.addComponent(comboBox_8, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblNewLabel_7)
							.addGap(41)
							.addComponent(comboBox_9, 0, 67, Short.MAX_VALUE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(chckbxNewCheckBox_4)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_8)
							.addGap(10)))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addComponent(lblRemoteHostChannel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField_17))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDomainNameServer)
										.addComponent(lblTransmissionStreamMode))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBox_11, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_10, 0, 84, Short.MAX_VALUE)))))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPort_1)
							.addGap(18)
							.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addGap(26)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRemoteHostUser_1)
								.addComponent(lblDdns_1))
							.addGap(14)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(lblid_1)
								.addGap(18)
								.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGap(54)
								.addComponent(lblProtocol_1)
								.addGap(18)
								.addComponent(comboBox_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDomainName)
								.addComponent(textField_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRemoteHostPort)
								.addComponent(textField_15, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDomainNameServer)
								.addComponent(comboBox_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDdns_1)
								.addComponent(textField_16, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(22)
									.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblDdns)
										.addComponent(textField_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_6)))
								.addGroup(gl_panel_2.createSequentialGroup()
									.addGap(20)
									.addComponent(lblRemoteHostUser_1)))
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoteHostPaaword)
								.addComponent(textField_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_7)
								.addComponent(comboBox_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRemoteHostChannel)
								.addComponent(textField_17, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblid_1)
								.addComponent(textField_19, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTransmissionStreamMode)
								.addComponent(comboBox_11, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(17)
							.addComponent(textField_18, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblManufacturerType)
							.addComponent(chckbxNewCheckBox_4)
							.addComponent(textField_20, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_8)
							.addComponent(lblProtocol_1)
							.addComponent(comboBox_12, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_21, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPort_1)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("URL");
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel_5)
					.addGap(18)
					.addComponent(textField_10, GroupLayout.DEFAULT_SIZE, 1001, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(textField_10, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Remote Host IP or Domain Name");
		
		chDeviceIP = new JTextField();
		chDeviceIP.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Channel No(Input)");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Remote Host Port");
		
		lDevicePort = new JTextField();
		lDevicePort.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Manufacturer Type");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "HIKVISION", "DAHUA", "SUNELL", "YAAN", "LAUNCH", ""}));
		
		JLabel lblRemoteHostUser = new JLabel("Remote Host User Name");
		
		chLoginUserName = new JTextField();
		chLoginUserName.setColumns(10);
		
		JLabel lblRemoteHostPassword = new JLabel("Remote Host Password");
		
		chLoginPwd = new JTextField();
		chLoginPwd.setColumns(10);
		
		JLabel lblTransmissionStreamProtocol = new JLabel("Transmission Stream Protocol");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"", "TCP", "UDP", "MCAST", "RTP"}));
		
		JLabel lblTransmission = new JLabel("Transmission Stream Mode");
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Main stream", "Secondary stream"}));
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Use Streaming Server");
		
		JLabel lblIp = new JLabel("IP");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		
		JLabel lblProtocol = new JLabel("Protocol");
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"", "TCP", "UDP"}));
		
		JLabel lblChannelType = new JLabel("Channel Type");
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"", "Ordinary channel", "Zero channel", "Stream ID", "Local input source", "Virtual screen service", "Stitching channel"}));
		
		JLabel lblid = new JLabel("Stream ID");
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		
		JLabel lblDeviceType = new JLabel("Device Type");
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"", "Decoder", "Encoder", "X86 HD input"}));
		
		JLabel lblResolution = new JLabel("Resolution");
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"", "CIF", "4CIF", "720P", "1080P", "500W"}));
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Display Channel No");
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Display Sub Channel No");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblRemoteHostUser))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(chLoginUserName)
										.addComponent(chDeviceIP, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2)
										.addComponent(lblRemoteHostPassword))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(chLoginPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(34)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lDevicePort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblTransmissionStreamProtocol)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox_2, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(chckbxNewCheckBox_1)
									.addGap(18)
									.addComponent(lblIp)
									.addGap(11)
									.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPort)
									.addGap(5)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
											.addComponent(lblProtocol)
											.addGap(12))
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblResolution)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(comboBox_7, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
											.addComponent(lblChannelType))
										.addComponent(chckbxNewCheckBox_2)))))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(130)
							.addComponent(lblDeviceType)
							.addGap(18)
							.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(53)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_4)
										.addComponent(lblTransmission))
									.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(comboBox_3, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(comboBox_1, 0, 95, Short.MAX_VALUE)))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(26)
									.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
									.addComponent(lblid)
									.addGap(18)
									.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(chckbxNewCheckBox_3)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(chDeviceIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(lDevicePort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemoteHostUser)
						.addComponent(chLoginUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRemoteHostPassword)
						.addComponent(chLoginPwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTransmissionStreamProtocol)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTransmission)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxNewCheckBox_1)
								.addComponent(lblIp)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(12)
							.addComponent(lblPort))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblChannelType)
								.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProtocol)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblid))))
					.addGap(14)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeviceType)
						.addComponent(comboBox_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblResolution)
						.addComponent(comboBox_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxNewCheckBox_2)
						.addComponent(chckbxNewCheckBox_3))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
		this.setLocationRelativeTo(null);
	}
	/**
	 * Stream setting
	 */
	public void UpStreamCfg() {
		// TODO Auto-generated method stub
		deviceIP=chDeviceIP.getText();
		devicePort=lDevicePort.getText();
		loginUserName=chLoginUserName.getText();
		loginPwd=chLoginPwd.getText();
	}
}
