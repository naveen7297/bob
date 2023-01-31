package com.extended.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.extended.bean.Role;
import com.extended.reponse.FinalResponse;

import com.extended.service.RoleService;

import jakarta.validation.Valid;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/InsertingData")
	public FinalResponse InsertingRoleRecord(@RequestBody @Valid Role role) {
		return roleService.InsertingOneRoleRecord(role);
	}

	
	@PostMapping("/InsertingAllData")
	public FinalResponse InsertingAllRoleRecord(@RequestBody  List<Role> role) {
		return roleService.InsertingMultipleRoleRecord(role);
	}

	@GetMapping("/gettingData/{roleId}")
	public FinalResponse GettingOneRecord(@PathVariable int roleId) {
		return roleService.GetOneRoleRecord(roleId);
	}

	
	@GetMapping("/gettingAllRecord")
	public FinalResponse GettingAllRecords() {
		return roleService.GetAllRoleRecords();
	}

	@DeleteMapping("/deletingRecord/{roleId}")
	public FinalResponse DeletingRecord(@PathVariable int roleId) {
		return roleService.DeleteingRoleRecord(roleId);
	}

	@PutMapping("/updatingRecord")
	public FinalResponse UpdatingRecord(@RequestBody Role role) {
		return roleService.UpadatingRoleRecord(role);
	}
}
