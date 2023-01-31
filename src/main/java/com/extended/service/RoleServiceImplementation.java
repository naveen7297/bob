package com.extended.service;

import java.util.InputMismatchException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.extended.bean.Role;
import com.extended.reponse.FinalResponse;
import com.extended.rolerepository.RoleRepository;
import com.extended.service.RoleService;

@Service
public class RoleServiceImplementation implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImplementation.class);

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public FinalResponse InsertingOneRoleRecord(Role role) {
		logger.info("InsertingOneRoleRecord::input::role:" + role);

		FinalResponse finalResponse = new FinalResponse();
		List<String> roles = roleRepository.FindByRoleName();
		String Duplicate = "  ";
		for (int i = 0; i < roles.size(); i++) {

			if (role.getRoleName().equals(roles.get(i))) {
				Duplicate = roles.get(i);
				finalResponse.setMessage("RoleName Is Already Taken");
				return finalResponse;
			}
		}

		if (Duplicate != role.getRoleName()) {
			try {

				roleRepository.InsertingOneRoleRecord(role);
				finalResponse.setStatus(HttpStatus.OK);
				finalResponse.setStatusCode("200");
				finalResponse.setMessage("One Record Is Inserted");
				return finalResponse;
			} catch (InputMismatchException ex) {
				logger.error("InputMismatchException::input::role");
			}

		}
		return finalResponse;
	}

	@Override
	public FinalResponse InsertingMultipleRoleRecord(List<Role> role) {
		logger.info("InsertingMultipleRoleRecord:: input:: role: " + role);
		FinalResponse finalResponse = new FinalResponse();
		List<String> roles = roleRepository.FindByRoleName();
		String Duplicate = "  ";
		String Original = "";

		for (int i = 0; i < roles.size(); i++) {
			for (int j = 0; j < role.size(); j++) {

				if (role.get(j).getRoleName().equals(roles.get(i))) {
					Original = roles.get(i);
					Duplicate = role.get(j).getRoleName();
					System.out.println(Duplicate);
					finalResponse.setMessage("RoleName Is Already Taken");
					return finalResponse;
				}

			}
		}

		if (Duplicate != Original) {
			try {
				roleRepository.InsertingMultipleRoleRecord(role);
			} catch (InputMismatchException e) {
				logger.error("InputMismatchException:: input:: role:" + e.getMessage());
			}
			if (role != null) {
				finalResponse.status = HttpStatus.OK;
				finalResponse.statusCode = "200";
				finalResponse.message = "Records are Inserted";
				return finalResponse;
			}
		}
		finalResponse.status = HttpStatus.NOT_IMPLEMENTED;
		finalResponse.statusCode = "500";
		finalResponse.message = "Records are not Inserted";
		return finalResponse;
	}

	@Override
	public FinalResponse GetOneRoleRecord(int roleId) {
		logger.info("InsertingMultipleRoleRecord:: input:: role: " + roleId);
		FinalResponse finalResponse = new FinalResponse();
		Object[] role = null;
		try {
			role = roleRepository.GetOneRoleRecord(roleId);
		} catch (InputMismatchException e) {
			logger.error("InputMismatchException:: input:: role:" + e.getMessage());
		}
		if (role != null) {
			finalResponse.status = HttpStatus.OK;
			finalResponse.statusCode = "200";
			finalResponse.message = " Record was present";
			finalResponse.data = role;
			return finalResponse;

		} else {
			finalResponse.status = HttpStatus.NOT_FOUND;
			finalResponse.statusCode = "404";
			finalResponse.message = "  Record is Not  present";
			return finalResponse;
		}
	}
	@Override
	public FinalResponse GetAllRoleRecords() {
		logger.info("GetAllRoleRecords:: input:: role: ");
		FinalResponse finalResponse = new FinalResponse();
		List<Role> role = null;
		try {
			role = roleRepository.GetAllRoleRecords();
		} catch (InputMismatchException e) {
			logger.error("InputMismatchException:: input:: role:" + e.getMessage());
		}
		if (role != null) {
			finalResponse.status = HttpStatus.OK;
			finalResponse.statusCode = "200";
			finalResponse.message = " All Records are present";
			finalResponse.datas = role;
			return finalResponse;
		} else {
			finalResponse.status = HttpStatus.NOT_FOUND;
			finalResponse.statusCode = "404";
			finalResponse.message = "  Records are not present";
			return finalResponse;
		}
	}

	@Override
	public FinalResponse DeleteingRoleRecord(int roleId) {
		logger.info("DeleteingRoleRecord:: input:: role: " + roleId);
		FinalResponse finalResponse = new FinalResponse();
		try {
			roleRepository.DeleteingRoleRecord(roleId);
		} catch (InputMismatchException e) {
			logger.error("InputMismatchException:: input:: role:" + e.getMessage());
		}
		if (roleId > 0) {
			finalResponse.status = HttpStatus.OK;
			finalResponse.statusCode = "200";
			finalResponse.message = " Record was deleted";
			return finalResponse;

		} else {
			finalResponse.status = HttpStatus.NOT_FOUND;
			finalResponse.statusCode = "404";
			finalResponse.message = " Record was not deleted";
			finalResponse.errorMessages = "check RoleId Once";
			return finalResponse;
		}
	}

	@Override
	public FinalResponse UpadatingRoleRecord(Role role) {
		logger.info("UpadatingRoleRecord:: input:: role: " + role);
		FinalResponse finalResponse = new FinalResponse();
		try {

			roleRepository.UpadatingRoleRecord(role);
		} catch (InputMismatchException e) {
			logger.error("InputMismatchException:: input:: role:" + e.getMessage());
		}
		if (role != null) {
			finalResponse.status = HttpStatus.OK;
			finalResponse.statusCode = "200";
			finalResponse.message = " Record was Updated";
			return finalResponse;
		} else {
			finalResponse.status = HttpStatus.NOT_IMPLEMENTED;
			finalResponse.statusCode = "500";
			finalResponse.message = "Record was not updated";
			finalResponse.errorMessages = "Check All fields";
			return finalResponse;
		}
	}
}
