package com.extended.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import com.extended.bean.Role;
import com.extended.reponse.FinalResponse;
import com.extended.service.RoleService;

@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {
	@InjectMocks
	RoleController controller;
	@Mock
	RoleService roleService;

	@Test
	@DisplayName("positive Test Case For Inserting One Record")
	public void InsertingRecordTest() {
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");

		FinalResponse finalResponse = new FinalResponse();
		finalResponse.setStatus(HttpStatus.OK);
		finalResponse.setStatusCode("200");
		finalResponse.setMessage("One Record Is Inserted");
		when(roleService.InsertingOneRoleRecord(any(Role.class))).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.InsertingRoleRecord(role);

		assertEquals(finalResponse, finalResponses);
	}
	

	@Test
	@DisplayName("negative Test Case For Inserting One Record")
	public void InsertingRecordNegativeTest() {
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		FinalResponse finalResponse = new FinalResponse();
		finalResponse.setMessage("RoleName Is Already Taken");
		when(roleService.InsertingOneRoleRecord(any(Role.class))).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.InsertingRoleRecord(role);
		assertEquals(finalResponse, finalResponses);
	}

	
	@Test
	@DisplayName("positive Test Case For Inserting Multiple Records")
	public void InsertingMultipleRecordTest() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("Ceo");
		role.setRoleType("IT");
		roles.add(role);

		Role role1 = new Role();
		role.setRoleName("Ceo");
		role.setRoleType("IT");
		roles.add(role1);

		FinalResponse finalResponse = new FinalResponse();
		finalResponse.status = HttpStatus.OK;
		finalResponse.statusCode = "200";
		finalResponse.message = "Records are Inserted";
		when(roleService.InsertingMultipleRoleRecord(any(List.class))).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.InsertingAllRoleRecord(roles);
		assertEquals(finalResponse, finalResponses);
	}
	 
	
	@Test
	@DisplayName("positive Test Case For Getting One Records")
	public void GettingOneRecord() {
		Object[] role= {
				"roleName","ceo",
				"roleType","IT"
		};
		System.out.println(role);
		FinalResponse finalResponse = new FinalResponse();
		finalResponse.status = HttpStatus.OK;
		finalResponse.statusCode = "200";
		finalResponse.message = " All Records are present";
		finalResponse.data=role;
		when(roleService.GetOneRoleRecord(anyInt())).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.GettingOneRecord(anyInt());
		assertEquals(finalResponse, finalResponses);
	}
	

	@Test
	@DisplayName("positive Test Case For Getting All Records")
	public void GettingRecord() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("Ceo");
		role.setRoleType("IT");
		roles.add(role);

		Role role1 = new Role();
		role1.setRoleName("Manager");
		role1.setRoleType("IT");
		roles.add(role1);
		FinalResponse finalResponse = new FinalResponse();
		finalResponse.status = HttpStatus.OK;
		finalResponse.statusCode = "200";
		finalResponse.message = " All Records are present";
		finalResponse.datas = roles;

		when(roleService.GetAllRoleRecords()).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.GettingAllRecords();
		assertEquals(finalResponse, finalResponses);
	}
	@Test
	@DisplayName("positive Test Case For updating One Record")
	public void UpdatingRecordTest() {
		Role role = new Role();
		role.setRoleName("Java Developer");
		role.setRoleType("IT");
		role.setRoleId(1);

		FinalResponse finalResponse = new FinalResponse();
		finalResponse.status = HttpStatus.OK;
		finalResponse.statusCode = "200";
		finalResponse.message = " Record was Updated";
		when(roleService.UpadatingRoleRecord(any(Role.class))).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.UpdatingRecord(role);

		assertEquals(finalResponse, finalResponses);
	}
	
	@Test
	@DisplayName("negative Test Case For Updating One Record")
	public void UpdatingRecordNegativeTest() {
		Role role = new Role();
		//role.setRoleName("Java Developer");
		role.setRoleType("IT");
		role.setRoleId(1);

		FinalResponse finalResponse = new FinalResponse();
		finalResponse.statusCode = "500";
		finalResponse.message = "Record was not updated";
		finalResponse.errorMessages = "Check All fields";
		when(roleService.UpadatingRoleRecord(any(Role.class))).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.UpdatingRecord(role);

		assertEquals(finalResponse, finalResponses);
	}
	
	@Test
	@DisplayName("Delete the record")
	public void deleterecord() {
		Role role = new Role();
		role.setRoleName("Java Developer");
		role.setRoleType("IT");
		FinalResponse finalResponse = new FinalResponse();
		finalResponse.status = HttpStatus.OK;
		finalResponse.statusCode = "200";
		finalResponse.message = " Record was deleted";
		when(roleService.DeleteingRoleRecord(anyInt())).thenReturn(finalResponse);
		FinalResponse finalResponses = controller.DeletingRecord(anyInt());

		assertEquals(finalResponse, finalResponses);
		
	}
	

}
