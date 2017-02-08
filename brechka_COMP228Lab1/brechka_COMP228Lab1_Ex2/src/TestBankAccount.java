import javax.swing.JOptionPane;

public class TestBankAccount {

	public static void main(String[] args) {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog(null,
					"Enter an account number:",
					"Bank account information input",
					JOptionPane.QUESTION_MESSAGE));
			String ownerName = JOptionPane.showInputDialog(null,
					"Enter an owner Name:",
					"Bank account information input",
					JOptionPane.QUESTION_MESSAGE);
			double initialDeposit = Double.parseDouble(JOptionPane.showInputDialog(null,
					"Enter initial amount:",
					"Bank account information input",
					JOptionPane.QUESTION_MESSAGE));

			BankAccount account = new BankAccount(id, ownerName, initialDeposit);

			int depositResponse = JOptionPane.showConfirmDialog(null, "Do you want make a deposit?");
			if (depositResponse == JOptionPane.YES_OPTION)
			{
				int depositAmount = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Enter amount to deposit:",
						"Deposit amount enter",
						JOptionPane.QUESTION_MESSAGE));
				JOptionPane.showMessageDialog(null,
						account.depositMoney(depositAmount),
						"Deposit procedure",
						JOptionPane.INFORMATION_MESSAGE);
			}

			int withdrawResponse = JOptionPane.showConfirmDialog(null, "Do you want withdraw money?");
			if (withdrawResponse == JOptionPane.YES_OPTION)
			{
				int withdrawAmount = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Enter amount to withdraw:",
						"Withdraw amount enter",
						JOptionPane.QUESTION_MESSAGE));
				JOptionPane.showMessageDialog(null,
						account.withdrawMoney(withdrawAmount),
						"Withdraw procedure",
						JOptionPane.INFORMATION_MESSAGE);
			}


			JOptionPane.showMessageDialog(null,
					account.getAccountInfo(),
					"Bank account information",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException numFormEx){
			JOptionPane.showMessageDialog(null, "Please, enter a double number!\nTry again.", "Wrong data input!", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException nullPointEx){
			JOptionPane.showMessageDialog(null, "Please, provide all info!\nTry again.", "Some data missed!", JOptionPane.ERROR_MESSAGE);
		}
	}

}
