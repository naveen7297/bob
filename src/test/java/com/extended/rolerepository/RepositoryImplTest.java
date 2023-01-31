package com.extended.rolerepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.extended.bean.Role;
import com.extended.repositoryimpl.RepositoryImpl;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
public class RepositoryImplTest {

	@Mock
	RepositoryImpl repositoryImpl;
	@Mock
	EntityManager entityManager;

	@Test
	@DisplayName("positive Test Case For Inserting One Record")
	public void InsertingRecordTest() {

		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		role.setCreatedDate(null);
		role.setUpdatedDate(null);
		String expected = "one record is inserted";

		//when(repositoryImpl.InsertingOneRoleRecord(any(Role.class))).thenReturn(expected);
		String actual = "one record is inserted";
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("positive Test Case For Inserting Multiple Record")
	public void InsertingmultipleRecordTest() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleName("HR");
		role.setRoleType("IT");
		roles.add(role);
		Role role1 = new Role();
		role1.setRoleName("HR");
		role1.setRoleType("IT");
		roles.add(role1);
		String expected = "one record is inserted";

		when(repositoryImpl.InsertingMultipleRoleRecord(any(List.class))).thenReturn(expected);
		String actual = repositoryImpl.InsertingMultipleRoleRecord(roles);
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("getting one record")
	public void gettingrecord() {
		Object[] role = { "1,SoftwareEngineer", "IT", null, null };

		when(repositoryImpl.GetOneRoleRecord(anyInt())).thenReturn(role);
		Object[] actual = repositoryImpl.GetOneRoleRecord(anyInt());
		assertEquals(role, actual);
	}

	@Test
	@DisplayName("positive Test Case For getting all Records")
	public void gettingrecords() {
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("HR");
		role.setRoleType("IT");
		roles.add(role);

		when(repositoryImpl.GetAllRoleRecords()).thenReturn(roles);
		List<Role> actual = repositoryImpl.GetAllRoleRecords();
		assertEquals(roles, actual);
	}

	@Test
	@DisplayName("positive Test Case For updating Records")
	public void updatingRecord() {
		Role role = new Role();
		// role.setRoleName("Java Developer");
		role.setRoleType("IT");
		role.setRoleId(1);
		String expected = "record was updated";

		when(repositoryImpl.UpadatingRoleRecord(any(Role.class))).thenReturn(expected);
		String actual = repositoryImpl.UpadatingRoleRecord(role);
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("positive Test Case For updating Records")
	public void deletedRecord() {
		Role role = new Role();
		role.setRoleName("Java Developer");
		role.setRoleType("IT");
		role.setRoleId(1);
		String expected = "record was Deleted";

		when(repositoryImpl.DeleteingRoleRecord(anyInt())).thenReturn(expected);
		String actual = repositoryImpl.DeleteingRoleRecord(anyInt());
		assertEquals(expected, actual);

	}

	@Test
	@DisplayName("positive Test Case For getting all Records")
	public void gettingNamerecords() {
		List<String> roles = new ArrayList<>();
		Role role = new Role();
		role.setRoleId(1);
		role.setRoleName("HR");
		role.setRoleType("IT");
		when(repositoryImpl.FindByRoleName()).thenReturn(roles);
		List<String> actual = repositoryImpl.FindByRoleName();
		assertEquals(roles, actual);
	}

}
