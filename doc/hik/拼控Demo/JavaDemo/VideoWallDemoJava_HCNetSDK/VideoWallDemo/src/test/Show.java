package test;
import javax.swing.JPanel;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.examples.win32.W32API.HWND;

import test.HCNetSDK;

import java.awt.Panel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class Show extends JPanel
{
	static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
	private boolean m_isPlaying;
	private boolean m_isFocus;
	private NativeLong m_lLogID;//logID
	private NativeLong m_lPreviewHandle; //priview handle
	private NativeLong m_lChannelNum; //channel num 
	
	private java.awt.Panel panelPlay;
	public Show() {
		setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(224, 5, 1, 1);
		add(panel);
		panel.setLayout(null);
		
		panelPlay = new Panel();
		panelPlay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				m_isFocus=true;
				panelPlay.setBackground(Color.CYAN);
			}
			@Override
			public void focusLost(FocusEvent e) {
				m_isFocus=false;
				panelPlay.setBackground(Color.lightGray);
			}
		});
		panelPlay.setBackground(Color.lightGray);
		panelPlay.setBounds(10, 5, 428, 266);
		add(panelPlay);
		GroupLayout gl_panelPlay = new GroupLayout(panelPlay);
		gl_panelPlay.setHorizontalGroup(
			gl_panelPlay.createParallelGroup(Alignment.LEADING)
				.addGap(0, 838, Short.MAX_VALUE)
		);
		gl_panelPlay.setVerticalGroup(
			gl_panelPlay.createParallelGroup(Alignment.LEADING)
				.addGap(0, 447, Short.MAX_VALUE)
		);
		panelPlay.setLayout(gl_panelPlay);
		m_isPlaying=false;
		m_isFocus=false;
		m_lChannelNum = new NativeLong(-1);
		m_lLogID = new NativeLong(-1);
	}
	
	void StopPlay()
	{
		if (m_isPlaying)
		{
			hCNetSDK.NET_DVR_StopRealPlay(m_lPreviewHandle);
			panelPlay.repaint();
			m_isPlaying = false;
		}
	}
	void StartPlay(NativeLong lUserId,HCNetSDK.NET_DVR_PREVIEWINFO struPreviewInfo)
	{
		if(m_isPlaying)
		{
			StopPlay();
		}
		m_lLogID=lUserId;
		m_isPlaying=true;
		HWND hwnd = new HWND(Native.getComponentPointer(panelPlay));
		struPreviewInfo.hPlayWnd = hwnd;
		m_lChannelNum = struPreviewInfo.lChannel;
		m_lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V40(lUserId, struPreviewInfo, null, null);		
		if (m_lPreviewHandle.intValue() < 0)
		{
			String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
			JavaDemo.AddLogNew("FAIL", "NET_DVR_RealPlay_V40", null, errInfo);
			DialogMessage dlg = new DialogMessage("预览失败");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
			return;
		}
		 else
		{
		   String errInfo=String.format("err[%d]", hCNetSDK.NET_DVR_GetLastError());
		   JavaDemo.AddLogNew("SUCC", "NET_DVR_RealPlay_V40", null, errInfo);
			DialogMessage dlg = new DialogMessage("预览成功");
			dlg.setBounds(780,500, 400, 200);
			dlg.setVisible(true);
		}	
	}
	
	boolean GetFocus()
	{
		return m_isFocus;
	}
	
	void SetFocus()
	{
		m_isFocus = true;
		panelPlay.setBackground(Color.lightGray);
	}
	
	NativeLong GetID()
	{
		return m_lLogID;
	}
	
	boolean GetIsPlaying()
	{
		return m_isPlaying;
	}
	
	NativeLong GetPreviewHanedle()
	{
		return m_lPreviewHandle;
	}	
	
	NativeLong GetChannelNum()
	{
		return m_lChannelNum;
	}

}
