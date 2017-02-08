import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class LiavontsiBrechka_COMP228Test2 extends JFrame{
    //Database config
    final static String DRIVER = "com.mysql.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost/Campus?user=root&password=Brechko23";
    Connection mainConnection;
    Statement statement;
    PreparedStatement preparedStatement;
    Vector<String> columns;
    Vector<Vector> rows;

    //Other Variables
    final static String STUDENTS_SELECT_STATEMENT = "SELECT * FROM students";
    final static String FILTER_SELECT_STATEMENT = "SELECT * FROM students WHERE lastname = \'";
    final static String ADDRESS_UPDATE_STATEMENT =
            "UPDATE students SET address = ?, city = ?, postalcode = ?, province = ? " +
                    "WHERE studentid = ?";

    //Swing variables
    JTabbedPane campus;
    JPanel updatePanel, informationInput, studentsPanel;
    JButton updateAddress, filterByName, refreshList;
    JLabel idLabel, addressLabel, cityLabel, pcLabel, provLabel;
    JTextField idTF, addressTF, cityTF, pcTF, provTF, filterTF;
    JTable studentsTableData, listTableData;
    DefaultTableModel studentsTableModel, listTableModel;
    JScrollPane studentsTableDataScroll, listTableScroll;
    ButtonHandler buttonHandler = new ButtonHandler();

    public LiavontsiBrechka_COMP228Test2() {
        super("Library");

        campus = new JTabbedPane();

        //Students list tab
        studentsPanel = new JPanel(new BorderLayout());
        studentsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        listTableModel = new DefaultTableModel();
        listTableData = new JTable(listTableModel);
        listTableScroll = new JScrollPane(listTableData);
        displayResults(STUDENTS_SELECT_STATEMENT, listTableModel);

        refreshList = new JButton("Refresh");
        refreshList.addActionListener(buttonHandler);

        //Update tab
        updatePanel = new JPanel(new BorderLayout());
        updatePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //--Info update pane
        informationInput = new JPanel();
        GridBagLayout updateInfoGrid = new GridBagLayout();
        informationInput.setLayout(updateInfoGrid);

        filterTF = new JTextField(10);
        filterByName = new JButton("Filter by Name");
        filterByName.addActionListener(buttonHandler);
        idLabel = new JLabel("ID");
        addressLabel = new JLabel("Address");
        cityLabel = new JLabel("City");
        pcLabel = new JLabel("Postal Code");
        provLabel = new JLabel("Province");
        idTF = new JTextField(10);
        addressTF = new JTextField(10);
        cityTF = new JTextField(10);
        pcTF = new JTextField(10);
        provTF = new JTextField(10);
        updateAddress = new JButton("Update Address");
        updateAddress.addActionListener(buttonHandler);

        addComponent(informationInput, updateInfoGrid, filterTF, 0, 0, 1, 1);
        addComponent(informationInput, updateInfoGrid, filterByName, 1, 0, 1, 1);
        addComponent(informationInput, updateInfoGrid, idLabel, 0, 1, 1, 1);
        addComponent(informationInput, updateInfoGrid, idTF, 1, 1, 1, 1);
        addComponent(informationInput, updateInfoGrid, addressLabel, 0, 2, 1, 1);
        addComponent(informationInput, updateInfoGrid, addressTF, 1, 2, 1, 1);
        addComponent(informationInput, updateInfoGrid, cityLabel, 0, 3, 1, 1);
        addComponent(informationInput, updateInfoGrid, cityTF, 1, 3, 1, 1);
        addComponent(informationInput, updateInfoGrid, pcLabel, 0, 4, 1, 1);
        addComponent(informationInput, updateInfoGrid, pcTF, 1, 4, 1, 1);
        addComponent(informationInput, updateInfoGrid, provLabel, 0, 5, 1, 1);
        addComponent(informationInput, updateInfoGrid, provTF, 1, 5, 1, 1);
        addComponent(informationInput, updateInfoGrid, updateAddress, 0, 6, 2, 1);

        //--Display students pane
        studentsTableModel = new DefaultTableModel();
        studentsTableData = new JTable(studentsTableModel);
        studentsTableDataScroll = new JScrollPane(studentsTableData);
        displayResults(STUDENTS_SELECT_STATEMENT, studentsTableModel);

        //Adding components
        studentsPanel.add(listTableScroll, BorderLayout.CENTER);
        studentsPanel.add(refreshList, BorderLayout.SOUTH);
        updatePanel.add(informationInput, BorderLayout.NORTH);
        updatePanel.add(studentsTableDataScroll, BorderLayout.CENTER);

        campus.addTab("View", null, studentsPanel, "Show students list");
        campus.addTab("Update patron info", null, updatePanel, "Change patron`s name or assign new books");

        Container content = getContentPane();
        content.add(campus, BorderLayout.CENTER);
    }

    private void addComponent(JPanel p,
                              GridBagLayout grid,
                              Component comp,
                              int gridx,
                              int gridy,
                              int gridwidth,
                              int gridheight) {
        GridBagConstraints constr = new GridBagConstraints();
        constr.gridx = gridx;
        constr.gridy = gridy;
        constr.gridwidth = gridwidth;
        constr.gridheight = gridheight;
        constr.fill = GridBagConstraints.HORIZONTAL;
        grid.setConstraints(comp, constr);
        p.add(comp);
    }

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if(e.getSource() == refreshList) {
                    displayResults(STUDENTS_SELECT_STATEMENT, listTableModel);
                } else if (e.getSource() == filterByName) {
                    if (filterTF.getText().equals("")) throw new IOException("Please, enter the Last Name to filter");
                    displayResults(FILTER_SELECT_STATEMENT + filterTF.getText() + "\'", studentsTableModel);
                } else if (e.getSource() == updateAddress) {
                    if (idTF.getText().equals("")) throw new IOException("Please, enter a student ID");
                    List newValues = new ArrayList<>(4);
                    newValues.add(addressTF.getText());
                    newValues.add(cityTF.getText());
                    newValues.add(pcTF.getText());
                    newValues.add(provTF.getText());
                    List id = new ArrayList<>(1);
                    id.add(idTF.getText());
                    updateRow(id, newValues, ADDRESS_UPDATE_STATEMENT);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private void connect() throws Exception{
        Class.forName(DRIVER).newInstance();
        mainConnection = DriverManager.getConnection(url);
    }

    private void displayResults(String selectStatement, DefaultTableModel tableModelToShow) {
        try {
            //Connection to database
            connect();

            statement = mainConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectStatement);
            ResultSetMetaData metaData = resultSet.getMetaData();

            rows = new Vector<>();
            columns = new Vector<>();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columns.addElement(metaData.getColumnName(i).toUpperCase());
            }

            while (resultSet.next()) {
                Vector<String> nextRow = new Vector<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    nextRow.addElement(resultSet.getString(i));
                }
                rows.addElement(nextRow);
            }

            tableModelToShow.setDataVector(rows, columns);
            resultSet.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            if (mainConnection != null) {
                try {
                    mainConnection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    private void updateRow(List oldValues, List newValues, String updateStatement) {
        try {
            //Connection to database
            connect();

            preparedStatement = mainConnection.prepareStatement(updateStatement);

            for (int i = 0; i < newValues.size(); i++) {
                String currentValueClass = newValues.get(i).getClass().getName();
                String currentValueType =
                        currentValueClass.substring(currentValueClass.lastIndexOf('.') + 1, currentValueClass.length());

                if (currentValueType.equals("String")) preparedStatement.setString(i + 1, newValues.get(i).toString());
                else if (currentValueType.equals("Integer"))
                    preparedStatement.setInt(i + 1, Integer.parseInt(newValues.get(i).toString()));
            }

            int parametersSize = newValues.size();
            for (int i = parametersSize; i < parametersSize + oldValues.size(); i++) {
                String currentValueClass = oldValues.get(i - parametersSize).getClass().getName();
                String currentValueType =
                        currentValueClass.substring(currentValueClass.lastIndexOf('.') + 1, currentValueClass.length());

                if (currentValueType.equals("String")) preparedStatement.setString(i + 1, oldValues.get(i - parametersSize).toString());
                else if (currentValueType.equals("Integer"))
                    preparedStatement.setInt(i + 1, Integer.parseInt(oldValues.get(i - parametersSize).toString()));
            }

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            if (mainConnection != null) {
                try {
                    mainConnection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }
    }

    public static void main(String args[]) {
        LiavontsiBrechka_COMP228Test2 sc = new LiavontsiBrechka_COMP228Test2();
        sc.setVisible(true);
        sc.setSize(900, 600);
        sc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}