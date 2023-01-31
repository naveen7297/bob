package com.extended.service;

import java.util.List;

import com.extended.bean.Role;
import com.extended.reponse.FinalResponse;


public interface RoleService {
	public FinalResponse InsertingOneRoleRecord(Role role);

	public FinalResponse InsertingMultipleRoleRecord(List<Role> role);

	public FinalResponse GetOneRoleRecord(int roleId) ;

	public FinalResponse GetAllRoleRecords() ;

	public FinalResponse DeleteingRoleRecord(int roleId);

	public FinalResponse UpadatingRoleRecord(Role role);
}
