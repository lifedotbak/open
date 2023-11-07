package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameWall extends JFrame {

	private JPanel contentPane;
	//定义组件

	int size = 6;
	JPanel panel = new JPanel();
	//JButton jbs[] = new JButton[size];
	Panel jbs[] = new Panel[size];
	private JTextField row;
	private JTextField col;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameWall frame = new FrameWall();
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
	public FrameWall() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		row = new JTextField();
		row.setColumns(10);
		
		col = new JTextField();
		col.setColumns(10);
		
		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Set1();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(122)
					.addComponent(row, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(97)
					.addComponent(col, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(294, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(458, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(94))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(row, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(col, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(34)
							.addComponent(button)))
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 241, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
		//创建组件



		}
	
	public void Set()
	{
		for(int i = 0 ; i<6 ; i++){

			jbs[i] = new Panel();
			jbs[i].setBackground(Color.gray);

		}

		//设置网格布局(3,3)表示3行3列

		panel.setLayout(new GridLayout(2,3,10,10));

		//添加组件

		for(int i = 0 ; i < 6 ; i++){

			panel.add(jbs[i]);
			JButton lab = new JButton("我是一个标签") ; 
			panel.add(lab);
		}

		//设置窗体属性

		//显示窗体

		panel.setVisible(true);
		panel.revalidate();
		panel.repaint();
	}
	
	public void Set1()
	{
		Panel newPanel=new Panel();

		newPanel.setBounds(0, 0, panel.getWidth(), panel.getHeight());//设置newPanel的位置，必不可少

		newPanel.setVisible(true);
		panel.add(newPanel);
		panel.revalidate();
		panel.repaint();
	}
}
