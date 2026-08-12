package com.employee.controller;

import com.employee.model.dto.EmployeeDto;
import com.employee.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Employee API", description = "Operations related to employee management")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Save Employee", description = "Creates a new employee record")
    @PostMapping("/save")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto response = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Employee", description = "Updates an existing employee by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto) {
        EmployeeDto response = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Delete Employee", description = "Deletes an employee by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    @Operation(summary = "Get Employee by ID", description = "Fetches a single employee record")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getSingleEmployee(
            @Parameter(description = "Employee ID") @PathVariable Long id) {
        EmployeeDto response = employeeService.getSingleEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Get All Employees", description = "Fetches all employee records")
    @GetMapping("/all")
    public ResponseEntity<Iterable<EmployeeDto>> getAllEmployee() {
        Iterable<EmployeeDto> response = employeeService.getAllEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-by-emp-code-company-name")
    public ResponseEntity<EmployeeDto>getEmployeeByEmpCodeAndCompanyName(@RequestParam(required = false) String empCode,@RequestParam(required = false) String companyName) {
        List<String> missingParameters = new ArrayList<>();
        if(empCode==null || empCode.trim().isEmpty())
        {
            missingParameters.add("empCode");
        }
        if(companyName==null || companyName.trim().isEmpty())
        {
            missingParameters.add("companyName");
        }

        if(!missingParameters.isEmpty())
        {
            String finalMessage=missingParameters.stream().collect(Collectors.joining(","));

            throw new IllegalArgumentException("Please provide : "+finalMessage);

        }

        EmployeeDto response=employeeService.getEmployeeByEmpCodeAndCompanyName(empCode,companyName);
        return new ResponseEntity<EmployeeDto>(response, HttpStatus.OK);
    }
}
