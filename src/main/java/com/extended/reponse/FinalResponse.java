package com.extended.reponse;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.extended.bean.Role;

import lombok.Data;

@Data
public class FinalResponse {

	public HttpStatus status;
	public String statusCode;
	public String message;
	public String errorMessages;
	public Object[] data;
	public List<Role> datas;

}
