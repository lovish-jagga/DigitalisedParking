package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oracle.jdbc.pool.OracleDataSource;

public class DBHandlerAdmin 
{
	public Connection getDBConnection()
	{
		Connection con=null;
		OracleDataSource ods;
		try 
		{
			ods=new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			con=ods.getConnection("lovish","icsd");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return con;
	}
	public boolean isValidAdmin(String strAdminName, String strAdminPassword) {
		// TODO Auto-generated method stub
		Connection con=getDBConnection();
		boolean res=false;
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Select * from Admin where AdminName=? and AdminPassword=?");
			stmt.setString(1, strAdminName);
			stmt.setString(2, strAdminPassword);
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

	public JTable  getAdminTable()
	{
		String[] colomnNames = {"USERNAME","CARNO.","PACK","PACKSTARTDATE","PACKENDDATE","LAPNO","TRACKNO","CARSTATUS","BLACKLISTED","IMPOSECHARGE"};
		Connection con = getDBConnection();
		try
		{
			PreparedStatement stmt=con.prepareStatement("Select UserSignUp.username,carinfo.carno,pack,packStartDate,packEndDate,carinfo.carstatus,lapno,trackno,blackliststatus ,imposedcharge from UserSignUp,carinfo,parking where parking.carid=carinfo.carid and usersignup.userid=parking.carid and carinfo.carstatus='parked'");
			ResultSet rset = stmt.executeQuery();
			DefaultTableModel model = new DefaultTableModel(colomnNames, 0)
			{
			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
			JTable table = new JTable(model);
			while (rset.next()) {
				Vector row = new Vector();
				String User_Name=rset.getString("username");
				String Car_No=rset.getString("carno");
				String Pack=rset.getString("pack");
				String Pack_StartDate=rset.getString("packStartDate");
				String Pack_EndDate=rset.getString("packEndDate");
				String Lap_No=rset.getString("lapno");
				String Track_No=rset.getString("trackno");
				String Car_Status=rset.getString("carstatus");
				String BlackListStatus=rset.getString("blackliststatus");
				int ImposedCharge=rset.getInt("imposedcharge");
				row.add(User_Name);
				row.add(Car_No);
				row.add(Pack);
				row.add(Pack_StartDate);
				row.add(Pack_EndDate);
				row.add(Lap_No);
				row.add(Track_No);
				row.add(Car_Status);
				row.add(BlackListStatus);
				row.add(ImposedCharge);
				model.addRow(row);
			}
			return table;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void SetImposedFine(String imposedcharge,String username)
	{
		Connection con=getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("Update usersignup set imposedcharge= ? where username=?");
			stmt.setString(1, imposedcharge);
			stmt.setString(2, username);
			stmt.executeUpdate();
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void setblockuser(String username)
	{
		String blackliststatus="yes";
		Connection con=getDBConnection();
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Update UserSignUp set blackliststatus=? where username=? ");
			stmt.setString(1, blackliststatus);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setunblockuser(String username)
	{
		String blackliststatus="no";
		Connection con=getDBConnection();
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Update UserSignUp set blackliststatus=? where username=? ");
			stmt.setString(1, blackliststatus);
			stmt.setString(2, username);
			stmt.executeUpdate();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public int getRegisteredUserCount()
	{
		int count=0;
		Connection con=getDBConnection();
		try 
		{
			PreparedStatement stmt=con.prepareStatement("Select max(UserId)as id from UserSignUp");
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				count=rset.getInt("id");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		System.out.println(count);
		return count;
		
	}
	

	public int getImposedCharge(String username)
	{
		int imposedcharge=0;
		Connection con=getDBConnection();
		try 
		{
			//update carinfo set carstatus=? where carno=?
			PreparedStatement stmt=con.prepareStatement("Select * from usersignup where username=?");
			stmt.setString(1, username);
			ResultSet rset=stmt.executeQuery();
			if(rset.next())
			{
				 imposedcharge=rset.getInt("imposedcharge");
				 
			}
			
			
			con.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return 0;
		}
		return imposedcharge;
	}


	
}