package com.dong.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dong.spring.model.Employee;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class EmployeeController {
		
	//Map to store employees, ideally we should use database
	Map<Integer, Employee> empData = new HashMap<Integer, Employee>();
	
	//test to get value from configuration properties file
	@Value("${test.value}")
	private String tValue;
	@Value("${test.sql}")
	private String tSql;

	
	@RequestMapping(value = "/rest/emp/dummy", method = RequestMethod.GET)
	public @ResponseBody Employee getDummyEmployee() {
		System.out.println("Start getDummyEmployee");

		System.out.println("\n\n1########### tValue in Controller is " + tValue);
		System.out.println("\n1^^^^^^^^^^^^ tSql in Controller is " + tSql);
		
		Employee emp = new Employee();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = "/rest/emp/{id}", method = RequestMethod.GET)
	public @ResponseBody Employee getEmployee(@PathVariable("id") int empId) {
		System.out.println("Start getEmployee. ID="+empId);
		
		return empData.get(empId);
	}
	
	@RequestMapping(value = "/rest/emps", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployees() {
		System.out.println("Start getAllEmployees.");
		List<Employee> emps = new ArrayList<Employee>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = "/rest/emp/create", method = RequestMethod.POST)
	public @ResponseBody Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}
	
	@RequestMapping(value = "/rest/emps/create", method = RequestMethod.POST)
	public @ResponseBody List<Employee> createEmployees(@RequestBody List<Employee> emps) {
		System.out.println("Start createEmployees.");
		List<Employee> empls = new ArrayList<Employee>();
		for (Employee emp : emps)	{
			emp.setCreatedDate(new Date());
			empData.put(emp.getId(), emp);
			System.out.println("emp data is " + emp.toString());
			System.out.println("empData data is " + empData.get(emp.getId()));
			empls.add(empData.get(emp.getId()));
		}
		return empls;
	}
	
	@RequestMapping(value = "/rest/emp/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody Employee deleteEmployee(@PathVariable("id") int empId) {
		System.out.println("Start deleteEmployee.");
		Employee emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
}
