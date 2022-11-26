package User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Database.DBHandlerUser;

public class SeeAvailiblity extends JFrame
{
	JLabel usernamelbl,carnolbl,usernametf,carnotf,avail, reserved,availtf, reservedtf;
	JButton back,availimg,reservedimg;
	DBHandlerUser objdb=new DBHandlerUser();
	public SeeAvailiblity(String username, String carno) 
	{
		setLayout(null);
		
			  setLayout(null);
			  
			  ImageIcon icnbg=new ImageIcon("car/1900698.jpg");
			  JLabel lblbg=new JLabel(icnbg);
			  lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			  add(lblbg);
			  
				Font f = new Font("Serif", Font.BOLD, 30);
				usernamelbl = new JLabel("User Name");
				usernamelbl.setBounds(150, 50, 150, 40);
				usernamelbl.setFont(f);
				usernamelbl.setForeground(Color.WHITE);
				lblbg.add(usernamelbl);
				
				carnolbl=new JLabel("Car No");
				carnolbl.setBounds(150, 150, 150, 40);
				carnolbl.setFont(f);
				carnolbl.setForeground(Color.WHITE);
				lblbg.add(carnolbl);
				
				usernametf = new JLabel(username);
				usernametf.setBounds(350, 50, 150, 30);
				usernametf.setFont(f);
				usernametf.setForeground(Color.WHITE);
				lblbg.add(usernametf);
				
				carnotf=new JLabel(carno);
				carnotf.setBounds(350, 150, 300, 30);
				carnotf.setFont(f);
				carnotf.setForeground(Color.WHITE);
				lblbg.add(carnotf);
				
				
				avail = new JLabel("Available Slots");
				avail.setFont(f);
				avail.setBounds(150, 250, 300, 40);
				avail.setForeground(Color.WHITE);
				lblbg.add(avail);

				reserved = new JLabel("Reserved Slots");
				reserved.setFont(f);
				reserved.setBounds(450, 250, 300, 40);
				reserved.setForeground(Color.WHITE);
				lblbg.add(reserved);
				
				ImageIcon availicn=new ImageIcon("images/availcaricon.png");
				availimg =new JButton(availicn);
				availimg.setBounds(180, 300, 135, 135);
				lblbg.add(availimg);

				ImageIcon reservedicn=new ImageIcon("images/reservedcaricon.png");
				reservedimg =new JButton(reservedicn);
				reservedimg.setBounds(470, 300, 135, 135);
				lblbg.add(reservedimg);

				int reservecount   = objdb.getAvailableCount();
				int availcount = 36 - reservecount;

				availtf = new JLabel();
				availtf.setFont(f);
				availtf.setBounds(240, 460, 40, 40);
				availtf.setText(String.valueOf(availcount));
				availtf.setForeground(Color.white);
				availtf.setBackground(Color.WHITE);
				lblbg.add(availtf);

				reservedtf = new JLabel();
				reservedtf.setFont(f);
				reservedtf.setBounds(500, 460, 40, 40);
				reservedtf.setText(String.valueOf(reservecount));
				reservedtf.setForeground(Color.white);
				reservedtf.setBackground(Color.WHITE);
				lblbg.add(reservedtf);
				
				ImageIcon backicn=new ImageIcon("images/images.png");
				back=new JButton(backicn);
				back.setFont(f);
				back.setBounds(700, 500, 120, 80);
				lblbg.add(back);
				
				back.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
						UserWelcome uWel=new UserWelcome(username, carno);
						dispose();
					}
				});
				
		setTitle("See Availiblity");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
