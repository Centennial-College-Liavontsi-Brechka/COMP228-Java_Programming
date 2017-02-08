import java.sql.*;
public class TestConnection1 {

	public static void main(String[] args) {

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/Campus?"
					+ "user=root&password=Brechko23";
			Connection c = DriverManager.getConnection(url);

			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Students");
			ResultSetMetaData md = rs.getMetaData();
			int row=0;
			System.out.println("Row " +row+"\n");
			while(rs.next())
			{
				row+=1;
				System.out.println("Row " +row);

				for( int i=1;i <= md.getColumnCount();i++)
				{
					System.out.print(md.getColumnName(i));
					System.out.println(": "+rs.getString(i)); 
				}
				System.out.println();
			}
			rs.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
}
