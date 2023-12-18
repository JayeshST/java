package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import static utils.HibernateUtils.getFactory;

import pojos.Department;
import pojos.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public String addEmployeeToExistingDept(Long deptId, Employee emp) {
		String mesg = "Adding emp to dept failed !!!!!!!!";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// 3. get dept from it's id
			Department dept = session.get(Department.class, deptId);
			if (dept != null) {
				// dept : persistent
				dept.addEmployee(emp);// setting up bi dir link
				// since no casacading yet : have to expl call persist
				// session.persist(emp);
				mesg = "Added new emp to dept : " + dept.getName();
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	}

	@Override
	public String removeEmpDetails(Long deptId, Long empId) {
		String mesg="Removing emp details failed !!!";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			//get dept from it's id
			Department dept=session.get(Department.class, deptId);
			Employee emp=session.get(Employee.class, empId);
			if(dept != null && emp != null)
			{
				//dept n emp : persistent
				dept.removeEmployee(emp);
				mesg="Removed emp : "+emp.getFirstName()+" from dept : "+dept.getName();
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	}

}
