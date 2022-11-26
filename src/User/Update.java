package User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.DBHandlerUser;
import ValidationsJava.Validation;

public class Update extends JFrame {
	JPanel packinfo, goldinfo, silverinfo, bronzeinfo;
	TitledBorder uinfoborder, packborder, goldborder, silverborder, bronzeborder;
	JLabel fullnmlbl, phonenolbl, passwordlbl, carnolbl, SelectPack, stmt, validity1, validity2, validity3, charge1,
			charge2, charge3;
	JTextField fullnmtf, phonenotf, carnotf;
	JPasswordField passwordtf;
	JRadioButton gold, silver, bronze;
	ButtonGroup packs;
	JButton back, clear, update;
	DBHandlerUser objdb = new DBHandlerUser();
	Validation isValidUser=new Validation();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c = Calendar.getInstance();

	public Update(String username) 
	{
		setLayout(null);
		Font f = new Font("Serif", Font.BOLD, 30);

		ImageIcon icnbg=new ImageIcon("car/4327427.jpg");
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setFont(f);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
		phonenolbl = new JLabel("Phone no.");
		phonenolbl.setFont(f);
		phonenolbl.setForeground(Color.white);
		phonenolbl.setBounds(170, 130, 150, 40);
		lblbg.add(phonenolbl);

		passwordlbl = new JLabel("Password");
		passwordlbl.setFont(f);
		passwordlbl.setForeground(Color.white);
		passwordlbl.setBounds(170, 190, 150, 40);
		lblbg.add(passwordlbl);

		carnolbl = new JLabel("Car No.");
		carnolbl.setFont(f);
		carnolbl.setForeground(Color.white);
		carnolbl.setBounds(170, 250, 150, 40);
		lblbg.add(carnolbl);

		phonenotf = new JTextField();
		phonenotf.setBounds(400, 130, 120, 40);
		phonenotf.setText(objdb.getPhonenobyUserName(username));
		lblbg.add(phonenotf);

		passwordtf = new JPasswordField();
		passwordtf.setBounds(400, 190, 120, 40);
		passwordtf.setText(objdb.getuserPasswordbyUserName(username));
		lblbg.add(passwordtf);

		carnotf = new JTextField();
		carnotf.setBounds(400, 250, 120, 40);
		carnotf.setText(objdb.getCarnobyusername(username));
		lblbg.add(carnotf);
        
		ImageIcon icnupdate=new ImageIcon("car/button_update.png");
		update = new JButton(icnupdate);
		update.setBounds(620, 580, 120, 60);
		lblbg.add(update);
		
		LineBorder borderp=new LineBorder(Color.WHITE, 3, true);
		packborder=new TitledBorder(borderp, "PACK INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.WHITE);
		packinfo = new JPanel();
		packinfo.setBorder(packborder);
		packinfo.setLayout(null);
		packinfo.setBackground(Color.BLACK);
		packinfo.setBounds(30, 310, 750, 150);
		lblbg.add(packinfo);
        
		LineBorder bordergold=new LineBorder(Color.BLACK, 3, true);
		goldborder=new TitledBorder(bordergold, "GOLD USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK);
		goldinfo = new JPanel();
		goldinfo.setBorder(goldborder);
		goldinfo.setLayout(null);
		goldinfo.setBackground(Color.WHITE);
		goldinfo.setBounds(30, 30, 220, 100);
		packinfo.add(goldinfo);

		validity1 = new JLabel("Validity : 5 days");
		Font font = new Font("Calibri", Font.BOLD, 18);
		validity1.setFont(font);
		validity1.setBounds(20, 20, 200, 30);
		goldinfo.add(validity1);

		charge1 = new JLabel("Cost : 100 Rs");
		charge1.setFont(font);
		charge1.setBounds(20, 60, 200, 30);
		goldinfo.add(charge1);


//		LineBorder bordergold=new LineBorder(Color.BLACK, 3, true);
//		goldborder=new TitledBorder(bordergold, "GOLD USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK);
//		goldinfo = new JPanel();
//		goldinfo.setBorder(goldborder);
//		goldinfo.setLayout(null);
//		goldinfo.setBackground(Color.WHITE);
//		goldinfo.setBounds(30, 30, 220, 100);
//		packinfo.add(goldinfo);

		
		silverborder=new TitledBorder(bordergold, "SILVER USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK);
		silverinfo = new JPanel();
		silverinfo.setBorder(silverborder);
		silverinfo.setLayout(null);
		silverinfo.setBackground(Color.WHITE);
		silverinfo.setBounds(260, 30, 220, 100);
		packinfo.add(silverinfo);

		validity2 = new JLabel("Validity : 3 days");
		validity2.setFont(font);
		validity2.setBounds(20, 20, 200, 30);
		silverinfo.add(validity2);

		charge2 = new JLabel("Cost : 60 Rs");
		charge2.setFont(font);
		charge2.setBounds(20, 60, 200, 30);
		silverinfo.add(charge2);

		
		bronzeborder=new TitledBorder(bordergold, "BRONZE USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 16), Color.BLACK);
		bronzeinfo = new JPanel();
		bronzeinfo.setBorder(bronzeborder);
		bronzeinfo.setLayout(null);
		bronzeinfo.setBackground(Color.WHITE);
		bronzeinfo.setBounds(500, 30, 220, 100);
		packinfo.add(bronzeinfo);

		validity3 = new JLabel("Validity : 1 day");
		validity3.setFont(font);
		validity3.setBounds(20, 20, 200, 30);
		bronzeinfo.add(validity3);

		charge3 = new JLabel("Cost : 20 Rs");
		charge3.setFont(font);
		charge3.setBounds(20, 60, 200, 30);
		bronzeinfo.add(charge3);

		SelectPack = new JLabel("Select pack");
		SelectPack.setFont(f);
		SelectPack.setForeground(Color.WHITE);
		SelectPack.setBounds(30, 550, 150, 30);
		lblbg.add(SelectPack);

		gold = new JRadioButton("Gold");
		gold.setBounds(200, 550, 120, 30);
		gold.setBackground(Color.WHITE);
		gold.setFont(f);
		lblbg.add(gold);

		silver = new JRadioButton("Silver");
		silver.setBounds(340, 550, 120, 30);
		silver.setFont(f);
		silver.setBackground(Color.WHITE);
		lblbg.add(silver);

		bronze = new JRadioButton("Bronze");
		bronze.setBounds(480, 550, 120, 30);
		bronze.setFont(f);
		bronze.setBackground(Color.WHITE);
		bronze.setSelected(true);
		lblbg.add(bronze);

		packs = new ButtonGroup();
		packs.add(bronze);
		packs.add(gold);
		packs.add(silver);

		String packselected = objdb.getPackFromUserName(username);
		if (packselected.equals("Gold")) {
			gold.setSelected(true);
		} else if (packselected.equals("Silver")) {
			silver.setSelected(true);
		} else {
			bronze.setSelected(true);
		}

		
		update.addActionListener(new ActionListener() {
			String phone=phonenotf.getText();
			String str="(0/91)?[7-9][0-9]{9}";
			Pattern phoneno=Pattern.compile(str);
			Matcher matcher = phoneno.matcher(phone);  
			boolean b=matcher.matches();
			
			public void actionPerformed(ActionEvent arg0) 
			{  
				if(phone.isEmpty()==true || b==false)
				{                         
					JOptionPane.showMessageDialog(null, "Please Enter Valid Phone Number");
				}
				else if (!isValidUser.CarNoValidator(carnotf.getText())) {

				} else {
                   String pack;
			if (gold.isSelected()) {
				pack = "Gold";
				
			} else if (silver.isSelected()) {
				pack = "Silver";
			} else {
				pack = "Bronze";
			}
			
			switch(pack)
			{
			case "Gold":
			{
				c.setTime(new Date());
				c.setTime(new Date());// Using today's date
				String packStartDate = sdf.format(c.getTime());
				c.add(Calendar.DATE, 5); // Adding 5 days
				String packEndDate = sdf.format(c.getTime());
				System.out.println(packEndDate);
				DBHandlerUser db=new DBHandlerUser();
				String carid=objdb.getCaridFromusername(username);
				objdb.Updatelapandtracktozero(carid);				
   				objdb.updateCarinfobyusername(carnotf.getText(), pack,"booked", username,packStartDate,packEndDate);
				dispose();
				UserLogin loginpage=new UserLogin();
				break;
			}
			case "Silver":
			{
				c.setTime(new Date());
				c.setTime(new Date());// Using today's date
				String packStartDate = sdf.format(c.getTime());
				c.add(Calendar.DATE, 3); // Adding 3 days
				String packEndDate = sdf.format(c.getTime());
				System.out.println(packEndDate);
				String carid=objdb.getCaridFromusername(username);
				objdb.Updatelapandtracktozero(carid);
				objdb.updateCarinfobyusername(carnotf.getText(), pack,"booked", username,packStartDate,packEndDate);				
				dispose();
				UserLogin loginpage=new UserLogin();
				break;
			}
			case "Bronze":
			{
				c.setTime(new Date());
				c.setTime(new Date());// Using today's date
				String packStartDate = sdf.format(c.getTime());
				c.add(Calendar.DATE, 1); // Adding 1 day
				String packEndDate = sdf.format(c.getTime());
				System.out.println(packEndDate);
				String carid=objdb.getCaridFromusername(username);
				objdb.Updatelapandtracktozero(carid);
				objdb.updateCarinfobyusername(carnotf.getText(), pack,"booked", username,packStartDate,packEndDate);
				dispose();
				UserLogin loginpage=new UserLogin();
				break;
			}
			}}
   				
   			}
		});		
		this.repaint();
		// footer
		setVisible(true);
		setTitle("Update Info Page");
		setBounds(50, 0, 870, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

