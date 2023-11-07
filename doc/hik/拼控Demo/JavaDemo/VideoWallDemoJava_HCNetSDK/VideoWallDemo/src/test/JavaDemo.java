package test;

import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.IntByReference;

import test.HCNetSDK.NET_DVR_DEVICEINFO_V40;
import test.HCNetSDK.NET_DVR_USER_LOGIN_INFO;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import java.awt.Font;
import java.awt.GridLayout;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.sun.org.apache.xpath.internal.axes.OneStepIterator;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JavaDemo extends JFrame
{
	protected final Logger log = Logger.getLogger(this.getClass().getName());

	/**
	 * 
	 */
	static String[] DisplayLinkMode = {"NONE", "BNC", "VGA", "HDMI", "DVI", "SDI", "FIBER", "RGB", "YPrPb", "VGA/HDMI/DVI", "3GSDI", "VGA/DVI", "HDTVI", "HDBaseT", "DP", "DVIT"}; 
	private static final long serialVersionUID = 1L;
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	static int m_iTreeNodeNum;// tree node num
	static DefaultMutableTreeNode m_DeviceRoot;
	static DefaultMutableTreeNode m_DeviceRoot1;
	static HashMap<String, DeviceInfo> m_hashDeviceInfo; // login info
	private boolean m_bPlay; // isPlaying
	private static int m_iChannelNum;// channel num
	private static int m_iDsiplayChannelNum;
	private static NativeLong m_lLogID; // login ID
	static Show[][] arrShowWall; // multiple windows play
	static Show[] arrShow; // multiple windows play
    static int[] disPlayWinNum;
	static NativeLong m_lPreviewHandle;// preview handle
    public static int isTree;
	private JPanel contentPane;
	public static Panel panelRealPlay = new Panel();
	private JPanel panelDemo;
	static Panel panelPlay;
	static JTree controlTreeDevice;
	private static JTable tableAlarm;
	private JScrollPane scrollPaneControll;
	static JComboBox comboBoxWall;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JPanel panelTime;
	private JPanel panelVersion;
	private JLabel demoVersionJL;
	private JLabel SDKVersionJL;
	private JLabel demoTime;
	private JScrollPane scrollPane;
	private JMenu mnDecodingAssociation;
	private JMenuItem mntmDynamicDecoding;
	private static JTree signalTreeDevice;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmDelAllWindsows;
	private JMenuItem mntmWindowDecodingInformation;
	private JMenuItem mntmDetail;
    private static Properties prop;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					hCNetSDK.NET_DVR_Init();
					boolean flag =hCNetSDK.NET_DVR_SetLogToFile(3, "G:\\JAVALog", true);
					JavaDemo frame = new JavaDemo();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JavaDemo()
	{
		setResizable(false);
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		prop = new Properties(); 
		log.info("java.home:" + System.getProperty("java.home"));
		initComponents();
		m_hashDeviceInfo = new HashMap<String, DeviceInfo>();
		m_lPreviewHandle = new NativeLong(-1);
		m_bPlay = false;
		arrShow = new Show[4];
		arrShow[0] = new Show();
		arrShow[1] = new Show();
		arrShow[2] = new Show();
		arrShow[3] = new Show();
		m_iChannelNum = -1;

	}

	// Init Component
	private void initComponents()
	{
		setTitle("VideoWall_demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 883);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		panelRealPlay.setBackground(Color.WHITE);
		panelRealPlay.setForeground(Color.GRAY);
		panelRealPlay.setBounds(176, 116, 784, 588);
		contentPane.setLayout(null);
		contentPane.add(panelRealPlay);
		panelRealPlay.setLayout(null);

		panelPlay = new Panel();
		panelPlay.setBackground(new Color(0, 255, 255));
		panelPlay.setBounds(0, 0, 784, 588);

		panelRealPlay.add(panelPlay);
		GroupLayout gl_panelPlay = new GroupLayout(panelPlay);
		gl_panelPlay.setHorizontalGroup(
			gl_panelPlay.createParallelGroup(Alignment.LEADING)
				.addGap(0, 784, Short.MAX_VALUE)
		);
		gl_panelPlay.setVerticalGroup(
			gl_panelPlay.createParallelGroup(Alignment.LEADING)
				.addGap(0, 588, Short.MAX_VALUE)
		);
		panelPlay.setLayout(gl_panelPlay);

		panelDemo = new JPanel();
		panelDemo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDemo.setBounds(172, 34, 660, 78);
		contentPane.add(panelDemo);
		panelDemo.setLayout(null);
		
		JLabel lblWallNo = new JLabel("Wall No");
		lblWallNo.setBounds(10, 35, 43, 15);
		panelDemo.add(lblWallNo);
		
		JButton WallSetBtn = new JButton("Wall Setting");
		//wall set
		WallSetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				        VideoWallSet(arg0);
				}			

		});
		WallSetBtn.setBounds(141, 30, 105, 23);
		panelDemo.add(WallSetBtn);
		
		comboBoxWall = new JComboBox();
		comboBoxWall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ComboxSelectChangeActionPerformed(arg0);
			}
		});
		comboBoxWall.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		comboBoxWall.setBounds(71, 31, 50, 21);
		panelDemo.add(comboBoxWall);
		
		btnNewButton = new JButton("Send Modify");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ModifyWall();
			}
		});
		btnNewButton.setBounds(268, 30, 105, 23);
		panelDemo.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Refresh");
		btnNewButton_2.setBounds(398, 31, 93, 23);
		panelDemo.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Detail Setting");
		btnNewButton_3.setBounds(516, 31, 117, 23);
		panelDemo.add(btnNewButton_3);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		menuBar.setBounds(172, 0, 660, 32);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Window Operate");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Refresh");
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Send Modify");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_2 = new JMenuItem("Quickly Open Window");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OnButQuickOpenRove();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		mntmDelAllWindsows = new JMenuItem("Del All Windsows");
		mntmDelAllWindsows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OnButCloseallwin();
			}
		});
		mnNewMenu_1.add(mntmDelAllWindsows);
		
		mntmWindowDecodingInformation = new JMenuItem("Window Decoding Information");
		mnNewMenu_1.add(mntmWindowDecodingInformation);
		
		mntmDetail = new JMenuItem("Detail");
		mnNewMenu_1.add(mntmDetail);
		
		JMenu mnNewMenu_3 = new JMenu("Ordinary Configure");
		menuBar.add(mnNewMenu_3);
		
		mnDecodingAssociation = new JMenu("Decoding Association");
		mnNewMenu_3.add(mnDecodingAssociation);
		
		mntmDynamicDecoding = new JMenuItem("Dynamic Decoding");
		mntmDynamicDecoding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogDynamicDecode dynamicDecode=new DialogDynamicDecode();
				dynamicDecode.setVisible(true);
			}
		});
		mnDecodingAssociation.add(mntmDynamicDecoding);
		
		JMenuItem mntmWindowDecodingState = new JMenuItem("Window Decoding State");
		mntmWindowDecodingState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogWinStatusList winStatusList=new DialogWinStatusList();
				winStatusList.setVisible(true);
				DialogWinStatusList.GetAllWinStatus();
			}
		});
		mnDecodingAssociation.add(mntmWindowDecodingState);

		JScrollPane scrollPaneAlarmList = new JScrollPane();
		scrollPaneAlarmList.setBounds(176, 710, 788, 124);
		contentPane.add(scrollPaneAlarmList);
		scrollPaneAlarmList.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));
		tableAlarm = new JTable();
		tableAlarm.setModel(this.initialTableModel());
		scrollPaneAlarmList.setViewportView(tableAlarm);

		scrollPaneControll = new JScrollPane();
		scrollPaneControll.setBounds(10, 116, 164, 350);
		contentPane.add(scrollPaneControll);
		scrollPaneControll.setBorder(new LineBorder(new java.awt.Color(127,157,185),1,false));

		controlTreeDevice = new JTree();
		scrollPaneControll.setViewportView(controlTreeDevice);
		controlTreeDevice.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				RightClickMouse(arg0);
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				DoubleClickMouse(e);
			}
		});
		controlTreeDevice.setFont(new Font("瀹嬩綋", Font.BOLD, 12));
		controlTreeDevice.setBackground(Color.WHITE);
		controlTreeDevice.setModel(initialTreeModel());
		controlTreeDevice.setFocusable(false);

		panelVersion = new JPanel();
		panelVersion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Version Information", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelVersion.setBounds(10, 0, 150, 85);
		contentPane.add(panelVersion);
		
		demoVersionJL = new JLabel("Demo V1.0");
		
		SDKVersionJL = new JLabel("SDK Version");
		int dwVersion=hCNetSDK.NET_DVR_GetSDKBuildVersion();
		String strVersion=String.format("HCNetSDK V%d.%d.%d.%d", (0xff000000 & dwVersion)>>24, (0x00ff0000 & dwVersion)>>16,(0x0000ff00 & dwVersion)>>8, (0x000000ff & dwVersion));
		SDKVersionJL.setText(strVersion);
		GroupLayout gl_panelVersion = new GroupLayout(panelVersion);
		gl_panelVersion.setHorizontalGroup(
			gl_panelVersion.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelVersion.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelVersion.createParallelGroup(Alignment.TRAILING)
						.addComponent(demoVersionJL, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(SDKVersionJL, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		gl_panelVersion.setVerticalGroup(
			gl_panelVersion.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVersion.createSequentialGroup()
					.addContainerGap()
					.addComponent(demoVersionJL)
					.addGap(13)
					.addComponent(SDKVersionJL)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelVersion.setLayout(gl_panelVersion);

		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 498, 160, 336);
		contentPane.add(scrollPane);
		
		signalTreeDevice = new JTree();
		signalTreeDevice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				RightClickMouse1(arg0);
			}
		});
		signalTreeDevice.setModel(initialTreeModel1());
		scrollPane.setViewportView(signalTreeDevice);
		JLabel lblNewLabel_1 = new JLabel("Signal");
		lblNewLabel_1.setIcon(new ImageIcon("G:\\项目办公\\项目DEMO开发\\VideoWallDemo\\res\\camera.png"));
		lblNewLabel_1.setBounds(44, 476, 99, 15);
		contentPane.add(lblNewLabel_1);
		
		panelTime = new JPanel();
		panelTime.setBounds(849, 21, 132, 53);
		contentPane.add(panelTime);
		panelTime.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Time", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		demoTime = new JLabel("Time");
		panelTime.add(demoTime);
		demoTime.setText(dateFormat.format(date));
		
				JButton btnNewButton_1 = new JButton("Exit");
				btnNewButton_1.setBounds(879, 75, 75, 28);
				contentPane.add(btnNewButton_1);
				
				JLabel lblNewLabel = new JLabel("Control");
				lblNewLabel.setIcon(new ImageIcon("G:\\项目办公\\项目DEMO开发\\VideoWallDemo\\res\\camera.png"));
				lblNewLabel.setBounds(44, 95, 99, 15);
				contentPane.add(lblNewLabel);
				btnNewButton_1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						ExitActionPerformed(arg0);
					}
				});
		this.setLocationRelativeTo(null);
		
	}

	/**
	 * 删除所有窗口
	 */
	private void OnButCloseallwin() {
		// TODO Auto-generated method stub
		String ip = getDeviceIP(); // get ip from device tree
		if (ip.length() == 0 || ip == "Control Device Tree") // not select ip or select "devicetree" root
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please select channel");
			DialogMessage dlg = new DialogMessage("Please choose display output channel");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong lUserID = m_hashDeviceInfo.get(ip).GetNUserID(); // get loginID

		if (lUserID.longValue() == -1)
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please login");
			DialogMessage dlg = new DialogMessage("Please login");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		VideoWallDataOp.GetAllWinPos(ip);
		VideoWallDataOp.WinCloseAll(ip);
	}

	//电视墙开窗
	private void OnButQuickOpenRove() {
		// TODO Auto-generated method stub
		String ip = getDeviceIP(); // get ip from device tree
		if (ip.length() == 0 || ip == "Control Device Tree") // not select ip or select "devicetree" root
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please select channel");
			DialogMessage dlg = new DialogMessage("Please choose display output channel");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong lUserID = m_hashDeviceInfo.get(ip).GetNUserID(); // get loginID

		if (lUserID.longValue() == -1)
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please login");
			DialogMessage dlg = new DialogMessage("Please login");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		VideoWallDataOp.SetWinPos(ip);
	}

	private void ModifyWall() {
		// TODO Auto-generated method stub
		String ip = getDeviceIP(); // get ip from device tree
		if (ip.length() == 0 || ip == "Control Device Tree") // not select ip or select "devicetree" root
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please select channel");
			DialogMessage dlg = new DialogMessage("Please select the display output channel to be bound");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 450, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong lUserID = m_hashDeviceInfo.get(ip).GetNUserID(); // get loginID

		if (lUserID.longValue() == -1)
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please login");
			DialogMessage dlg = new DialogMessage("Please login");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}

		disPlayWinNum=new int[4];
		disPlayWinNum[0] = 16777233;
		disPlayWinNum[1] = 16777234;
		disPlayWinNum[2] = 16777235;
		disPlayWinNum[3] = 16777236;
		
		
