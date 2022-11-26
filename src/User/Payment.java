package User;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Database.DBHandlerUser;

public class Payment extends JFrame 
{
	JLabel[] lblBilling;
	JLabel lblbill;
	JButton paybill;
	JTextField txtusername,txtcarno,txtpackselected,txtpackstartdate,txtpackenddate,txtfixedcharge,txtimpposedfine,txttotalcharge;
	DBHandlerUser db=new DBHandlerUser();
	Font f=new Font("Serif",Font.BOLD,30);
	public Payment(String username, String carno) 
	{
		setLayout(null);
		ImageIcon icnbg=new ImageIcon("car/loginbg2.jpg");
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
		String carid=db.getCaridFromusername(username);
		String packstartdate=db.getPackStartDate(carid);
		String packenddate=db.getPackEndDate(carid);
		String pack=db.getPackFromUserName(username);
		int paymentpending=db.getPaymentPending(carid);
		
		ImageIcon icnbillpage=new ImageIcon("images/button_bill-page (1).png");
		lblbill=new JLabel(icnbillpage);
		lblbill.setFont(new Font("Serif",Font.BOLD,70));
		lblbill.setBounds(210,50,450,90);
		lblbg.add(lblbill);

		
		String strlbl[]= {"UserName","Car Number","Pack Selected","Start Date","End Date","Fixed Charge","Imposed Charge","Total Charge"};
		lblBilling=new JLabel[9]; 
		int xpos=200,ypos=150;
		for(int i=0;i<8;i++)
		{
			lblBilling[i]=new JLabel(strlbl[i]);
			lblBilling[i].setFont(f);
			lblBilling[i].setBounds(xpos,ypos,250,100);
			lblBilling[i].setForeground(Color.WHITE);
			lblbg.add(lblBilling[i]);
			ypos+=60;
		}
		
		txtusername=new JTextField();
		txtusername.setFont(f);
		txtusername.setBounds(450,187,220,35);
		txtusername.setText(username);
		txtusername.setEnabled(false);
		txtusername.setBackground(Color.BLACK);
		lblbg.add(txtusername);
		
		txtcarno=new JTextField();
		txtcarno.setFont(f); 
		txtcarno.setBounds(450,241,220,35);
		txtcarno.setText(carno);
		txtcarno.setEnabled(false);
		txtcarno.setBackground(Color.BLACK);
		lblbg.add(txtcarno);

		txtpackselected=new JTextField();
		txtpackselected.setFont(f); 
		txtpackselected.setBounds(450,298,220,35);
		txtpackselected.setText(pack);
		txtpackselected.setEnabled(false);
		txtpackselected.setBackground(Color.BLACK);
		lblbg.add(txtpackselected);

		txtpackstartdate =new JTextField();
		txtpackstartdate.setFont(f);
		txtpackstartdate.setBounds(450,355,220,35);
		txtpackstartdate.setText(packstartdate);
		txtpackstartdate.setEnabled(false);
		txtpackstartdate.setBackground(Color.BLACK);
		lblbg.add(txtpackstartdate);
		
		txtpackenddate =new JTextField();
		txtpackenddate.setFont(f);
		txtpackenddate.setBounds(450,420,220,35);
		txtpackenddate.setText(packenddate);
		txtpackenddate.setEnabled(false);
		txtpackenddate.setBackground(Color.BLACK);
		lblbg.add(txtpackenddate);

		txtfixedcharge =new JTextField();
		txtfixedcharge.setFont(f);
		txtfixedcharge.setBounds(450,480,220,35);
		txtfixedcharge.setText(String.valueOf(paymentpending));
		txtfixedcharge.setEnabled(false);
		txtfixedcharge.setBackground(Color.BLACK);
		lblbg.add(txtfixedcharge);
		
		int imposedfine=db.getImposedFine(username);
			txtimpposedfine =new JTextField();
			txtimpposedfine.setFont(f);
			txtimpposedfine.setBounds(450,540,220,35);
			txtimpposedfine.setText(String.valueOf(imposedfine));
			txtimpposedfine.setEnabled(false);
			txtimpposedfine.setBackground(Color.BLACK);
			lblbg.add(txtimpposedfine);
		System.out.println(imposedfine);
		
		int total=imposedfine+paymentpending;
		JTextField txttotal =new JTextField();
		txttotal.setFont(f);
		txttotal.setBounds(450,600,220,35);
		txttotal.setText(String.valueOf(total));
		txttotal.setEnabled(false);
		txttotal.setBackground(Color.BLACK);
		lblbg.add(txttotal);

		ImageIcon icn=new ImageIcon("images/button_pay-bill.png");
		paybill=new JButton(icn);
		paybill.setFont(f);
		paybill.setBounds(920,600,170,50);
		lblbg.add(paybill);
		paybill.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Thank you for using our digital parking system! Come back again");
				db.deleteUsersignup(username);
				db.deleteUserCarinfo(carid);
				db.deleteUserCarParking(carid);
				dispose();
			}
		});
		
		setVisible(true);
		setTitle("Payment Page");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(0);
	}
}
