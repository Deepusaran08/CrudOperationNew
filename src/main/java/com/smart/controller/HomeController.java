package com.smart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smart.entity.Employee;
import com.smart.repository.EmployeeRepository;

@Controller
public class HomeController {

	@Autowired
	EmployeeRepository employeeReposirtory;
	//API for testing
	@GetMapping("/")
	public String home()
	{
		return "index";
	}
	
	@GetMapping("/register")
	public String home(Employee employee)
	{
		return "registration";
	}
	//API for create
	@PostMapping("/saveEmployee")
	@ResponseBody
	public String saveEmployee(@ModelAttribute Employee employee)
	{
		
		employeeReposirtory.save(employee);
		return "Data Saved!!!";
	}
	//API to fetch all data
	@GetMapping("/getAllEmployee")
	public String getAllEmployee(Model model)
	{
		List<Employee> empList=employeeReposirtory.findAll();
		model.addAttribute("empList",empList);
		return "showEmpList";
	}
	//API for fetching a single a record
	@GetMapping("/showEmployee/{id}")
	public String showEmployee(@PathVariable("id") int id, Model model)
	{
		Optional<Employee> findBy=employeeReposirtory.findById(id);
		Employee emp=findBy.get();
		model.addAttribute("emp",emp);
		return "showEmployee";
	}
	//API for fetching a delete records
	@GetMapping("/deleteEmployee/{id}")
	@ResponseBody
	public String deleteEmployee(@PathVariable("id") int id)
	{
		Optional<Employee> findBy=employeeReposirtory.findById(id);
		Employee emp=findBy.get();
		if(emp!=null)
			employeeReposirtory.deleteById(id);
		return "employee deleted successfully!!";
	}
	//API for show update page form
	@GetMapping("/update")
	public String update(Employee employee)
	{
		return "updatePage";
	}
	//API for update the data
	@PutMapping("/updateEmployee")
	@ResponseBody
	public String updateEmployee(@ModelAttribute Employee employee)
	{
		employeeReposirtory.save(employee);
		return "Employee Data Updated Successfully!!!";
	}
	//API to delete all data
	@GetMapping("/deleteAllEmployee")
	@ResponseBody
	public String deleteAllEmployee()
	{
		employeeReposirtory.deleteAll();
		return "All employees data deleted";
	}
	
}
