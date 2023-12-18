package tester;

import static utils.HibernateUtils.getFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.DepartmentDaoImpl;
import pojos.Department;

public class GetDepartmentAndEmpDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			DepartmentDaoImpl dao = new DepartmentDaoImpl();
			System.out.println("Enter dept name ");
			Department dept = dao.getDepartmentAndEmployeeDetails(sc.next());
			System.out.println("Dept details" + dept);
			System.out.println("Emp details");
			dept.getEmployees().forEach(System.out::println);
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
