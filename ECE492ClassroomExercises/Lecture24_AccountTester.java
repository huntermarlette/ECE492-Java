
public class Lecture24_AccountTester
{
public static void main(String[] args)  throws Exception
	{
	Lecture24_CheckingAccount a1 = new Lecture24_CheckingAccount();
    a1.setCustomerName(args[0]);
    a1.deposit(50);
    a1.withdraw(25);
    System.out.println(a1.getCustomerName() + " "
                     + a1.getAccountNumber() + " "
                     + " fees paid are $" + a1.getFeesPaid()     + " "
                     + " balance is $" + a1.getBalance());
    try {
        a1.payFee(30);
        }
    catch(Lecture24_OverdraftException oe)
       {
    	System.out.println("payFee failure due to insufficient funds."); 
       }
    a1.deposit(10);
    try {
        a1.payFee(30);
        }
    catch(Lecture24_OverdraftException oe)
       {
    	System.out.println("payFee failure due to insufficient funds."); 
       }
    System.out.println(a1.getCustomerName() + " "
            + a1.getAccountNumber() + " "
            + " fees paid are $" + a1.getFeesPaid()     + " "
            + " balance is $" + a1.getBalance());

    Lecture24_SavingsAccount a2 = new Lecture24_SavingsAccount("Barnes,Betty");
    a2.deposit(60);
    a2.addInterest(20);
    double withdrawAmount = 200;
    try {
        a2.withdraw(withdrawAmount);
        }
    catch(Lecture24_OverdraftException oe)
        {
    	System.out.println("Withdraw of $" + withdrawAmount + " denied on account #" + a2.getAccountNumber()
    	                + " due to insufficient funds.");
        }
    System.out.println(a2); // println calls toString() on OBJECTS 

	}
}
