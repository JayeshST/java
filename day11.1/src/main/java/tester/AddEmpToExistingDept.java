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

public class AddEmpToExistingDept {

	public static void main(String[] args) {
		try (SessionFactory sf = getFactory(); Scanner sc = new Scanner(System.in)) {
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			System.out.println("Enter dept id");
			Long deptId = sc.nextLong();
			System.out.println(
					"Enter new emp details : firstName,  lastName,  email,  password,  joinDate,emp type,  salary");
			Employee emp = new Employee(sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()),
					EmploymentType.valueOf(sc.next().toUpperCase()), sc.nextDouble());
			System.out.println(empDao.addEmployeeToExistingDept(deptId, emp));
		} // JVM : sf.close() --> DB CP --cleaned up , cns closed
		catch (Exception e) {
			e.printStackTrace();
			
	}

	}

}
