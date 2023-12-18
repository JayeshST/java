package dao;

import pojos.Department;

public interface DepartmentDao {
	String addNewDepartment(Department dept);
	String deleteDepartmentDetails(Long deptId);
	Department getDepartmentDetails(String deptName);
	Department getDepartmentAndEmployeeDetails(String deptName);
}
