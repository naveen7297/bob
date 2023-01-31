package com.extended.repositoryimpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.extended.bean.Role;
import com.extended.rolerepository.RoleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class RepositoryImpl implements RoleRepository {
	@Autowired
	private EntityManager entityManager;

	
	@Transactional
	@Override
	public String InsertingOneRoleRecord(Role role) {
		Date date = new Date();
		Query query = entityManager.createNativeQuery("insert into Role values(?,?,?,?,?)");
		query.setParameter(1, role.getRoleId());
		query.setParameter(2, role.getRoleName());

		query.setParameter(3, role.getRoleType());
		query.setParameter(4, date);

		query.setParameter(5, role.getUpdatedDate());
		query.executeUpdate();

		return "one record is inserted";
	}
	

	@Override
	public Object[] GetOneRoleRecord(int roleId) {
		Query query = entityManager.createNativeQuery("select * from role where role_id=?");
		query.setParameter(1, roleId);
		Object[] role = (Object[]) query.getSingleResult();

		return role;
	}

	@Override
	public List<Role> GetAllRoleRecords() {
		Query query = entityManager.createNativeQuery("select * from role");
		@SuppressWarnings("unchecked")
		List<Role> role = query.getResultList();

		return role;
	}

	
	@Override
	@Transactional
	public String DeleteingRoleRecord(int id) {
		Query query = entityManager.createNativeQuery("delete from role where role_id=?");
		query.setParameter(1, id);
		int Rec = query.executeUpdate();
		if (Rec > 0) {
			return "record was  Deleted";

		}

		return "record was not Deleted";
	}

	@Override
	@Transactional
	public String InsertingMultipleRoleRecord(List<Role> role) {

		Date date = new Date();
		for (Role roles : role) {
			Query query = entityManager.createNativeQuery("insert into Role values(?,?,?,?,?)");
			query.setParameter(1, roles.getRoleId());
			query.setParameter(2, roles.getRoleName());

			query.setParameter(3, roles.getRoleType());
			query.setParameter(4, date);

			query.setParameter(5, roles.getUpdatedDate());
			query.executeUpdate();

		}

		return "All Records Are Inserted";

	}

	@Override
	@Transactional
	public String UpadatingRoleRecord(Role role) {
		Date date = new Date();
		Query query = entityManager
				.createNativeQuery("update Role set role_type=?,role_name=?,updated_date=? where role_id=?");
		query.setParameter(1, role.getRoleType());
		query.setParameter(2, role.getRoleName());

		query.setParameter(3, date);
		query.setParameter(4, role.getRoleId());
		int Rec = query.executeUpdate();
		if (Rec > 0) {
			return "record was updated";
		}

		return "record was Not Updated";
	}

	@Override
	public List<String> FindByRoleName() {

		Query query = entityManager.createNativeQuery("select  role_Name from role");
		List<String> roles = query.getResultList();
		return roles;
	}

}
