package com.georgesleblanc.glb.services;

import com.georgesleblanc.glb.entities.Employee;
import com.georgesleblanc.glb.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Component
public class EmployeeService {

    private Logger logger  = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository repository;

    public Employee saveEmployee(Employee employee){
        logger.info("employee saved!");
        return repository.save(employee);
    }

    public ResponseEntity<List<Employee>> findAllEmployees(){
        List<Employee> employees = repository.findAll();
        if(!employees.isEmpty()){
            logger.info("the list of employees is not empty");
            ResponseEntity<List<Employee>>responseEntity = new ResponseEntity<>(employees, HttpStatus.OK);
            return responseEntity;
        }
        logger.warn("the list of employees is empty!!!");
        return new ResponseEntity<>(employees, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Employee> updateEmployee(Employee employee, Long id){
        Optional<Employee> optionalEmployeeemployee = repository.findById(id);
        if(optionalEmployeeemployee.isPresent()){
            logger.info("the employee is present");
            optionalEmployeeemployee.get().setFirstname(employee.getFirstname());
            optionalEmployeeemployee.get().setLastname(employee.getLastname());
            optionalEmployeeemployee.get().setEmployeeid(employee.getEmployeeid());
            optionalEmployeeemployee.get().setAge(employee.getAge());
            optionalEmployeeemployee.get().setVilleid(employee.getVilleid());
            Employee savedEmployee = repository.save(optionalEmployeeemployee.get());
            return new ResponseEntity<>(savedEmployee, HttpStatus.OK);

        }
        logger.warn("the employee with the id {} doesn't exist !!!", id);
        return new ResponseEntity<>(employee, HttpStatus.NOT_FOUND);
    }

    public void removeEmployeeWithID(Long id){
        Optional<Employee>optionalEmployee = repository.findById(id);
        if(optionalEmployee.isPresent()){
            logger.info("the employee with the id {} exist and has the following names: firstname= {}, name= {}", id,
                    optionalEmployee.get().getFirstname(), optionalEmployee.get().getLastname());
            repository.deleteById(id);
        }
       else{
           logger.info("the employee with the id {} doesn't exist in the database");
        }

    }
}
