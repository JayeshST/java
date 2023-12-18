package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.AddressDaoImpl;
import dao.DepartmentDaoImpl;
import pojos.Address;
import pojos.Department;

public class AssignEmpAddress {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); 
				Scanner sc = new Scanner(System.in)) {
			AddressDaoImpl dao=new AddressDaoImpl();
			System.out.println("Enter emp id");
			Long empId=sc.nextLong();
			System.out.println("Enter address details : street,  city,  state,  country,  zipCode");
			System.out.println(dao.assignAddressToEmp(empId, new Address
					(sc.next(), sc.next(), sc.next(), sc.next(), sc.next())));
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
