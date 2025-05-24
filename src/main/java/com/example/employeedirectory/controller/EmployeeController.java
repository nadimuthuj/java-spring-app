package com.example.employeedirectory.controller;

import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> listEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    @ResponseBody
    public String addEmployee(@RequestBody Employee employee) {
        if (!repository.existsById(employee.getId())) {
            repository.save(employee);
            return "Employee added";
        } else {
            return "Employee with this ID already exists";
        }
    }

    @GetMapping("/ui")
    public String employeeForm(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "index";
    }

    @PostMapping("/submit")
    public String handleForm(@RequestParam int id,
                             @RequestParam String name,
                             @RequestParam String role,
                             Model model) {
        if (!repository.existsById(id)) {
            repository.save(new Employee(id, name, role));
        }
        model.addAttribute("employees", repository.findAll());
        return "index";
    }
}
