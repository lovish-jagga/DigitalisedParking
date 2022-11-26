package User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Database.DBHandlerAdmin;
import Database.DBHandlerUser;

public class UserSignUp extends JFrame
{
	public UserSignUp()
	{
		setLayout(null);
		
		ImageIcon icnbg=new ImageIcon("car/signinbg.jpg");	
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
		
		JLabel lblUnm=new JLabel("User Name");
		lblUnm.setFont(new Font("Serif",Font.BOLD,30));
		lblUnm.setForeground(Color.WHITE);
		lblUnm.setBounds(150,100,200, 50);
		lblbg.add(lblUnm);
		
		
		JTextField txtunm=new JTextField();
		txtunm.setFont(new Font("Serif",Font.BOLD,30));
		txtunm.setBounds(450, 105, 200, 30);
		lblbg.add(txtunm);
		
		JLabel lblPassword=new JLabel("Password");
		lblPassword.setFont(new Font("Serif",Font.BOLD,30));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(150,200,200, 50);
		lblbg.add(lblPassword);
		
		JPasswordField txtpwd=new JPasswordField();
		txtpwd.setBounds(450, 205, 200, 30);
		lblbg.add(txtpwd);

		JLabel lblRadio=new JLabel("Gender");
		lblRadio.setFont(new Font("Serif",Font.BOLD,30));
		lblRadio.setForeground(Color.WHITE);
		lblRadio.setBounds(150,300,200,50);
		lblbg.add(lblRadio);
		
		JRadioButton rdbMale=new JRadioButton("Male");
		rdbMale.setFont(new Font("Serif",Font.BOLD,30));
		rdbMale.setBounds(450,300,100,40);
		lblbg.add(rdbMale);
		JRadioButton rdbFemale=new JRadioButton("Female");
		rdbFemale.setFont(new Font("Serif",Font.BOLD,30));
		rdbFemale.setBounds(600,300,130,40);
		lblbg.add(rdbFemale);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(rdbFemale);
		grp.add(rdbMale);
		
		JLabel lblphone=new JLabel("Phone Number");
		lblphone.setFont(new Font("Serif",Font.BOLD,30));
		lblphone.setForeground(Color.WHITE);
		lblphone.setBounds(150,400,200,50);
		lblbg.add(lblphone);
		
		JTextField txtphone=new JTextField();
		txtphone.setFont(new Font("Serif",Font.BOLD,30));
		txtphone.setBounds(450,400,350,40);
		lblbg.add(txtphone);
		
		JLabel lbladdress=new JLabel("Address");
		lbladdress.setFont(new Font("Serif",Font.BOLD,30));
		lbladdress.setForeground(Color.WHITE);
		lbladdress.setBounds(150,500,200,50);
		lblbg.add(lbladdress);
		
		JTextArea txtAddress=new JTextArea();
		JScrollPane address=new JScrollPane(txtAddress);
		address.setFont(new Font("Serif",Font.BOLD,30));
		address.setBounds(450,500,200,100);
		lblbg.add(address);
		
		ImageIcon imgsignup=new ImageIcon("images/button_sign-up (4).png");
		JButton btnSignup=new JButton(imgsignup);
		btnSignup.setFont(new Font("Serif",Font.BOLD,30));
		btnSignup.setBounds(750,600,150,40);
		lblbg.add(btnSignup);
		btnSignup.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				DBHandlerUser obUser=new DBHandlerUser();
				String strUnm,strPassword,strPhoneno,strAddress;
				int strpid;
				String strTable="UserSignUp";
				String strColumnName="UserId";
				String strGender = null;
				{
					strpid=obUser.getMaxId(strTable, strColumnName);
					strUnm=txtunm.getText();
					System.out.println(strUnm);
					strPassword=String.valueOf(txtpwd.getPassword());
					System.out.println("here");
					if(rdbMale.isSelected())
					{
						strGender="Male";
					}
					else if(rdbFemale.isSelected())
					{
						strGender="Female";
					}
					strPhoneno=txtphone.getText();
					
					strAddress=txtAddress.getText();
					System.out.println(strpid+" "+strUnm+" "+strPassword+" "+strGender+" "+strPhoneno+" "+strAddress);
		
					String phone=txtphone.getText();
					String str="(0/91)?[7-9][0-9]{9}";
					Pattern phoneno=Pattern.compile(str);
					Matcher matcher = phoneno.matcher(phone);  
					boolean b=matcher.matches();
					if(strUnm.isEmpty()==true)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Username");
					}
					else if(strPassword.isEmpty()==true)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Password");
					}
					else if(strPhoneno.isEmpty()==true || b==false)
					{                         
						JOptionPane.showMessageDialog(null, "Please Enter Valid Phone Number");
					}
					else if(strAddress.isEmpty()==true)
					{
						JOptionPane.showMessageDialog(null, "Please Enter Address");
					}
					else
					{					
					obUser.insertIntoUserSignUp( strpid,strUnm, strPassword, strGender, phone, strAddress);
					UserLogin ulogin=new UserLogin();
					dispose();
					}
				}
			}
		});

		ImageIcon iconback=new ImageIcon("images/images.png");
		JButton btnback=new JButton(iconback);
		btnback.setBounds(1100,550,120,90);
		lblbg.add(btnback);
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserLogin ulogin=new UserLogin();
				dispose();
			}
		});

		
		
		
		setTitle("User Sign Up");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
