package com.extended.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import com.extended.rolerepository.RoleRepository;
import com.extended.service.RoleServiceImplementation;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplementationTest {
	@InjectMocks
	RoleServiceImplementation roleService;

	@Mock
	RoleRepository roleRepository;

	@Test
	@DisplayName("positive Test Case For Inserting Record")
	public void insertTest() {
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");

		FinalResponse expected = new FinalResponse();
		expected.setStatus(HttpStatus.OK);
		expected.setStatusCode("200");
		expected.setMessage("One Record Is Inserted");
		when(roleRepository.InsertingOneRoleRecord(any(Role.class))).thenReturn("one Record Was inserted");
		FinalResponse actual = roleService.InsertingOneRoleRecord(role);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("negative Test Case For Inserting Record")
	public void insertNegativeTest() {
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		String expected = "RoleName Is Already Taken";

		when(roleRepository.InsertingOneRoleRecord(any(Role.class))).thenReturn(expected);
		String actual = roleRepository.InsertingOneRoleRecord(role);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Positive Test Case for Inserting Multiple records")
	public void insertingmultipletrecordstest() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		roles.add(role);

		Role role1 = new Role();
		role1.setRoleName("HR");
		role1.setRoleType("IT");
		roles.add(role1);
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.OK;
		expected.statusCode = "200";
		expected.message = "Records are Inserted";

		when(roleRepository.InsertingMultipleRoleRecord(roles)).thenReturn("All Records Are Inserted");
		FinalResponse actual = roleService.InsertingMultipleRoleRecord(roles);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("Positive Test Case for Inserting Multiple records")
	public void insertingmultipletrecordsnegativetestcase() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		roles.add(role);
		String expected = ("RoleName Is Already Taken");

		when(roleRepository.InsertingMultipleRoleRecord(roles)).thenReturn(expected);
		FinalResponse actual = roleService.InsertingMultipleRoleRecord(roles);
		assertNotEquals(expected, actual);

	}

	@Test
	@DisplayName("negative Test Case for Inserting Multiple records")
	public void insertingmultipletrecordsnegativetest() {
		List<Role> roles = null;
		FinalResponse expected = new FinalResponse();

		expected.status = HttpStatus.NOT_IMPLEMENTED;
		expected.statusCode = "500";
		expected.message = "Records are not Inserted";

		when(roleRepository.InsertingMultipleRoleRecord(roles)).thenReturn("All Records Are Inserted");
		FinalResponse actual = roleService.InsertingMultipleRoleRecord(roles);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("positive test case for Updating records")
	public void updatingrecord() {
		Role role = new Role();
		role.setRoleName("Java Developer");
		role.setRoleType("IT");
		role.setRoleId(1);
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.OK;
		expected.statusCode = "200";
		expected.message = " Record was Updated";
		when(roleRepository.UpadatingRoleRecord(role)).thenReturn("record was Updated");
		FinalResponse actual = roleService.UpadatingRoleRecord(role);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("negative test case for Updating records")
	public void updatingnegativetestcase() {
		Role role = null;
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.NOT_IMPLEMENTED;
		expected.statusCode = "500";
		expected.message = "Record was not updated";
		expected.errorMessages = "Check All fields";
		when(roleRepository.UpadatingRoleRecord(role)).thenReturn("record was Updated");
		FinalResponse actual = roleService.UpadatingRoleRecord(role);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("positive Test Case For Getting One Record")
	public void gettingonerecord() {

		Object[] role = { "roleName", "ceo", "roleType", "IT" };

		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.OK;
		expected.statusCode = "200";
		expected.message = "Record was present";
		expected.data = role;
		when(roleRepository.GetOneRoleRecord(anyInt())).thenReturn(role);
		FinalResponse actual = roleService.GetOneRoleRecord(1);
		assertNotEquals(expected, actual);
	}

	@Test
	@DisplayName("positive Test Case For Getting One Record")
	public void gettingonerecordtest() {

		Object[] role = null;

		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.NOT_FOUND;
		expected.statusCode = "404";
		expected.message = "  Record are  present";

		when(roleRepository.GetOneRoleRecord(anyInt())).thenReturn(role);
		FinalResponse actual = roleService.GetOneRoleRecord(anyInt());
		assertNotEquals(expected, actual);
	}

	@Test
	@DisplayName("getting all records")
	public void gettingallrecords() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("Ceo");
		role.setRoleType("IT");
		roles.add(role);

		Role role1 = new Role();
		role1.setRoleName("Manager");
		role1.setRoleType("IT");
		roles.add(role1);
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.OK;
		expected.statusCode = "200";
		expected.message = " All Records are present";
		expected.datas = roles;
		when(roleRepository.GetAllRoleRecords()).thenReturn(roles);
		FinalResponse actual = roleService.GetAllRoleRecords();
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName(" Negative Testcase getting all records")
	public void gettingallrecordsnegativetest() {
		List<Role> roles = null;

		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.NOT_FOUND;
		expected.statusCode = "404";
		expected.message = "  Records are not present";
		when(roleRepository.GetAllRoleRecords()).thenReturn(roles);
		FinalResponse actual = roleService.GetAllRoleRecords();
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("deleting record")
	public void deletingrecordtest() {
		Role role = new Role();
		role.setRoleName("softwareengineer");
		role.setRoleType("IT");
		role.setRoleId(1);
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.OK;
		expected.statusCode = "200";
		expected.message = " Record was deleted";
		when(roleRepository.DeleteingRoleRecord(anyInt())).thenReturn("record is deleted");
		FinalResponse actual = roleService.DeleteingRoleRecord(1);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("deleting record")
	public void deletingrecordnegativetest() {
		Role role = new Role();
		role.setRoleName("softwareengineer");
		role.setRoleType("IT");
		role.setRoleId(1);
		FinalResponse expected = new FinalResponse();
		expected.status = HttpStatus.NOT_FOUND;
		expected.statusCode = "404";
		expected.message = " Record was not deleted";
		expected.errorMessages = "check RoleId Once";
		when(roleRepository.DeleteingRoleRecord(anyInt())).thenReturn("record is deleted");
		FinalResponse actual = roleService.DeleteingRoleRecord(anyInt());
		assertEquals(expected, actual);
	}
}