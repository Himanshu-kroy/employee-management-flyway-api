package com.himanshu.employeeflyway.service.impl;

import com.himanshu.employeeflyway.dto.EmployeeRequestDTO;
import com.himanshu.employeeflyway.dto.EmployeeResponseDTO;
import com.himanshu.employeeflyway.entity.Employee;
import com.himanshu.employeeflyway.exception.ResourceNotFoundException;
import com.himanshu.employeeflyway.repository.EmployeeRepository;
import com.himanshu.employeeflyway.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        Employee employee = mapToEntity(requestDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponseDTO(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToResponseDTO(employee);
    }

    @Override
    @Transactional
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());

        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToResponseDTO(updatedEmployee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    private Employee mapToEntity(EmployeeRequestDTO requestDTO) {
        Employee employee = new Employee();
        employee.setFirstName(requestDTO.getFirstName());
        employee.setLastName(requestDTO.getLastName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDepartment(requestDTO.getDepartment());
        employee.setSalary(requestDTO.getSalary());
        return employee;
    }

    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        responseDTO.setId(employee.getId());
        responseDTO.setFirstName(employee.getFirstName());
        responseDTO.setLastName(employee.getLastName());
        responseDTO.setEmail(employee.getEmail());
        responseDTO.setDepartment(employee.getDepartment());
        responseDTO.setSalary(employee.getSalary());
        return responseDTO;
    }
}
