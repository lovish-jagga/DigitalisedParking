package User;

import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Admin.AdminWelcome;
import Common.Mainframe;
import Database.DBHandlerAdmin;
import Database.DBHandlerUser;

public class UserLogin extends JFrame
{
	int count=0;
	DBHandlerUser objdb=new DBHandlerUser();
	public UserLogin()
	{
		setLayout(null);
		ImageIcon imgbg=new ImageIcon("images/userloginbg1.jpg");
		JLabel lblbg=new JLabel(imgbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);

		ImageIcon imgbg2=new ImageIcon("images/unnamed.jpg");
		JLabel lblbg2=new JLabel(imgbg2);
		lblbg2.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		lblbg.add(lblbg2);

		JLabel lblsignin=new JLabel("User Sign In");
		lblsignin.setFont(new Font("Serif",Font.BOLD,30));
		lblsignin.setForeground(Color.BLACK);
		lblsignin.setBounds(590, 180, 200, 60);
		lblbg2.add(lblsignin);

		JLabel lblUnm=new JLabel("User Name");
		lblUnm.setFont(new Font("Serif",Font.BOLD,30));
		lblUnm.setForeground(Color.BLACK);
		lblUnm.setBounds(500, 260, 180, 60);
		lblbg2.add(lblUnm);

		JTextField txtUnm=new JTextField();
		txtUnm.setBounds(680, 275, 180, 40);
		lblbg.add(txtUnm);

		JLabel lblPwd=new JLabel("Password");
		lblPwd.setFont(new Font("Serif",Font.BOLD,30));
		lblPwd.setForeground(Color.BLACK);
		lblPwd.setBounds(500, 320, 180, 60);
		lblbg2.add(lblPwd);

		JPasswordField txtpwd=new JPasswordField();
		txtpwd.setBounds(680, 335, 180, 40);
		lblbg2.add(txtpwd);

		ImageIcon imgsignin=new ImageIcon("images/Signbg1.png");
		JButton btnsignin=new JButton(imgsignin);
		btnsignin.setBounds(502,420, 360, 40);
		btnsignin.setFont(new Font("Serif",Font.BOLD,30));
		lblbg2.add(btnsignin);
		ImageIcon imgsignUp=new ImageIcon("images/Createnewaccountbg.png");
		JButton btnsingup=new JButton(imgsignUp);
		btnsingup.setBounds(502,480,360,40);
		lblbg2.add(btnsingup);

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

		
		btnsignin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				System.out.println("here");
				String username = txtUnm.getText();
				String userpassword = String.valueOf(txtpwd.getPassword());
				String carstatus=objdb.getCarStatusFromCarno(username);
				System.out.println(carstatus);
				String carno=objdb.getCarnobyusername(username);
				System.out.println(carno);
				 String carid,strStartDate,strEndDate;
				 carid=objdb.getCaridFromusername(username);
				 System.out.println(carid);
				 strStartDate=objdb.getPackStartDate(carid);
				 strEndDate=objdb.getPackEndDate(carid);
				 System.out.println(strStartDate);
				 System.out.println(strEndDate);
						if(objdb.isValidUser(username, userpassword)==false) 
						{
							if(count>=2)
							{
								JOptionPane.showMessageDialog(null, "Please SignUp again! Sorry,but we are enable to find you");
								UserSignUp signup=new UserSignUp();		
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Invalid User");
								count++;
							}
						} 
					 	else if (objdb.getBlacklistStatus(username)) 
						{
					 		System.out.println("here");
							JOptionPane.showMessageDialog(null, "You are blacklist.Contact admin for further info.");
						} 
						 else if (objdb.existingCustomer(carno))
						 {
							 System.out.println("inside existing customer");
							 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								Calendar c = Calendar.getInstance();
								c.setTime(new Date());
								c.setTime(new Date());// Using today's date
								String TodaysDate = sdf.format(c.getTime());
								c.add(Calendar.DATE, 0); // Adding 3 days
//								String packEndDate = sdf.format(c.getTime());
								System.out.println("this is todays date " +TodaysDate);

							 try
							 {
								 System.out.println("Inside Existing Customer");
							 if(sdf.parse(strEndDate).after(sdf.parse(TodaysDate)))
							 {
								 System.out.println("Inside welcome");
							 System.out.println(TodaysDate);
							 System.out.println(strEndDate);
							 UserWelcome uwel=new UserWelcome(username, carno);
							 dispose();
							 }
							 else if(sdf.parse(strEndDate).before(sdf.parse(TodaysDate)))
							 {	
								 System.out.println("inside update");
								 System.out.println(TodaysDate);
								 System.out.println(strEndDate);
								 JOptionPane.showMessageDialog(null, "Your Previous Pack has Been Expired,Please Select a new pack");
								 Update update=new Update(username);
								 dispose();
							 }
							 else if(sdf.parse(TodaysDate).equals(sdf.parse(strEndDate)))
							 {
								 UserWelcome uWel=new UserWelcome(username, carno);
							 }
							 }
							 catch(Exception e)
							 {
								 e.printStackTrace();
							 }
						 }
						else
							{
							 if(objdb.getCarStatusFromCarno(carno)==null)
								{
									JOptionPane.showMessageDialog(null, "Please Select a pack"); 
									AvailablePacks avpacks=new AvailablePacks(username); 
									dispose();
								}
							 System.out.println("Inside else");
							 //date condition
							}
							 }});

		btnsingup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserSignUp usignup=new UserSignUp();
			}
		});
		
		setTitle("User Login Frame");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
