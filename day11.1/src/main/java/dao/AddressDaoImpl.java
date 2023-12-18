package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Address;
import pojos.Employee;

import static utils.HibernateUtils.getFactory;

public class AddressDaoImpl implements AddressDao {

	@Override
	public String assignAddressToEmp(Long empId, Address adr) {
		String mesg="assigning adr failed !";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			//3. get emp from it's id
			Employee employee=session.get(Employee.class, empId);
			if(employee != null)
			{
				//link Address --> Emp
				adr.setEmp(employee);
				session.persist(adr);
				mesg="adr liked to emp :  "+employee.getLastName();
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
