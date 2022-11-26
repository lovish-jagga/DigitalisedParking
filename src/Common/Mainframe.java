package Common;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.tools.Tool;

import Admin.Admin;
import User.UserLogin;


public class Mainframe extends JFrame
{
	public Mainframe() 
	{
		setLayout(null);
		
		ImageIcon icnbg1=new ImageIcon("images/Mainframebg.jpg");
		JLabel lblbg1=new JLabel(icnbg1);
		lblbg1.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg1);
		
		ImageIcon icnbg=new ImageIcon("images/Mainframe.jpg");
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setBounds(-250,-70,1000,1000);
		lblbg1.add(lblbg);
		
		ImageIcon icnbg2=new ImageIcon("images/Mainframebg2.jpg");
		JLabel lblbg2=new JLabel(icnbg2);
		lblbg2.setBounds(320,200,1000,700);
		lblbg1.add(lblbg2);
		 
		ImageIcon icnWelcome=new ImageIcon("images/Mainframe_welcome-to-car-parking.png");
		JLabel lblWelcome=new JLabel(icnWelcome);
		lblWelcome.setFont(new Font("Serif",Font.BOLD,30));
		lblWelcome.setBounds(610,100,380,70);
		lblbg1.add(lblWelcome);
		
		ImageIcon icnbtnAdmin=new ImageIcon("images/Mainframe_button_admin.jpg");
		JButton btnAdmin=new JButton(icnbtnAdmin);
		btnAdmin.setFont(new Font("Serif",Font.BOLD,30));
		btnAdmin.setBounds(700,200,200,50);
		lblbg1.add(btnAdmin);
		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Admin obj=new Admin();
				dispose();
			}
		});
		
		
		ImageIcon icnbtnuser=new ImageIcon("images/Mainframe_button_user .jpg");
		JButton btnuser=new JButton(icnbtnuser);
		btnuser.setFont(new Font("Serif",Font.BOLD,30));
		btnuser.setBounds(710,300,170,50);
		lblbg1.add(btnuser);
		btnuser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				UserLogin ulogin=new UserLogin();
				dispose();
			}
		});
		
		setTitle("Car Parking");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}