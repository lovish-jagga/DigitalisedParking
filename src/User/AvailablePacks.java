package User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Common.Mainframe;
import Database.DBHandlerUser;

public class AvailablePacks extends JFrame
{	
	JPanel carinfo, packinfo, goldinfo, silverinfo, bronzeinfo;
	TitledBorder carborder, packborder, goldborder, silverborder, bronzeborder;
	JLabel lblcarid, carid,caridtf,carno, brand, SelectPack, stmt, validity1, validity2, validity3, charge1, charge2,
			charge3;
	JTextField  carnotf;
	JButton next, clear, back;
	JComboBox brandjcb;
	JRadioButton gold, silver, bronze;
	ButtonGroup packs;
	DBHandlerUser objdb=new DBHandlerUser();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Calendar c = Calendar.getInstance();

	public AvailablePacks(String strUserName)
	{
		setLayout(null);
			Font f = new Font("Serif",Font.BOLD,30);
			ImageIcon icnbg = new ImageIcon("car/1900815.jpg");
	        JLabel lblbg = new JLabel(icnbg);
	        lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	        add(lblbg);
			
			lblcarid=new JLabel("Car ID");
			lblcarid.setFont(f);
			lblcarid.setForeground(Color.WHITE);
			lblcarid.setBounds(250,60,140,40);
			lblbg.add(lblcarid);
			
			carno = new JLabel("Car No.");
			carno.setBounds(250, 120, 140, 40);
			carno.setFont(f);
			carno.setForeground(Color.white);
			lblbg.add(carno);

			brand = new JLabel("Car Brand");
			brand.setBounds(250, 180, 140, 40);
			brand.setFont(f);
			brand.setForeground(Color.white);
			lblbg.add(brand);
	
			caridtf=new JLabel();
			caridtf.setFont(f);
			caridtf.setText(String.valueOf(objdb.getUserIdByName(strUserName)));
			caridtf.setBounds(450, 60, 100, 40);
			caridtf.setForeground(Color.white);
			lblbg.add(caridtf);
			
			carnotf = new JTextField();
			carnotf.setBounds(450, 120, 150, 40);
			lblbg.add(carnotf);

			brandjcb = new JComboBox();
			String brands[] = { "Ferrari", "MG-Hector", "Mercedes-Benz", "Audi", "BMW", "Volkswagen", "Ford", "Hyundai",
					"Toyota", "Renault", "Nissan", "Honda" };
			for (String str : brands) {
				brandjcb.addItem(str);
			}
			brandjcb.setBounds(450, 180, 150, 40);
			lblbg.add(brandjcb);

			LineBorder borderp=new LineBorder(Color.BLACK, 3, true);
			packborder=new TitledBorder(borderp, "PACK INFO", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLACK);
			packinfo = new JPanel();
			packinfo.setBorder(packborder);
			packinfo.setLayout(null);
			packinfo.setBackground(Color.WHITE);
			packinfo.setBounds(50, 250, 750, 150);
			lblbg.add(packinfo);

			LineBorder bordergold=new LineBorder(Color.BLACK, 3, true);
			goldborder=new TitledBorder(bordergold, "GOLD USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLACK);
			goldinfo = new JPanel();
			goldinfo.setBorder(goldborder);
			goldinfo.setLayout(null);
			goldinfo.setBackground(Color.white);
			goldinfo.setBounds(50, 30, 220, 100);
			packinfo.add(goldinfo);

			validity1 = new JLabel("Validity : 5 days");
			Font font = new Font("Calibri", Font.BOLD, 18);
			validity1.setFont(font);
			validity1.setBounds(40, 20, 200, 30);
			goldinfo.add(validity1);

			charge1 = new JLabel("Cost : 80 Rs");
			charge1.setFont(font);
			charge1.setBounds(40, 60, 200, 30);
			goldinfo.add(charge1);

			LineBorder bordersilver=new LineBorder(Color.BLACK, 3, true);
			silverborder=new TitledBorder(bordersilver, "SILVER USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLACK);
			silverinfo = new JPanel();
			silverinfo.setBorder(silverborder);
			silverinfo.setLayout(null);
			silverinfo.setBackground(Color.white);
			silverinfo.setBounds(280, 30, 220, 100);
			packinfo.add(silverinfo);

			validity2 = new JLabel("Validity : 3 days");
			validity2.setFont(font);
			validity2.setBounds(40, 20, 200, 30);
			silverinfo.add(validity2);
	       
			charge2 = new JLabel("Cost : 50 Rs");
			charge2.setFont(font);
			charge2.setBounds(40, 60, 200, 30);
			silverinfo.add(charge2);

			LineBorder borderbronze=new LineBorder(Color.BLACK, 3, true);
			bronzeborder=new TitledBorder(borderbronze, "BRONZE USER", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 14), Color.BLACK);
			bronzeinfo = new JPanel();
			bronzeinfo.setBorder(bronzeborder);
			bronzeinfo.setLayout(null);
			bronzeinfo.setBackground(Color.white);
			bronzeinfo.setBounds(520, 30, 220, 100);
			packinfo.add(bronzeinfo);

			validity3 = new JLabel("Validity : 1 day");
			validity3.setFont(font);
			validity3.setBounds(40, 20, 200, 30);
			bronzeinfo.add(validity3);

			charge3 = new JLabel("Cost : 20 Rs");
			charge3.setFont(font);
			charge3.setBounds(40, 60, 200, 30);
			bronzeinfo.add(charge3);
			
			SelectPack = new JLabel("Select pack");
			SelectPack.setFont(f);
			SelectPack.setForeground(Color.WHITE);
			SelectPack.setBounds(50, 430, 200, 30);
			lblbg.add(SelectPack);

			gold = new JRadioButton("Gold");
			gold.setBounds(250, 430, 120, 30);
			gold.setFont(f);
			lblbg.add(gold);

			silver = new JRadioButton("Silver");
			silver.setBounds(450, 430, 120, 30);
			silver.setFont(f);
			lblbg.add(silver);

			bronze = new JRadioButton("Bronze");
			bronze.setBounds(650, 430, 120, 30);
			bronze.setFont(f);
			bronze.setSelected(true);
			lblbg.add(bronze);

			packs = new ButtonGroup();
			packs.add(bronze);
			packs.add(gold);
			packs.add(silver);

			ImageIcon icnnext=new ImageIcon("images/images(1).jpg");
			next = new JButton(icnnext);
			next.setFont(f);
			next.setBounds(630, 490, 120, 90);
			lblbg.add(next);
	         next.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					DBHandlerUser objdb=new DBHandlerUser();
					if(carnotf.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill out all the textfields.");
					}else if(objdb.UniqueCarNumber(carnotf.getText())) {
						JOptionPane.showMessageDialog(null, "please enter a unique carno.");
					}
					else if(objdb.CarNoValidator(carnotf.getText())==false) {
			
					}
					else 
					{
						
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
						objdb.insertInCarinfo(strUserName,Integer.parseInt(caridtf.getText()) , carnotf.getText(),packStartDate,packEndDate,pack);
						String carnumber=carnotf.getText();
						dispose();
						UserWelcome userwelcomepage=new UserWelcome(strUserName,carnumber);
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
						objdb.insertInCarinfo(strUserName,Integer.parseInt(caridtf.getText()) , carnotf.getText(),packStartDate,packEndDate,pack);
						String carnumber=carnotf.getText();
						dispose();
						UserWelcome userwelcomepage=new UserWelcome(strUserName,carnumber);
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
						objdb.insertInCarinfo(strUserName,Integer.parseInt(caridtf.getText()) , carnotf.getText(),packStartDate,packEndDate,pack);
						String carnumber=carnotf.getText();
						dispose();
						UserWelcome userwelcomepage=new UserWelcome(strUserName,carnumber);
						break;
					}
					}}}});

		
		ImageIcon iconback=new ImageIcon("images/images.png");
		JButton btnback=new JButton(iconback);
		btnback.setBounds(50,490,120,90);//630, 490, 120, 90
		lblbg.add(btnback);
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserLogin frm=new UserLogin();
				dispose();
			}
		});

		
		setTitle("Available Packs");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
