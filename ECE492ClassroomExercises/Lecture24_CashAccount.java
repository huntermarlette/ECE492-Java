import java.math.BigDecimal;
import java.math.MathContext;

public abstract class Lecture24_CashAccount extends Lecture20_Account
{
private double balance;

public Lecture24_CashAccount() throws Exception
	{

	}

public Lecture24_CashAccount(String customerName) throws Exception
	{
	super(customerName);// call PARENT constructor 
		
	}

public double getBalance()
    {
	return balance;
    }

public synchronized void deposit(double amount) throws IllegalArgumentException
   {
	if (amount < 0) throw new IllegalArgumentException("deposit amount must be positive.");
	balance += amount;
   }

public synchronized void withdraw(double amount) throws IllegalArgumentException, Lecture24_OverdraftException
   {
	if (amount < 0) throw new IllegalArgumentException("withdraw amount must be positive.");
	if (amount > balance) throw new Lecture24_OverdraftException();
	balance -= amount;
   }

public String toString()   
   {
    BigDecimal balanceBD = new BigDecimal(balance, MathContext.DECIMAL32); //sets precision to 7 digits
    balanceBD = balanceBD.setScale(2, BigDecimal.ROUND_UP); // 2 decimal digits
    
	return super.toString() + " $" + balanceBD.toPlainString(); // no exponentials! 
   }
}
