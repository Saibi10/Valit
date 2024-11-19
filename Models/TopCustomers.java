package Models;

public class TopCustomers {
	public String Customer;
	
	public String nobColumn;
	
	public String tapColumn;
	
	public TopCustomers(String Customer , String nobColumn , String tapColumn)
	{
		this.Customer = Customer;
		this.nobColumn = nobColumn;
		this.tapColumn = tapColumn;
	}
	
	public String getCustomer() {
		return Customer;
	}
	
	public String getNumberofBookings() {
		return nobColumn;
	}
	
	public String getTotalAmountPaid()
	{
		return tapColumn;
	}
	
}
