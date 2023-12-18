package tester;

import static utils.HibernateUtils.getFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.DepartmentDaoImpl;
import dao.EmployeeDaoImpl;
import pojos.Department;
import pojos.Employee;
import pojos.EmploymentType;

public class RemoveEmpDetails {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter dept id n emp id");
			System.out.println(empDao.removeEmpDetails(sc.nextLong(), sc.nextLong()));
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
