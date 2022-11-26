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

import Common.Mainframe;
import Database.DBHandlerUser;

public class Park extends JFrame
{
	JLabel lblPark;

	JLabel[] lblLapno;
	
	JLabel[] lblTrackno;
	JButton[][] btn;
	Font f=new Font("Calibri",Font.BOLD,30);
	DBHandlerUser db=new DBHandlerUser();
	String carstatus;
	public Park(String username, String carno) 
	{
		setLayout(null);
		
		ImageIcon icnbg=new ImageIcon("car/4327427.jpg");
		JLabel lblbg=new JLabel(icnbg);
		lblbg.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		add(lblbg);
		
		carstatus=db.getCarStatusFromCarno(carno);
		lblPark = new JLabel("Click on the Button where you want to park the car");
		lblPark.setFont(new Font("Serif",Font.ITALIC,50));
		lblPark.setForeground(Color.WHITE);
		lblPark.setBounds(80,20,1200,70);
		lblbg.add(lblPark);
	
		String strlbl[]= {"Trackno 1","Trackno 2","Trackno 3","Trackno 4","Trackno 5","Trackno 6"};
		lblTrackno=new JLabel[6]; 
		int xpos=140,ypos=95;
		for(int i=0;i<6;i++)
		{
			lblTrackno[i]=new JLabel(strlbl[i]);
			lblTrackno[i].setFont(f);
			lblTrackno[i].setForeground(Color.YELLOW);
			lblTrackno[i].setBounds(xpos,ypos,220,120);//200,100
			lblbg.add(lblTrackno[i]);
			xpos+=150;
		}
		
		String strlap[]= {"Lapno 1","Lapno 2","Lapno 3","Lapno 4","Lapno 5","Lapno 6"};
		lblLapno=new JLabel[6]; 
		int xposlap=20,yposlap=155;
		for(int i=0;i<6;i++)
		{
			lblLapno[i]=new JLabel(strlap[i]);
			lblLapno[i].setFont(f);
			lblLapno[i].setForeground(Color.YELLOW);
			lblLapno[i].setBounds(xposlap,yposlap,200,100);
			lblbg.add(lblLapno[i]);
			yposlap+=82;
		}

		ImageIcon icnGreen=new ImageIcon("images/button_green-are-available-slots.png");
		JLabel btnGreen=new JLabel(icnGreen);
		btnGreen.setBounds(1015,250,320,120);
		lblbg.add(btnGreen);
		
		ImageIcon icnRed=new ImageIcon("images/button_red-are-reserved-slots.png");
		JLabel btnRed=new JLabel(icnRed);
		btnRed.setBounds(1020,350,320,130);
		lblbg.add(btnRed);
		
		ImageIcon iconback=new ImageIcon("images/images.png");
		JButton btnback=new JButton(iconback);
		btnback.setBounds(1020,500,120,90);
		lblbg.add(btnback);
		btnback.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UserWelcome frm=new UserWelcome(username, carno);
				dispose();
			}
		});
		
		String[][] strbuttn= {
				 {"00","01","02","03","04","05","06"},
				 {"10","1","2","3","4","5","6"},
				 {"20","7","8","9","10","11","12"},
				 {"30","13","14","15","16","17","18"},
				 {"40","19","20","21","22","23","24"},
				 {"50","25","26","27","28","29","30"},
				 {"60","31","32","33","34","35","36"}
		 			};

		btn=new JButton[7][7];
		int y=175,x=160;
		for(int i=1;i<7;i++)
		{
			for(int j=1;j<7;j++)
			{
			btn[i][j]=new JButton(strbuttn[i][j]);
			btn[i][j].setFont(f);
			btn[i][j].setBounds(x,y,80,50);
			lblbg.add(btn[i][j]);
			x+=150;
			if((i==1 &&j==6) || (i==2 && j==6) || (i==3 &&j==6)  || (i==4 && j==6) ||
					(i==5 && j==6) || (i==6 && j==6))
			{
				x=160;
				y+=82;
			} 
			boolean b=db.getCarStatus(i, j);
			if(b==false)
			{
				btn[i][j].setBackground(Color.GREEN);
			} 
			else
			{
				btn[i][j].setBackground(Color.RED);
				btn[i][j].setEnabled(false);

			}
			btn[i][j].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
				String	strAcmd=e.getActionCommand();
				if(strAcmd.equals("1"))
				{
					System.out.println("1 button clicked");
					btn[1][1].setEnabled(false);
					btn[1][1].setBackground(Color.RED);
					String trackno="1";
					String lapno="1";
					String status="parked";

					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("2"))
				{
					System.out.println("2 button clicked");
					btn[1][2].setEnabled(false);
					btn[1][2].setBackground(Color.RED);
					String trackno="2";
					String lapno="1";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("3"))
				{
					System.out.println("3 button clicked");
					String trackno="3";
					String lapno="1";
					String status="parked";
					btn[1][3].setEnabled(false);
					btn[1][3].setBackground(Color.RED);
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
					
				}
				else if(strAcmd.equals("4"))
				{
					btn[1][4].setEnabled(false);
					btn[1][4].setBackground(Color.RED);
					System.out.println("4 button clicked");
					String trackno="4";
					String lapno="1";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("5"))
				{
					btn[1][5].setEnabled(false);
					btn[1][5].setBackground(Color.RED);
					System.out.println("5 button clicked");
					String trackno="5";
					String lapno="1";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("6"))
				{
					btn[1][6].setEnabled(false);
					btn[1][6].setBackground(Color.RED);
					System.out.println("6 button clicked");
					String trackno="6";
					String lapno="1";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("7"))
				{
					btn[2][1].setEnabled(false);
					btn[2][1].setBackground(Color.RED);
					System.out.println("7 button clicked");
					String trackno="1";
					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("8"))
				{
					System.out.println("8 button clicked");
					String trackno="2";
					btn[2][2].setEnabled(false);
					btn[2][2].setBackground(Color.RED);

					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("9"))
				{
					System.out.println("9 button clicked");
					btn[2][3].setEnabled(false);
					btn[2][3].setBackground(Color.RED);

					String trackno="3";
					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("10"))
				{
					btn[2][4].setEnabled(false);
					btn[2][4].setBackground(Color.RED);

					System.out.println("10 button clicked");
					String trackno="4";
					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("11"))
				{
					btn[2][5].setEnabled(false);
					btn[2][5].setBackground(Color.RED);

					System.out.println("11 button clicked");
					String trackno="5";
					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("12"))
				{
					btn[2][6].setEnabled(false);
					btn[2][6].setBackground(Color.RED);

					System.out.println("12 button clicked");
					String trackno="6";
					String lapno="2";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("13"))
				{
					btn[3][1].setEnabled(false);
					btn[3][2].setBackground(Color.RED);

					System.out.println("13 button clicked");
					String trackno="1";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("14"))
				{
					btn[3][2].setEnabled(false);
					btn[3][2].setBackground(Color.RED);

					System.out.println("14 button clicked");
					String trackno="2";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("15"))
				{
					btn[3][3].setEnabled(false);
					btn[3][3].setBackground(Color.RED);

					System.out.println("15 button clicked");
					String trackno="3";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("16"))
				{
					btn[3][4].setEnabled(false);
					btn[3][4].setBackground(Color.RED);

					System.out.println("16 button clicked");
					String trackno="4";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("17"))
				{
					btn[3][5].setEnabled(false);
					btn[3][5].setBackground(Color.RED);

					System.out.println("17 button clicked");
					String trackno="5";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("18"))
				{
					btn[3][6].setEnabled(false);
					btn[3][6].setBackground(Color.RED);

					System.out.println("18 button clicked");
					String trackno="6";
					String lapno="3";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("19"))
				{
					btn[4][1].setEnabled(false);
					btn[4][1].setBackground(Color.RED);

					System.out.println("19 button clicked");
					String trackno="1";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();
				}
				else if(strAcmd.equals("20"))
				{
					btn[4][2].setEnabled(false);
					btn[4][2].setBackground(Color.RED);

					System.out.println("20 button clicked");
					String trackno="2";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("21"))
				{
					btn[4][3].setEnabled(false);
					btn[4][3].setBackground(Color.RED);

					System.out.println("21 button clicked");
					String trackno="3";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("22"))
				{

					btn[4][4].setEnabled(false);
					btn[4][4].setBackground(Color.RED);
					System.out.println("22 button clicked");
					String trackno="4";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("23"))
				{
					btn[4][5].setEnabled(false);
					btn[4][5].setBackground(Color.RED);
					System.out.println("23 button clicked");
					String trackno="5";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("24"))
				{
					btn[4][6].setEnabled(false);
					btn[4][6].setBackground(Color.RED);

					System.out.println("24 button clicked");
					String trackno="6";
					String lapno="4";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("25"))
				{
					btn[5][1].setEnabled(false);
					btn[5][1].setBackground(Color.RED);

					System.out.println("25 button clicked");
					String trackno="1";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("26"))
				{
					btn[5][2].setEnabled(false);
					btn[5][2].setBackground(Color.RED);

					System.out.println("26 button clicked");
					String trackno="2";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("27"))
				{
					btn[5][3].setEnabled(false);
					btn[5][3].setBackground(Color.RED);

					System.out.println("27 button clicked");
					String trackno="3";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("28"))
				{
					btn[5][4].setEnabled(false);
					btn[5][4].setBackground(Color.RED);

					System.out.println("28 button clicked");
					String trackno="4";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("29"))
				{
					btn[5][5].setEnabled(false);
					btn[5][5].setBackground(Color.RED);

					System.out.println("29 button clicked");
					String trackno="5";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("30"))
				{
					btn[5][6].setEnabled(false);
					btn[5][6].setBackground(Color.RED);

					System.out.println("30 button clicked");
					String trackno="6";
					String lapno="5";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("31"))
				{
					btn[6][1].setEnabled(false);
					btn[6][1].setBackground(Color.RED);

					System.out.println("31 button clicked");
					String trackno="1";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("32"))
				{
					btn[6][2].setEnabled(false);
					btn[6][2].setBackground(Color.RED);

					System.out.println("32 button clicked");
					String trackno="2";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("33"))
				{
					btn[6][3].setEnabled(false);
					btn[6][3].setBackground(Color.RED);

					System.out.println("33 button clicked");
					String trackno="3";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					System.out.println("33 button clicked");
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("34"))
				{
					btn[6][4].setEnabled(false);
					btn[6][4].setBackground(Color.RED);

					System.out.println("34 button clicked");
					String trackno="4";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					System.out.println("34 button clicked");
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("35"))
				{
					btn[6][5].setEnabled(false);
					btn[6][5].setBackground(Color.RED);

					System.out.println("35 button clicked");
					String trackno="5";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					System.out.println("35 button clicked");
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				else if(strAcmd.equals("36"))
				{
					btn[6][6].setEnabled(false);
					btn[6][6].setBackground(Color.RED);

					String trackno="6";
					String lapno="6";
					String status="parked";
					String carid=db.getCaridFromusername(username);
					db.addParkingStatus(carid,trackno,lapno,status);
					db.UpdateCarStatus(status, carno);
					System.out.println("36 button clicked");
					JOptionPane.showMessageDialog(null, "Successfully booked your location");
					UserWelcome uwel=new UserWelcome(username, carno);
					dispose();

				}
				}
			});
			}
		}
	
	
		setVisible(true);
		setTitle("Park Page");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
}