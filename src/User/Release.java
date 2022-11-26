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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.DBHandlerUser;
import Table.JTables;
public class Release extends JFrame {
	JPanel park;
	TitledBorder parkborder;
	JLabel usernamelbl,carnolbl,usernametf,carnotf,doplbl,doptf,parktable;
	JButton back,payment;
	JTable ptable;
    JScrollPane ptablesp;
	DBHandlerUser objdb=new DBHandlerUser();
	
	public Release(String username,String carno) {
	  setLayout(null);
	  	
	  	ImageIcon icnbg=new ImageIcon("car/bmwi8-1643713872237-6296.jpg");
	  	JLabel lblbg=new JLabel(icnbg);
	  	lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	  	add(lblbg);
	  	
		Font f = new Font("Serif", Font.PLAIN, 40);
		LineBorder border=new LineBorder(Color.white, 4, true);
		usernamelbl = new JLabel("User Name");
		usernamelbl.setBounds(170, 30, 200, 50);
		usernamelbl.setFont(f);
		usernamelbl.setForeground(Color.white);
		lblbg.add(usernamelbl);
		
		carnolbl=new JLabel("Car No");
		carnolbl.setBounds(170, 120, 200, 50);
		carnolbl.setFont(f);
		carnolbl.setForeground(Color.white);
		lblbg.add(carnolbl);
	     
		usernametf = new JLabel(username);
		usernametf.setBounds(500, 30, 200, 50);
		usernametf.setFont(f);
		usernametf.setForeground(Color.white);
		lblbg.add(usernametf);
	
		carnotf=new JLabel(carno);
		carnotf.setBounds(500, 130, 400, 30);
		carnotf.setFont(f);
		carnotf.setForeground(Color.white);
		lblbg.add(carnotf);                
        parktable = new JLabel("Park Table -: ");
		parktable.setBounds(170, 220, 250, 40);
		parktable.setFont(f);
		parktable.setForeground(Color.white);
		lblbg.add(parktable);
	
		String carid=objdb.getCaridFromusername(username);
		JTables objTable=new JTables();
        ptable=objTable.getParkedTable(carid);
        ptablesp=new JScrollPane(ptable);
  	    ptablesp.setBounds(500, 205, 600, 100);
  	    lblbg.add(ptablesp);
		 ImageIcon paymenticn=new ImageIcon("car/button_payment (3).png");
		payment=new JButton(paymenticn);
		payment.setFont(f);
		payment.setBounds(1050, 400, 200, 60);
		lblbg.add(payment);
		payment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("button clicked");
				String carid=objdb.getCaridFromusername(username);
				System.out.println(carid);
				String pack=objdb.getPackFromUserName(username);
				System.out.println(pack);
				if(pack.equals("Bronze"))
				{
					System.out.println(pack);
					objdb.UpdatePaymentPendingBronze(carid);
					Payment pay=new Payment(username, carno);
					dispose();
				}
				else if(pack.equals("Silver"))
				{
					objdb.UpdatePaymentPendingSilver(carid);
					Payment pay=new Payment(username, carno);
					dispose();
				}
				else if(pack.equals("Gold"))
				{
					objdb.UpdatePaymentPendingGold(carid);
					Payment pay=new Payment(username, carno);
					dispose();
				}
				
			}
		});		
		setVisible(true);
		setTitle("Release Page");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(0);
  }

}
