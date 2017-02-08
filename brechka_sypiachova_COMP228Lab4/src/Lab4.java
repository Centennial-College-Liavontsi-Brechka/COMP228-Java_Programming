import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Lab4 extends JFrame{
    //Database config
    final static String DRIVER = "com.mysql.jdbc.Driver";
    final static String url = "jdbc:mysql://localhost/library?user=root&password=Brechko23";
    Connection mainConnection;
    Statement statement;
    PreparedStatement preparedStatement;
    Vector<String> columns;
    Vector<Vector> rows;

    //--Patron variables
    final static String PATRON_TABLE_NAME= "patron";
    final static String PATRON_INSERT_STATEMENT =
            "INSERT INTO patron (first_name, last_name) VALUES(?,?)";
    final static String PATRON_UPDATE_STATEMENT =
            "UPDATE patron SET first_name = ?, last_name = ? " +
                    "WHERE first_name = ? AND last_name = ?";
    final static String PATRON_SELECT_STATEMENT =
            "SELECT * FROM patron";
    final static String PATRON_MAIN_VALUES = "first_name, last_name";
    List<Object> patronList;

    //--Book variables
    final static String BOOK_TABLE_NAME = "book";
    final static String BOOK_INSERT_STATEMENT =
            "INSERT INTO book (author_name, title, pub_year) VALUES(?,?,?)";
    final static String BOOK_SELECT_STATEMENT =
            "SELECT * FROM book";
    final static String BOOK_MAIN_VALUES = "title";
    List<Object> bookList;

    //--BookReader variables
    final static String BOOK_READER_INSERT_STATEMENT =
            "INSERT INTO bookreader (patron_id, book_id, checkout_date) VALUES (?,?,?)";

    //--other variables
    final static String REPORTS_SELECT_STATEMENT =
            "SELECT first_name, last_name, title, checkout_date " +
                    "FROM BookReader " +
                    "INNER JOIN Patron ON Patron.id = patron_id " +
                    "INNER JOIN Book ON Book.id = book_id " +
                    "ORDER BY last_name, first_name";
    final static String MOST_FREQUENT_BOOKS_SELECT_STATEMENT =
            "SELECT a.author_name, a.title, a.pub_year " +
                    "FROM bookreader b " +
                    "INNER JOIN book a ON a.id = b.book_id " +
                    "GROUP BY b.book_id " +
                    "ORDER BY MAX(b.checkout_date) DESC";

    //Swing variables
    JTabbedPane library, insertTabbedPane;
    JPanel insertPatronTab, insertBookTab, informationPatronEntry,
            informationBookEntry, updatePatronInfoPanel, selectPatronInfoPanel,
            updatePatronButtonPanel, reportsPanel, mostFrequentBookPanel;
    JButton addPatronRecord, showPatronTable, addBookRecord, showBookTable, updatePatronInfo;
    JLabel firstNameLabel, lastNameLabel, bookTitleLabel,
            bookAuthorLabel, bookPubYearLabel, selectPatronToUpdateLabel,
            updateFirstNameLabel, updateLastNameLabel;
    JTextField firstNameTF, lastNameTF, bookTitleTF, bookAuthorTF,
            bookPubYearTF, updateFirstNameTF, updateLastNameTF;
    JComboBox selectPatronToUpdateCombo, selectBookToAssignCombo;
    JCheckBox changeNameCB, assignReadBookCB;
    JTable patronTableData, bookTableData, reportsTableData, mostFrequentBookTableData;
    DefaultTableModel patronTableModel, bookTableModel, reportsTableModel, mostFrequentBookTableModel;
    JScrollPane patronTableDataScroll, bookTableDataScroll, reportsTableScroll, mostFrequentBookTableScroll;
    GridBagLayout updatePatronInfoGrid;
    ButtonHandler buttonHandler = new ButtonHandler();
    CheckBoxHandler checkBoxHandler = new CheckBoxHandler();

    public Lab4() {
        super("Library");

        library = new JTabbedPane();

        //Insert tab declaration
        insertTabbedPane = new JTabbedPane();

        //--Patron tab
        insertPatronTab = new JPanel(new BorderLayout());
        insertPatronTab.setBorder(new EmptyBorder(10, 10, 10, 10));

        //----Info patron pane
        informationPatronEntry = new JPanel();
        GridBagLayout patronInfoGrid = new GridBagLayout();
        informationPatronEntry.setLayout(patronInfoGrid);

        firstNameLabel = new JLabel("First Name");
        lastNameLabel = new JLabel("Last Name");
        firstNameTF = new JTextField();
        lastNameTF = new JTextField();
        addPatronRecord = new JButton("Add new patron");
        addPatronRecord.addActionListener(buttonHandler);
        showPatronTable = new JButton("Show all patron records");
        showPatronTable.addActionListener(buttonHandler);

        addComponent(informationPatronEntry, patronInfoGrid, firstNameLabel, 0, 0, 1, 1);
        addComponent(informationPatronEntry, patronInfoGrid, firstNameTF, 1, 0, 1, 1);
        addComponent(informationPatronEntry, patronInfoGrid, lastNameLabel, 0, 1, 1, 1);
        addComponent(informationPatronEntry, patronInfoGrid, lastNameTF, 1, 1, 1, 1);
        addComponent(informationPatronEntry, patronInfoGrid, addPatronRecord, 0, 2, 1, 1);
        addComponent(informationPatronEntry, patronInfoGrid, showPatronTable, 1, 2, 1, 1);

        //----Display patron pane
        patronTableModel = new DefaultTableModel();
        patronTableData = new JTable(patronTableModel);
        patronTableDataScroll = new JScrollPane(patronTableData);

        //--Book tab
        insertBookTab = new JPanel(new BorderLayout());
        insertBookTab.setBorder(new EmptyBorder(10, 10, 10, 10));

        //----Info book pane
        informationBookEntry = new JPanel();
        GridBagLayout bookInfoGrid = new GridBagLayout();
        informationBookEntry.setLayout(bookInfoGrid);

        bookTitleLabel = new JLabel("Book Title");
        bookAuthorLabel = new JLabel("Book Author");
        bookPubYearLabel = new JLabel("Publication Year");
        bookTitleTF = new JTextField();
        bookAuthorTF = new JTextField();
        bookPubYearTF = new JTextField();
        addBookRecord = new JButton("Add new book");
        addBookRecord.addActionListener(buttonHandler);
        showBookTable = new JButton("Show all book records");
        showBookTable.addActionListener(buttonHandler);

        addComponent(informationBookEntry, bookInfoGrid, bookTitleLabel, 0, 0, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, bookTitleTF, 1, 0, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, bookAuthorLabel, 0, 1, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, bookAuthorTF, 1, 1, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, bookPubYearLabel, 0, 2, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, bookPubYearTF, 1, 2, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, addBookRecord, 0, 3, 1, 1);
        addComponent(informationBookEntry, bookInfoGrid, showBookTable, 1, 3, 1, 1);

        //----Display book pane
        bookTableModel = new DefaultTableModel();
        bookTableData = new JTable(bookTableModel);
        bookTableDataScroll = new JScrollPane(bookTableData);

        //Update patron info tab
        updatePatronInfoPanel = new JPanel(new BorderLayout());
        updatePatronInfoPanel.setBorder(new EmptyBorder(20, 10, 20, 20));

        //--Select patron info to update
        selectPatronInfoPanel = new JPanel();
        updatePatronInfoGrid = new GridBagLayout();
        selectPatronInfoPanel.setLayout(updatePatronInfoGrid);

        selectPatronToUpdateLabel = new JLabel("Select patron name");
        patronList = getListOfRows(PATRON_TABLE_NAME, PATRON_MAIN_VALUES);
        selectPatronToUpdateCombo = new JComboBox<>(patronList.toArray());
        changeNameCB = new JCheckBox("Do you want to change name?");
        changeNameCB.addItemListener(checkBoxHandler);
        assignReadBookCB = new JCheckBox("Did patron read a new book?");
        assignReadBookCB.addItemListener(checkBoxHandler);

        addComponent(selectPatronInfoPanel, updatePatronInfoGrid, selectPatronToUpdateLabel, 0, 0, 1, 1);
        addComponent(selectPatronInfoPanel, updatePatronInfoGrid, selectPatronToUpdateCombo, 1, 0, 1, 1);
        addComponent(selectPatronInfoPanel, updatePatronInfoGrid, changeNameCB, 0, 1, 1, 1);
        addComponent(selectPatronInfoPanel, updatePatronInfoGrid, assignReadBookCB, 1, 1, 1, 1);

        //----Update Name
        updateFirstNameLabel = new JLabel("First name");
        updateLastNameLabel = new JLabel("Last name");
        updateFirstNameTF = new JTextField();
        updateLastNameTF = new JTextField();

        //----Assign Book
        bookList = getListOfRows(BOOK_TABLE_NAME, BOOK_MAIN_VALUES);
        selectBookToAssignCombo = new JComboBox<>(bookList.toArray());

        //--Update button panel
        updatePatronInfo = new JButton("Update information");
        updatePatronInfo.addActionListener(buttonHandler);
        updatePatronButtonPanel = new JPanel(new FlowLayout());
        updatePatronButtonPanel.add(updatePatronInfo);

        //Reports tab
        reportsPanel = new JPanel(new BorderLayout());
        reportsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        reportsTableModel = new DefaultTableModel();
        reportsTableData = new JTable(reportsTableModel);
        reportsTableScroll = new JScrollPane(reportsTableData);
        displayResults(REPORTS_SELECT_STATEMENT, reportsTableModel);

        //Most frequent books tab
        mostFrequentBookPanel = new JPanel();
        mostFrequentBookPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        mostFrequentBookTableModel = new DefaultTableModel();
        mostFrequentBookTableData = new JTable(mostFrequentBookTableModel);
        mostFrequentBookTableScroll = new JScrollPane(mostFrequentBookTableData);
        displayResults(MOST_FREQUENT_BOOKS_SELECT_STATEMENT, mostFrequentBookTableModel);

        //Adding components
        insertPatronTab.add(informationPatronEntry, BorderLayout.NORTH);
        insertPatronTab.add(patronTableDataScroll, BorderLayout.CENTER);
        insertBookTab.add(informationBookEntry, BorderLayout.NORTH);
        insertBookTab.add(bookTableDataScroll, BorderLayout.CENTER);
        updatePatronInfoPanel.add(selectPatronInfoPanel, BorderLayout.NORTH);
        reportsPanel.add(reportsTableScroll, BorderLayout.CENTER);
        mostFrequentBookPanel.add(mostFrequentBookTableScroll);

        insertTabbedPane.addTab("New patron record", null, insertPatronTab, "Insert new patron record to the database");
        insertTabbedPane.addTab("New book record", null, insertBookTab, "Insert new book record to the database");

        library.addTab("Insert record", null, insertTabbedPane, "Add new book or patron record to the database");
        library.addTab("Update patron info", null, updatePatronInfoPanel, "Change patron`s name or assign new books");
        library.addTab("Patron reports", null, reportsPanel, "Show patron reports");
        library.addTab("Most frequent books", null, mostFrequentBookPanel, "Show most frequently read books");

        Container content = getContentPane();
        content.add(library, BorderLayout.CENTER);
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
                if (e.getSource() == showPatronTable) {
                    displayResults(PATRON_SELECT_STATEMENT, patronTableModel);
                } else if (e.getSource() == addPatronRecord) {
                    List<Object>patronInsertValues = new ArrayList<>(2);

                    patronInsertValues.add(firstNameTF.getText());
                    patronInsertValues.add(lastNameTF.getText());

                    if (patronInsertValues.get(0) == null || patronInsertValues.get(0).equals("")
                            || patronInsertValues.get(1) == null || patronInsertValues.get(1).equals(""))
                        throw new SQLException("Please, enter valid data (not null).");

                    insertRow(patronInsertValues, PATRON_INSERT_STATEMENT);

                    selectPatronToUpdateCombo.addItem(patronInsertValues.get(0) + " " + patronInsertValues.get(1));
                    selectPatronInfoPanel.revalidate();
                    selectPatronInfoPanel.repaint();
                    JOptionPane.showMessageDialog(null, "Patron record is added.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (e.getSource() == showBookTable) {
                    displayResults(BOOK_SELECT_STATEMENT, bookTableModel);
                } else if (e.getSource() == addBookRecord) {
                    List<Object> bookInsertValues = new ArrayList<>(3);

                    bookInsertValues.add(bookAuthorTF.getText());
                    bookInsertValues.add(bookTitleTF.getText());
                    bookInsertValues.add(Integer.parseInt(bookPubYearTF.getText()));

                    if (bookInsertValues.get(0) == null || bookInsertValues.get(0).equals("")
                            || bookInsertValues.get(1) == null || bookInsertValues.get(1).equals("")
                            || bookInsertValues.get(2) == null || bookInsertValues.get(2).equals(""))
                        throw new SQLException("Please, enter valid data (not null).");

                    insertRow(bookInsertValues, BOOK_INSERT_STATEMENT);

                    selectBookToAssignCombo.addItem(bookInsertValues.get(1));
                    selectPatronInfoPanel.revalidate();
                    selectPatronInfoPanel.repaint();
                    JOptionPane.showMessageDialog(null, "Book record is added.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (e.getSource() == updatePatronInfo) {
                    String[] patronName = selectPatronToUpdateCombo.getSelectedItem().toString().split("\\s");
                    String[] bookName = {selectBookToAssignCombo.getSelectedItem().toString()};

                    if (assignReadBookCB.isSelected()) {
                        String[] constraintPatronFields = {"first_name", "last_name"};
                        String[] constraintBookFields = {"title"};
                        List<Object> bookreaderInsertValues = new ArrayList<>();

                        int patronID = getID(PATRON_TABLE_NAME, constraintPatronFields, patronName);
                        int bookID = getID(BOOK_TABLE_NAME, constraintBookFields, bookName);

                        bookreaderInsertValues.add(patronID);
                        bookreaderInsertValues.add(bookID);

                        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                        bookreaderInsertValues.add(currentDate);

                        insertRow(bookreaderInsertValues, BOOK_READER_INSERT_STATEMENT);

                        displayResults(REPORTS_SELECT_STATEMENT, reportsTableModel);
                        displayResults(MOST_FREQUENT_BOOKS_SELECT_STATEMENT, mostFrequentBookTableModel);
                        selectPatronInfoPanel.revalidate();
                        selectPatronInfoPanel.repaint();
                    }

                    if (changeNameCB.isSelected()) {
                        List<Object> newValues = new ArrayList<>(2);
                        newValues.add(updateFirstNameTF.getText());
                        newValues.add(updateLastNameTF.getText());

                        updateRow(Arrays.asList(patronName), newValues, PATRON_UPDATE_STATEMENT);

                        selectPatronToUpdateCombo.removeItemAt(selectPatronToUpdateCombo.getSelectedIndex());
                        selectPatronToUpdateCombo.addItem(newValues.get(0) + " " + newValues.get(1));
                        selectPatronInfoPanel.revalidate();
                        selectPatronInfoPanel.repaint();
                    }

                    JOptionPane.showMessageDialog(null, "Patron`s info is updated.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class CheckBoxHandler implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            if (changeNameCB.isSelected() || assignReadBookCB.isSelected())
                updatePatronInfoPanel.add(updatePatronButtonPanel, BorderLayout.CENTER);
            else
                updatePatronInfoPanel.remove(updatePatronButtonPanel);

            if (e.getSource() == changeNameCB) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addComponent(selectPatronInfoPanel, updatePatronInfoGrid, updateFirstNameLabel,
                            0, updatePatronInfoGrid.getLayoutDimensions()[1].length, 1, 1);
                    addComponent(selectPatronInfoPanel, updatePatronInfoGrid, updateFirstNameTF,
                            1, updatePatronInfoGrid.getLayoutDimensions()[1].length, 1, 1);
                    addComponent(selectPatronInfoPanel, updatePatronInfoGrid, updateLastNameLabel,
                            0, updatePatronInfoGrid.getLayoutDimensions()[1].length + 1, 1, 1);
                    addComponent(selectPatronInfoPanel, updatePatronInfoGrid, updateLastNameTF,
                            1, updatePatronInfoGrid.getLayoutDimensions()[1].length + 1, 1, 1);
                    updatePatronInfoPanel.revalidate();
                    updatePatronInfoPanel.repaint();
                } else {
                    selectPatronInfoPanel.remove(updateFirstNameLabel);
                    selectPatronInfoPanel.remove(updateFirstNameTF);
                    selectPatronInfoPanel.remove(updateLastNameLabel);
                    selectPatronInfoPanel.remove(updateLastNameTF);
                    updatePatronInfoPanel.revalidate();
                    updatePatronInfoPanel.repaint();
                }
            } else if (e.getSource() == assignReadBookCB) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    addComponent(selectPatronInfoPanel, updatePatronInfoGrid, selectBookToAssignCombo,
                            0, updatePatronInfoGrid.getLayoutDimensions()[1].length, 2, 1);
                    updatePatronInfoPanel.revalidate();
                    updatePatronInfoPanel.repaint();
                } else {
                    selectPatronInfoPanel.remove(selectBookToAssignCombo);
                    updatePatronInfoPanel.revalidate();
                    updatePatronInfoPanel.repaint();
                }
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

    private void insertRow(List insertValues, String insertStatement) {
        try {
            //Connection to database
            connect();

            preparedStatement = mainConnection.prepareStatement(insertStatement);

            for (int i = 0; i < insertValues.size(); i++) {
                String currentValueClass = insertValues.get(i).getClass().getName();
                String currentValueType =
                        currentValueClass.substring(currentValueClass.lastIndexOf('.') + 1, currentValueClass.length());

                if (currentValueType.equals("String")) preparedStatement.setString(i + 1, insertValues.get(i).toString());
                else if (currentValueType.equals("Integer"))
                    preparedStatement.setInt(i + 1, Integer.parseInt(insertValues.get(i).toString()));
                else if (currentValueType.equals("Date"))
                    preparedStatement.setDate(i + 1, (java.sql.Date)insertValues.get(i));
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

    private ArrayList<Object> getListOfRows(String tableToSelectFrom, String valuesToSelect) {
        ArrayList<Object> rowsList = new ArrayList<>();

        try {
            //Connection to database
            connect();

            statement = mainConnection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT "
                    + valuesToSelect +
                    " FROM "
                    + tableToSelectFrom
                    + " ORDER BY "
                    + valuesToSelect);
            ResultSetMetaData metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                String nextRow = "";

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    nextRow += resultSet.getString(i) + " ";
                }
                rowsList.add(nextRow);
            }

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

        return rowsList;
    }

    private int getID(String tableToSelectFrom, String[] constraintFields, String[] constraintValues) {
        int id = -1;
        String query = "SELECT id FROM "
                + tableToSelectFrom
                + " WHERE ";

        if (constraintFields.length > 1) {
            for (int i = 0; i < constraintFields.length - 1; i++) {
                query += constraintFields[i] + " = \'" + constraintValues[i] + "\' AND ";
            }
        }

        query += constraintFields[constraintFields.length - 1]
                + " = \'" + constraintValues[constraintFields.length - 1] + "\'";

        try {
            //Connection to database
            connect();

            statement = mainConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            id = resultSet.getInt(1);

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

        return id;
    }

    public static void main(String args[]) {
        Lab4 sc = new Lab4();
        sc.setVisible(true);
        sc.setSize(750, 600);
        sc.setResizable(false);
        sc.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}