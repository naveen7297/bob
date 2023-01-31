package com.extended.rolerepository;

import java.util.List;

import com.extended.bean.Role;

public interface RoleRepository {

	public String InsertingOneRoleRecord(Role role);

	public String InsertingMultipleRoleRecord(List<Role> role);

	public Object[] GetOneRoleRecord(int roleId);

	public List<Role> GetAllRoleRecords();

	public String DeleteingRoleRecord(int id);

	public String UpadatingRoleRecord(Role role);

	public List<String> FindByRoleName();

}
