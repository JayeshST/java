package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.DepartmentDaoImpl;
import pojos.Department;

public class GetDepartmentDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			DepartmentDaoImpl dao = new DepartmentDaoImpl();
			System.out.println("Enter dept name ");
			Department dept = dao.getDepartmentDetails(sc.next());
			System.out.println("Dept details" + dept);		
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
