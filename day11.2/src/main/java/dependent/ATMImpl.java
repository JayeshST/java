package dependent;

import dependency.Transport;

public class ATMImpl implements ATM {
	private Transport myTransport;// =new HttpTransport();//dep

	public ATMImpl() {
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		myTransport.informBank(data);
	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		myTransport.informBank(data);// atm init (depnt) is calling depencency (transparent layer)
	}

	// setter Based Dependency Injection (D.I)
	// add a setter per dependency + in the pure xml approach add a <bean> :add a
	// <property name= "myTransport" ref="test"/>: 1 per dependency
	public void setMyTransport(Transport myTransport) {
		this.myTransport = myTransport;
		System.out.println("in setter " + this.myTransport);
	}

//custom init method 
	public void anyInit() // throws exceptions
	{

		// sc will invoke this method after D.I for singleton as well as prototype
		System.out.println("in init " + myTransport);

	}

	// custom destroy method
	public void anyDestroy() // throws exceptions
	{
		// sc will invoke this method just before garbage collectiong of singletons
		// beans

		System.out.println("in destroy " + myTransport);

	}
	
	
	
	

}