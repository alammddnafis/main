package com.employee.services.impl;

import com.employee.exception.BadRequestException;
import com.employee.exception.ResourceNotFoundException;
import com.employee.model.dto.EmployeeDto;
import com.employee.model.entity.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        if(employeeDto.getId()!=null)
        {
            throw new RuntimeException("Employee already exist");
        }
        Employee employee=modelMapper.map(employeeDto, Employee.class);
        Employee saveEntity=employeeRepository.save(employee);
        return modelMapper.map(saveEntity,EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        if(id==null || employeeDto.getId()==null)
        {
            throw new BadRequestException("plese provide employee id");
        }
        if(!Objects.equals(id,employeeDto.getId()))
        {
            throw new BadRequestException("id missmatch");
        }
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
        Employee employee=modelMapper.map(employeeDto,Employee.class);
        Employee updateEmployee=employeeRepository.save(employee);
        return modelMapper.map(updateEmployee,EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
        employeeRepository.deleteById(id);
    }

    @Override
    public EmployeeDto getSingleEmployee(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found with id:"+id));
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        if(employees.isEmpty())
        {
            throw new ResourceNotFoundException("No Employees Found");
        }
        return employees.stream().map(emp->modelMapper.map(emp,EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDto getEmployeeByEmpCodeAndCompanyName(String empCode, String companyName) {
    Employee employee=employeeRepository.findByEmpCodeAndCompanyName(empCode,companyName).orElseThrow(()->new ResourceNotFoundException("Employee not found with empCode:"+empCode+ "and companyName"+companyName));
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
