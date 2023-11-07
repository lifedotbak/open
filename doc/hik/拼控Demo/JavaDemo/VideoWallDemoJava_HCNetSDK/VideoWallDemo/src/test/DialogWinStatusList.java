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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogWinStatusList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable m_lstWinStatus;
	private static int m_dwChanNum;
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private static NativeLong m_lUserID;
	public static	String dynDwChannelEx=null;
	public static	String dynDecodeStatus=null;
	public static String dynStreamType=null;
	public static  String dynPacketType = null ;
	public static	int dynFpsDecV;
	public	static int dynFpsDecA;
	public  static int dynDecodedV;
	public	static int dynDecodedA;
	public static	int dynWImg;
	public	static int dynWImgH;
	public static String dynStreamMode;
	public static  int   winStatusListRowNum=0;
	public static int dwChannel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogWinStatusList dialog = new DialogWinStatusList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogWinStatusList() {
		Font font=new Font("Dialog",Font.PLAIN,12);
		java.util.Enumeration keys=UIManager.getDefaults().keys();
		while(keys.hasMoreElements()){
			Object key=keys.nextElement();
			Object value=UIManager.get(key);
			if(value instanceof javax.swing.plaf.FontUIResource){
				UIManager.put(key, font);
			}
		}
		setTitle("Window Decoding Status");
		setBounds(100, 100, 1007, 571);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JScrollPane winStatus = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(21, Short.MAX_VALUE)
					.addComponent(winStatus, GroupLayout.PREFERRED_SIZE, 942, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(31)
					.addComponent(winStatus, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		
		m_lstWinStatus = new JTable();
		m_lstWinStatus.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Win No", "Decode Status", "Stream Type", "Packet Type", "FpsDecV", "FpsDecA", "DecodedV", "DecodedA", "ImgW", "ImgH", "StreamMode"
			}
		));
		winStatus.setViewportView(m_lstWinStatus);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Refresh");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						GetAllWinStatus();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Exit");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Quit(arg0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setLocationRelativeTo(null);
	}

	private void Quit(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.dispose();	
	}
	
	/**
	 * Get all window numbers
	 */
	public static void GetAllWin(){
		int[] dwWinNo=new int[hCNetSDK.MAX_VM_WIN_NUM * 16];
		int iWinCount = 0;
		int iRet = 0; 

		HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION_EXTENDGET struWindowPos = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION_EXTENDGET();
		Pointer pOutputBuff = struWindowPos.getPointer();
		struWindowPos.write();
		int dwOutputBuff = 4 + 256 * (new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION()).size();
		HCNetSDK.DwWallNo dwWinNumEX = new HCNetSDK.DwWallNo();
		dwWinNumEX.dwWallNo =1<<24;;
		Pointer lpDwWinNumEX = dwWinNumEX.getPointer();	
		dwWinNumEX.write();
		if (false == hCNetSDK.NET_DVR_GetDeviceConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLWINDOWPOSITION,0xffffffff, lpDwWinNumEX, 4, null, pOutputBuff, dwOutputBuff))
		{
		   	String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", null, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to get window number");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			iRet = -1; 
		}
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", null, errInfo);
			struWindowPos.read();
			DialogMessage dlg = new DialogMessage("Get window number successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			System.out.println(struWindowPos.strWinPos[0].dwWindowNo);
			dwChannel=struWindowPos.strWinPos[0].dwWindowNo;
		}
		/*else
		{
			int dwWinCount=10;
			HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION[] lpWinPos = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION[4];
			for (int i = 0, k=0; i < dwWinCount; i++)
			{
				int iSubMode = GetSubWin(lpWinPos[i].dwWindowNo); 
				if ( iSubMode == -1)
				{
					dwWinNo[iWinCount++] = lpWinPos[i].dwWindowNo;
					continue;
				}
				for (int j=0; j < iSubMode; j++)
				{
					dwWinNo[iWinCount++] = ((j+1)<<16) + lpWinPos[i].dwWindowNo;
				}; 
			} 
			iRet = iWinCount; 
		}
		return iRet;*/
	}
	
	/**
	 * Window decoding status
	 */
	
	public static void GetAllWinStatus()
	{
		
		GetAllWin();
		DrawStatus(); 
	}
	
	/**
	 * Get all sub windows
	 */
	public static  int GetSubWin(int dwWinNo)
	{
		HCNetSDK.NET_DVR_WALLWINPARAM struWinWallParam = new HCNetSDK.NET_DVR_WALLWINPARAM();
		Pointer lpStruWinWallParam = struWinWallParam.getPointer();	
		struWinWallParam.write();
		IntByReference dwReturned = new IntByReference(0);	
		if (false == hCNetSDK.NET_DVR_GetDVRConfig(m_lUserID, HCNetSDK.NET_DVR_WALLWINPARAM_GET, new NativeLong(dwWinNo), lpStruWinWallParam,struWinWallParam.size(), dwReturned))
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_WALLWINPARAM_GET", null, errInfo);
			return -1;
		}
		return (struWinWallParam.byWinMode==0)?1:struWinWallParam.byWinMode;
	}
	
	/**
	 * Get window decoding status
	 */
	public static  void DrawStatus()
	{
		HCNetSDK.NET_DVR_WALLWIN_INFO struWallWinInfo = new HCNetSDK.NET_DVR_WALLWIN_INFO();
		Pointer lpStruWallWinInfo = struWallWinInfo.getPointer();			
		HCNetSDK.NET_DVR_WALL_WIN_STATUS struWallWinStatus = new HCNetSDK.NET_DVR_WALL_WIN_STATUS();
		Pointer lpStruWallWinStatus = struWallWinStatus.getPointer();			
		String[] pStreamType = {"UNKOWN","H264", "S264", "MPEG4", "ORIGSTREAM", "PICTURE", "MJPEG", "MPEG2","H265","SVAC"}; 
		String[] pPacketType = {"UNKOWN","PRIVT", "UNDEFINE","UNDEFINE","UNDEFINE","UNDEFINE","UNDEFINE","TS", "PS", "RTP", "ORIGIN"}; 
		String[] pDecodeStatus={"Not decoded","Decoding"};
        DefaultTableModel dtm=(DefaultTableModel)m_lstWinStatus.getModel();
        dtm.setRowCount(0);
        winStatusListRowNum=0;
		for (int i = 0; i < 1/*m_dwChanNum*/; i++) {
			int dwChannelEx = dwChannel;
			struWallWinInfo.dwSize=struWallWinInfo.size();
			System.out.println(dwChannel);
			struWallWinInfo.dwWinNum = dwChannelEx & 0xffff;
			System.out.println(struWallWinInfo.dwWinNum);
			struWallWinInfo.dwSubWinNum = (dwChannelEx >> 16) & 0xff;
			System.out.println(struWallWinInfo.dwSubWinNum);
			struWallWinInfo.dwWallNo = (dwChannelEx >> 24) & 0xff;
			System.out.println(struWallWinInfo.dwWallNo);
			
			struWallWinStatus.write();
			struWallWinInfo.write();
			if (false == hCNetSDK.NET_DVR_GetDeviceStatus(m_lUserID, HCNetSDK.NET_DVR_MATRIX_GETWINSTATUS, 0,
					lpStruWallWinInfo, struWallWinInfo.size(), null, lpStruWallWinStatus, struWallWinStatus.size())) {
				String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				JavaDemo.AddLogNew("FAIL", "NET_DVR_MATRIX_GETWINSTATUS", null, errInfo);
				DialogMessage dlg = new DialogMessage("Failed to get window decoding status");
				dlg.setBounds(780,500, 400, 200);
				dlg.setVisible(true);
				continue;
			}
			else
			{
				String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				JavaDemo.AddLogNew("SUCC", "NET_DVR_MATRIX_GETWINSTATUS", null, errInfo);
				DialogMessage dlg = new DialogMessage("Get window decoding status successfully");
				dlg.setBounds(780,500, 400, 200);
				dlg.setVisible(true);
				struWallWinStatus.read();
			}

			dynDwChannelEx = (dwChannelEx >> 24) + "_" + ((dwChannelEx >> 16) & 0xff) + "_" + (dwChannelEx & 0xffff);;
			dynDecodeStatus = pDecodeStatus[struWallWinStatus.byDecodeStatus];
			dynStreamType = pStreamType[struWallWinStatus.byStreamType];
			dynPacketType = pPacketType[struWallWinStatus.byPacketType];
			dynFpsDecV = struWallWinStatus.byFpsDecV;
			dynFpsDecA = struWallWinStatus.byFpsDecA;
			dynDecodedV = struWallWinStatus.dwDecodedV;
			dynDecodedA = struWallWinStatus.dwDecodedA;
			dynWImg = struWallWinStatus.wImgW;
			dynWImgH = struWallWinStatus.wImgH;
			switch (struWallWinStatus.byStreamMode)
			{
			case 0:
				dynStreamMode="Network";
				break; 
			case 1:
				dynStreamMode="Local Input Source";
				break; 
			case (byte) 0xff:
				dynStreamMode="Invalid";
				break;
			default:
				dynStreamMode="Unknown Value";
			}   
			dtm.addRow(new Object[]{dynDwChannelEx,dynDecodeStatus,dynStreamType,dynPacketType,dynFpsDecV,dynFpsDecA,dynDecodedV,dynDecodedA,dynWImg,dynWImgH,dynStreamMode});  
			winStatusListRowNum++;
		}
		
	}
}
