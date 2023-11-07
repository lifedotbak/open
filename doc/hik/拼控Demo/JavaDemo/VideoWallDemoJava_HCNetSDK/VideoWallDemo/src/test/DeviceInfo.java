package test;

import com.sun.jna.NativeLong;

public class DeviceInfo
{
	private String m_sDVRIP;
	private short m_wDVRPort;
	private String m_sUserName;
	private String m_sPassword;
	private NativeLong m_nUserID;
	HCNetSDK.NET_DVR_DEVICEINFO_V30 m_lpDeviceInfo;
	

	
	DeviceInfo(String sDVRIP, short wDVRPort, String sUserName, String sPassword, HCNetSDK.NET_DVR_DEVICEINFO_V30 lpDeviceInfo,NativeLong nUserID)
	{
		m_sDVRIP=sDVRIP;
		m_wDVRPort=wDVRPort;
		m_sUserName=sUserName;
		m_sPassword=sPassword;
		m_nUserID=nUserID;
		m_lpDeviceInfo=lpDeviceInfo;
		
	}
	
	void SetNUserID(NativeLong nUserID)
	{
		m_nUserID=nUserID;
	}
	
	String GetIP()
	{
		return m_sDVRIP;
	}
	
	short GetPort()
	{
		return m_wDVRPort;
	}
	
	String GetUserName()
	{
		return m_sUserName;
	}
	
	String GetPassword()
	{
		return m_sPassword;
	}
		
	NativeLong GetNUserID()
	{
		return m_nUserID;
	}
	
	HCNetSDK.NET_DVR_DEVICEINFO_V30 GetlpDeviceInfo()
	{
		return m_lpDeviceInfo;
	}
		
	
}
