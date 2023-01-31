package com.extended.bean;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Role {
	private int roleId;
	@NotNull(message = "roleName Should Be Mention")
	@NotBlank
	private String roleName;
	@NotNull(message = "roleType Should Be Mention")
	@NotBlank
	private String roleType;
	private Date createdDate;
	private Date updatedDate;

}
