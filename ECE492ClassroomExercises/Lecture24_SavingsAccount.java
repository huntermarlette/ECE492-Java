
public class Lecture24_SavingsAccount extends Lecture24_CashAccount
{
private double interestReceived;

public Lecture24_SavingsAccount() throws Exception
	{
	super();
	}

public Lecture24_SavingsAccount(String customerName) throws Exception
	{
	super(customerName);
	}

public double getInterestReceived()
    {
	return interestReceived;
    }

public void addInterest(double amount) 
    {
	if (amount < 0) throw new IllegalArgumentException("Interest amount must be positive.");
    deposit(amount); // subtract fee from account balance
    interestReceived += amount; // keep track f total fees paid.
    }
public String toString()
    {
	return super.toString() + " total interest accrued $" + getInterestReceived(); 
    }
}
