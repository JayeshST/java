package dao;

import pojos.Employee;

public interface EmployeeDao {
	String addEmployeeToExistingDept(Long deptId, Employee emp);
	String removeEmpDetails(Long deptId,Long empId);
}
