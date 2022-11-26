package Admin;

import java.awt.Color;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Common.Mainframe;
import Database.DBHandlerAdmin;
import Database.DBHandlerUser;

public class AdminWelcome extends JFrame
{
	DBHandlerAdmin db=new DBHandlerAdmin();
	DBHandlerUser dbuser=new DBHandlerUser();
	JTable Admintable;
	Font f=new Font("Serif",Font.BOLD,20);
	JScrollPane jscroll;
	public AdminWelcome()
	{
		setLayout(null);
		
		ImageIcon icnbg=new ImageIcon("images/2920116.jpg");
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
		Admintable=db.getAdminTable();
		
		jscroll=new JScrollPane(Admintable);
		jscroll.setBounds(100, 30,1020,160);
		lblbg.add(jscroll);
		
		
		ImageIcon iconback=new ImageIcon("images/button_logout (2).png");
		JButton btnback=new JButton(iconback);
		btnback.setBounds(1150,100,150,40);
		lblbg.add(btnback);
		
		JButton btnimposefine=new JButton("OK");
		btnimposefine.setBounds(1110,250,80,40);
		lblbg.add(btnimposefine);
				
		ImageIcon icnblockuser=new ImageIcon("images/block.png");
		JButton btnblockuser=new JButton(icnblockuser);
		btnblockuser.setBounds(850,350,240,40);
		lblbg.add(btnblockuser);
		
		ImageIcon icnunblockuser=new ImageIcon("images/unblock.png");
		JButton btnunblockuser=new JButton(icnunblockuser);
		btnunblockuser.setBounds(850,450,240,40);
		lblbg.add(btnunblockuser);
		
		ImageIcon icnregisteredusers=new ImageIcon("images/registereduser.png");
		JButton btnregistereduser=new JButton(icnregisteredusers);
		btnregistereduser.setBounds(700,550,240,40);
		lblbg.add(btnregistereduser);
		
		JTextField txtregisteredusers=new JTextField();
		txtregisteredusers.setBounds(950,550,50,40);
		txtregisteredusers.setFont(new Font("Serif",Font.BOLD,40));
		 

		
		btnregistereduser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String strAcmd=e.getActionCommand();
				int registereduser=db.getRegisteredUserCount();
				lblbg.add(txtregisteredusers);
				txtregisteredusers.setText(String.valueOf(registereduser));
				txtregisteredusers.setAlignmentX(CENTER_ALIGNMENT);
				txtregisteredusers.setDisabledTextColor(Color.BLACK);
			}
		});
		
		
		
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Mainframe frm=new Mainframe();
				dispose();
			}
		});
		
		//400,220,250,20                 "images/images.png"
		ImageIcon icnimposefine=new ImageIcon("images/impose.png");
		JLabel lblimposefine=new JLabel(icnimposefine);
		lblimposefine.setBounds(700,250,240,40);
		lblbg.add(lblimposefine);
		
		JTextField txtimposefine=new JTextField();
		txtimposefine.setBounds(950,250,150,40);
		lblbg.add(txtimposefine);
		
		

		String strlbl[]= {"USERNAME","CARNO","PACK","PACKSTARTDATE","PACKENDDATE","LAPNO","TRACKNO","CARSTATUS","BLACKLISTED","IMPOSECHARGE"};
		JLabel[] lblAdminInfo=new JLabel[10]; 
		int xpos=50,ypos=150;
		for(int i=0;i<10;i++)
		{
			lblAdminInfo[i]=new JLabel(strlbl[i]);
			lblAdminInfo[i].setFont(f);
			lblAdminInfo[i].setForeground(Color.WHITE);
			lblAdminInfo[i].setBounds(xpos,ypos,250,150);
			lblbg.add(lblAdminInfo[i]);
			ypos+=50;
		}
		
		Admintable.addMouseListener(new MouseListener() {	
			@Override
			public void mouseReleased(MouseEvent e) {
			}			
			@Override
			public void mousePressed(MouseEvent e) 
			{
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub				
			}
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int row=Admintable.rowAtPoint(e.getPoint());
				String username=Admintable.getValueAt(row,0).toString();
				String carno=Admintable.getValueAt(row,1).toString();
				String pack=Admintable.getValueAt(row,2).toString();
				String packStartDate=Admintable.getValueAt(row,3).toString();
				String packEndDate=Admintable.getValueAt(row,4).toString();
				String lapno=Admintable.getValueAt(row,5).toString();
				String trackno=Admintable.getValueAt(row,6).toString();
				String carstatus=Admintable.getValueAt(row, 7).toString();
				String blackliststatus=Admintable.getValueAt(row,8).toString();
				String imposedcharge=Admintable.getValueAt(row,9).toString();

				
				
				JTextField txtusername=new JTextField();
				txtusername.setFont(f);
				txtusername.setText(username);
				txtusername.setEditable(false);
				txtusername.setBounds(400,220,250,20);
				lblbg.add(txtusername);
				
				JTextField txtcarno=new JTextField();
				txtcarno.setFont(f);
				txtcarno.setText(carno);
				txtcarno.setEditable(false);
				txtcarno.setBounds(400,265,250,20);
				lblbg.add(txtcarno);
				
				JTextField txtpack=new JTextField();
				txtpack.setFont(f);
				txtpack.setText(pack);
				txtpack.setEditable(false);
				txtpack.setBounds(400,315,250,20);
				lblbg.add(txtpack);
				
				
				JTextField txtpackStartDate=new JTextField();
				txtpackStartDate.setFont(f);
				txtpackStartDate.setText(packStartDate);
				txtpackStartDate.setEditable(false);
				txtpackStartDate.setBounds(400,365,250,20);
				lblbg.add(txtpackStartDate);
				
				JTextField txtpackEndDate=new JTextField();
				txtpackEndDate.setFont(f);
				txtpackEndDate.setText(packEndDate);
				txtpackEndDate.setEditable(false);
				txtpackEndDate.setBounds(400,415,250,20);
				lblbg.add(txtpackEndDate);

				
				JTextField txtlap=new JTextField();
				txtlap.setFont(f);
				txtlap.setText(lapno);
				txtlap.setEditable(false);
				txtlap.setBounds(400,466,250,20);
				lblbg.add(txtlap);
				
				JTextField txttrack=new JTextField();
				txttrack.setFont(f);
				txttrack.setText(trackno);
				txttrack.setEditable(false);
				txttrack.setBounds(400,516,250,20);
				lblbg.add(txttrack);
				
				JTextField txtcarstatus=new JTextField();
				txtcarstatus.setFont(f);
				txtcarstatus.setText(carstatus);
				txtcarstatus.setEditable(false);
				txtcarstatus.setBounds(400,568,250,20);
				lblbg.add(txtcarstatus);
				
				JTextField txtblack=new JTextField();
				txtblack.setFont(f);
				txtblack.setText(blackliststatus);
				txtblack.setEditable(false);
				txtblack.setBounds(400,618,250,20);
				lblbg.add(txtblack);
				
				JTextField txtimpose=new JTextField();
				txtimpose.setFont(f);
				txtimpose.setText(String.valueOf(imposedcharge));
				txtimpose.setEditable(false);
				txtimpose.setBounds(400,668,250,20);
				lblbg.add(txtimpose);

				btnimposefine.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						String strAction=e.getActionCommand();
						String updatedfine=txtimposefine.getText();
						db.SetImposedFine(updatedfine,username);
						txtimposefine.setText("");
						
						JScrollPane jsp;
						jsp=admintable();
						lblbg.add(jsp);
						
						int imposed=db.getImposedCharge(username);
						JTextField txtimposedddd=new JTextField();
						txtimposedddd.setFont(f);
						txtimposedddd.setText(String.valueOf(imposed));
						txtimposedddd.setEditable(false);
						txtimposedddd.setBounds(400,668,250,20);
						lblbg.add(txtimposedddd);
						
						JOptionPane.showMessageDialog(null, "Fine Imposed Successfully");
					}
				});

				btnblockuser.addActionListener(new ActionListener() 
				{	
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						String strAcmd=e.getActionCommand();
						
						db.setblockuser(username);
						Admintable=db.getAdminTable();
						JScrollPane jsp1;
						jsp1=admintable();
						lblbg.add(jsp1);

					
						JOptionPane.showMessageDialog(null, "User Blocked Successfully");
						boolean b;
						JTextField black=new JTextField();
						black.setFont(f);
						if(dbuser.getBlacklistStatus(username)==true)
						{
						black.setText("yes");
						black.setEditable(false);
						black.setBounds(400,618,250,20);
						lblbg.add(black);
						}
						}
						
					
				});
				btnunblockuser.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						db.setunblockuser(username);
						JScrollPane jsp2;
						jsp2=admintable();
						lblbg.add(jsp2);

						//Updated blacklist 
						boolean b;
						JTextField black=new JTextField();
						black.setFont(f);
						if(dbuser.getBlacklistStatus(username)==false)
						{
						black.setText("no");
						black.setEditable(false);
						black.setBounds(400,618,250,20);
						lblbg.add(black);
						}

					
						JOptionPane.showMessageDialog(null, "User Unblocked Successfully");
					}
				});
				
			}
		});
		setTitle("Car Parking Admin Block");
		setVisible(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	public static JScrollPane admintable()
	{
		DBHandlerAdmin db=new DBHandlerAdmin();
		JTable Admintable;
		Admintable=db.getAdminTable();
		
		JScrollPane jsp=new JScrollPane(Admintable);
		jsp.setBounds(100, 30,1020,160);
		return jsp;
	}
}
