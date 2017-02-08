import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

  public class Calculator extends JFrame {

    private JButton btn[] = new JButton[20];
    private JButton bsp= new JButton("Backspace");
    private JButton ce= new JButton("CE");
    private JButton clear= new JButton("Clear");
    int index  = 0;

    private String  lblName[]= {
                                "7","8","9","/","sqrt",
                                "4","5","6","*","%",
                                "1","2","3","-","1/x",
                                "0","+/-",".","+","="
                               };


    private JTextField tDisplay = new JTextField(20);

    public Calculator() {

	    //Set the GridBagLayout for the frame
	    GridBagLayout gridBag = new GridBagLayout();
	    setLayout(gridBag);
	
	    //constraints for the text field display
	    GridBagConstraints Constr1 = new GridBagConstraints();
	    Constr1.gridx = 0; //first column
	    Constr1.gridy = 0; //first row
	    Constr1.gridwidth = 5; //number of cells in the row that will be covered
	    Constr1.gridheight = 1; //number of cells in the column
	    Constr1.fill = GridBagConstraints.HORIZONTAL; //resize the component horozontally
	    // add the text field
	    gridBag.setConstraints(tDisplay, Constr1); //apply the constraints to the grid
	    add(tDisplay);
	
	
	    //constraints for the backspace button
	    GridBagConstraints Constr2 = new GridBagConstraints();
	    Constr2.gridx = 0; //first column
	    Constr2.gridy = 1; //second row
	
	    Constr2.gridwidth = 2; //number of cells in the row that will be covered
	    Constr2.gridheight = 1; //number of cells in the column
	    Constr2.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
	    Constr2.insets = new Insets(3, 3, 3, 3);
	    // //add the backspace button
	    gridBag.setConstraints(bsp, Constr2);
	    add(bsp);
	
	    // constraints for the CE button
	    GridBagConstraints Constr3 = new GridBagConstraints();
	    Constr3.gridx = 2; //starts at third column
	    Constr3.gridy = 1; //second row
	
	    Constr3.gridwidth = 1; //number of cells in the row that will be covered
	    Constr3.gridheight = 1; //number of cells in the column
	    Constr3.fill = GridBagConstraints.HORIZONTAL; //resize the component horizontally
	    Constr3.insets = new Insets(3, 3, 3, 3);
	    // add the CE button
	    gridBag.setConstraints(ce, Constr3);
	    add(ce);
	
	    // constraints the clear button
	    GridBagConstraints Constr4 = new GridBagConstraints();
	    Constr4.gridx = 3; //starts at fourth column
	    Constr4.gridy = 1; //second row
	
	    Constr4.gridwidth = 2;
	    Constr4.gridheight = 1;
	    Constr4.fill = GridBagConstraints.BOTH; //make the component fill its display area entirely
	    Constr4.insets = new Insets(3, 3, 3, 3);
	
	    // add the clear button
	    gridBag.setConstraints(clear, Constr4);
	    add(clear);
	
	    //add the numerical pad
	    for(int i = 1; i<=4;i++)
	    {
	      for(int j=1;j<=5;j++)
	      {
	    	  GridBagConstraints constrNum = new GridBagConstraints();
	          constrNum.gridx = j-1; //column number
	          constrNum.gridy = i+1; //row number
	          constrNum.gridwidth = 1;
	          constrNum.gridheight = 1;
	          constrNum.fill = GridBagConstraints.BOTH;
//			  constrNum.weightx = 0.1;
//			  constrNum.weighty = 0.1;
	          //determine the space between the component and the edges of its display area
	          constrNum.insets = new Insets(3, 3, 3, 3); //inset from the top,left,bottom, right
	
	          btn[index] = new JButton(lblName[index]);
	
	          gridBag.setConstraints(btn[index], constrNum);
	          add(btn[index]);
	          index = index+1;
	
		  }
		}
	  

  } // constructor
  public static void main(String[] args) {

		Calculator calc = new Calculator();
		calc.setTitle("Calculator");
		calc.setSize(300,300);
		calc.setVisible(true);
	}


} //end of SETCalculator
