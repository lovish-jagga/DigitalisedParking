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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.DBHandlerUser;

public class UserWelcome extends JFrame
{	
	JPanel titlelogo, userinfo, location, operations;
	TitledBorder infoborder, locborder, opborder;
	JLabel usernamelbl, carnolbl, usernametf, carnotf, lapnolbl, tracknolbl, lapnotf, tracknotf, tagline1, tagline2;
	JButton logout, park, release, seeavail;
	DBHandlerUser objdb = new DBHandlerUser();
	int lapno, trackno;

	
	
	public UserWelcome(String username, String carno)
	{
		System.out.println(username+" "+carno);
		setLayout(null);
		
		ImageIcon imgbg=new ImageIcon("images/userloginbg1.jpg");
		JLabel lblbg=new JLabel(imgbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
			Font f = new Font("Serif", Font.ITALIC, 70);
			String carid=objdb.getCaridFromusername(username);
			tagline1 = new JLabel("WELCOME TO Car Parking");
			tagline1.setFont(f);
			tagline1.setForeground(Color.WHITE);
			tagline1.setBounds(50, 60, 900, 90);
			lblbg.add(tagline1);

			LineBorder borderinfo = new LineBorder(Color.white, 3, true);
			infoborder = new TitledBorder(borderinfo, " USER INFO ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
					new Font("Arial", Font.BOLD, 24), Color.WHITE);
			userinfo = new JPanel();
			userinfo.setLayout(null);
			userinfo.setBackground(new Color(0,0,0));
			userinfo.setBounds(30, 200, 300, 140);
			userinfo.setBorder(infoborder);
			lblbg.add(userinfo);

			usernamelbl = new JLabel("User Name");
			Font f1 = new Font("Calibri", Font.PLAIN, 18);
			usernamelbl.setBounds(20, 20, 110, 40);
			usernamelbl.setFont(f1);
			usernamelbl.setForeground(Color.WHITE);
			userinfo.add(usernamelbl);

			carnolbl = new JLabel("Car No.");
			carnolbl.setBounds(20, 80, 110, 40);
			carnolbl.setFont(f1);
			carnolbl.setForeground(Color.WHITE);
			userinfo.add(carnolbl);

			usernametf = new JLabel();
			usernametf.setText(username);
			usernametf.setBounds(160, 20, 120, 40);
			usernametf.setForeground(Color.WHITE);
			usernametf.setFont(f1);
			userinfo.add(usernametf);

			carnotf = new JLabel();
			carnotf.setBounds(160, 80, 120, 40);
			carnotf.setFont(f1);
			carnotf.setText(carno);
			carnotf.setForeground(Color.WHITE);
			userinfo.add(carnotf);

			// creation of loc panel along with locborder
			LineBorder border = new LineBorder(Color.WHITE, 3, true);
			locborder = new TitledBorder(border, " LOCATION ", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
					new Font("Arial", Font.BOLD, 24), Color.WHITE);
			location = new JPanel();
			location.setLayout(null);
			location.setBounds(30, 400, 300, 140);
			location.setBackground(new Color(0,0,0));
			location.setBorder(locborder);
			lblbg.add(location);

			// content inside loc panel
			lapnolbl = new JLabel("Lap no.");
			lapnolbl.setBounds(20, 20, 110, 40);
			lapnolbl.setFont(f1);
			lapnolbl.setForeground(Color.WHITE);
			location.add(lapnolbl);

			tracknolbl = new JLabel("Track no.");
			tracknolbl.setBounds(20, 80, 110, 40);
			tracknolbl.setFont(f1);
			tracknolbl.setForeground(Color.WHITE);
			location.add(tracknolbl);

			lapnotf = new JLabel("lap");
			lapnotf.setBounds(160, 20, 120, 40);
			lapnotf.setFont(f1);
			lapnotf.setForeground(Color.WHITE);
			location.add(lapnotf);

			tracknotf = new JLabel();
			tracknotf.setBounds(160, 80, 120, 40);
			tracknotf.setFont(f1);
			tracknotf.setForeground(Color.WHITE);
			location.add(tracknotf);

			
			if (objdb.verifyneworexistinguser(carid)) 
			{
				System.out.println("here inside verify");
				
				lapno = objdb.getLapOfLoginUser(carid);
				trackno = objdb.getTrackOfLoginUser(carid);
				System.out.println(lapno);
				System.out.println(trackno);
			} 

			lapnotf.setText(String.valueOf(lapno));
			tracknotf.setText(String.valueOf(trackno));

			// buttons within operation
			LineBorder borderop = new LineBorder(Color.WHITE, 3, true);
			opborder = new TitledBorder(borderop, "OPERATIONS", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION,
					new Font("Arial", Font.BOLD, 24), Color.WHITE);
			operations = new JPanel();
			operations.setLayout(null);
			operations.setBounds(430, 190, 270, 250);
			operations.setBorder(opborder);
			operations.setBackground(new Color(0,0,0));
			lblbg.add(operations);
//			ImageIcon icnpark = new ImageIcon("Images/park.jpg");
			ImageIcon icnparkyourcar=new ImageIcon("images/parkyourcar.png");
			park = new JButton(icnparkyourcar);
			park.setBounds(30, 50,220,40);
			park.setFont(f1);
			operations.add(park);
			park.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Park p1=new Park(username, carno);
					dispose();
				}
			});
			
			ImageIcon icnseeavail = new ImageIcon("images/seeavailiblity.png");
			seeavail = new JButton(icnseeavail);
			seeavail.setBounds(30, 120, 220, 40);
			seeavail.setFont(f1);
			operations.add(seeavail);
			seeavail.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SeeAvailiblity seeavail = new SeeAvailiblity(username, carno);
					dispose();
				}
			});

			ImageIcon icnreleasecar = new ImageIcon("images/releasecar.png");
			release = new JButton(icnreleasecar);
			release.setBounds(30, 190, 220, 40);
			operations.add(release);
			release.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int value = JOptionPane.showConfirmDialog(null, "Are you sure you want to release?", "Confirmation",
							JOptionPane.YES_NO_OPTION);
					if (value == JOptionPane.YES_OPTION) {
						Release release = new Release(username, carno);
						dispose();
					} else {

					}
				}
			});
			String carstatus;
			carstatus = objdb.getCarStatusFromCarno(carno);
			if (carstatus.equals("parked")) 
			{
				park.setEnabled(false);
				
			} 
			else if(carstatus.equals("booked"))
			{
				release.setEnabled(false);
			}

			ImageIcon icnlogout = new ImageIcon("images/button_logout.png");
			logout = new JButton(icnlogout);
			logout.setFont(f1);
			logout.setBounds(340, 600, 150, 40);
			lblbg.add(logout);
			logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					UserLogin ulogin=new UserLogin();
					dispose();
					
				}
			});
		
		
		
		
		setTitle("User Welcome Window");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