/*		TreePath[] selectionPath =controlTreeDevice.getSelectionPaths();
		
		for (int i = 0; i < disPlayWinNum.length; i++)
		{
			String sChannelName = ((DefaultMutableTreeNode) selectionPath[i].getLastPathComponent()).toString();
			disPlayWinNum[i] = Integer.parseInt(sChannelName.substring(0,8));
			
		}*/
		
		
		
	/*	for (int i = 0; i < disPlayWinNum.length; i++)
		{
			{
				DialogMessage dlg = new DialogMessage("请选择第"+(i+1)+"个显示输出口");
				dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
				dlg.setVisible(true);
				TreePath tp = controlTreeDevice.getSelectionPath();
				if (tp != null)
				{
					String sChannelName = ((DefaultMutableTreeNode) tp.getLastPathComponent()).toString();
					disPlayWinNum[i] = Integer.parseInt(sChannelName.substring(0,8));
					
				}
				else
				{
					return;
				}
			}
		}*/
		/*int iDisplayChannelNum = getChannelNumber();
		if (iDisplayChannelNum == -1)
		{
			DialogMessage dlg = new DialogMessage("Please select channel");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		m_iDsiplayChannelNum=iDisplayChannelNum;
		HWND hwnd = new HWND(Native.getComponentPointer(panelPlay)); // get sub_window
*/		
		VideoWallDataOp.GetDisplayPos(ip);
		VideoWallDataOp.SetDisplayPos(ip,disPlayWinNum);

	}

	private void ComboxSelectChangeActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/*boolean temp=true;
		if (comboBoxWall.getSelectedIndex() == 0)
		{
			if(temp)
			{
				InitVideoWall( 1,2, 2);
			}
			else
			{
				OnPaint(3,2);
				InitVideoWall( 1,2, 2);
			}

		}
		if (comboBoxWall.getSelectedIndex() == 1)
		{
			OnPaint(2,2);
			InitVideoWall( 2,1, 2);
			temp=false;
		}*/
		if (comboBoxWall.getSelectedIndex() == 1)
		{
			panelRealPlay.remove(panelPlay);
			int iwidth = panelRealPlay.getWidth();
			int iHeight = panelRealPlay.getHeight();
			iwidth /= 2;
			iHeight /= 2;

			arrShow[0].setBounds(-8, -6, iwidth + 4, iHeight + 4);
			panelRealPlay.add(arrShow[0]);

			arrShow[1].setBounds(iwidth - 10, -6, iwidth + 8, iHeight + 4);
			panelRealPlay.add(arrShow[1]);
			arrShow[2].setBounds(-8, iHeight - 6, iwidth + 4, iHeight + 4);
			panelRealPlay.add(arrShow[2]);
			arrShow[3].setBounds(iwidth - 10, iHeight - 6, iwidth + 8, iHeight + 4);
			panelRealPlay.add(arrShow[3]);

		}
		else
		{
			panelRealPlay.remove(arrShow[0]);
			panelRealPlay.remove(arrShow[1]);
			panelRealPlay.remove(arrShow[2]);
			panelRealPlay.remove(arrShow[3]);
			int iwidth = panelRealPlay.getWidth();
			int iHeight = panelRealPlay.getHeight();
			panelPlay.setBounds(0, 0, iwidth, iHeight);
			panelRealPlay.add(panelPlay);
		}
		
		
	}

	/**
	 * 墙设置
	 * @param arg0
	 */
	private void VideoWallSet(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String ip = GetTreeIp();
		if (ip == "")
		{
			return;
		}
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		NativeLong lUserID = deviceInfo.GetNUserID();
		if (lUserID.intValue() == -1)
		{
			DialogMessage dlg = new DialogMessage("Please login");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 400, 200);
			dlg.setVisible(true);
			return;
		}
		FrameWallSet wallSetFrm=new FrameWallSet(lUserID);
		wallSetFrm.setVisible(true);
	}

	/**
	 * 
	 * @Title ExitActionPerformed
	 * @Description
	 * @param arg0
	 */
	private void ExitActionPerformed(ActionEvent arg0)
	{
		
		@SuppressWarnings("rawtypes")
		Iterator iterator = m_hashDeviceInfo.keySet().iterator(); // exit all device
		while (iterator.hasNext())
		{
			NativeLong lUserID = m_hashDeviceInfo.get(iterator.next()).GetNUserID();
			hCNetSDK.NET_DVR_Logout(lUserID);
		}

		// cleanup SDK
		hCNetSDK.NET_DVR_Cleanup();
		JavaDemo.this.dispose();
	}

	//start play
	private void StartPlay(NativeLong lUserId, HCNetSDK.NET_DVR_PREVIEWINFO struPreviewInfo)
	{
		if (m_bPlay)
		{
			StopPlay();
		}
		HWND hwnd = new HWND(Native.getComponentPointer(panelPlay)); // get preview handle
		struPreviewInfo.hPlayWnd = hwnd;
		m_lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V40(lUserId, struPreviewInfo, null, null);
		long m_lPreviewHandleNew = m_lPreviewHandle.longValue();
		if (m_lPreviewHandleNew < 0)
		{
			int ierr = hCNetSDK.NET_DVR_GetLastError();
			DialogMessage dlg = new DialogMessage("Failed NET_DVR_RealPlay_V40:" + ierr);
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
		} 
		m_bPlay = true;
	}

	//stop play
	private void StopPlay()
	{
		if (m_bPlay)
		{
			hCNetSDK.NET_DVR_StopRealPlay(m_lPreviewHandle);
			panelPlay.repaint();
			m_bPlay = false;
		}
	}

	//create device tree1
	private static void CreateDeviceTree1(NativeLong lUserID, String sDeviceIP, HCNetSDK.NET_DVR_DEVICEINFO_V40 strInfo)
	{
		HCNetSDK.NET_DVR_DEVICEINFO_V30 strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		strDeviceInfo = strInfo.struDeviceV30;
		IntByReference ibrBytesReturned = new IntByReference(0);// get ip param
		boolean bRet = false;

		HCNetSDK.NET_DVR_IPPARACFG strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG();
		strIpparaCfg.write();
		Pointer lpIpParaConfig = strIpparaCfg.getPointer();
		bRet = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_IPPARACFG, new NativeLong(0), lpIpParaConfig,
				strIpparaCfg.size(), ibrBytesReturned);
		strIpparaCfg.read();

		DefaultTreeModel TreeModel = ((DefaultTreeModel) signalTreeDevice.getModel());// get tree model
		MyTreeNode TreeIP = new MyTreeNode(sDeviceIP);
		m_DeviceRoot1.add(TreeIP);// add device to tree
		if (!bRet)
		{
			for (int iChannum = 0; iChannum < strDeviceInfo.byChanNum; iChannum++)
			{
				MyTreeNode newNode = new MyTreeNode("Camera" + (iChannum + strDeviceInfo.byStartChan));
				TreeIP.add(newNode);
			}
		}
		else
		{
			// ip camera
			for (int iChannum = 0; iChannum < strDeviceInfo.byChanNum; iChannum++)
			{
				{
					MyTreeNode newNode = new MyTreeNode("Camera" + (iChannum + strDeviceInfo.byStartChan));
					TreeIP.add(newNode);
					m_iTreeNodeNum++;
				}
			}
			for (int iChannum = 0; iChannum < /*HCNetSDK.MAX_IP_CHANNEL*/strDeviceInfo.byIPChanNum; iChannum++)
				{
					MyTreeNode newNode = new MyTreeNode("IPCamera" + (iChannum + strDeviceInfo.byStartChan));
					TreeIP.add(newNode);
				}
		}
		TreeModel.reload();
		signalTreeDevice.expandPath(new TreePath(TreeIP.getPath()));
		signalTreeDevice.setCellRenderer(new NodeRenderer());
	}

	//create device tree
	private static void CreateDeviceTree(NativeLong lUserID, String sDeviceIP, HCNetSDK.NET_DVR_DEVICEINFO_V40 strInfo)
	{
		HCNetSDK.NET_DVR_DEVICEINFO_V30 strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
		strDeviceInfo = strInfo.struDeviceV30;
		VideoWallDataOp.GetDisplayNo(sDeviceIP,strInfo);
//		IntByReference ibrBytesReturned = new IntByReference(0);// get ip param
//		boolean bRet = false;
//		HCNetSDK.NET_DVR_DISPLAYCFG strIpparaCfg = new HCNetSDK.NET_DVR_DISPLAYCFG();
//		strIpparaCfg.write();
//		Pointer lpIpParaConfig = strIpparaCfg.getPointer();
//		 
//		if(!hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLDISPLAYNO, new NativeLong(0), lpIpParaConfig,
//				strIpparaCfg.size(), ibrBytesReturned))
//		{
//			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
//			AddLogNew("FAIL", "NET_DVR_GetDVRConfig", sDeviceIP, errInfo);
//		}
//		
//		else
//		{
//			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
//			AddLogNew("SUCC", "NET_DVR_GetDVRConfig", sDeviceIP, errInfo);
//			strIpparaCfg.read();
//		}		
//		DefaultTreeModel TreeModel = ((DefaultTreeModel) controlTreeDevice.getModel());// get tree model
//		MyTreeNode TreeIP = new MyTreeNode(sDeviceIP);
//		m_DeviceRoot.add(TreeIP);// add device to tree
//		if (true)
//		{
//			for (int iChannum = 0; iChannum < hCNetSDK.MAX_DISPLAY_NUM; iChannum++)
//			{
//				if (strIpparaCfg.struDisplayParam[iChannum].byDispChanType == 0 )
//	            {
//	                continue; 
//	            }
//				MyTreeNode newNode = new MyTreeNode(((strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo>>24)+"_"+ ((strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo>>16)&0xff))+"_"+(strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo&0xffff)+"_"+DisplayLinkMode[strIpparaCfg.struDisplayParam[iChannum].byDispChanType]);
//				TreeIP.add(newNode);
//			}
//		}
//		TreeModel.reload();
//		controlTreeDevice.expandPath(new TreePath(TreeIP.getPath()));
//		controlTreeDevice.setCellRenderer(new NodeRenderer());
	}
	
	//init tree model
	private DefaultTreeModel initialTreeModel()
	{
		m_DeviceRoot = new MyTreeNode("Control Device Tree");
		DefaultTreeModel myDefaultTreeModel = new DefaultTreeModel(m_DeviceRoot);
		MyTreeNode newNode = new MyTreeNode(ReadDeviceInfo());	
		//MyTreeNode newNode = new MyTreeNode("10.19.82.178");
		m_DeviceRoot.add(newNode);
		
		Icon ClosedIcon=new ImageIcon("res/tree.bmp"); 
		DefaultTreeCellRenderer render=(DefaultTreeCellRenderer)(controlTreeDevice.getCellRenderer()); 
		render.setClosedIcon(ClosedIcon);
		return myDefaultTreeModel;
	}
	private DefaultTreeModel initialTreeModel1()
	{
		m_DeviceRoot1 = new MyTreeNode("Signal Device Tree");

		DefaultTreeModel myDefaultTreeModel1 = new DefaultTreeModel(m_DeviceRoot1);
		return myDefaultTreeModel1;
	}
	//getDisplaylNumber
	private int getChannelNumber()
	{
		int iChannelNum = -1;
		TreePath tp = controlTreeDevice.getSelectionPath();
		if (tp != null)
		{
			String sChannelName = ((DefaultMutableTreeNode) tp.getLastPathComponent()).toString();
			
		    iChannelNum = Integer.parseInt(sChannelName);
	
		}
		else
		{
			return -1;
		}
		return iChannelNum;
	}
   
	//getChannelNumber
	private int getChannelNumber1()
	{
		int iChannelNum = -1;
		TreePath tp = controlTreeDevice.getSelectionPath();
		if (tp != null)
		{
			String sChannelName = ((DefaultMutableTreeNode) tp.getLastPathComponent()).toString();
			if (sChannelName.charAt(0) == 'C') // Camara
			{
				iChannelNum = Integer.parseInt(sChannelName.substring(6));
			}
			else
			{
				if (sChannelName.charAt(0) == 'I') // IPCamara
				{
					iChannelNum = Integer.parseInt(sChannelName.substring(8));
				}
				else
				{
					return -1;
				}
			}
		}
		else
		{
			return -1;
		}
		return iChannelNum;
	}
	/**
	 * 选择显示输出号
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void ChooseDisplayChannel(String sDeviceIP){
		String ip = sDeviceIP; // get ip from device tree
		if (ip.length() == 0 || ip == "Control Device Tree") // not select ip or select "devicetree" root
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please select channel");
			DialogMessage dlg = new DialogMessage("Please choose display output channel");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong lUserID = m_hashDeviceInfo.get(ip).GetNUserID(); // get loginID

		if (lUserID.longValue() == -1)
		{
			//JOptionPane.showMessageDialog(JavaDemo.this, "Please login");
			DialogMessage dlg = new DialogMessage("Please login");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		int[] iDisplayChannelNum=new int[4];
        for(int i=0;i<4;i++)
        {
        	 iDisplayChannelNum[i] = getChannelNumber();
        }

		
	}
	/**
	 * @Title getDeviceIP
	 * @Description 
	 * @return
	 */
	private static String getDeviceIP()
	{
		String Ip = "";
		TreePath tp = controlTreeDevice.getSelectionPath();
		if (tp != null)
		{
			DefaultMutableTreeNode leafNode = (DefaultMutableTreeNode) tp.getLastPathComponent();
			if (leafNode.getParent() == null)
			{
				return Ip;
			}
			Ip = (leafNode.getParent()).toString();
		}
		return Ip;
	}

	// right click device tree
	@SuppressWarnings("deprecation")
	private void RightClickMouse(MouseEvent e)
	{

		TreePath path = controlTreeDevice.getPathForLocation(e.getX(), e.getY());
		controlTreeDevice.setSelectionPath(path);
		int selectRow = controlTreeDevice.getRowForLocation(e.getX(), e.getY());
		if (selectRow < 0)
		{
			/*DialogMessage dlg = new DialogMessage("Please choose correct device");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);*/
			return;
		}
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			TreePath selTree = controlTreeDevice.getPathForRow(selectRow);
			DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) selTree.getLastPathComponent();

		    if (selNode.toString() == "Control Device Tree")
			{
		    	isTree=1;
				JPopupMenu pop = new JPopupMenu();
				JMenuItem addDevice = new JMenuItem();
				addDevice.setLabel("Add Device");
				addDevice.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						DialogLogin dlg = new DialogLogin();
						dlg.setBounds(JavaDemo.this.getX() + JavaDemo.this.getWidth() / 4,
								JavaDemo.this.getY() + JavaDemo.this.getHeight() / 4, 460, 600);
						dlg.setVisible(true);
						dlg.setModal(true);
					}
				});
				pop.add(addDevice);
				pop.show(e.getComponent(), e.getX(), e.getY());
			}
			else
			{
				JPopupMenu pop = new JPopupMenu();
				JMenuItem menuLogin = new JMenuItem();
				menuLogin.setLabel("Login");
				menuLogin.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						TreeLoginActionPerformed(e);
					}
				});
				JMenuItem menuLogout = new JMenuItem();
				menuLogout.setLabel("Logout");
				menuLogout.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						// TODO Auto-generated method stub
						LogoutActionPerformed(arg0);
					}
				});

				JMenuItem menuAttribute = new JMenuItem();
				menuAttribute.setLabel("Attribute");
				menuAttribute.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						AttributeActionPerformed(e);
					}
				});
				JMenuItem menuDelete = new JMenuItem();
				menuDelete.setLabel("Delete");
				menuDelete.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						DeleteActionPerformed(e);
					}
				});
				pop.add(menuLogout);
				pop.add(menuLogin);
				pop.add(menuAttribute);
				pop.add(menuDelete);
				pop.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	// right click device tree1
		@SuppressWarnings("deprecation")
		private void RightClickMouse1(MouseEvent e)
		{

			TreePath path = signalTreeDevice.getPathForLocation(e.getX(), e.getY());
			signalTreeDevice.setSelectionPath(path);
			int selectRow = signalTreeDevice.getRowForLocation(e.getX(), e.getY());
			if (selectRow < 0)
			{
				/*DialogMessage dlg = new DialogMessage("Please choose correct device");
				dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
				dlg.setVisible(true);*/
				return;
			}
			if (e.getButton() == MouseEvent.BUTTON3)
			{
				TreePath selTree = signalTreeDevice.getPathForRow(selectRow);
				DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) selTree.getLastPathComponent();

			 if (selNode.toString() == "Signal Device Tree")
				{
					isTree=2;
					JPopupMenu pop = new JPopupMenu();
					JMenuItem addDevice = new JMenuItem();
					addDevice.setLabel("Add Device");
					addDevice.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							// TODO Auto-generated method stub
							DialogLogin dlg = new DialogLogin();
							dlg.setBounds(JavaDemo.this.getX() + JavaDemo.this.getWidth() / 4,
									JavaDemo.this.getY() + JavaDemo.this.getHeight() / 4, 460, 600);
							dlg.setVisible(true);
							dlg.setModal(true);
						}
					});
					pop.add(addDevice);
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
				else
				{
					JPopupMenu pop = new JPopupMenu();
					JMenuItem menuLogin = new JMenuItem();
					menuLogin.setLabel("Login");
					menuLogin.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							// TODO Auto-generated method stub
							TreeLoginActionPerformed1(e);
						}
					});
					JMenuItem menuLogout = new JMenuItem();
					menuLogout.setLabel("Logout");
					menuLogout.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0)
						{
							// TODO Auto-generated method stub
							LogoutActionPerformed(arg0);
						}
					});

					JMenuItem menuAttribute = new JMenuItem();
					menuAttribute.setLabel("Attribute");
					menuAttribute.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
						{
							// TODO Auto-generated method stub
							AttributeActionPerformed(e);
						}
					});
					JMenuItem menuDelete = new JMenuItem();
					menuDelete.setLabel("Delete");
					menuDelete.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							// TODO Auto-generated method stub
							DeleteActionPerformed(e);
						}
					});
					pop.add(menuLogout);
					pop.add(menuLogin);
					pop.add(menuAttribute);
					pop.add(menuDelete);
					pop.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		}

	// login device
	private void DoubleClickMouse(MouseEvent e)
	{
		TreePath path = controlTreeDevice.getPathForLocation(e.getX(), e.getY());
		controlTreeDevice.setSelectionPath(path);
		int selectRow = controlTreeDevice.getRowForLocation(e.getX(), e.getY());
		if (selectRow < 0 || selectRow == 0)
		{
			/*DialogMessage dlg = new DialogMessage("Please choose correct device");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;*/
			return;
		}

		if (e.getClickCount() == 2) 
		{
			TreePath selTree = controlTreeDevice.getPathForRow(selectRow);
			DefaultMutableTreeNode selNode = (DefaultMutableTreeNode) selTree.getLastPathComponent();
			if (selNode.isLeaf())
			{

			}
			else
			{
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
				String ip = treeNode.toString();
				DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);

				NativeLong nlUserID = LoginDevice(deviceInfo);
				deviceInfo.SetNUserID(nlUserID);
				m_hashDeviceInfo.replace(ip, deviceInfo);
				controlTreeDevice.expandPath(controlTreeDevice.getSelectionPath());
				MyTreeNode node = (MyTreeNode) treeNode;
				node.m_isOnLine = true;
				controlTreeDevice.setCellRenderer(new NodeRenderer());
			}
		}
	}

	// login out
	@SuppressWarnings("deprecation")
	private void LogoutActionPerformed(ActionEvent arg0)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
		String ip = treeNode.toString();
		NativeLong lUserID = m_hashDeviceInfo.get(ip).GetNUserID();
		if (lUserID.longValue() == -1)
		{
			DialogMessage dlg = new DialogMessage("Please choose No unregistered device");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		if (!hCNetSDK.NET_DVR_Logout(lUserID))
		{
			int ierr = hCNetSDK.NET_DVR_GetLastError();
			String errInfo=String.format("err[%d]",ierr);
			AddLogNew("FAIL", "NET_DVR_Logout", ip, errInfo);
		} 
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			AddLogNew("SUCC", "NET_DVR_Logout", ip, errInfo);	
		}
		
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		deviceInfo.SetNUserID(new NativeLong(-1));
		m_hashDeviceInfo.replace(ip, deviceInfo);

		controlTreeDevice.collapsePath(controlTreeDevice.getSelectionPath());
		MyTreeNode node = (MyTreeNode) treeNode;
		node.m_isOnLine = false;
		controlTreeDevice.setCellRenderer(new NodeRenderer());

	}

	// login tree
	private void TreeLoginActionPerformed(ActionEvent event)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
		String ip = treeNode.toString();
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		if (deviceInfo.GetNUserID().longValue() != -1)
		{
			DialogMessage dlg = new DialogMessage("Device is already logged in");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong nlUserID = LoginDevice(deviceInfo);
		deviceInfo.SetNUserID(nlUserID);
		m_hashDeviceInfo.replace(ip, deviceInfo);
		controlTreeDevice.expandPath(controlTreeDevice.getSelectionPath());
		MyTreeNode node = (MyTreeNode) treeNode;
		node.m_isOnLine = true;
		controlTreeDevice.setCellRenderer(new NodeRenderer());

	}
	// login tree1
	private void TreeLoginActionPerformed1(ActionEvent event)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) signalTreeDevice.getLastSelectedPathComponent();
		String ip = treeNode.toString();
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		if (deviceInfo.GetNUserID().longValue() != -1)
		{
			DialogMessage dlg = new DialogMessage("Device is already logged in");
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
			return;
		}
		NativeLong nlUserID = LoginDevice(deviceInfo);
		deviceInfo.SetNUserID(nlUserID);
		m_hashDeviceInfo.replace(ip, deviceInfo);
		signalTreeDevice.expandPath(signalTreeDevice.getSelectionPath());
		MyTreeNode node = (MyTreeNode) treeNode;
		node.m_isOnLine = true;
		signalTreeDevice.setCellRenderer(new NodeRenderer());

	}
	
	private NativeLong LoginDevice(DeviceInfo deviceInfo)
	{
		NET_DVR_USER_LOGIN_INFO struLoginInfo = new NET_DVR_USER_LOGIN_INFO();
		NET_DVR_DEVICEINFO_V40 struDeviceInfo = new NET_DVR_DEVICEINFO_V40();
		for (int i = 0; i < deviceInfo.GetIP().length(); i++)
		{
			struLoginInfo.sDeviceAddress[i] = (byte) deviceInfo.GetIP().charAt(i);
		}
		for (int i = 0; i < deviceInfo.GetPassword().length(); i++)
		{
			struLoginInfo.sPassword[i] = (byte) deviceInfo.GetPassword().charAt(i);
		}
		for (int i = 0; i < deviceInfo.GetUserName().length(); i++)
		{
			struLoginInfo.sUserName[i] = (byte) deviceInfo.GetUserName().charAt(i);
		}
		struLoginInfo.wPort = deviceInfo.GetPort();
		NativeLong nlUserID = hCNetSDK.NET_DVR_Login_V40(struLoginInfo, struDeviceInfo);
		long lUserID = nlUserID.longValue();
		if (lUserID == -1)
		{
			int ierr = hCNetSDK.NET_DVR_GetLastError();
			String errInfo=String.format("err[%d]",ierr);
			AddLogNew("FAIL", "NET_DVR_Login_V40", deviceInfo.GetIP(), errInfo);
			DialogMessage dlg = new DialogMessage("login error,error code :" + ierr);
			dlg.setBounds(this.getX()+this.getWidth()/3,this.getY()+this.getHeight()/3 , 370, 200);
			dlg.setVisible(true);
		} 
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			AddLogNew("SUCC", "NET_DVR_Login_V40", deviceInfo.GetIP(), errInfo);	
		}
		return nlUserID;
	}

	// delete device
	@SuppressWarnings("deprecation")
	private void DeleteActionPerformed(ActionEvent e)
	{
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
		DefaultTreeModel model = (DefaultTreeModel) controlTreeDevice.getModel();
		model.removeNodeFromParent(treeNode);
		String ip = treeNode.toString();
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		NativeLong lUserID = deviceInfo.GetNUserID();

		if (lUserID.longValue() != -1)
		{
			
			if (!hCNetSDK.NET_DVR_Logout(lUserID))
			{
				int ierr = hCNetSDK.NET_DVR_GetLastError();
				String errInfo=String.format("err[%d]",ierr);
				AddLogNew("FAIL", "NET_DVR_Logout", ip, errInfo);
			} 
			else
			{
				String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				AddLogNew("SUCC", "NET_DVR_Logout", ip, errInfo);	
			}			
		}
		m_hashDeviceInfo.remove(ip);
	}

	// device attribute
	void AttributeActionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
		String ip = treeNode.toString();
		DeviceInfo deviceInfo = m_hashDeviceInfo.get(ip);
		DialogDeviceAttribute dlg = new DialogDeviceAttribute(deviceInfo);
		dlg.setBounds(this.getX() + this.getWidth() / 4, this.getY() + this.getHeight() / 4, 440, 260);
		dlg.setVisible(true);
	}

	public static int TreeAddDevice(NativeLong nlUserID, String sDeviceIP,
			HCNetSDK.NET_DVR_DEVICEINFO_V40 strDeviceInfo, DeviceInfo deviceInfo)
	{
		if (m_hashDeviceInfo.containsKey(sDeviceIP) && m_hashDeviceInfo.get(sDeviceIP).GetNUserID().longValue() != -1)
		{
			return -1;
		}
		if (m_hashDeviceInfo.containsKey(sDeviceIP))
		{
			m_hashDeviceInfo.replace(sDeviceIP, deviceInfo);

		}
		else
		{
			m_hashDeviceInfo.put(sDeviceIP, deviceInfo);
			String ip = getDeviceIP(); // get ip from device tree
			System.out.println(ip);
			if (isTree==1 || ip == "Control Device Tree") // not select ip or select "devicetree" root
			{
				CreateDeviceTree(nlUserID, sDeviceIP, strDeviceInfo);
			}
			if (isTree==2||ip == "Signal Device Tree") // not select ip or select "devicetree" root
			{
				CreateDeviceTree1(nlUserID, sDeviceIP, strDeviceInfo);
			}

		}
		return 0;
	}

	// get ip
	public static String GetTreeIp()
	{
		NativeLong lUserId = new NativeLong(-1);
		String ip = "";
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) controlTreeDevice.getLastSelectedPathComponent();
		if (treeNode == null)
		{
			return ip;
		}
		if (treeNode.toString() == "Control Device Tree")
		{
			return ip;
		}
		if (treeNode.isLeaf())
		{
			ip = getDeviceIP();
		}
		else
		{
			ip = treeNode.toString();
		}
		return ip;
	}

	public static void centerWindow(Container window)
	{
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int w = window.getSize().width;
		int h = window.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		window.setLocation(x, y);
	}

		// Init table mode
	public DefaultTableModel initialTableModel()
	{
		String tabeTile[];
		tabeTile = new String[] { "Time","State","Operation","Device Info", "Erroe Info" };
		DefaultTableModel alarmTableModel = new DefaultTableModel(tabeTile, 0);
		return alarmTableModel;
	}

	public static DefaultTableModel getTableModel()
	{
		return ((DefaultTableModel) tableAlarm.getModel());
	}
	
	public static void AddLog(String errInfo,String deviceInfo)
	{
        Calendar calendar = Calendar.getInstance();  
        Date time = calendar.getTime(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String logTime = sdf.format(time);
    	String[] log = {logTime, errInfo, deviceInfo};
    	JavaDemo.getTableModel().addRow(log);
	}
	/**
	 * log
	 * @param status
	 * @param command
	 * @param deviceInfo
	 * @param errInfo
	 */
	public static void AddLogNew(String status,String command,String deviceInfo,String errInfo )
	{
		 Calendar calendar = Calendar.getInstance();  
	        Date time = calendar.getTime(); 
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String logTime = sdf.format(time);
	    	String[] log = {logTime,status, command, deviceInfo,errInfo};
	    	JavaDemo.getTableModel().addRow(log);
	}
	
	/**
	 * InitVideoWall
	 */
	public static void InitVideoWall( int lVideoWallNo, int iRow, int iColumn)
	{	
		//panelRealPlay.repaint();
		arrShowWall = new Show[iRow][iColumn];
		for (int i = 0; i != iRow; ++i)
		{
			for (int j = 0; j != iColumn; ++j)
			{
				arrShowWall[i][j] = new Show();
			}
		}
		if (iRow <= 0 || iColumn <= 0)
		{
			return;
		}
		int iXProportion = 4;  //电视墙显示单元显示比例 默认为4:3
		int iYProportion = 3;

		int iNum1 = panelRealPlay.getWidth()/ (iXProportion * iColumn);
	    int iNum2 = panelRealPlay.getHeight()/ (iYProportion * iRow);
	    int iResoult = (iNum1 < iNum2) ? iNum1 : iNum2;
		
	    int iAdjustFrameWidth = iResoult * iXProportion * iColumn;
	    int iAdjustFrameHeight = iResoult * iYProportion * iRow;

		int iLeft = (panelRealPlay.getWidth()  - iAdjustFrameWidth) / 2;
		int iTop =  (panelRealPlay.getHeight() - iAdjustFrameHeight) / 2;
		int iCellWidth = iResoult * iXProportion;
		int iCellHeight = iResoult * iYProportion;


		int iTemX = 0;
	    int iTemY = 0;

		panelRealPlay.remove(panelPlay);
	    //画出电视墙
		for (int i = 0; i != iRow; ++i)
		{
			iTemY = i * iCellHeight;
			for (int j = 0; j != iColumn; ++j)
			{
				iTemX = j * iCellWidth;
				//arrShowWall[i][j].setBackground(Color.blue);
				panelRealPlay.add(arrShowWall[i][j]);
				arrShowWall[i][j].revalidate();
				arrShowWall[i][j].repaint();
			}
		}
		//OnPaint();
	}

	private static void OnPaint(int iRow, int iColumn) {
		for (int i = 0; i != iRow; ++i)
		{
			for (int j = 0; j != iColumn; ++j)
			{
				panelRealPlay.remove(arrShowWall[i][j]);
			}
		}
		int iwidth = panelRealPlay.getWidth();
		int iHeight = panelRealPlay.getHeight();
		panelPlay.setBounds(0, 0, iwidth, iHeight);
		panelRealPlay.add(panelPlay);
	}

	/**
	 * Write device information
	 * 
	 * @param
	 * @return
	 */
	public static void WriteDeviceInfo(DeviceInfo deviceInfo) {
		try {
			// Save device information
			prop = new Properties();
			FileOutputStream oFile = new FileOutputStream("device.properties", true);
			prop.setProperty("IP", deviceInfo.GetIP());
			prop.setProperty("Port", String.valueOf(deviceInfo.GetPort()));
			prop.setProperty("UserName", deviceInfo.GetUserName());
			prop.setProperty("PassWord", deviceInfo.GetPassword());
			prop.store(oFile, "The New properties file");
			oFile.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Read device information
	 * 
	 */
	private String ReadDeviceInfo() {
		String deviceIP = null;
		try {

			// Read device information
			InputStream in = new BufferedInputStream(new FileInputStream("device.properties"));
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			while (it.hasNext()) {
				String key = it.next();
				if (key == "IP") {
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
