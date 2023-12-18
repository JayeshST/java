package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Department;
import static utils.HibernateUtils.getFactory;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public String addNewDepartment(Department dept) {
		String mesg = "Adding dept failed!!!!!!!!";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			session.persist(dept);
			// dept : persistent
			tx.commit();
			mesg = "Added new dept with ID " + dept.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	}

	@Override
	public String deleteDepartmentDetails(Long deptId) {
		String mesg = "deleting dept details failed !!!!";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// get dept details from it's id
			Department dept = session.get(Department.class, deptId);
			if (dept != null) {
				// dept : persistent
				session.delete(dept);// dept : REMOVED --marked for removal
				mesg = "Deleted dept n asso emps .....";
			}
			tx.commit();//session.flush--auto dirty chking --> casading --> delete emp details n then dept details
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return mesg;
	}

	@Override
	public Department getDepartmentDetails(String deptName) {
		Department dept=null;
		String jpql="select d from Department d where d.name=:name";
		// 1. get session from SF
		Session session=getFactory().getCurrentSession();
		//2. begin a tx
		Transaction tx=session.beginTransaction();
		try {
			dept=session.createQuery(jpql, Department.class)
					.setParameter("name", deptName)
					.getSingleResult();
			//dept : persistent
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
	
		return dept;//detached
	}
	@Override
	public Department getDepartmentAndEmployeeDetails(String deptName) {
		Department dept=null;
	//	String jpql="select d from Department d where d.name=:name";
		String jpql="select d from Department d left join fetch d.employees where d.name=:name";
		// 1. get session from SF
		Session session=getFactory().getCurrentSession();
		//2. begin a tx
		Transaction tx=session.beginTransaction();
		try {
			dept=session.createQuery(jpql, Department.class)
					.setParameter("name", deptName)
					.getSingleResult();//select : depts
			//dept : persistent
			//Hint : simply access the size of the collection
	//		dept.getEmployees().size();//select : emps
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			throw e;
		}
	
		return dept;//detached
	}
	

}
