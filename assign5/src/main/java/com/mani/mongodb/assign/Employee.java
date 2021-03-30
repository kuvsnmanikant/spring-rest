package com.mani.mongodb.assign;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection = "Employee")
public class Employee {
	@Id
	private int id;
	private String name;
	private String department;
	private String designation;
	private double salary;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	


}


//employeeId,
//employeeName, employeeDepartment, employeeDesignation, employeeSalary