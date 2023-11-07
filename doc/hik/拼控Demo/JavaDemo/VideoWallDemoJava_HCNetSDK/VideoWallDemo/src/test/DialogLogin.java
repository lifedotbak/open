package test;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.xml.soap.Detail;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.jna.NativeLong;

import test.HCNetSDK.NET_DVR_DEVICEINFO_V40;
import test.HCNetSDK.NET_DVR_USER_LOGIN_INFO;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

@SuppressWarnings("serial")
public class DialogLogin extends JDialog
{
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private int m_iDeviceIndex ;	
	private JTextField textFieldIP;
	private JTextField textFieldPort;
	private JTextField textFieldUserName;
	private JPasswordField passwordFieldPwd;
    private DefaultTableModel modelDevicelist;
    private static Properties prop;
	
	
	public DialogLogin() 
	{
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 14));
		getContentPane().setBackground(new Color(0, 255, 153));
		m_iDeviceIndex = -1;
		InitComponent();
		setModal(true);
	}
	
	public void SetDeivceIndex(int i)
	{
		m_iDeviceIndex = i;
	}
	
	public void InitComponent()
	{
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		setTitle("Add Device");
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(127, 82, 54, 15);
		
		textFieldIP = new JTextField();
		textFieldIP.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldIP.setBounds(235, 79, 89, 21);
		textFieldIP.setText("10.19.82.178");
		textFieldIP.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Port:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(127, 134, 54, 15);
		
		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldPort.setBounds(235, 131, 89, 21);
		textFieldPort.setText("8000");
		textFieldPort.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("UserName:");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(127, 189, 68, 15);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("宋体", Font.PLAIN, 14));
		textFieldUserName.setBounds(235, 186, 88, 21);
		textFieldUserName.setText("admin");
		textFieldUserName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(127, 237, 68, 15);
		
		passwordFieldPwd = new JPasswordField();
		passwordFieldPwd.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordFieldPwd.setBounds(234, 234, 89, 21);
		passwordFieldPwd.setText("soft12345");
	    
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(60, 329, 78, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginActionPerformed(arg0);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_1.setBounds(274, 329, 78, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitActionPerformed(e);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblNewLabel);
		getContentPane().add(textFieldIP);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(textFieldPort);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(textFieldUserName);
		getContentPane().add(lblNewLabel_3);
		getContentPane().add(passwordFieldPwd);
		getContentPane().add(btnNewButton);
		getContentPane().add(btnNewButton_1);
	}

	/**
	 * add device
	 * @param event
	 */
	void LoginActionPerformed(ActionEvent event)
	{
		HCNetSDK.NET_DVR_DEVICEINFO_V30 strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		
		String sDeviceIP = textFieldIP.getText();
		if( textFieldPort.getText() == null || textFieldPort.getText().trim().length() == 0)
		{
			DialogMessage dlg = new DialogMessage("Port must be not null");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			
			return;
		}
		int iPort = Integer.parseInt(textFieldPort.getText());
		
		NET_DVR_USER_LOGIN_INFO struLoginInfo = new NET_DVR_USER_LOGIN_INFO();
		NET_DVR_DEVICEINFO_V40 struDeviceInfo = new NET_DVR_DEVICEINFO_V40();
		
		for(int i=0;i<sDeviceIP.length();i++)
		{
			struLoginInfo.sDeviceAddress[i]= (byte)sDeviceIP.charAt(i);
		}
		for(int i=0;i<passwordFieldPwd.getPassword().length;i++)
		{
			struLoginInfo.sPassword[i]=(byte) passwordFieldPwd.getPassword()[i];
		}
		for(int i=0;i<textFieldUserName.getText().length();i++)
		{
			struLoginInfo.sUserName[i]=(byte)textFieldUserName.getText().charAt(i);
		}		
		struLoginInfo.wPort = (short)iPort;		
		
		NativeLong nlUserID = hCNetSDK.NET_DVR_Login_V40(struLoginInfo, struDeviceInfo);
		long lUserID = nlUserID.longValue();		
		if (lUserID == -1)
		{
			sDeviceIP = "";
			int ierr = hCNetSDK.NET_DVR_GetLastError();
			String errInfo=String.format("err[%d]",ierr);
			JavaDemo.AddLogNew("FAIL", "NET_DVR_Login_V40", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("login error,error code :" + ierr);
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
		} 
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_Login_V40", sDeviceIP, errInfo);
			DeviceInfo deviceInfo=new DeviceInfo(sDeviceIP, (short)iPort, textFieldUserName.getText(),
					new String(passwordFieldPwd.getPassword()), struDeviceInfo.struDeviceV30, nlUserID);
			JavaDemo.WriteDeviceInfo(deviceInfo);
			int iRet = JavaDemo.TreeAddDevice(nlUserID,sDeviceIP,struDeviceInfo,deviceInfo);
			if(iRet == -1)
			{
				DialogMessage dlg = new DialogMessage("device already login" );
				dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
				dlg.setVisible(true);
			}
			this.dispose();
		
		}
	}
	
	/**
	 * quit
	 * @param event
	 */
	void ExitActionPerformed(ActionEvent event)
	{
		this.dispose();
	}
	/**
	 * Write device information
	 * @param 
	 * @return
	 */
	public void WriteDeviceInfo(DeviceInfo deviceInfo){	   
	        try{
	            //Save device information
	    		prop = new Properties(); 
	            FileOutputStream oFile = new FileOutputStream("device.properties", true);
	            prop.setProperty("IP", deviceInfo.GetIP());
	            prop.setProperty("Port", String.valueOf(deviceInfo.GetPort()));
	            prop.setProperty("UserName", deviceInfo.GetUserName());
	            prop.setProperty("PassWord", deviceInfo.GetPassword());
	            prop.store(oFile, "The New properties file");
	            oFile.close();          
	        }
	        catch(Exception e){
	            System.out.println(e);
	        }
	}
	
	/**
	 * Read device information
	 * 
	 */
	public static String ReadDeviceInfo(){
		String deviceIP = null;
		try {
            
            //Read device information
            InputStream in = new BufferedInputStream (new FileInputStream("device.properties"));
            prop.load(in);   
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                if(key=="IP")
				{
					deviceIP = prop.getProperty(key);
					System.out.println(key + ":" + prop.getProperty(key));
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(deviceIP);
		return deviceIP;
	}
	
}
