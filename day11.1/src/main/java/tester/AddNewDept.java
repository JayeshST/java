package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.DepartmentDaoImpl;
import pojos.Department;

public class AddNewDept {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); 
				Scanner sc = new Scanner(System.in)) {
			DepartmentDaoImpl dao = new DepartmentDaoImpl();
			System.out.println("Enter dept details : name location");
			System.out.println(dao.addNewDepartment(new Department(sc.next(), sc.next())));
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
