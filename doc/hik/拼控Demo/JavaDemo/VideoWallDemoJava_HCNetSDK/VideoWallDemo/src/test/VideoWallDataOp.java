package test;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HANDLE;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.IntByReference;



public class VideoWallDataOp {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private static NativeLong m_lUserID;
	public static final int VW_BASE_RESOLUTION_FIRST = 1920;
	public static final int VW_BASE_RESOLUTION_SECOND = 1920;
	public static int dwChannel;
	/**
	 * Login
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	HCNetSDK.STRU_DEVICE_INFO[] g_struDeviceInfo=new HCNetSDK.STRU_DEVICE_INFO[hCNetSDK.MAX_DEVICES];
	public boolean DoLoginEx(int iDeviceIndex) {
       return true;
	}
	/**
	 * wall
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public boolean GetDispMode(byte byWallNo,String sDeviceIP, HCNetSDK.NET_DVR_DEVICEINFO_V40 strInfo) {
		HCNetSDK.NET_DVR_VIDEOWALLDISPLAYMODE struDisplayMode = new HCNetSDK.NET_DVR_VIDEOWALLDISPLAYMODE();
		struDisplayMode.write();
		Pointer lpStruDisplayMode = struDisplayMode.getPointer();
		IntByReference ibrBytesReturned = new IntByReference(0);
		int dwChannelEx=byWallNo;
		dwChannelEx <<= 24; 
		boolean bSupportSetMode = true;
		if (bSupportSetMode)
		{
			if (!hCNetSDK.NET_DVR_GetDVRConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLDISPLAYMODE, new NativeLong(dwChannelEx), lpStruDisplayMode, struDisplayMode.size(), ibrBytesReturned))
			{
				DialogMessage dlg = new DialogMessage("The wall does not exist");
				dlg.setBounds(0,0 , 400, 200);
				dlg.setVisible(true);
				String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				JavaDemo.AddLogNew("FAIL", "NET_DVR_GET_VIDEOWALLDISPLAYMODE", sDeviceIP, errInfo);
			}
			else
			{
				String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				JavaDemo.AddLogNew("SUCC", "NET_DVR_GET_VIDEOWALLDISPLAYMODE", sDeviceIP, errInfo);
				struDisplayMode.read();
			}
		}
		if (struDisplayMode.byEnable>0)
	    {
	        byte byWidth =  (byte) (struDisplayMode.struRect.dwWidth / VW_BASE_RESOLUTION_FIRST); 
	        byte byHeigth = (byte) (struDisplayMode.struRect.dwHeight / VW_BASE_RESOLUTION_SECOND); 
	        //JavaDemo.InitVideoWall(dwChannelEx >> 24, byHeigth, byWidth);
	    }
		return false;
	}
	/**
	 * Get display output channel
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static boolean GetDisplayNo(String sDeviceIP, HCNetSDK.NET_DVR_DEVICEINFO_V40 strInfo) {
		HCNetSDK.NET_DVR_DISPLAYCFG strIpparaCfg = new HCNetSDK.NET_DVR_DISPLAYCFG();
		strIpparaCfg.write();
		Pointer lpIpParaConfig = strIpparaCfg.getPointer();
		IntByReference ibrBytesReturned = new IntByReference(0);
		if(!hCNetSDK.NET_DVR_GetDVRConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLDISPLAYNO, new NativeLong(0), lpIpParaConfig,
				strIpparaCfg.size(), ibrBytesReturned))
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_GetDVRConfig", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to get display output channel");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		}
		
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_GetDVRConfig", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Get display output channel successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			strIpparaCfg.read();
		}
		DefaultTreeModel TreeModel = ((DefaultTreeModel) JavaDemo.controlTreeDevice.getModel());// get tree model
		MyTreeNode TreeIP = new MyTreeNode(sDeviceIP);
		JavaDemo.m_DeviceRoot.add(TreeIP);// add device to tree
		if (true)
		{
			for (int iChannum = 0; iChannum < hCNetSDK.MAX_DISPLAY_NUM; iChannum++)
			{
				if (strIpparaCfg.struDisplayParam[iChannum].byDispChanType == 0 )
	            {
	                continue; 
	            }
				MyTreeNode newNode = new MyTreeNode(((strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo)+"_"+ (strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo>>24)+"_"+ ((strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo>>16)&0xff))+"_"+(strIpparaCfg.struDisplayParam[iChannum].dwDisplayNo&0xffff)+"_"+JavaDemo.DisplayLinkMode[strIpparaCfg.struDisplayParam[iChannum].byDispChanType]);
				TreeIP.add(newNode);
			}
		}
		TreeModel.reload();
		JavaDemo.controlTreeDevice.expandPath(new TreePath(TreeIP.getPath()));
		JavaDemo.controlTreeDevice.setCellRenderer(new NodeRenderer());
		return true;
	}

	/**
	 * Get display output position
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void GetDisplayPos(String sDeviceIP) {
		HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION_EXTENDGET struDisplayEx = new HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION_EXTENDGET();
		Pointer pOutputBuff=struDisplayEx.getPointer();
		struDisplayEx.write();
		int dwOutputBuff=4+256*(new HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION()).size();
		if (false == hCNetSDK.NET_DVR_GetDeviceConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLDISPLAYPOSITION, 0xffffffff, null, 0,null, pOutputBuff, dwOutputBuff))
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_GET_VIDEOWALLDISPLAYPOSITION", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to get display output position");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			return;
		}
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_GET_VIDEOWALLDISPLAYPOSITION", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Get display output location success");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			struDisplayEx.read();
			int dwCount=struDisplayEx.dwCount;
			System.out.println(dwCount);
			for(int i=0;i<dwCount;i++)
			{
				byte byEnable= struDisplayEx.strDisPlayPos[i].byEnable;
				int dwDisplayNo=struDisplayEx.strDisPlayPos[i].dwDisplayNo;
				System.out.println(struDisplayEx.strDisPlayPos[i].dwDisplayNo);
			}
		}
	}
	/**
	 * Set display output location
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void SetDisplayPos(String sDeviceIP,int[] disPlayWinNum) {
		HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION[] strSendItem = (HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION[])new HCNetSDK.NET_DVR_VIDEOWALLDISPLAYPOSITION().toArray(4);
		Pointer pIutputBuff=strSendItem[0].getPointer();
		HCNetSDK.INT_ARRAY pInt = new HCNetSDK.INT_ARRAY(4);
		 pInt.iValue[0] = -1;
	     pInt.iValue[1] = -1;
	     pInt.iValue[2] = -1;
	     pInt.iValue[3] = -1;
	     pInt.write();
	     
		HCNetSDK.DisPlayWinNum strDisPlayWinNum = new HCNetSDK.DisPlayWinNum();
		
		for(int i=0;i<4;i++)
		{
			strDisPlayWinNum.disPlayWinNumber[i]=disPlayWinNum[i];
			System.out.println(disPlayWinNum[i]);
			strSendItem[i].dwSize = strSendItem[i].size();
			strSendItem[i].byEnable=1;
			strSendItem[i].dwDisplayNo=disPlayWinNum[i];
			strSendItem[i].dwVideoWallNo=(1 << 24);
		}
		strSendItem[0].struRectCfg.dwXCoordinate =  0; 
		strSendItem[0].struRectCfg.dwYCoordinate =  0;
		strSendItem[0].write();		
		strSendItem[1].struRectCfg.dwXCoordinate =  VW_BASE_RESOLUTION_FIRST; 
		strSendItem[1].struRectCfg.dwYCoordinate =  0;
		strSendItem[1].write();		
		strSendItem[2].struRectCfg.dwXCoordinate = 0; 
		strSendItem[2].struRectCfg.dwYCoordinate = VW_BASE_RESOLUTION_SECOND;
		strSendItem[2].write();		
		strSendItem[3].struRectCfg.dwXCoordinate = VW_BASE_RESOLUTION_FIRST; 
		strSendItem[3].struRectCfg.dwYCoordinate = VW_BASE_RESOLUTION_SECOND;
		strSendItem[3].write();			
		Pointer lpStrDisPlayWinNum=strDisPlayWinNum.getPointer();
		strDisPlayWinNum.write();	
		if (false == hCNetSDK.NET_DVR_SetDeviceConfig(m_lUserID, HCNetSDK.NET_DVR_SET_VIDEOWALLDISPLAYPOSITION, 4, lpStrDisPlayWinNum, 4*4,pInt.getPointer(), pIutputBuff, strSendItem[0].size()*4))
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_SET_VIDEOWALLDISPLAYPOSITION", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to set display output position");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			return;
		}
		else
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_SET_VIDEOWALLDISPLAYPOSITION", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Set display output position successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			strSendItem[0].read();
		}
	}
	/**
	 * Get all windows
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void GetAllWinPos(String sDeviceIP) {
		HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION_EXTENDGET struWindowPos = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION_EXTENDGET();
		Pointer pOutputBuff = struWindowPos.getPointer();
		struWindowPos.write();
		int dwOutputBuff = 4 + 256 * (new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION()).size();
		HCNetSDK.DwWallNo dwWinNumEX = new HCNetSDK.DwWallNo();
		dwWinNumEX.dwWallNo=1<<24;
		Pointer ipDwWinNumEX = dwWinNumEX.getPointer();	
		dwWinNumEX.write();		
		if (false == hCNetSDK.NET_DVR_GetDeviceConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLWINDOWPOSITION,
				0xffffffff, ipDwWinNumEX, 4, null, pOutputBuff, dwOutputBuff)) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to get all windows");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			return;
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", sDeviceIP, errInfo);
			struWindowPos.read();
			dwChannel=struWindowPos.strWinPos[0].dwWindowNo;
			//System.out.println(dwChannel);
			DialogMessage dlg = new DialogMessage("Get all windows successfully, window number is"+dwChannel);
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		}
	}
	/**
	 * Get a window
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void GetWinPos(final int dwWinNo,String sDeviceIP){
		HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION struWindowPos = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION();
		Pointer LpStruWindowPos=struWindowPos.getPointer();
		struWindowPos.write();
		HCNetSDK.DwWallNo dwWinNumEX = new HCNetSDK.DwWallNo();
		Pointer lpDwWinNumEX = dwWinNumEX.getPointer();
		dwWinNumEX.write();
		dwWinNumEX.dwWallNo=dwWinNo;
		HCNetSDK.STATUS_LIST_4 m_struStatusList = new HCNetSDK.STATUS_LIST_4();
		Pointer lpStatusList = m_struStatusList.getPointer();
		m_struStatusList.write();
		   if (false == hCNetSDK.NET_DVR_GetDeviceConfig(m_lUserID, HCNetSDK.NET_DVR_GET_VIDEOWALLWINDOWPOSITION, 1, lpDwWinNumEX, 4, lpStatusList, LpStruWindowPos, struWindowPos.size()))
		    {
		    	String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
				JavaDemo.AddLogNew("FAIL", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", sDeviceIP, errInfo);
				return;
		    }
		    else
		    {
		    	String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
		    	JavaDemo.AddLogNew("SUCC", "NET_DVR_GET_VIDEOWALLWINDOWPOSITION", sDeviceIP, errInfo);
		    	struWindowPos.read();
		    }
	}
	/**
	 * Window roaming
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void SetWinPos(){
		int[] dwRetWinNum=new int[hCNetSDK.MAX_VM_WIN_NUM];
		HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION modify = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION();
		Pointer LpModify=modify.getPointer();
		modify.write();
		HCNetSDK.NET_DVR_IN_PARAM struInputPapam = new HCNetSDK.NET_DVR_IN_PARAM();
		Pointer lpStruInputPapam=struInputPapam.getPointer();
		struInputPapam.write();
		HCNetSDK.NET_DVR_OUT_PARAM struOutputPapam = new HCNetSDK.NET_DVR_OUT_PARAM();
		Pointer lpStruOutputPapam=struOutputPapam.getPointer();
		struOutputPapam.write();
		Pointer lpWinNumEx = null;
		//to do 
		int iModifyNum=4;
		struInputPapam.struCondBuf.pBuf = lpWinNumEx;
		struInputPapam.struCondBuf.nLen = iModifyNum * 4;
		struInputPapam.struInParamBuf.pBuf = LpModify;
		struInputPapam.struInParamBuf.nLen = iModifyNum * (new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION()).size();
		//struOutputPapam.lpStatusList = m_dwStatus;
		//struOutBuf.pBuf = dwRetWinNum;
		struOutputPapam.struOutBuf.nLen = iModifyNum * 4;
		if (false == hCNetSDK.NET_DVR_SetDeviceConfigEx(m_lUserID, HCNetSDK.NET_DVR_SET_VIDEOWALLWINDOWPOSITION, iModifyNum,lpStruInputPapam,lpStruOutputPapam))
		{
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_VIDEOWALLWINDOWPOSITION", null, errInfo);
			return;
		} 
		else
		{	
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_VIDEOWALLWINDOWPOSITION", null, errInfo);
			struOutputPapam.read();		
		}
			
		
	}
	/**
	 * Open a new window separately
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void SetWinPos(String sDeviceIP){
		
		HCNetSDK.INT_ARRAY pInt = new HCNetSDK.INT_ARRAY(1);
		Pointer lpInt=pInt.getPointer();
		pInt.iValue[0] = -1;
	    pInt.write();
	    HCNetSDK.WinNo strWinNo = new HCNetSDK.WinNo();
	    Pointer lpStrWinNo=strWinNo.getPointer();
	    strWinNo.winNo= 1<<24 |0<<16 | 0; 
	    HCNetSDK.RetWinNo strRetWinNo = new HCNetSDK.RetWinNo();
	    Pointer lpRetStrWinNo=strWinNo.getPointer();    
	    HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION struWindowPos = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION();
		Pointer LpStruWindowPos=struWindowPos.getPointer();
		struWindowPos.dwSize = struWindowPos.size();
		struWindowPos.byEnable = 1;
		struWindowPos.dwWindowNo = 1<<24;
		struWindowPos.struRect.dwXCoordinate = 0;
		struWindowPos.struRect.dwYCoordinate = 0; 
		struWindowPos.struRect.dwWidth = 1920; 
		struWindowPos.struRect.dwHeight = 1920; 
		HCNetSDK.NET_DVR_IN_PARAM struInputPapam = new HCNetSDK.NET_DVR_IN_PARAM();
		HCNetSDK.NET_DVR_OUT_PARAM struOutputPapam = new HCNetSDK.NET_DVR_OUT_PARAM();
		Pointer lpStruInputPapam = struInputPapam.getPointer();
		Pointer lpStruOutputPapam = struOutputPapam.getPointer();
		struInputPapam.struCondBuf.pBuf = lpStrWinNo;
		struInputPapam.struCondBuf.nLen = 4;
		struInputPapam.struInParamBuf.pBuf = LpStruWindowPos;
		struInputPapam.struInParamBuf.nLen = 1*(new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION()).size();
		struOutputPapam.lpStatusList = lpInt;
		struOutputPapam.struOutBuf.pBuf = lpRetStrWinNo;
		struOutputPapam.struOutBuf.nLen = 4;
		struInputPapam.write();
		struInputPapam.write();
		pInt.write();
		strWinNo.write();
		strRetWinNo.write();
		struWindowPos.write();
		if (false == hCNetSDK.NET_DVR_SetDeviceConfigEx(m_lUserID, HCNetSDK.NET_DVR_SET_VIDEOWALLWINDOWPOSITION, 1,
				lpStruInputPapam, lpStruOutputPapam)) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "SET_VIDEOWALLWINDOWPOSITION New rove", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to open window");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			return;
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "SET_VIDEOWALLWINDOWPOSITION New rove", sDeviceIP, errInfo);
			struOutputPapam.read();
			DialogMessage dlg = new DialogMessage("Window opened successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			
		}
	
	}
	/**
	 * Window top
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void WinTop(int dwChannelEx, String sDeviceIP) {
		HCNetSDK.DwWallNo dwWinNumEX = new HCNetSDK.DwWallNo();
		Pointer ipDwWinNumEX = dwWinNumEX.getPointer();	
		dwWinNumEX.write();
		dwWinNumEX.dwWallNo=1;

		if (false == hCNetSDK.NET_DVR_RemoteControl(m_lUserID, HCNetSDK.NET_DVR_SWITCH_WIN_TOP,ipDwWinNumEX,4)) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "Failed to Set Window Top", sDeviceIP, errInfo);
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "Success to Set Window Top", sDeviceIP, errInfo);
		}

	}
	/**
	 * Bottom window
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void WinBottom(int dwChannelEx, String sDeviceIP)
	{
		HCNetSDK.DwWallNo dwWinNumEX = new HCNetSDK.DwWallNo();
		dwWinNumEX.write();
		dwWinNumEX.dwWallNo=1;
		Pointer ipDwWinNumEX = dwWinNumEX.getPointer();	
		if (false == hCNetSDK.NET_DVR_RemoteControl(m_lUserID, HCNetSDK.NET_DVR_SWITCH_WIN_BOTTOM,ipDwWinNumEX,4)) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_SWITCH_WIN_BOTTOM", sDeviceIP, errInfo);
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_SWITCH_WIN_BOTTOM", sDeviceIP, errInfo);
		}
	}
	/**
	 * Close all windows
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void WinCloseAll(String sDeviceIP){

		HCNetSDK.DwWallNo dwWallNo = new HCNetSDK.DwWallNo();
		dwWallNo.dwWallNo = 1<<24;
		Pointer lpDwWallNo = dwWallNo.getPointer();	
		dwWallNo.write();
		
		if (false == hCNetSDK.NET_DVR_RemoteControl(m_lUserID, HCNetSDK.NET_DVR_VIDEOWALLWINDOW_CLOSEALL,lpDwWallNo,4)) 
		{
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_VIDEOWALLWINDOW_CLOSEALL", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Failed to close window");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "NET_DVR_VIDEOWALLWINDOW_CLOSEALL", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Close window successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		}
	}
	/**
	 * Dynamic decoding
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	
	public static void StartDynamic(HCNetSDK.NET_DVR_PU_STREAM_CFG_V41 struParam, int dwChannel, String sDeviceIP,HWND hWnd) {
		Pointer lpStruParam = struParam.getPointer();
		struParam.write();

		if (false == hCNetSDK.NET_DVR_MatrixStartDynamic_V41(m_lUserID, dwChannel, lpStruParam)) {
			Boolean flag = hCNetSDK.NET_DVR_SetLogToFile(3, "C:\\JAVALog", true);
			
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "faild to Start DynamcDecode", null, errInfo);
			DialogMessage dlg = new DialogMessage("Dynamic decoding failed");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			Preview(struParam, hWnd, sDeviceIP);
			return;
		}

		else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "success to Start DynamicDecode", null, errInfo);
			DialogMessage dlg = new DialogMessage("Dynamic decoding succeeded");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			Preview(struParam, hWnd, sDeviceIP);
		}
	}
	/**
	 * Stop dynamic decoding
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public static void StopDynamic(int dwChannelEx,String sDeviceIP){
		if (false == hCNetSDK.NET_DVR_MatrixStopDynamic(m_lUserID, dwChannelEx)) {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "Failed to Stop Dynamic Decode", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Stop dynamic decoding failed");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		} else {
			String errInfo = String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("SUCC", "Stop Dynamic Decode successfully", sDeviceIP, errInfo);
			DialogMessage dlg = new DialogMessage("Stop dynamic decoding successfully");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		}
	}
	/**
	 * Get window number decoding status
	 * 
	 * @param iDeviceIndex
	 * @return
	 */
	public void GetWinDecodeStatus(int dwChannelEx,String sDeviceIP){
		HCNetSDK.NET_DVR_WALLWIN_INFO struWallWinInfo = new HCNetSDK.NET_DVR_WALLWIN_INFO();
		HCNetSDK.NET_DVR_WALL_WIN_STATUS struWallWinStatus = new HCNetSDK.NET_DVR_WALL_WIN_STATUS();
		Pointer lpStruWallWinInfo = struWallWinInfo.getPointer();
		Pointer lpStruWallWinStatus = struWallWinStatus.getPointer();	
		struWallWinInfo.write();
		struWallWinStatus.write();
	    struWallWinInfo.dwWinNum = dwChannelEx &0xffff;
	    struWallWinInfo.dwSubWinNum = (dwChannelEx>>16)&0xff;
	    struWallWinInfo.dwWallNo = (dwChannelEx>>24)&0xff;
	    if (false == hCNetSDK.NET_DVR_GetDeviceStatus(m_lUserID, HCNetSDK.NET_DVR_MATRIX_GETWINSTATUS, 0, lpStruWallWinInfo, struWallWinInfo.size(), null, lpStruWallWinStatus, struWallWinStatus.size()))
	    {
	    	String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "SET_VIDEOWALLWINDOWPOSITION New rove", sDeviceIP, errInfo);
			return;
	    }
	    else
	    {
	    	String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
	    	JavaDemo.AddLogNew("SUCC", "SET_VIDEOWALLWINDOWPOSITION New rove", sDeviceIP, errInfo);
	    	struWallWinStatus.read();
	    }		
			
	}
	/**
	 * Preview
	 */
	public static void Preview(HCNetSDK.NET_DVR_PU_STREAM_CFG_V41 struParam, HWND hWnd,String sDeviceIP){
		NativeLong lRealPlayHandle;
		HCNetSDK.NET_DVR_PREVIEWINFO struPlayInfo = new HCNetSDK.NET_DVR_PREVIEWINFO();
		struPlayInfo.hPlayWnd = hWnd;
		switch (struParam.byStreamMode)
		{
		case 1://Streaming by IP or domain name
			struPlayInfo.lChannel = new NativeLong(struParam.uDecStreamMode.struDecStreamDev.struDevChanInfo.dwChannel + 1);
			struPlayInfo.dwStreamType = struParam.uDecStreamMode.struDecStreamDev.struDevChanInfo.byTransMode;
			struPlayInfo.dwLinkMode = struParam.uDecStreamMode.struDecStreamDev.struDevChanInfo.byTransProtocol;
			break;
		case 2://Streaming by URL
			struPlayInfo.lChannel = new NativeLong(struParam.uDecStreamMode.struUrlInfo.byChannel + 1);
			struPlayInfo.dwStreamType = 0;
			struPlayInfo.dwLinkMode = struParam.uDecStreamMode.struUrlInfo.byTransPortocol;
			break;
		case 3://Streaming to the device through dynamic domain name resolution
			struPlayInfo.lChannel = new NativeLong(struParam.uDecStreamMode.struDdnsDecInfo.struDdnsInfo.dwChannel + 1);
			struPlayInfo.dwStreamType = struParam.uDecStreamMode.struDdnsDecInfo.struDdnsInfo.byTransMode;
			struPlayInfo.dwLinkMode = struParam.uDecStreamMode.struDdnsDecInfo.struDdnsInfo.byTransProtocol;
			break;
		default:
			break;
		}
		//JavaDemo.arrShow[0][0].StartPlay(m_lUserID, struPlayInfo);
		/*lRealPlayHandle = hCNetSDK.NET_DVR_RealPlay_V40(m_lUserID, struPlayInfo, null, null);
		if (lRealPlayHandle.intValue() < 0)
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_RealPlay_V40", sDeviceIP, errInfo);
			return;
		}
		 else
		{
		   JavaDemo.AddLogNew("SUCC", "NET_DVR_RealPlay_V40", sDeviceIP, null);
		}	*/
	}

}
