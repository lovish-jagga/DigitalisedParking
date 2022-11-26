package Admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Common.Mainframe;
import Database.DBHandlerAdmin;

public class Admin extends JFrame
{
	/////////Only One Admin////////////////
	public Admin()
	{ 
		setLayout(null);
		ImageIcon imgbg=new ImageIcon("images/adminbg3.jpg");
		JLabel lblbg=new JLabel(imgbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);

		ImageIcon imgbg2=new ImageIcon("images/unnamed.jpg");
		JLabel lblbg2=new JLabel(imgbg2);
		lblbg2.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		lblbg.add(lblbg2);

		JLabel lblsignin=new JLabel("Admin Sign In");
		lblsignin.setFont(new Font("Serif",Font.BOLD,30));
		lblsignin.setForeground(Color.black);
		lblsignin.setBounds(590, 160, 200, 60);
		lblbg2.add(lblsignin);


		JLabel lblUnm=new JLabel("Admin Name");
		lblUnm.setFont(new Font("Serif",Font.BOLD,30));
		lblUnm.setForeground(Color.black);
		lblUnm.setBounds(500, 260, 180, 60);
		lblbg2.add(lblUnm);

		JTextField txtUnm=new JTextField();
		txtUnm.setBounds(680, 275, 180, 40);
		lblbg.add(txtUnm);

		JLabel lblPwd=new JLabel("Password");
		lblPwd.setFont(new Font("Serif",Font.BOLD,30));
		lblPwd.setForeground(Color.black);
		lblPwd.setBounds(500, 320, 180, 60);
		lblbg2.add(lblPwd);

		JPasswordField txtpwd=new JPasswordField();
		txtpwd.setBounds(680, 335, 180, 40);
		lblbg2.add(txtpwd);

		ImageIcon imgsignin=new ImageIcon("images/signinadmin.png");
		JButton btnsignin=new JButton(imgsignin);
		btnsignin.setBounds(502,420, 360, 40);
		btnsignin.setFont(new Font("Serif",Font.BOLD,30));
		lblbg2.add(btnsignin);



		ImageIcon iconback=new ImageIcon("images/images.png");
		JButton btnback=new JButton(iconback);
		btnback.setBounds(725,545,120,90);
		lblbg2.add(btnback);
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Mainframe frm=new Mainframe();
				dispose();
			}
		});

		btnsignin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				DBHandlerAdmin objDH=new DBHandlerAdmin();
				String strAdminName,strPwd;
				{
					strAdminName=txtUnm.getText();
					strPwd=String.valueOf(txtpwd.getPassword());
				 	setTitle(strAdminName+" "+ strPwd);
					if(strAdminName.isEmpty()==true)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Admin Name");
					}
					else if(strPwd.isEmpty()==true)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Password");
					}
					else
					{
						boolean admin=objDH.isValidAdmin(strAdminName, strPwd);
						if(admin==true)
						{
							AdminWelcome adWel=new AdminWelcome();
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Please Enter Valid Name and Password");
							setTitle("InValid Admin");
						}
					}
					}}});
	
		setTitle("Car Parking");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
//		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
