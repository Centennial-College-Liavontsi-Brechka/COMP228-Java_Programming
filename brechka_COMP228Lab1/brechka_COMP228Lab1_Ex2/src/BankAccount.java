
public class BankAccount {
    private int accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(int accountNumber, String ownerName, double initialBalance){
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        depositMoney(initialBalance);
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public double getBalance() {
        return balance;
    }

    public String getAccountInfo(){
        return String.format("\n--------------------\n" +
                "Account number: %d" +
                "\nOwner\'s name: %s" +
                "\nBalance: $%,.2f" +
                "\n--------------------\n", this.accountNumber, this.ownerName, this.balance);
    }

    public String depositMoney(double depositAmount){
        if (depositAmount > 0)
        {
            this.balance += depositAmount;
            return String.format("\n--------------------\n" +
                    "Thank you!" +
                   "\nYour balance: $%,.2f" +
                    "\n--------------------\n", this.balance);
        } else
        {
            return String.format("\n--------------------\n" +
                    "Wrong amount to deposit!" +
                    "\nPlease, enter positive amount." +
                    "\n--------------------\n");
        }
    }

    public String withdrawMoney(double withdrawAmount)
    {
        if (withdrawAmount <= this.balance && withdrawAmount > 0)
        {
            this.balance -= withdrawAmount;
            return String.format("\n--------------------\n" +
                    "Thank you!" +
                    "\nYou successfully withdrawn $%,.2f from your account!" +
                    "\nYour balance: $%,.2f" +
                    "\n--------------------\n", withdrawAmount, this.balance);
        } else
        {
            return String.format("\n--------------------\n" +
                    "Wrong amount to withdraw!" +
                    "\nPlease, enter positive amount that less or equal your balance!" +
                    "\n--------------------\n");
        }
    }
}
