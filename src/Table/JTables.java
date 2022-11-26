package Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.DBHandlerAdmin;

public class JTables 
{
	DBHandlerAdmin objAdmin=new DBHandlerAdmin();
		public JTable getParkedTable(String carid) 
		{
			String[] columnNames = { "CarID", "LapNo.", "TrackNo.", "CarStatus" };
			Connection con = objAdmin.getDBConnection();
			try {
				PreparedStatement stmt = con.prepareStatement("select * from parking where carid=?");
				stmt.setString(1, carid);
				ResultSet rset = stmt.executeQuery();
				DefaultTableModel model = new DefaultTableModel(columnNames, 0);
				JTable jtable = new JTable(model)
				{
				    @Override
				    public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
				};

				while (rset.next()) {
					Vector row = new Vector();

					int CarId = rset.getInt("carid");
					int lapno = rset.getInt("lapno");
					int trackno = rset.getInt("trackno");
					String CarStatus = rset.getString("carstatus");

					row.add(CarId);
					row.add(lapno);
					row.add(trackno);
					row.add(CarStatus);
					model.addRow(row);
				}
				return jtable;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}