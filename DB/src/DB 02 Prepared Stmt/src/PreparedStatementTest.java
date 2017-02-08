import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreparedStatementTest extends JFrame {

	final static String DRIVER = "com.mysql.jdbc.Driver";             
	final static String url = "jdbc:mysql://localhost/Campus?"
			+ "user=root&password=1234";
	//GUI objects
	private JButton insert, show;
	private JTextArea display;
	private JPanel pEntry;
	private JLabel lblDescription,lblPrice;
	private JTextField txtDescription,txtPrice;
	//JDBC objects
	Connection c ;
	Statement st;
	PreparedStatement pst;

	//
	public PreparedStatementTest()
	{
		//create and add the text area to south area of the frame
		display=new JTextArea(10,5);
		JScrollPane scrollPane = new JScrollPane(display);
		add(scrollPane,BorderLayout.SOUTH);
		//
		pEntry =new JPanel();
		GridBagLayout grid = new GridBagLayout();
		pEntry.setLayout (grid);
		//

		lblDescription=new JLabel("Description:");
		txtDescription=new JTextField(20);
		//
		lblPrice=new JLabel("Price:");
		txtPrice=new JTextField(20);
		//
		insert=new JButton("Insert");
		show = new JButton("Show");

		//add components to the grid
		addComponent(pEntry, grid, lblDescription, 0,0,1,1);
		addComponent(pEntry, grid, txtDescription, 1,0,1,1);
		addComponent(pEntry, grid, insert, 2,0,1,1);
		addComponent(pEntry, grid, lblPrice, 0,1,1,1);
		addComponent(pEntry, grid, txtPrice, 1,1,1,1);
		addComponent(pEntry, grid, show, 2,1,1,1);


		add(pEntry,BorderLayout.WEST);
		//
		ButtonHandler bHandler= new ButtonHandler();
		insert.addActionListener(bHandler);
		show.addActionListener(bHandler);
		//
		connect();

	}
	public void addComponent(JPanel p, GridBagLayout grid, Component c, int gridx, int gridy,
			int gridwidth, int gridheight)
	{
		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = gridx; //column
		constr.gridy = gridy; //row
		constr.gridwidth = gridwidth; //number of cells in the row that will be covered
		constr.gridheight = gridheight; //number of cells in the column that will be covered
		constr.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
		// add the component 
		grid.setConstraints(c, constr); //apply the constraints to the grid
		p.add(c);
	}

	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==insert)
			{
				String description = txtDescription.getText();
				double price = Double.parseDouble(txtPrice.getText());
				insertRow(description, price);
			}
			else if(e.getSource()==show)
				displayResults();
		}

	}
	//
	public void connect()
	{
		try
		{			
			Class.forName( DRIVER ).newInstance();
			// establish connection to database                              
			c = DriverManager.getConnection( url);

		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	//
	public void displayResults()
	{
		try
		{
			st = c.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM product");
			ResultSetMetaData md = rs.getMetaData();
			int row=0;
			String info="";
			while(rs.next())
			{
				for( int i=1;i <= md.getColumnCount();i++)
				{
					info+=md.getColumnName(i)+"\t: "+rs.getObject(i)+"\t"; 
				}
				row+=1;
				info+="\n";
			}
			display.setText(info);
			rs.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			//e.printStackTrace();
		}
	}
	//
	public void insertRow(String description, double price)
	{
		try {

			pst = c.prepareStatement("Insert into product (description, price) VALUES(?,?)");

			pst.setString(1, description); 
			pst.setDouble(2, price);

			//Execute the prepared statement using executeUpdate method:  
			int val = pst.executeUpdate(); //returns the row count


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("Done!");
		}
	}
	public static void main(String[] args) {
		JFrame frame = new PreparedStatementTest();
		frame.setSize(600,300);
		frame.setVisible(true);

	}

}
