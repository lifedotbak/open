package test;

import com.sun.jna.NativeLong;
import com.sun.jna.ptr.IntByReference;

public class WinPosBM {
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private NativeLong m_lUserID;
	
	private int m_dwMax;
	private int m_dwResourceMax;
	private int m_dwNum;
	private int m_dwModifyNum;
	private int m_dwRetNum;
	private int m_dwWallNo;
	IntByReference m_lpModifyRecord = new IntByReference();
	
	HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION m_lpSrcAddr = new HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION();
	
	public boolean Init(HCNetSDK.NET_DVR_VIDEOWALLWINDOWPOSITION lpSrcAddr,int dwMax)
	{
	    if ( lpSrcAddr == null ||dwMax == 0)
	    {
	        return false; 
	    }
	    m_lpSrcAddr = lpSrcAddr; 
	    m_dwResourceMax = dwMax; 
	    m_dwModifyNum = 0; 
	    m_dwNum = 0; 
	    m_dwMax = dwMax; 
	    return true; 
	}

}
