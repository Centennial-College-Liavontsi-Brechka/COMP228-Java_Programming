import javax.swing.JOptionPane;

public class TestPatient {

	public static void main(String[] args) {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Enter a patient ID:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE));
			String firstName = JOptionPane.showInputDialog(null,
					"Enter a patient First Name:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);
			String lastName = JOptionPane.showInputDialog(null,
					"Enter a patient Last Name:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);
			String address = JOptionPane.showInputDialog(null,
					"Enter a patient address:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);
			String city = JOptionPane.showInputDialog(null,
					"Enter a patient city:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);
			String province = JOptionPane.showInputDialog(null,
					"Enter a patient province:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);
			String postalCode = JOptionPane.showInputDialog(null,
					"Enter a patient postalCode:",
					"Patient information input",
					JOptionPane.QUESTION_MESSAGE);

			Patient patient = new Patient(id, firstName, lastName,
					address, city, province.toUpperCase(), postalCode.toUpperCase());

			JOptionPane.showMessageDialog(null,
					patient.getPatientInfo(),
					"Patient information",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException numFormEx){
			JOptionPane.showMessageDialog(null, "Please, enter an integer number!\nTry again.", "Wrong data input!", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException nullPointEx){
			JOptionPane.showMessageDialog(null, "Please, provide all info!\nTry again.", "Some data missed!", JOptionPane.ERROR_MESSAGE);
		}
	}

}
