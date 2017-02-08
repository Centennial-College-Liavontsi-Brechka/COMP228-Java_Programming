import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Lab3 extends JFrame{
	static Lab3 frame;
	JButton calculateButton;
	JComboBox <String> numberOfCoursesCombo;
	JComboBox[] creditHoursCombo, gradeCombo;
	JTextField[] courseCodes;
	JLabel creditsL, currentGPA_L, numberOfCoursesL, nrL, numberOfRow, courseCodeL, creditHoursL, gradeL, finalGPA;
	JPanel topPanel, topPanelUpper, topPanelLower, coursePanel, buttonPanel, finalGPAPanel, bottomPanel;
	JTextField creditsTF, currentGPA_TF;
	String [] creditHours = {"1", "2", "3", "4"};
	String [] gradeNames = {"A+: 90-100%","A:   80-89%", "B+: 75-79%","B:   70-74%","C+: 65-69%",
			"C:   60-64%","D+: 55-59%", "D:   50-54%", "F:   0-49%"};
	double[] gradePoints = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0};
	ListenerClass listener;
	Color importantFieldsColor;

	public Lab3()
	{
		setLayout( new BorderLayout());
		topPanel = new JPanel();
		topPanelUpper = new JPanel();
		topPanelLower = new JPanel();
		coursePanel = new JPanel();
		buttonPanel = new JPanel();
		finalGPAPanel = new JPanel();
		bottomPanel = new JPanel();
		listener = new ListenerClass();
		importantFieldsColor = new Color(194, 221, 129);

		creditsL = new JLabel("Credit hours earned:");
		creditsL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		currentGPA_L = new JLabel("Current GPA:");
		currentGPA_L.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		creditsTF = new JTextField (4);
		creditsTF.setBackground(importantFieldsColor);
		currentGPA_TF = new JTextField (4);
		currentGPA_TF.setBackground(importantFieldsColor);
		topPanelUpper.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topPanelUpper.add(creditsL);
		topPanelUpper.add(creditsTF);
		topPanelUpper.add(currentGPA_L);
		topPanelUpper.add(currentGPA_TF);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(topPanelUpper, BorderLayout.NORTH);

		numberOfCoursesL = new JLabel("Number of courses");
		numberOfCoursesL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		String[] courses = {"0", "1", "2", "3", "4", "5", "6"};
		numberOfCoursesCombo = new JComboBox<>(courses);
		numberOfCoursesCombo.setEditable(true);
		numberOfCoursesCombo.getEditor().getEditorComponent().setBackground(importantFieldsColor);
		numberOfCoursesCombo.addActionListener(listener);
		topPanelLower.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topPanelLower.add(numberOfCoursesL);
		topPanelLower.add(numberOfCoursesCombo);
		topPanel.add(topPanelLower, BorderLayout.SOUTH);
		topPanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(topPanel, BorderLayout.NORTH);

		nrL = new JLabel ("", SwingConstants.CENTER);
		nrL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		courseCodeL = new JLabel ("", SwingConstants.CENTER);
		courseCodeL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		creditHoursL = new JLabel("", SwingConstants.CENTER);
		creditHoursL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		gradeL = new JLabel ("", SwingConstants.CENTER);
		gradeL.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		coursePanel.setLayout(new GridLayout(0, 4, 3, 3));
		coursePanel.add(nrL);
		coursePanel.add(courseCodeL);
		coursePanel.add(creditHoursL);
		coursePanel.add(gradeL);
		coursePanel.setBorder(new EmptyBorder(0, 20, 0, 20));
		add(coursePanel, BorderLayout.CENTER);

		calculateButton = new JButton ("Calculate");
		calculateButton.addActionListener(listener);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(calculateButton);
		finalGPA = new JLabel("Final GPA: ");
		finalGPA.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		finalGPAPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		finalGPAPanel.add(finalGPA);
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(buttonPanel, BorderLayout.NORTH);
		bottomPanel.add(finalGPAPanel, BorderLayout.SOUTH);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		frame = new Lab3();
		frame.setTitle("GPA Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(609, 188);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	private class ListenerClass implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int numberOfRows = Integer.parseInt(numberOfCoursesCombo.getSelectedItem().toString());

				if (e.getSource() == numberOfCoursesCombo) {
					coursePanel.removeAll();

					nrL.setText("Nr.");
					courseCodeL.setText("Course Code:");
					creditHoursL.setText("Credit Hours:");
					gradeL.setText("Grade:");

					if (numberOfRows != 0){
						nrL.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
						coursePanel.add(nrL);
						courseCodeL.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
						coursePanel.add(courseCodeL);
						creditHoursL.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
						coursePanel.add(creditHoursL);
						coursePanel.add(gradeL);
					}

					courseCodes = new JTextField[numberOfRows];
					for (int i = 0; i < numberOfRows; i++) {
						courseCodes[i] = new JTextField(6);
						courseCodes[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
					}
					creditHoursCombo = new JComboBox[numberOfRows];
					for (int i = 0; i < numberOfRows; i++) {
						creditHoursCombo[i] = new JComboBox<>(creditHours);
						creditHoursCombo[i].setEditable(true);
						creditHoursCombo[i].getEditor().getEditorComponent().setBackground(importantFieldsColor);
						creditHoursCombo[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
					}
					gradeCombo = new JComboBox[numberOfRows];
					for (int i = 0; i < numberOfRows; i++) {
						gradeCombo[i] = new JComboBox<>(gradeNames);
						gradeCombo[i].setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black));
					}

					for (int i = 0; i < numberOfRows; i++) {
						numberOfRow = new JLabel(" " + Integer.toString(i + 1), SwingConstants.CENTER);
						numberOfRow.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 1, Color.black));
						coursePanel.add(numberOfRow);
						coursePanel.add(courseCodes[i]);
						coursePanel.add(creditHoursCombo[i]);
						coursePanel.add(gradeCombo[i]);
					}

					coursePanel.revalidate();
					if (numberOfRows == 0) frame.setSize(609, 188);
					else frame.pack();

				} else if (e.getSource() == calculateButton) {
					double currentGPA = Double.parseDouble(currentGPA_TF.getText());
					int currentCreditsEarned = Integer.parseInt(creditsTF.getText());
					double currentQualityPoints = currentCreditsEarned * currentGPA;

					int newCreditsEarned = 0;
					double newQualityPoints = 0.0;
					for (int i = 0; i < numberOfRows; i++) {
						newCreditsEarned += Double.parseDouble(creditHoursCombo[i].getSelectedItem().toString());
						newQualityPoints += Double.parseDouble(creditHoursCombo[i].getSelectedItem().toString()) *
								gradePoints[gradeCombo[i].getSelectedIndex()];
					}

					finalGPA.setText("<html><u>Final GPA: " +
							String.format("%.2f",((currentQualityPoints + newQualityPoints) /
									(currentCreditsEarned + newCreditsEarned))) + "</u>");
				}
			} catch (NumberFormatException | NegativeArraySizeException e1) {
				JOptionPane.showMessageDialog(frame,
						"Please, check values in next boxes:\n" +
						" - Credit hours earned (whole number and >0)\n" +
						" - Current GPA (float number and >=0)\n" +
						" - Number of courses (whole number and >0)\n" +
						" - Credit hours box for each course (whole number and >0)",
						"Wrong Input",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}