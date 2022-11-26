package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.sun.net.httpserver.Authenticator.Result;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jdbc.rowset.OracleCachedRowSet;

public class DBHandlerUser 
{
	DBHandlerAdmin db=new DBHandlerAdmin();
	public boolean isValidUser(String strUserName, String strUserPassword) 
	{
		Connection con=db.getDBConnection();
		boolean res=false;
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Select * from UserSignUp where Username=? and UserPassword=?");
			stmt.setString(1, strUserName);
			stmt.setString(2, strUserPassword);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				res=true;
			}
			else
			{
				res=false;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public void insertIntoUserSignUp(int strpid, String strUnm, String strPassword,String strGender, String phone, String strAddress)
	{
		int imposedcharge=0;
		String blackliststatus = "no";
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("insert into UserSignUp values(?,?,?,?,?,?,?,?)");
			stmt.setInt(1, strpid);
			stmt.setString(2, strUnm);
			stmt.setString(3, strPassword);
			stmt.setString(4, strGender);
			stmt.setString(5, phone);
			stmt.setString(6, strAddress);
			stmt.setString(7, blackliststatus);
			stmt.setInt(8, imposedcharge);
			ResultSet rset=stmt.executeQuery();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public int getMaxId(String strTable,String strColumnName)
	{
		int id=1;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("select max(" + strColumnName + ") as USERID from " + strTable);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				id=rset.getInt("USERID");
			}
			 else {
					id = 0;
				}
				id++;
				System.out.println("User ID generated is " + id);
				con.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public int getUserIdByName(String username)
	{
		int id=0;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try
		{		//"select * from tbluserf where username=?"
			stmt=con.prepareStatement("select * from UserSignUp where Username=?");
			stmt.setString(1, username);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				
				id=rset.getInt("UserId");
				System.out.println("generated id is"+id);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public boolean UniqueCarNumber(String carno) {
		Connection con = db.getDBConnection();
		String carnumber;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo");
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				carnumber = rset.getString("carno");
				if (carno.equals(carnumber)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean CarNoValidator(String carno) {
		String patternvalidation="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";
		boolean b=true;
		Pattern pattern=Pattern.compile(patternvalidation);
		Matcher matcher=pattern.matcher(carno);
		if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "Please enter a valid car number");
			b=false;
		}
		return b;
	}
	
	public void insertInCarinfo(String username, int carid, String carno, String packStartDate,String packEndDate,String pack) 
	{
		Connection con = db.getDBConnection();
		String carstatus = "booked";
		try {
//			strUserName,Integer.parseInt(caridtf.getText()) , carnotf.getText(),packStartDate,packEndDate		
			PreparedStatement stmt = con.prepareStatement("insert into carinfo values(?,?,?,?,?,?,?)");
			stmt.setString(1,username);
			stmt.setInt(2,carid);
			stmt.setString(3,carno);
			stmt.setString(4, pack);
			stmt.setString(5,packStartDate);
			stmt.setString(6,packEndDate);
			stmt.setString(7, carstatus);
			stmt.executeUpdate();
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean getBlacklistStatus(String username) {
		Connection con = db.getDBConnection();
		String blacklist;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from UserSignUp where Username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				blacklist = rset.getString("blackliststatus");
				if (blacklist.equals("yes")) {
					return true;
				}
				else 
				{
					return false;
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public String getCarnobyusername(String username)
	{
		String carno=null;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("Select carno from carinfo where username=?");
			stmt.setString(1, username);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				carno=rset.getString("carno");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return carno;
	}

	public String getCaridFromusername(String username)
	{
		String carid=null;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("Select carid from carinfo where username=?");
			stmt.setString(1, username);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				carid=rset.getString("carid");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return carid;
	}
	
	public String getPackStartDate(String carid)
	{
		String strDate=null;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("Select packStartDate from carinfo where carid=?");
			stmt.setString(1, carid);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				strDate=rset.getString("packStartDate");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return strDate;
	}

	public String getPackEndDate(String carid)
	{
		String strDate=null;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			stmt=con.prepareStatement("Select packEndDate from carinfo where carid=?");
			stmt.setString(1, carid);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				strDate=rset.getString("packEndDate");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return strDate;
	}	
	public boolean existingCustomer(String carno)
	{
		boolean b=false;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try 
		{
			//carno varchar(40), 
			stmt=con.prepareStatement("Select * from carinfo where carno=?");
			stmt.setString(1, carno);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				return b=true;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public String getCarStatusFromCarno(String carno) {
		Connection con = db.getDBConnection();
		String carstatus = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				carstatus = rset.getString("carstatus");
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return carstatus;
	}
	
	public String getPhonenobyUserName(String username) {
		String phoneno = null;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from usersignup where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				phoneno = rset.getString("phoneno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phoneno;
	}

	public String getuserPasswordbyUserName(String username) {
		String userpassword = "";
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from usersignup where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				userpassword = rset.getString("userpassword");
			} else {
				JOptionPane.showMessageDialog(null, "no such user exists..");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userpassword;
	}
	
	public String getPackFromUserName(String username) {
		String pack = null;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where username=?");
			stmt.setString(1, username);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				pack = rset.getString("pack");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pack;
	}

	public void updateCarinfobyusername(String carno, String pack, String carstatus, String username,String strStartDate,String strEndDate) {
		Connection con = db.getDBConnection();
		try {
			System.out.println("here");
			System.out.println(carno);
			System.out.println(pack);
			System.out.println(strStartDate);
			System.out.println(strEndDate);

			PreparedStatement stmt = con.prepareStatement("update carinfo set carno=?,pack=? ,"
					+ "carstatus=?,packStartDate=?,packEndDate=? "
					+ "where username=?");
			stmt.setString(1, carno);
			stmt.setString(2, pack);
			stmt.setString(3, carstatus);
			stmt.setString(4, strStartDate);
			stmt.setString(5, strEndDate);
			stmt.setString(6, username);
			stmt.executeUpdate();
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean verifyneworexistinguser(String carid) {
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where carid=?");
			stmt.setString(1, carid);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}
	
	public int getLapOfLoginUser(String carid) 
	{
		int lapno=0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where carid=?");
			stmt.setString(1, carid);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) 
			{
				lapno = rset.getInt("lapno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lapno;
	}

	public int getTrackOfLoginUser(String carid) {
		int trackno = 0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where carid=?");
			stmt.setString(1, carid);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) 
			{
				trackno = rset.getInt("trackno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trackno;
	}
	
	
	public int getLapnoOfNewUser() {
		int lapno = 0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where locstatus=?");
			stmt.setString(1, "avail");
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				lapno = rset.getInt("lapno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lapno;
	}
	
	public int getTracknoOfNewUser() {
		int trackno = 0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where locstatus=?");
			stmt.setString(1, "avail");
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				trackno = rset.getInt("trackno");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trackno;
	}
	
	
	public void Updatelapandtracktozero(String carid)
	{
		Connection con=db.getDBConnection();
		int lapno=0;
		int trackno=0;
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Update parking set lapno=?,trackno=? where carid=?");
			stmt.setInt(1, lapno);
			stmt.setInt(2, trackno);
			stmt.setString(3, carid);
			stmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void UpdateLocStatusofNewUser(String carno, int lapno, int trackno) {
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con
					.prepareStatement("update parking set carno=? , locstatus=? where lapno=? and trackno=?");
			stmt.setString(1, carno);
			stmt.setString(2, "reserved");
			stmt.setInt(3, lapno);
			stmt.setInt(4, trackno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
////////////********FOR PARKING*************/////////////////////
	public String getPackFromCarno(String carno) {
		String pack = null;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from carinfo where carno=?");
			stmt.setString(1, carno);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				pack = rset.getString("pack");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pack;
	}
	
	
//	public void insertintblPayment(String carno, String pack, String dop) {
//		String dor = null;
//		String tor = null;
//		int fixedch = 0;
//		int extrach = 0;
//		int finech = 0;
//		Connection con = db.getDBConnection();
//		try {
//			PreparedStatement stmt = con.prepareStatement("insert into tblpayment values(?,?,?,?,?,?,?,?)");
//			stmt.setString(1, carno);
//			stmt.setString(2, dop);
//			stmt.setString(3, dor);
//			stmt.setString(4, tor);
//			stmt.setString(5, pack);
//			stmt.setInt(6, fixedch);
//			stmt.setInt(7, extrach);
//			stmt.setInt(8, finech);
//			stmt.executeUpdate();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	public void UpdateCarStatus(String carstatus, String carno) {
		Connection con =db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("update carinfo set carstatus=? where carno=?");
			stmt.setString(1, carstatus);
			stmt.setString(2, carno);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void addParkingStatus(String carid, String trackno, String lapno, String status) 
	{
		OracleDataSource ods;
		Connection con=db.getDBConnection();
		String paymentpending="0";
		try
		{
			PreparedStatement stmt=con.prepareStatement("Insert into parking values(?,?,?,?,?) ");
			stmt.setString(1, carid);
			stmt.setString(2, trackno);
			stmt.setString(3, lapno);
			stmt.setString(4, status);
			stmt.setString(5, paymentpending);
			ResultSet rset=stmt.executeQuery();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int lapnumber(int i) 
	{
		int lapno = 0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where lapno=?");
			stmt.setInt(1, i);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				lapno = rset.getInt("lapno");
				return lapno;
			}
			else 
			{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lapno;
	}
	
	public int tracknumber(int j) 
	{
		int trackno = 0;
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where trackno=?");
			stmt.setInt(1, j);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) {
				trackno = rset.getInt("trackno");
				return trackno;
			}
			else 
			{
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trackno;
	}
	
	public boolean getCarStatus(int lapnumber,int tracknumber)
	{
		boolean b=false;
		String carstatus="parked";
		Connection con = db.getDBConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from parking where lapno=? and trackno=? and carstatus=?");
			stmt.setInt(1, lapnumber);
			stmt.setInt(2, tracknumber);
			stmt.setString(3, carstatus);
			ResultSet rset = stmt.executeQuery();
			if (rset.next()) 
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public int getAvailableCount()
	{
		int count=0;
		Connection con=db.getDBConnection();
		PreparedStatement stmt;
		try
		{
			stmt=con.prepareStatement("Select count(carstatus) as availableslots from parking where carstatus='parked'");
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				count=rset.getInt("availableslots");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return count;
	}
	
	public void UpdatePaymentPendingBronze(String carid)
	{
		String paymentpending="20";
		Connection con=db.getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("Update parking set paymentpending=? where carid=?");
			stmt.setString(1, paymentpending);
			stmt.setString(2, carid);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void UpdatePaymentPendingSilver(String carid)
	{
		String paymentpending="50";
		Connection con=db.getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("Update parking set paymentpending=? where carid=?");
			stmt.setString(1, paymentpending);
			stmt.setString(2, carid);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void UpdatePaymentPendingGold(String carid)
	{
		String paymentpending="80";
		Connection con=db.getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("Update parking set paymentpending=? where carid=?");
			stmt.setString(1, paymentpending);
			stmt.setString(2, carid);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getPaymentPending(String carid)
	{
		int paymentpending=0;
		Connection con=db.getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("select paymentpending from parking where carid=?");
			stmt.setString(1, carid);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				paymentpending=rset.getInt("paymentpending");
			}
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return paymentpending;
	}

	public int getImposedFine(String username)
	{
		int imposedfine = 0;
		Connection con=db.getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("select imposedcharge from usersignup where username=?");
			stmt.setString(1, username);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				imposedfine=rset.getInt("imposedcharge");
			}
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return imposedfine;
	}

	public void deleteUsersignup(String username)
	{
		Connection con=db.getDBConnection();
		try 
		{
		//DELETE FROM table_name WHERE condition;	
			PreparedStatement stmt=con.prepareStatement("delete from usersignup where username=?");
			stmt.setString(1, username);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void deleteUserCarinfo(String carid)
	{
		Connection con=db.getDBConnection();
		try 
		{
		//DELETE FROM table_name WHERE condition;	
			PreparedStatement stmt=con.prepareStatement("delete from carinfo where carid=?");
			stmt.setString(1, carid);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void deleteUserCarParking(String carid)
	{
		Connection con=db.getDBConnection();
		try 
		{
		//DELETE FROM table_name WHERE condition;	
			PreparedStatement stmt=con.prepareStatement("delete from parking where carid=?");
			stmt.setString(1, carid);
			stmt.executeUpdate();
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

}
