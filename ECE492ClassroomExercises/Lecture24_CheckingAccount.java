
public class Lecture24_CheckingAccount extends Lecture24_CashAccount
{
private double feesPaid;

public Lecture24_CheckingAccount() throws Exception
	{
	super();
	}

public Lecture24_CheckingAccount(String customerName) throws Exception
	{
	super(customerName);

	}

public double getFeesPaid()
    {
	return feesPaid;
    }

public void payFee(double amount) throws Lecture24_OverdraftException
    {
	if (amount < 0) throw new IllegalArgumentException("Fee amount must be positive.");
    withdraw(amount); // subtract fee from account balance
    feesPaid += amount; // keep track f total fees paid.
    }

public String toString()
   {
   return super.toString() + " total fees paid $" + getFeesPaid(); 
   }
}
