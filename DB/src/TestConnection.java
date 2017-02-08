import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class TestConnection extends JFrame{
	JTable table;
	Vector rows,columns;
	DefaultTableModel tableModel;
	JScrollPane scrollPane;
	JPanel mainPanel;
	//
	Connection conn;
	Statement st;
	ResultSet rs;

	public TestConnection() {

		rows=new Vector();
		columns= new Vector();

		tableModel=new DefaultTableModel();
		table = new JTable(tableModel);
		scrollPane= new JScrollPane(table);//ScrollPane
		//
		mainPanel=new JPanel();
		setSize(750,300);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add("Center",scrollPane);

		mainPanel.setBackground(Color.white);
		table.getParent().setBackground(Color.cyan);
		getContentPane().add(mainPanel);
		setVisible(true);

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/Campus?"
						+ "user=root&password=1234";
			conn = DriverManager.getConnection(url);

			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM Students");
			ResultSetMetaData md = rs.getMetaData();
			//create columns headers
			for( int i=1;i <= md.getColumnCount();i++)
			{
				columns.addElement(md.getColumnName(i));
			}

			int row=0;
			while(rs.next())
			{
				Vector vRow = new Vector(); //to store the current row
				//System.out.println("Row " +row+"\n");
				for( int i=1;i <= md.getColumnCount();i++)
				{

					String columnValue = rs.getString(i);
					vRow.addElement(columnValue);
				}
				row+=1;
				rows.addElement(vRow);

			}

			tableModel.setDataVector(rows,columns);
			rs.close();

		}
		catch(Exception e) {
			e.printStackTrace();

		}
		finally
		{
			if (conn != null) { 
				try { 
					conn.close(); // close the connection after you're finnished with it
				} catch (SQLException ex) {/*nothing here*/} 
				conn = null; 
			}

		}

	}
	public static void main(String[] args) {
		TestConnection tc=new TestConnection();
		tc.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}
}
